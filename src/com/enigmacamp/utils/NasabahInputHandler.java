package com.enigmacamp.utils;

import java.util.Scanner;

public class NasabahInputHandler {
    private Scanner scanner;

    public NasabahInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getInt(String prompt){
        System.out.print(prompt);

        while(!scanner.hasNextInt()){
            System.out.println("Input harus berupa angka !");
            scanner.nextLine();
            System.out.print(prompt);
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public String getString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
