package StartProject;

import Controller.LoginController;
import View.LoginView;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView view = new LoginView();
                try {
                    LoginController controller = new LoginController(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //new View.LoginView();
    }
}
