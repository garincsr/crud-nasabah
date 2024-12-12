package com.enigmacamp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=========== Nasabah Bank CRUD ===========");
            System.out.println("1. Create Nasabah");
            System.out.println("2. Read Nasabah");
            System.out.println("3. Update Nasabah");
            System.out.println("4. Delete Nasabah");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    try {
                        createNasabah(scanner);
                    } catch (InvalidDataException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    readNasabah();
                    break;
                case 3:
//                    updateNasabah();
                    break;
                case 4:
//                    deleteNasabah();
                    break;
                case 5:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (choice != 5);

        scanner.close();
    }

    public static void createNasabah (Scanner scanner) throws InvalidDataException{
        if (Nasabah.nasabahList.size() >= Nasabah.MAX_NASABAH){
            throw new InvalidDataException("Nasabah sudah penuh!!");
        }

        System.out.print("Enter ID: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter NIK: ");
        String nik = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Birth Date: ");
        String birthDate = scanner.nextLine();

        for(Nasabah n : Nasabah.nasabahList){
            if (n.getId().equals(id)){
                throw new InvalidDataException("ID must be unique");
            }
            if (n.getNik().equals(nik)) {
                throw new InvalidDataException("NIK must be unique");
            }
        }

        Nasabah.nasabahList.add(new Nasabah(id, fullName, nik, phoneNumber,birthDate));
        System.out.println("Nasabah added successfully!");

    }

    public static void readNasabah(){
        if (Nasabah.nasabahList.isEmpty()){
            System.out.println("No nasabah to display.");
        } else {
            System.out.println("=========== List of nasabah ===========");
            for (int i = 0; i < Nasabah.nasabahList.size() ; i++) {
                System.out.println((i + 1) + ", " + Nasabah.nasabahList.get(i));
            }
        }
    }

}

//Lanjutkanlah hands on dengan ketentuan berikut :
//1. Lengkapi service crud (create, read(List dan by id), update dan delete)
//2. Implementasikan sebuah exception untuk validasi berikut
//      a. validasi jika data sudah penuh
//      b. validasi jika data tidak ada ketika melakukan (update, delete dan get by id)
//      c. validasi jika id, nik dan phone number sudah ada artinya harus unique
//3, buatkah branch aru dengan nama 02-use-interface dan gunakanlah interface/contract untuk CRUD service
//4. buatlah commit per feature, contoh :
//                              - feat : create nasabah
//                              - feat : update nasabah
//                              - fix : unique attribute validation when create data
