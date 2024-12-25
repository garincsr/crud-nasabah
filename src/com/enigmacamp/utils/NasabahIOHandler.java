package com.enigmacamp.utils;

import com.enigmacamp.model.Nasabah;
import com.enigmacamp.service.NasabahService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NasabahIOHandler {
    public static File file = new File("storage.txt");
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

    public static List<Nasabah> readFile(Integer nasabahCount) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
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
                    nasabahCount++;
                }
                data = bufferedReader.readLine();
            }
            return read;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Nasabah readFileById(Integer id){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data;

//            // Membaca baris pertama dank
//                        service.readAllNasabah();
//                        Integer idDelete = inputHandler.getInt("Enter ID to Delete: ");
//                        //cek nasabah sesuai id
//                        service.readNasabahById(idDelete);
//                        //hapus data
//                        service.deleteNasabahById(idDelete) melewatkannya jika itu adalah header
            data = bufferedReader.readLine();
            if (data != null && data.toLowerCase().startsWith("id")) {
                // Melewati header
                data = bufferedReader.readLine();
            }

            while (data != null) {
                String[] splitData = data.split(",");
                if (splitData.length == 5){
                    if ((Integer.parseInt(splitData[0])) == id){
                        return new Nasabah(
                                Integer.parseInt(splitData[0].trim()),
                                splitData[1].trim(),
                                splitData[2].trim(),
                                splitData[3].trim(),
                                splitData[4].trim()
                        );
                    }
                }
                data = bufferedReader.readLine();
            }
            throw new IllegalStateException("Nasabah witd id " + id + " not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeFile(List<Nasabah> nasabahList){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
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

    public static void writeFileById(Integer id, Nasabah updatedNasabah) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String data;

            // Membaca file dan menyimpan semua baris
            data = reader.readLine();
            if (data != null && data.toLowerCase().startsWith("id")) {
                lines.add(data); // Menyimpan header
                data = reader.readLine();
            }

            boolean found = false; // Untuk mengecek apakah ID ditemukan
            while (data != null) {
                String[] splitData = data.split(",");
                if (splitData.length == 5) {
                    Integer currentId = Integer.parseInt(splitData[0].trim());
                    if (currentId.equals(id)) {
                        // Jika ID cocok, gunakan data yang diperbarui
                        String updatedLine = currentId + "," +
                                updatedNasabah.getFullName() + "," +
                                updatedNasabah.getNik() + "," +
                                updatedNasabah.getPhoneNumber() + "," +
                                updatedNasabah.getBirthDate();
                        lines.add(updatedLine);
                        found = true;
                    } else {
                        // Jika ID tidak cocok, tambahkan baris asli
                        lines.add(data);
                    }
                }
                data = reader.readLine();
            }

            if (!found) {
                throw new IllegalArgumentException("Nasabah with ID " + id + " not found");
            }

            // Menulis ulang file dengan data yang diperbarui
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing file", e);
        }
    }



    public static void deleteById(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
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
