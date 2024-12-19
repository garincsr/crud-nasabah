package com.enigmacamp.service;

import com.enigmacamp.model.Nasabah;

import java.io.*;
import java.util.List;

public class IOService {
    public static File file = new File("storage.txt");

    public static void checkOrCreateFile(File file){
        if (!file.exists()){
            createNewFile(file);
        } else {
            System.out.println("File sudah ada!");
        }
    }

    public static void createNewFile(File file){
        try {
            if (file.createNewFile()){
                System.out.println("File berhasil dibuat: " + file.getName());
            } else {
                System.out.println("File gagal dibuat");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readAllFile(List<Nasabah> nasabahList, Integer nasabahCount){
        if (!file.exists()){
            System.out.println("File tidak ada!");
            return;
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data = bufferedReader.readLine();

            while((data = bufferedReader.readLine()) != null) {
                String[] splitData = data.split(",");
                if (splitData.length == 5) {
                    Nasabah nasabah = new Nasabah(
                            Integer.parseInt(splitData[0].trim()),
                            splitData[1].trim(),
                            splitData[2].trim(),
                            splitData[3].trim(),
                            splitData[4].trim()
                    );
                    nasabahList.add(nasabah);
                    nasabahCount++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(List<Nasabah> nasabahList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            //Header
            bufferedWriter.write("id, fullName, nik, phoneNumber, birthDate");
            bufferedWriter.newLine();

            //Data
            for (Nasabah nasabah : nasabahList) {
                System.out.println("INI BELAJALAN");
                bufferedWriter.write(
                        nasabah.getId() + "," +
                                nasabah.getFullName() + "," +
                                nasabah.getNik() + "," +
                                nasabah.getPhoneNumber() + "," +
                                nasabah.getBirthDate()
                );
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Data berhasil ditambahkan ke " + file.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
