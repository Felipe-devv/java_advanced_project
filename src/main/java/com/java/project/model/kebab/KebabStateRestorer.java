package com.java.project.model.kebab;

public class KebabStateRestorer {
    private final KebabDTO kebabDTO;

    

    public KebabStateRestorer(KebabDTO kebabDTO) {
        this.kebabDTO = kebabDTO;
    }

    public Kebab restorKebab() {
        return kebabDTO.toEntity();
    }
    
}
