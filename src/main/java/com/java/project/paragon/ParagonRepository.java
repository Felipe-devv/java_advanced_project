package com.java.project.paragon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParagonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Paragon> getAllParagons() {

        return jdbcTemplate.query("SELECT id,Turek_id,Kebab_id,miasto,kod_pocztowy,suma FROM Paragon", BeanPropertyRowMapper.newInstance(Paragon.class));

    }

    public Paragon getParagonById(int id) {

        var sql = "SELECT id,Turek_id,Kebab_id,miasto,kod_pocztowy,suma FROM Paragon WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Paragon.class), id);

    }

    public boolean insertParagon(Paragon p) {

        var sql = "INSERT INTO Paragon ( Kebab_id,Turek_id,miasto, kod_pocztowy, suma) VALUES (?, ?, ?, ?, ?)";

        int zmienonychRekordow = jdbcTemplate.update(sql, p.getKebab(), p.getTurek(), p.getMiasto(), p.getKodPocztowy(), p.getSuma());

        return zmienonychRekordow >= 1;

    }

    public boolean updateParagon(Paragon p) {

        var sql = "UPDATE Paragon SET Kebab_id = ?, Turek_id = ?, miasto = ?, kod_pocztowy = ?, suma = ? WHERE id = ?";

        int zmienonychRekordow = jdbcTemplate.update(sql, p.getKebab(), p.getTurek(), p.getMiasto(), p.getKodPocztowy(), p.getSuma(),p.getId());

        return zmienonychRekordow >= 1;
    }

    public boolean deleteParagon(int id) {
        var sql = "DELETE FROM Paragon WHERE id = ?";

        int zmienonychRekordow = jdbcTemplate.update(sql, id);
        return zmienonychRekordow == 1;
    }

}
