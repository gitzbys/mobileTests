package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

public class TestClass {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 30; i >= 0; i--) {
            Thread.sleep(100);
            System.out.println(i);
            //напишите тут ваш код
        }

        System.out.println("Бум!");
    }
}