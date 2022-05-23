package Controller;

import java.io.IOException;

import static View.PanelCenterView.*;
import static Controller.PanelWestController.*;


public class PanelCenterController {

    public static void clearTable_Before_Data_Into() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static void display_Data_After_Login() throws IOException {
        clearTable_Before_Data_Into();
        Object[] row;
        array2d = new Object[arrayList_DataUser.size()][5];
        for (int i = 0; i < arrayList_DataUser.size(); i++) {
            Object id = array2d[i][0] = arrayList_DataUser.get(i).getId();
            Object name = array2d[i][1] = arrayList_DataUser.get(i).getName();
            Object age = array2d[i][2] = arrayList_DataUser.get(i).getAge();
            Object address = array2d[i][3] = arrayList_DataUser.get(i).getAddress();
            Object gpa = array2d[i][4] = arrayList_DataUser.get(i).getGPA();
            row = new Object[]{id, name, age, address, gpa};
            model.addRow(row);
            writeToFile(fileData);
        }
    }
}
