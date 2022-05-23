package View;

import Controller.LoginController;

import static Controller.LoginController.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;


public class LoginView extends JFrame implements ActionListener {

    static public JButton buttonLogin, buttonSignUp;
    static public JTextField textFieldName;
    static public JPasswordField passwordField;
    static public JLabel labelUserName, labelPassword, labelLogo;
    static public ImageIcon imageIcon;

    public LoginView() {
        setFrame();
      //  setButtonLogin();
        setButtonSignUp();
    }

    private void setFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1350, 300, 300, 400);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setVisible(true);
        labelLogo = new JLabel();
        labelLogo.setIcon(imageIcon = new ImageIcon("C:\\Users\\User\\Downloads\\icons8-student-center-48.png"));
        labelLogo.setBounds(110, 20, 50, 50);
        textFieldName = new JTextField();
        textFieldName.setBounds(60, 100, 170, 30);
        textFieldName.setBackground(Color.black);
        textFieldName.setCaretColor(Color.WHITE);
        textFieldName.setForeground(Color.WHITE);
        labelUserName = new JLabel();
        labelUserName.setIcon(imageIcon = new ImageIcon("C:\\Users\\User\\Downloads\\icons8-user-24.png"));
        labelUserName.setBounds(10, 100, 24, 24);
        passwordField = new JPasswordField();
        passwordField.setBounds(60, 145, 170, 30);
        passwordField.setBackground(Color.black);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setForeground(Color.WHITE);
        labelPassword = new JLabel();
        labelPassword.setIcon(imageIcon = new ImageIcon("C:\\Users\\User\\Downloads\\icons8-password-24.png"));
        labelPassword.setBounds(10, 147, 24, 24);
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(this); //warning
        buttonLogin.setBounds(90, 200, 100, 25);
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setBackground(Color.BLACK);
        buttonSignUp = new JButton("Sign Up");
        buttonSignUp.setBounds(190, 330, 80, 25);
        buttonSignUp.setForeground(Color.WHITE);
        buttonSignUp.setBackground(Color.BLACK);
        add(labelLogo);
        add(textFieldName);
        add(labelUserName);
        add(passwordField);
        add(labelPassword);
        add(buttonLogin);
        add(buttonSignUp);
    }

    private void setButtonLogin() {
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    setAction_ButtonLogin();
                    if (check == true) {
                        setVisible(false);
                        new DataView();
                    } else {
                        return;
                    }
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void setButtonSignUp() {
        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new SignUpView();
            }
        });
    }

    public static void showMessage_LoginView(String message) {
        JOptionPane.showMessageDialog(null, message, "Title", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addLoginListener(ActionListener listener) throws IOException {
        buttonLogin.addActionListener(listener);
    }

    public void actionPerformed(ActionEvent actionEvent) {

    }
}
