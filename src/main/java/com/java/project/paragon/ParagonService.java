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

    public boolean insertParagon(ParagonDTO paragon) {

        return paragonRepository.insertParagon(paragon.toParagonEntity());
    }

    public boolean updateParagon(ParagonDTO paragon) {
        return paragonRepository.updateParagon(paragon.toParagonEntity());
    }

    public boolean deleteParagon(int paragonId) {
        return paragonRepository.deleteParagon(paragonId);
    }


}
