package com.enigmacamp.service;

import com.enigmacamp.model.Nasabah;

import java.util.List;
import java.util.Scanner;

public interface NasabahInterface {
    void createNasabah(Nasabah createNewNasabah);
    List<Nasabah> readAllNasabah();
    Nasabah readNasabahById(Integer id);
    void updateNasabahById(Integer id, Nasabah updateNasabah);
    void deleteNasabahById(Integer id);
}
