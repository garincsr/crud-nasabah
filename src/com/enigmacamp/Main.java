package com.enigmacamp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NasabahService nasabahService = new NasabahService();
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
