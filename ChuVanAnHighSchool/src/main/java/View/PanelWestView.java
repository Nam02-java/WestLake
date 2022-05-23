package View;

import static Controller.PanelWestController.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class PanelWestView extends JPanel {
    JPanel panel = new JPanel();
    public static JTextField textFieldName, textFieldAge, textFieldAddress, textFieldGPA;
    JButton buttonAdd, buttonDelete, buttonEdit, buttonSave;

    public PanelWestView() throws IOException {
        setComponent();
        setButtonAdd();
        setButtonSave();
        setButtonDelete();
        setButtonEdit();
        checkFileExists(fileData);
    }

    private void setComponent() {
        setLayout(new GridLayout(6, 1));
        setPreferredSize(new Dimension(450, 420));
        add(new JLabel("Name"));
        add(textFieldName = new JTextField());
        add(new JLabel("Age"));
        add(textFieldAge = new JTextField());
        add(new JLabel("Address"));
        add(textFieldAddress = new JTextField());
        add(new JLabel("GPA"));
        add(textFieldGPA = new JTextField());
        add(panel);
        setPanel();
    }

    private void setPanel() {
        panel.setLayout(new FlowLayout(3));
        panel.add(buttonAdd = new JButton("Add"));
        panel.add(buttonEdit = new JButton("Edit"));
        panel.add(buttonDelete = new JButton("Delete"));
        panel.add(buttonSave = new JButton("Save into MySQL"));
    }

    private void setButtonAdd() {
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setAction_ButtonAdd();
                } catch (Exception exception) {
                    showMessage_PanelWestView("Vui lòng điền đúng dữ liệu và không được để ô trống");
                }
            }
        });
    }

    private void setButtonSave() {
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setAction_ButtonSave();
                    showMessage_PanelWestView("Lưu thành công");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void setButtonDelete() {
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setAction_ButtonDelete();
            }
        });
    }

    private void setButtonEdit() {
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setAction_ButtonEdit();
            }
        });
    }

    public static void showMessage_PanelWestView(String message) {
        JOptionPane.showMessageDialog(null, message, "Title", JOptionPane.INFORMATION_MESSAGE);
    }
}
