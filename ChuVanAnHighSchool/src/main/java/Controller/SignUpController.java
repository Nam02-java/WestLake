package Controller;

import static JDBC.SystemCenter.*;

import Model.SignUpModel;
import View.SignUpView;

import java.sql.SQLException;
import java.util.ArrayList;

import static JDBC.SystemCenter.getJDBCConnection;
import static View.SignUpView.*;

public class SignUpController {

    private static ArrayList<SignUpModel> arrayList_SignUp = new ArrayList<>();

    public static void setAction_ButtonRegister() throws SQLException {
        if (textFieldName.getText().equals("") && passwordField.getText().equals("")) {
            showMessage_SignUpView("Không được để ô trống");
        } else if (textFieldName.getText().equals("")) {
            showMessage_SignUpView("Không được để ô tên trống");
        } else if (passwordField.getText().equals("")) {
            showMessage_SignUpView("Không được để ô mật khẩu trống");
        } else {
            int id_user = 0;
            arrayList_SignUp.clear();
            connection = getJDBCConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM table_user;";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id_user = resultSet.getInt(1);
                String user_name = resultSet.getString(2);
                String user_password = resultSet.getString(3);
                arrayList_SignUp.add(new SignUpModel(user_name, user_password));
            }
            if (arrayList_SignUp.size() == 0) {
                id_user = 1;
                query = "INSERT INTO `table_user`(`id`, `user_name`,`password`) VALUES (" + "\"" + id_user + "\", " + "\"" + SignUpView.textFieldName.getText() + "\"," + "" + "\"" + SignUpView.passwordField.getText() + "\"" + ")";
                statement.executeUpdate(query);
                showMessage_SignUpView("Tạo tài khoản thành công");
                textFieldName.setText("");
                passwordField.setText("");
                arrayList_SignUp.clear();
            } else if (arrayList_SignUp.size() >= 1) {
                for (int i = 0; i < arrayList_SignUp.size(); i++) {
                    if (arrayList_SignUp.get(i).getName().equals(textFieldName.getText())) {
                        showMessage_SignUpView("Tên đã có người sử dụng");
                        textFieldName.setText("");
                        arrayList_SignUp.clear();
                        return;
                    }
                }
                id_user += 1;
                query = "INSERT INTO `table_user`(`id`, `user_name`,`password`) VALUES (" + "\"" + id_user + "\", " + "\"" + SignUpView.textFieldName.getText() + "\"," + "" + "\"" + SignUpView.passwordField.getText() + "\"" + ")";
                statement.executeUpdate(query);
                showMessage_SignUpView("Tạo tài khoản thành công");
                textFieldName.setText("");
                passwordField.setText("");
                arrayList_SignUp.clear();
            }
            connection.close();
        }
    }
}

