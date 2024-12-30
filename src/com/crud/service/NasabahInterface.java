package com.crud.service;

import com.crud.model.Nasabah;

import java.util.List;

public interface NasabahInterface {
    void createNasabah(Nasabah createNewNasabah);
    List<Nasabah> readAllNasabah();
    Nasabah readNasabahById(Integer id);
    void updateNasabahById(Integer id, Nasabah updateNasabah);
    void deleteNasabahById(Integer id);
}
