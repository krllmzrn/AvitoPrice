package com.example.avito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class TableCopyService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableCopyService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createTableCopy(String tableName, String tableNameOld) {
        String createTableQuery = "CREATE TABLE " + tableName + " AS SELECT * FROM " + tableNameOld;
        jdbcTemplate.update(createTableQuery);
    }

}
