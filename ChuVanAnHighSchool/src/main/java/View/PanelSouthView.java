package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import static Controller.PanelSouthController.*;

public class PanelSouthView extends JPanel {

    public static JButton buttonLogout, buttonDisplay, button_Sort_By_GPA, button_Sort_By_Name;
    JPanel panel = new JPanel();

    protected PanelSouthView() {
        setComponent();
        setButtonDisplay();
        setButton_Sort_By_GPA();

    }

    private void setComponent() {
        add(panel);
        setPanel();

    }

    private void setPanel() {
        panel.setLayout(new FlowLayout(1));
        panel.add(buttonLogout = new JButton("Log out"));
        panel.add(buttonDisplay = new JButton("Display"));
        panel.add(button_Sort_By_GPA = new JButton("GPA sort"));
        panel.add(button_Sort_By_Name = new JButton("Name sort"));
    }

    private void setButtonDisplay() {
        buttonDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setAction_ButtonDisplay();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void setButton_Sort_By_GPA() {
        button_Sort_By_GPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setAction_Button_Sort_By_GPA();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setButton_Sort_By_Name() {
        button_Sort_By_Name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setAction_Button_Sort_By_Name();
            }
        });
    }
}
