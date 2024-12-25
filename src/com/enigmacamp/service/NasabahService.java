package com.enigmacamp.service;

import com.enigmacamp.model.Nasabah;
import com.enigmacamp.utils.NasabahIOHandler;

import java.util.ArrayList;
import java.util.List;

public class NasabahService implements NasabahInterface {
    public List<Nasabah> nasabahList = new ArrayList<>();
    public Integer nasabahCount = 0;

    public void createNasabah(Nasabah createNewNasabah){
        nasabahList.add(createNewNasabah);
        NasabahIOHandler.writeFile(this.nasabahList);
    }

    public List<Nasabah> readAllNasabah(){
        List<Nasabah> readAll = NasabahIOHandler.readFile(this.nasabahCount);

        if (readAll.isEmpty()){
            throw new IllegalStateException("Nasabah is empty!");
        }

        return readAll;
    }

    public Nasabah readNasabahById(Integer id){
//        List<Nasabah> allNasabah = readAllNasabah();
//        for (Nasabah nasabah : allNasabah){
//            if (nasabah.getId().equals(id)){
//                return nasabah;
//            }
//        }
//        throw new IllegalStateException("Nasabah with id " + id + " not found!");
        return NasabahIOHandler.readFileById(id);
    }

    public void updateNasabahById(Integer id, Nasabah updateNasabah){
//        Nasabah readId = readNasabahById(id);
//
//        readId.setFullName(updateNasabah.getFullName());
//        readId.setNik(updateNasabah.getNik());
//        readId.setPhoneNumber(updateNasabah.getPhoneNumber());
//        readId.setBirthDate(updateNasabah.getBirthDate());
        NasabahIOHandler.writeFileById(id, updateNasabah);

    }

    public void deleteNasabahById(Integer id){
//        Nasabah readId = readNasabahById(id);
//        this.nasabahList.remove(readId);
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
