package com.java.project.kebab;

import java.util.List;

public interface KebabService {

    List<KebabDTO> getAllKebabs();

    KebabDTO getKebabById(Integer id);

    void insertKebab(AddUpdateKebabRequest request);

    void updateKebab(Integer id, AddUpdateKebabRequest request);

    void deleteKebab(Integer id);
    
    void deactivateKebab(Integer id);

}
