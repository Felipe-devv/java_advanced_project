package com.java.project.kebab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.kebab.domain.Kebab;
import com.java.project.kebab.domain.KebabDTO;
import com.java.project.kebab.payload.AddUpdateKebabRequest;

@Service
public class KebabService {

    @Autowired
    private KebabRepository kebabRepository;

    public List<KebabDTO> getAllKebabs() {
        List<Kebab> kebabs = kebabRepository.getAllKebabs();
        return kebabs.stream().map(KebabDTO::new).toList();
    }

    public KebabDTO getKebabById(Integer id) {
        return new KebabDTO(kebabRepository.getKebabById(id));
    }

    public void insertKebab(AddUpdateKebabRequest request) {
        kebabRepository.insertKebab(request);
    }

    public void updateKebab(AddUpdateKebabRequest request, Integer id) {
        kebabRepository.updateKebab(request, id);
    }

    public void deleteKebab(Integer id) {
        kebabRepository.deleteKebab(id);
    }

}
