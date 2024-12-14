package com.enigmacamp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NasabahService service = new NasabahService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=========== Nasabah Bank CRUD ===========");
            System.out.println("1. Create Nasabah");
            System.out.println("2. Read Nasabah");
            System.out.println("3. Update Nasabah");
            System.out.println("4. Delete Nasabah");
            System.out.println("5. Delete Nasabah by ID");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    try {
                        service.createNasabah(scanner);
                    } catch (InvalidDataException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    service.readNasabah();
                    break;
                case 3:
                    service.updateNasabah(scanner);
                    break;
                case 4:
                    service.deleteNasabah(scanner);
                    break;
                case 5:
                    service.deleteNasabahById(scanner);
                    break;
                case 6:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (choice != 6);

        scanner.close();
    }

}

//Lanjutkanlah hands on dengan ketentuan berikut :
//1. Lengkapi service crud (create, read(List dan by id), update dan delete)
//2. Implementasikan sebuah exception untuk validasi berikut
//      a. validasi jika data sudah penuh
//      b. validasi jika data tidak ada ketika melakukan (update, delete dan get by id)
//      c. validasi jika id, nik dan phone number sudah ada artinya harus unique
//3, buatkah branch baru dengan nama 02-use-interface dan gunakanlah interface/contract untuk CRUD service
//4. buatlah commit per feature, contoh :
//                              - feat : create nasabah
//                              - feat : update nasabah
//                              - fix : unique attribute validation when create data
