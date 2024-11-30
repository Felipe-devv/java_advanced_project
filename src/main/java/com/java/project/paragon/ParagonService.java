package com.java.project.paragon;

import java.util.List;


public interface ParagonService {

    List<ParagonDTO> getAllParagones();

    ParagonDTO getParagonById(int paragonId);

    Paragon insertParagon(ParagonPOJO paragon);

    Paragon updateParagon(ParagonPOJO paragon,int paragonId);

    void deleteParagon(int paragonId);
}
