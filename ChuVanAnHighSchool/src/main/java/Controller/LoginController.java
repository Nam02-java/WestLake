package Controller;

import Model.DataModel;
import Model.LoginModel;
import View.DataView;
import View.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static View.LoginView.*;
import static JDBC.SystemCenter.*;
import static Controller.PanelWestController.*;

public class LoginController {

    private static ArrayList<LoginModel> arrayList_Login = new ArrayList<>();
    public static Boolean check ; //origin check = null
    public static int checkId_user;
    public static String checkUser_name;
    protected LoginView loginview;

    public LoginController(LoginView view) throws IOException {
        this.loginview = view;
        view.addLoginListener(new LoginListener());
    }

    public static void setAction_ButtonLogin() throws SQLException, IOException {
        if (textFieldName.getText().equals("") && passwordField.getText().equals("")) {
            showMessage_LoginView("Không được để ô trống");
        } else if (textFieldName.getText().equals("")) {
            showMessage_LoginView("Không được để ô tên trống");
        } else if (passwordField.getText().equals("")) {
            showMessage_LoginView("Không được để ô mật khẩu trống");
        } else {
            arrayList_Login.clear();
            connection = getJDBCConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM table_user";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_user = resultSet.getInt(1);
                String user_name = resultSet.getString(2);
                String user_password = resultSet.getString(3);
                arrayList_Login.add(new LoginModel(id_user, user_name, user_password));
            }
            if (arrayList_Login.size() == 0) {
                showMessage_LoginView("tên đăng nhập hoặc mật khẩu không đúng");
                check = false;
                return;
            } else if (arrayList_Login.size() >= 1) {
                for (int i = 0; i < arrayList_Login.size(); i++) {
                    if (arrayList_Login.get(i).getUser_name().equals(textFieldName.getText())) {
                        if (arrayList_Login.get(i).getUser_password().equals(passwordField.getText())) {
                            checkId_user = arrayList_Login.get(i).getId_user();
                            checkUser_name = arrayList_Login.get(i).getUser_name();
                            showMessage_LoginView("Đăng nhập thành công");
                            clearArrayList(arrayList_DataUser);
                            clearFile(fileData);
                            arrayList_Login.clear();
                            check = true;
                            query = "SELECT d.id , e.student_name , e.age , e.address , e.gpa FROM students_data.table_data e INNER JOIN students_data.table_user d ON e.id = d.id HAVING d.id = " + checkId_user + ";";
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                int id_user = resultSet.getInt(1);
                                String student_name = resultSet.getString(2);
                                int age = resultSet.getInt(3);
                                String address = resultSet.getString(4);
                                int gpa = resultSet.getInt(5);
                                arrayList_DataUser.add(new DataModel(id_user, student_name, age, address, gpa));
                            }
                            return;
                        }
                    }
                }
                showMessage_LoginView("Tên đăng nhập hoặc mật khẩu không đúng");
                check = false;
                return;
            }
            connection.close();
        }
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                setAction_ButtonLogin();
                System.out.println(check);
                if(check == true){
                    loginview.setVisible(false);
                    new DataView();
                }else{
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
