package ru.otus.homework;

import ru.otus.homework.web.JettyWebServerApplication;

public class Main {
    public static void main(String[] args) throws Exception {
        new JettyWebServerApplication().start();
    }
}
