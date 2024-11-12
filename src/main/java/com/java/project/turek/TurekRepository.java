package com.java.project.turek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TurekRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Turek> getAllTureks() {
        return jdbcTemplate.query("SELECT id, imie, nazwisko, stanowisko, stawka_godzinowa FROM Turek", BeanPropertyRowMapper.newInstance(Turek.class));
    }

    public Turek getTurekById(Long id) {
        var sql = "SELECT id, imie, nazwisko, stanowisko, stawka_godzinowa FROM Turek WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Turek.class), id);
    }

    public boolean insertTurek(Turek turek) {
        var sql = "INSERT INTO Turek (imie, nazwisko, stanowisko, stawka_godzinowa) VALUES (?, ?, ?, ?)";
        int zmienionychRekordow = jdbcTemplate.update(sql, turek.getImie(), turek.getNazwisko(), turek.getStanowisko(), turek.getStawkaGodzinowa());
        return zmienionychRekordow >= 1;
    }

    public boolean updateTurek(Turek turek, Long id) {
        var sql = "UPDATE Turek SET imie = ?, nazwisko = ?, stanowisko = ?, stawka_godzinowa = ? WHERE id = ?";
        int zmienionychRekordow = jdbcTemplate.update(sql, turek.getImie(), turek.getNazwisko(), turek.getStanowisko(), turek.getStawkaGodzinowa(), id);
        return zmienionychRekordow >= 1;
    }

    public boolean deleteTurek(Long id) {
        var sql = "DELETE FROM Turek WHERE id = ?";
        int zmienionychRekordow = jdbcTemplate.update(sql, id);
        return zmienionychRekordow == 1;
    }

}
