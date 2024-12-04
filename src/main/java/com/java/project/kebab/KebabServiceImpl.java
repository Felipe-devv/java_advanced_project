package com.java.project.kebab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KebabServiceImpl implements KebabService {

   
    private final KebabRepository kebabRepository;

    @Override
    public List<KebabDTO> getAllKebabs() {
        List<Kebab> kebabs = kebabRepository.findByIsActiveTrue();
        return kebabs.stream().map(KebabDTO::new).toList();
    }

    @Override
    public KebabDTO getKebabById(Integer id) {
        Kebab kebab = kebabRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Kebab not found"));
        if(!kebab.getIsActive()) {
            throw new IllegalArgumentException("Kebab is not active");
        }
        return new KebabDTO(kebab);
    }

    @Override
    public void insertKebab(AddUpdateKebabRequest request) {
        Kebab kebab = new Kebab();
        kebab.setName(request.getName());
        kebab.setSize(request.getSize());
        kebab.setMeat(request.getMeat());
        kebab.setSauce(request.getSauce());
        kebab.setPrice(request.getPrice());
        kebab.setIsActive(true);

        kebabRepository.save(kebab);
    }

    @Override
    public void updateKebab(Integer id, AddUpdateKebabRequest request) {
        Kebab kebab = kebabRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Kebab not found"));

        if(kebab.getIsActive() == false) {
            throw new IllegalArgumentException("Kebab is not active");
        }
        if(request.getName() != null) {
            kebab.setName(request.getName());
        }
        if(request.getSize() != null) {
            kebab.setSize(request.getSize());
        }
        if(request.getMeat() != null) {
            kebab.setMeat(request.getMeat());
        }
        if(request.getSauce() != null) {
            kebab.setSauce(request.getSauce());
        }
        if(request.getPrice() != null) {
            kebab.setPrice(request.getPrice());
        }

        kebabRepository.save(kebab);
    }

    @Override
    public void deleteKebab(Integer id) {
        Kebab kebab = kebabRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Kebab not found"));
        kebabRepository.delete(kebab);
    }

    @Override
    public void deactivateKebab(Integer id) {
        Kebab kebab = kebabRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Kebab not found"));

        if(kebab.getIsActive() == false) {
            throw new IllegalArgumentException("Kebab is not active");
        }

        kebab.setIsActive(false);
        kebabRepository.save(kebab);
    }
}
