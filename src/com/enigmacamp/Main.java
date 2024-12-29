package com.enigmacamp;

import com.enigmacamp.model.Nasabah;
import com.enigmacamp.service.NasabahService;
import com.enigmacamp.utils.NasabahIOHandler;
import com.enigmacamp.utils.NasabahInputHandler;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //file
        File file = new File("storage.txt");
        NasabahIOHandler.checkOrCreateFile(file);

        //initiate Scanner
        Scanner scanner = new Scanner(System.in);
        //panggil object
        NasabahService service = new NasabahService();
        NasabahInputHandler inputHandler = new NasabahInputHandler(scanner);

        int choice;
        do {
            System.out.println("==================== CRUD Nasabah ====================");
            System.out.println("1. Create Nasabah");
            System.out.println("2. Read All Nasabah");
            System.out.println("3. Read Nasabah By ID");
            System.out.println("4. Update Nasabah By ID");
            System.out.println("5. Delete Nasabah By ID");
            System.out.println("6. Exit");
            choice = inputHandler.getInt("Choose Option: ");

            switch (choice){
                case 1:
                    Integer id = inputHandler.getInt("Enter ID: ");
                    String fullName = inputHandler.getString("Enter Full Name: ");
                    String nik = inputHandler.getString("Enter NIK Number: ");
                    String phoneNumber = inputHandler.getString("Enter Phone Number: ");
                    String birtDate = inputHandler.getString("Enter Birth Date: ");
                    //validasi unique
                    try {
                        Nasabah.validationCreate(id, nik, phoneNumber, service.nasabahList);
                    } catch (RuntimeException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    //create nasabah
                    Nasabah createNewNasabah = new Nasabah(id, fullName, nik, phoneNumber,birtDate);
                    service.createNasabah(createNewNasabah);
                    System.out.println("Haloo " + createNewNasabah.getFullName() + " your data succesfully added!");
                    break;
                case 2:
                    try {
                        List<Nasabah> readAll = service.readAllNasabah();
                        for (Nasabah nasabah : readAll){
                            System.out.println(nasabah.getId() + ", "
                                    + nasabah.getFullName() + ", "
                                    + nasabah.getNik() + ", "
                                    + nasabah.getPhoneNumber() + ", "
                                    + nasabah.getBirthDate() + ", "
                            );
                        }
                        System.out.println("Jumlah nasabah ada " + service.getNasabahCount());
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try{
                        try {
                            //cek nasabah list kosong atau tidak
                            service.readAllNasabah();
                        } catch (IllegalStateException e){
                            System.out.println(e.getMessage());
                            break;
                        }

                        try {
                            Integer idRead = inputHandler.getInt("Enter ID to Show Data: ");
                            Nasabah nasabah = service.readNasabahById(idRead);
                            System.out.println("Data: " + nasabah);
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                    }catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        //cek nasabah list kosong atau tidak
                        service.readAllNasabah();
                    } catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    //masukkan id yang mau di update
                    Integer idUpdate = inputHandler.getInt("Enter ID to Update: ");
                    //cek id benar atau tidak
                    try {
                        service.readNasabahById(idUpdate);
                    } catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    //input data data selanjutnya
                    String fullNameUpdate = inputHandler.getString("Enter Full Name: ");
                    String nikUpdate = inputHandler.getString("Enter nik: ");
                    String phoneNumberUpdate = inputHandler.getString("Enter Phone Number: ");
                    String birthDateUpdate = inputHandler.getString("Enter Birth Date: ");
                    //tangkap data yang diinput lalu parsing ke service
                    Nasabah updateNasabah = new Nasabah(idUpdate, fullNameUpdate, nikUpdate, phoneNumberUpdate, birthDateUpdate);
                    service.updateNasabahById(idUpdate, updateNasabah);
                    break;
                case 5:
                    try {
                        List<Nasabah> readAll = service.readAllNasabah();
                        for (Nasabah nasabah : readAll){
                            System.out.println(nasabah.getId() + ", "
                                    + nasabah.getFullName() + ", "
                                    + nasabah.getNik() + ", "
                                    + nasabah.getPhoneNumber() + ", "
                                    + nasabah.getBirthDate() + ", "
                            );
                        }
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    Integer idDelete = inputHandler.getInt("Enter ID to Delete: ");
                    service.deleteNasabahById(idDelete);
                    System.out.println("Data Deleted!!");
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Goodbye~");
                    break;
                default:
                    System.out.println("Invalid choice, choose the right number");
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



// 1, Refactor menggunakan ArrayList
// 2. Buatkanlah Bash Console untuk proses managemen data
// 3. Buat branch baru:
//      03-with-arraylist
//      04-bash-console
