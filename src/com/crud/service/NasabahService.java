package com.crud.service;

import com.crud.model.Nasabah;
import com.crud.utils.NasabahIOHandler;

import java.util.ArrayList;
import java.util.List;

public class NasabahService implements NasabahInterface {
    public List<Nasabah> nasabahList = new ArrayList<>();
    public Integer nasabahCount = 0;

    public void createNasabah(Nasabah createNewNasabah){
        // Pastikan memuat semua data dari file
        List<Nasabah> existingNasabah = NasabahIOHandler.readFile();

        // Tambahkan nasabah baru ke daftar
        existingNasabah.add(createNewNasabah);

        // Perbarui file dengan data baru
        NasabahIOHandler.writeFile(existingNasabah);
    }

    public List<Nasabah> readAllNasabah(){
        List<Nasabah> readAll = NasabahIOHandler.readFile();
        this.nasabahList = readAll;
        this.nasabahCount = readAll.size();

        if (readAll.isEmpty()){
            throw new IllegalStateException("Nasabah is empty!");
        }

        return readAll;
    }

    public Nasabah readNasabahById(Integer id){
        List<Nasabah> nasabahFile = NasabahIOHandler.readFile();

        for (Nasabah nasabah : nasabahFile) {
            if (nasabah.getId().equals(id)){
                return nasabah;
            }
        }

        throw new IllegalStateException("Nasabah dengan ID " + id + " tidak ditemukan");
    }

    public void updateNasabahById(Integer id, Nasabah updateNasabah){
        // Baca semua nasabah dari file
        List<Nasabah> nasabahFile = NasabahIOHandler.readFile();

        // Flag untuk mengecek apakah data ditemukan
        boolean isUpdated = false;

        // Perbarui data nasabah dengan ID yang sesuai
        for (Nasabah nasabah : nasabahFile) {
            if (nasabah.getId().equals(id)) {
                nasabah.setFullName(updateNasabah.getFullName());
                nasabah.setNik(updateNasabah.getNik());
                nasabah.setPhoneNumber(updateNasabah.getPhoneNumber());
                nasabah.setBirthDate(updateNasabah.getBirthDate());
                isUpdated = true;
                break;
            }
        }

        // Jika data ditemukan dan diupdate, tulis ulang file
        if (isUpdated) {
            NasabahIOHandler.writeFile(nasabahFile);
            System.out.println("Nasabah dengan ID " + id + " berhasil diperbarui.");
        } else {
            throw new IllegalStateException("Nasabah dengan ID " + id + " tidak ditemukan.");
        }
    }

    public void deleteNasabahById(Integer id){
        NasabahIOHandler.deleteById(id);
    }

    //=========== GETTER
    public Integer getNasabahCount() {
        return nasabahCount;
    }

    public List<Nasabah> getNasabahList() {
        return nasabahList;
    }
}
