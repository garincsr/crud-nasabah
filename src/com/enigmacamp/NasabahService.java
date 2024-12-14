package com.enigmacamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NasabahService implements NasabahInterface{
    public static final Integer MAX_NASABAH = 15;
    public static List<Nasabah> nasabahList = new ArrayList<>();

    public void createNasabah (Scanner scanner) throws InvalidDataException{
        if (nasabahList.size() >= MAX_NASABAH){
            throw new InvalidDataException("Nasabah sudah penuh!!");
        }

        System.out.print("Enter ID: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        for(Nasabah n : nasabahList){
            if (n.getId().equals(id)){
                throw new InvalidDataException("ID must be unique");
            }
        }

        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter NIK: ");
        String nik = scanner.nextLine();

        for(Nasabah n : nasabahList){
            if (n.getNik().equals(nik)) {
                throw new InvalidDataException("NIK must be unique");
            }
        }

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Birth Date: ");
        String birthDate = scanner.nextLine();

        nasabahList.add(new Nasabah(id, fullName, nik, phoneNumber,birthDate));
        System.out.println("Nasabah added successfully!");

    }

    public void readNasabah(){
        if (nasabahList.isEmpty()){
            System.out.println("No nasabah to display.");
        } else {
            System.out.println("=========== List of nasabah ===========");
            for (int i = 0; i < nasabahList.size() ; i++) {
                System.out.println((i + 1) + ", " + nasabahList.get(i));
            }
        }
    }

    public void updateNasabah(Scanner scanner) throws InvalidDataException{
        readNasabah();
        if (!nasabahList.isEmpty()){
            System.out.print("Enter the ID of nasabah to update data: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Boolean isFound = false;
            for (int i = 0; i < nasabahList.size() ; i++) {
                if (nasabahList.get(i).getId() == id){
                    System.out.print("Enter full name: ");
                    String fullName = scanner.nextLine();
                    nasabahList.get(i).setFullName(fullName);

                    System.out.print("Enter NIK: ");
                    String nik = scanner.nextLine();
                    nasabahList.get(i).setNik(nik);

                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    nasabahList.get(i).setNik(phoneNumber);

                    System.out.print("Enter birth date: ");
                    String birthDate = scanner.nextLine();
                    nasabahList.get(i).setNik(birthDate);

                    isFound = true;
                }
            }

            if (!isFound){
                throw new InvalidDataException("Data not found, cant update");
            }

        }
    }

    public void deleteNasabah(Scanner scanner) {
        readNasabah();
        if (!nasabahList.isEmpty()) {
            System.out.print("Enter the number of nasabah to delete: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (index >= 0 && index < nasabahList.size()) {
                nasabahList.remove(index);
                System.out.println("Nasabah deleted successfully!");
            } else {
                System.out.println("Invalid nasabah number.");
            }
        }
    }

    public void deleteNasabahById(Scanner scanner) throws InvalidDataException {
        readNasabah();
        if (!nasabahList.isEmpty()) {
            System.out.print("Enter Nasabah ID to delete: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            Boolean isFound = false;
            for (int i = 0; i < nasabahList.size() ; i++) {
                if (nasabahList != null && nasabahList.get(i).getId() == id){
                    nasabahList.remove(i);
                    isFound = true;
                }
            }
            if (!isFound){
                throw new InvalidDataException("Data not found, cand delete");
            }
        }
    }
}
