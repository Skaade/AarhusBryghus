package setup;

import application.controller.Controller;

public class main {
    public static void main(String[] args) {
        Controller c = Controller.getInstance();

        c.loadStorage();
        System.out.println(c.getSalgssituationer());

    }
}
