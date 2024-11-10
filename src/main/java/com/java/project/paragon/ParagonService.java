package com.java.project.paragon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParagonService {

    @Autowired
    private ParagonRepository paragonRepository;


    public List<ParagonDTO> getAllParagones() {

        var paragonList = paragonRepository.getAllParagons();

        return paragonList.stream().map(ParagonDTO::new).toList();
    }

    public ParagonDTO getParagonById(int paragonId) {

        return new ParagonDTO(paragonRepository.getParagonById(paragonId));
    }

    public boolean insertParagon(ParagonPOJO paragon) {

        return paragonRepository.insertParagon(paragon);
    }

    public boolean updateParagon(ParagonPOJO paragon,int paragonId) {
        return paragonRepository.updateParagon(paragon,paragonId);
    }

    public boolean deleteParagon(int paragonId) {
        return paragonRepository.deleteParagon(paragonId);
    }


}
