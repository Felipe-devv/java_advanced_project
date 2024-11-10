package com.java.project.kebab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.project.kebab.domain.Kebab;
import com.java.project.kebab.payload.AddUpdateKebabRequest;

@Repository
public class KebabRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Kebab> getAllKebabs() {
        String sql = "select * from Kebab";
        return jdbcTemplate.query(sql, new KebabRowMapper());
    }

    public Kebab getKebabById(int id) {
        String sql = "select * from Kebab where id = ?";
        List<Kebab> kebabs = jdbcTemplate.query(sql, new KebabRowMapper(), id);
        if (kebabs.isEmpty()) {
            throw new RuntimeException("Kebab not found");
        }
        return kebabs.get(0);
    }

    public void insertKebab(AddUpdateKebabRequest kebab) {
        String sql = "insert into Kebab (nazwa, rozmiar, mieso, sos, cena) values (?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, kebab.getName(), kebab.getSize(), kebab.getMeat(), kebab.getSauce(), kebab.getPrice());
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to insert into Kebab");
        }
    }

    public void updateKebab(AddUpdateKebabRequest kebab,Integer id) {
        String sql = "update Kebab set nazwa = ?, rozmiar = ?, mieso = ?, sos = ?, cena = ? where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, kebab.getName(), kebab.getSize(), kebab.getMeat(), kebab.getSauce(), kebab.getPrice(), id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to update Kebab");
        }
    }

    public void deleteKebab(Integer id) {
        String sql = "delete from Kebab where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to delete Kebab");
        }
    }
}
