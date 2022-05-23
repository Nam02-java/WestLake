package Controller;

import Model.DataModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;

import static JDBC.SystemCenter.*;
import static Controller.PanelCenterController.*;
import static Controller.LoginController.*;
import static View.LoginView.*;
import static Controller.PanelWestController.*;

public class PanelSouthController {

    public static void setAction_ButtonLogout() throws IOException {
        clearArrayList(arrayList_DataUser);
        clearFile(fileData);
        textFieldName.setText(checkUser_name);
    }

    public static void setAction_ButtonDisplay() throws IOException, SQLException {
        clearArrayList(arrayList_DataUser);
        clearFile(fileData);
        clearTable_Before_Data_Into();
        connection = getJDBCConnection();
        statement = connection.createStatement();
        String query = "SELECT d.id , e.student_name , e.age , e.address , e.gpa FROM students_data.table_data e INNER JOIN students_data.table_user d ON e.id = d.id HAVING d.id = " + checkId_user + ";";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id_user = resultSet.getInt(1);
            String student_name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            String address = resultSet.getString(4);
            int gpa = resultSet.getInt(5);
            arrayList_DataUser.add(new DataModel(id_user, student_name, age, address, gpa));
        }
        connection.close();
        display_Data_After_Login();
    }

    public static void setAction_Button_Sort_By_GPA() throws IOException {
        Collections.sort(arrayList_DataUser, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel d1, DataModel d2) {
                if (d1.getGPA() - d2.getGPA() > 0) {
                    return 1;
                } else if (d1.getGPA() - d2.getGPA() < 0) {
                    return -1;
                } else {
                    if (d1.getName().compareTo(d2.getName()) > 0) {
                        return 1;
                    } else if (d1.getName().compareTo(d2.getName()) < 0) {
                        return -1;
                    } else {
                        if (d1.getAge() - d2.getAge() > 0) {
                            return 1;
                        } else if (d1.getAge() - d2.getAge() < 0) {
                            return -1;
                        } else {
                            if (d1.getAddress().compareTo(d2.getAddress()) > 0) {
                                return 1;
                            } else if (d1.getAddress().compareTo(d2.getAddress()) < 0) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    }
                }
            }
        });
        display_Data_After_Login();
    }

    public static void setAction_Button_Sort_By_Name() {
        Collections.sort(arrayList_DataUser, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel d1, DataModel d2) {
                if (d1.getName().compareTo(d2.getName()) > 0) {
                    return 1;
                } else if (d1.getName().compareTo(d2.getName()) < 0) {
                    return -1;
                } else {
                    if (d1.getGPA() - d2.getGPA() > 0) {
                        return 1;
                    } else if (d1.getGPA() - d2.getGPA() < 0) {
                        return -1;
                    } else {
                        if (d1.getAge() - d2.getAge() > 0) {
                            return 1;
                        } else if (d1.getAge() - d2.getAge() < 0) {
                            return -1;
                        } else {
                            if (d1.getAddress().compareTo(d2.getAddress()) > 0) {
                                return 1;
                            } else if (d1.getAddress().compareTo(d2.getAddress()) < 0) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    }
                }
            }
        });
    }
}
