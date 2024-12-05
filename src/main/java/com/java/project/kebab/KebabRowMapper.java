package com.java.project.kebab;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KebabRowMapper implements RowMapper<Kebab> {
    @Override
    public Kebab mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Kebab.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("nazwa"))
                .size(rs.getString("rozmiar"))
                .meat(rs.getString("mieso"))
                .sauce(rs.getString("sos"))
                .price(rs.getDouble("cena"))
                .build();
    }
}