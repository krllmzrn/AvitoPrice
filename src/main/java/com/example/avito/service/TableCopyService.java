package com.example.avito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class TableCopyService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableCopyService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String createAndCopyUserSelectedTable(String selectedTableName) {
        String newTableName = generateNewTableName(selectedTableName);
        // Создаем копию выбранной таблицы
        createTableCopy(newTableName, selectedTableName);
        return newTableName;
    }
    public void createTableCopy(String tableName, String tableNameOld) {
        String createTableQuery = "CREATE TABLE " + tableName + " AS SELECT * FROM " + tableNameOld;
        jdbcTemplate.update(createTableQuery);
    }
    private String generateNewTableName(String baseTableName) {
        String getMaxNumberQuery = "SELECT MAX(CAST(SUBSTRING(table_name, LENGTH('" + baseTableName + "') + 1) AS UNSIGNED)) FROM information_schema.tables WHERE table_name LIKE '" + baseTableName + "%'";
        Integer maxNumber = jdbcTemplate.queryForObject(getMaxNumberQuery, Integer.class);
        int newNumber = maxNumber != null ? maxNumber + 1 : 1;

        return baseTableName + "_" + newNumber;
    }
    public List<String> findTablesStartingWith(String prefix) {
        String searchQuery = "SELECT table_name FROM information_schema.tables WHERE table_name LIKE '" + prefix + "%'";
        return jdbcTemplate.queryForList(searchQuery, String.class);
    }
    public List<Map<String, Object>> getTableData(String tableName, int pageNumber, int pageSize) {
        int offset = pageNumber * pageSize;
        String query = "SELECT * FROM " + tableName + " LIMIT " + pageSize + " OFFSET " + offset;
        return jdbcTemplate.queryForList(query);
    }
}
