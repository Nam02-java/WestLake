package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static View.PanelSouthView.*;
import static Controller.PanelSouthController.*;

public class DataView extends JFrame {

    public DataView() throws IOException {
        setComponent();
        setButtonLogout();
    }

    private void setComponent() throws IOException {
        setTitle("Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 130, 1300, 700);
        setVisible(true);
        add(new PanelWestView(), BorderLayout.WEST);
        add(new PanelCenterView(), BorderLayout.CENTER);
        add(new PanelSouthView(), BorderLayout.SOUTH);

    }

    private void setButtonLogout() {
        buttonLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new LoginView();
                try {
                    setAction_ButtonLogout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

