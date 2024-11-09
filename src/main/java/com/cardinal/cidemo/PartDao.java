package com.cardinal.cidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Part> getParts() {
        return jdbcTemplate.query("SELECT * FROM parts.part", (rs, rowNum) -> {
            Part part = new Part();
            part.setPartNumber(rs.getString("part_number"));
            part.setPartDescription(rs.getString("part_description"));
            part.setPartCost(rs.getBigDecimal("part_cost"));
            part.setPartListPrice(rs.getBigDecimal("part_list_price"));
            return part;
        });
    }
}
