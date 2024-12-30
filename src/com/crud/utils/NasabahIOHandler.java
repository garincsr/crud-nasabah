package com.crud.utils;

import com.crud.model.Nasabah;
import com.crud.service.NasabahService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NasabahIOHandler {
    public static final String pathName = "storage.txt";
    public static NasabahService service = new NasabahService();

    public static void checkOrCreateFile(File file){
        if (!file.exists()){
            createNewFile(file);
        } else {
            System.out.println("File ready: " + file.getName());
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
            throw new RuntimeException(e);
        }
    }

    public static List<Nasabah> readFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName))) {
            String data;
            List<Nasabah> read = new ArrayList<>();

            // Membaca baris pertama dan melewatkannya jika itu adalah header
            data = bufferedReader.readLine();
            if (data != null && data.toLowerCase().startsWith("id")) {
                // Melewati header
                data = bufferedReader.readLine();
            }

            while (data != null) {
                String[] splitData = data.split(",");
                if (splitData.length == 5) {
                    Nasabah nasabah = new Nasabah(
                            Integer.parseInt(splitData[0].trim()), // Konversi id
                            splitData[1].trim(),
                            splitData[2].trim(),
                            splitData[3].trim(),
                            splitData[4].trim()
                    );
                    read.add(nasabah);
                }
                data = bufferedReader.readLine();
            }
            return read;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeFile(List<Nasabah> nasabahList){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathName))) {

            //Buat header
             bufferedWriter.write("id, fullName, nik, phoneNumber, birthDate");
             bufferedWriter.newLine();

            //Buat data sesuai input
            for (Nasabah nasabah : nasabahList){
                bufferedWriter.write(
                        nasabah.getId() + ","
                                + nasabah.getFullName() + ","
                                + nasabah.getNik() + ","
                                + nasabah.getPhoneNumber() + ","
                                + nasabah.getBirthDate()
                );
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteById(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
            List<String> lines = new ArrayList<>();
            String data;

            // Membaca file
            data = reader.readLine();
            if (data != null && data.toLowerCase().startsWith("id")) {
                lines.add(data); // Simpan header
                data = reader.readLine();
            }

            boolean found = false; // Untuk mengecek apakah ID ditemukan
            while (data != null) {
                String[] splitData = data.split(",");
                if (splitData.length == 5) {
                    Integer currentId = Integer.parseInt(splitData[0].trim());
                    if (!currentId.equals(id)) {
                        lines.add(data); // Tambahkan baris yang tidak sesuai dengan ID
                    } else {
                        found = true; // Tandai ID ditemukan
                    }
                }
                data = reader.readLine();
            }

            // Jika ID tidak ditemukan, lempar exception
            if (!found) {
                throw new IllegalArgumentException("Nasabah with ID " + id + " not found");
            }

            // Tulis ulang file tanpa baris yang dihapus
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing file", e);
        }
    }

}
