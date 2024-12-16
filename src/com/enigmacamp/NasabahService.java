package com.enigmacamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NasabahService implements NasabahInterface{
    public static final Integer MAX_NASABAH = 15;
    public static List<Nasabah> nasabahList = new ArrayList<>();

    public void createNasabah (Scanner scanner) throws InvalidDataException{
        if (nasabahList.size() >= MAX_NASABAH){
            throw new InvalidDataException("Nasabah sudah penuh!!");
        }

        NasabahInputHandler inputHandler = new NasabahInputHandler(scanner);
        Integer id = inputHandler.getInt("Enter ID: ");
        String fullName = inputHandler.getString("Enter Full Name: ");
        String nik = inputHandler.getString("Enter NIK: ");
        String phoneNumber = inputHandler.getString("Enter Phone Number: ");
        String birthDate = inputHandler.getString("Enter Birth Date: ");

        validation(id, nik, phoneNumber);
        nasabahList.add(new Nasabah(id, fullName, nik, phoneNumber,birthDate));
        System.out.println("Nasabah added successfully!");

    }

    public void readNasabah(){
        if (nasabahList.isEmpty()){
            System.out.println("No nasabah to display.");
        } else {
            System.out.println("=========== List of nasabah ===========");
            for (int i = 0; i < nasabahList.size() ; i++) {
                System.out.println((i + 1) + ". " + nasabahList.get(i));
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
                    NasabahInputHandler inputHandler = new NasabahInputHandler(scanner);

                    int randomIntSpecificRange = ThreadLocalRandom.current().nextInt(0, 9);
                    Integer idRandom = (nasabahList.get(i).getId() * randomIntSpecificRange + randomIntSpecificRange);
                    String fullName = inputHandler.getString("Enter Full Name: ");
                    String nik = inputHandler.getString("Enter NIK: ");
                    String phoneNumber = inputHandler.getString("Enter Phone Number: ");
                    String birthDate = inputHandler.getString("Enter Birth Date: ");

                    validation(idRandom, nik, phoneNumber);

                    nasabahList.get(i).setId(idRandom);
                    nasabahList.get(i).setFullName(fullName);
                    nasabahList.get(i).setNik(nik);
                    nasabahList.get(i).setPhoneNumber(phoneNumber);
                    nasabahList.get(i).setBirthDate(birthDate);
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

    public void validation(Integer id, String nik, String phoneNumber) throws InvalidDataException{
        for (Nasabah n : nasabahList){
            if (n != null && n.getId() == id){
                throw new InvalidDataException("ID must be unique");
            }

            if (n != null && n.getNik().equals(nik)){
                throw new InvalidDataException("NIK must be unique");
            }

            if (n != null && n.getPhoneNumber().equals(phoneNumber)){
                throw new InvalidDataException("Phone Number must be unique");
            }
        }
    }
}
