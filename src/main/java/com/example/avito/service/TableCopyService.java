package com.example.avito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public class TableCopyService {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TableCopyService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public String createAndCopyUserSelectedBaselineTable(String selectedTableName) {
        String newTableName = generateNewTableBaselineName(selectedTableName);
        createTableCopy(newTableName, selectedTableName);
        return newTableName;
    }
    public String createAndCopyUserSelectedDiscountTable(String selectedTableName) {
        String newTableName = generateNewTableDiscountName(selectedTableName);
        createTableCopy(newTableName, selectedTableName);
        return newTableName;
    }
//    public void updatePrices(List<Map<String, Object>> tableData, String copiedTableName,Map<String, Object> editedPrices) {
//        List<Object[]> batchArgs = new ArrayList<>();
//
//        for (Map<String, Object> row : tableData) {
//            String microcategoryId = String.valueOf(row.get("microcategory_id"));
//            String locationId = String.valueOf(row.get("location_id"));
//
//            for (Map<String, Object> editedPriceMap : editedPrices) {
//                String editedMicrocategoryId = String.valueOf(editedPriceMap.get("microcategory_id"));
//                String editedLocationId = String.valueOf(editedPriceMap.get("location_id"));
//                if (microcategoryId.equals(editedMicrocategoryId) && locationId.equals(editedLocationId)) {
//                    String editedPrice = String.valueOf(editedPriceMap.get("price"));
//                    int newPrice = Integer.parseInt(editedPrice);
//                    row.put("price", newPrice);
//                    batchArgs.add(new Object[] { newPrice, microcategoryId, locationId });
//                    break; // Exit the loop once the price is updated
//                }
//            }
//        }
//
//        String updateQuery = "UPDATE " + copiedTableName + " SET price = ? WHERE microcategory_id = ? AND location_id = ?";
//        jdbcTemplate.batchUpdate(updateQuery, batchArgs);
//    }

    public void createTableCopy(String tableName, String tableNameOld) {
        String createTableQuery = "CREATE TABLE " + tableName + " AS SELECT * FROM " + tableNameOld;
        jdbcTemplate.update(createTableQuery);
    }
    private String generateNewTableBaselineName(String baseTableName) {
        String getMaxNumberQuery = "SELECT MAX(CAST(SUBSTRING(table_name, LENGTH('" + "baseline_matrix_" + "') + 1) AS UNSIGNED)) FROM information_schema.tables WHERE table_name LIKE '" + "baseline_matrix_" + "%'";
        Integer maxNumber = jdbcTemplate.queryForObject(getMaxNumberQuery, Integer.class);
        int newNumber = maxNumber != null ? maxNumber + 1 : 1;

        return "baseline_matrix_" + newNumber;
    }
    private String generateNewTableDiscountName(String baseTableName) {
        String getMaxNumberQuery = "SELECT MAX(CAST(SUBSTRING(table_name, LENGTH('" + "discount_matrix_" + "') + 1) AS UNSIGNED)) FROM information_schema.tables WHERE table_name LIKE '" + "discount_matrix_" + "%'";
        Integer maxNumber = jdbcTemplate.queryForObject(getMaxNumberQuery, Integer.class);
        int newNumber = maxNumber != null ? maxNumber + 1 : 1;

        return "discount_matrix_" + newNumber;
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
    public int getTotalRows(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        int totalRows = jdbcTemplate.queryForObject(query, Integer.class);
        return totalRows;
    }
}
