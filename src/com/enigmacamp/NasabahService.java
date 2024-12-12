package com.enigmacamp;

import java.util.ArrayList;
import java.util.List;

public class NasabahService{
    private Nasabah[] items = new Nasabah[15];
    private int nasabahCount = 0;

    public NasabahService() {}

    //Registrasi Nasabah

    public void create(Nasabah nasabah){
        items[nasabahCount++] = nasabah;
        System.out.println("Nasabah berhasil ditambahkan");
        // items[0] -> increment
    }

    public Nasabah[] getItems() {
        return items;
    }
}
