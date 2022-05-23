package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

import static Controller.PanelCenterController.*;

public class PanelCenterView extends JPanel {
    public static DefaultTableModel model;
    public static JTable table;
    public static String[] column;
    public static Object[][] array2d;
    public static Object[] row;

    protected PanelCenterView() throws IOException {
        setComponent();
        display_Data_After_Login();
    }

    private void setComponent() {
        column = new String[]{"ID_Teacher", "Student_Name", "Student_Age", "Student_City", "Student_GPA"};
        model = new DefaultTableModel(array2d, column);
        table = new JTable(model);
        add(new JScrollPane(table));
        table.setEnabled(false);
    }
}
