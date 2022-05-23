package Controller;

import Model.DataModel;

import static View.PanelCenterView.*;
import static Controller.LoginController.*;
import static View.PanelWestView.*;
import static JDBC.SystemCenter.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PanelWestController {

    public static File fileData = new File("Data.txt");
    public static ArrayList<DataModel> arrayList_DataUser = new ArrayList<DataModel>();


    public static void setAction_ButtonAdd() throws IOException {
        arrayList_DataUser.clear();
        readFileToArrayList(fileData);
        arrayList_DataUser.add(new DataModel(checkId_user, textFieldName.getText(), Integer.parseInt(textFieldAge.getText()), textFieldAddress.getText(), Integer.parseInt(textFieldGPA.getText())));
        array2d = new Object[arrayList_DataUser.size()][5];
        Collections.reverse(arrayList_DataUser);
        for (int i = 0; i < arrayList_DataUser.size(); i++) {
            Object id = array2d[i][0] = arrayList_DataUser.get(i).getId();
            Object name = array2d[i][1] = arrayList_DataUser.get(i).getName();
            Object age = array2d[i][2] = arrayList_DataUser.get(i).getAge();
            Object address = array2d[i][3] = arrayList_DataUser.get(i).getAddress();
            Object gpa = array2d[i][4] = arrayList_DataUser.get(i).getGPA();
            row = new Object[]{id, name, age, address, gpa};
            model.addRow(row);
            textFieldName.setText("");
            textFieldAge.setText("");
            textFieldAddress.setText("");
            textFieldGPA.setText("");
            Collections.reverse(arrayList_DataUser);
            writeToFile(fileData);
            break;
        }
    }

    /**
     * how to fix this bug
     * class java.lang.String cannot be cast to class java.lang.Integer
     * int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(row, 0)));
     */
    public static void setAction_ButtonSave() throws IOException, SQLException {
        clearArrayList(arrayList_DataUser);
        for (int row = 0; row < table.getModel().getRowCount(); row++) {
            int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(row, 0)));
            String name = (String) table.getModel().getValueAt(row, 1);
            int age = Integer.parseInt(String.valueOf(table.getModel().getValueAt(row, 2)));
            String address = (String) table.getModel().getValueAt(row, 3);
            int gpa = Integer.parseInt(String.valueOf(table.getModel().getValueAt(row, 4)));
            arrayList_DataUser.add(new DataModel(id, name, age, address, gpa));
        }
        writeToFile(fileData);
        connection = getJDBCConnection();
        statement = connection.createStatement();
        String query = "DELETE FROM table_data WHERE id =" + checkId_user + " ;";
        statement.executeUpdate(query);
        if (table.getModel().getRowCount() == 0) {
            return;
        } else {
            for (int i = 0; i < arrayList_DataUser.size(); i++) {
                query = "INSERT INTO table_data (id, student_name,age,address,gpa) VALUES (" + checkId_user + ", " + "\"" + arrayList_DataUser.get(i).getName() + "\"" + ", " + arrayList_DataUser.get(i).getAge() + ", " + "\"" + arrayList_DataUser.get(i).getAddress() + "\"" + ", " + arrayList_DataUser.get(i).getGPA() + ");";
                statement.executeUpdate(query);
            }
        }
        table.setEnabled(false);
        connection.close();
    }

    public static void setAction_ButtonDelete() {
        model.removeRow(table.getSelectedRow());
    }

    public static void setAction_ButtonEdit(){
        table.setEnabled(true);
    }

    public static void clearArrayList(ArrayList arrayList) {
        arrayList.clear();
    }

    public static void checkFileExists(File file) throws IOException {
        if (file.exists()) {
        } else {
            file.createNewFile();
        }
    }

    public static void clearFile(File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public static void writeToFile(File file) throws IOException {
        if (table.getModel().getRowCount() == 0) {
            return;
        } else {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < arrayList_DataUser.size(); i++) {
                bufferedWriter.write(arrayList_DataUser.get(i).getId() + " " + arrayList_DataUser.get(i).getName() + " " + arrayList_DataUser.get(i).getAge() + " " + arrayList_DataUser.get(i).getAddress() + " " + arrayList_DataUser.get(i).getGPA() + "\n");
            }
            bufferedWriter.close();
        }
    }

    public static void readFileToArrayList(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            arrayList_DataUser.add(new DataModel(scanner.nextInt(), scanner.next(), scanner.nextInt(), scanner.next(), scanner.nextInt()));
        }
        scanner.close();
    }
}
