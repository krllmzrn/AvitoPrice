package com.example.avito.controllers;

import com.example.avito.service.TableCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class BaselineMatrixController {
    @Autowired
    TableCopyService tableCopyService;
    @GetMapping("/")
    public String showBaseline(Model model) {
        List<String> tableNames = tableCopyService.findTablesStartingWith("baseline_matrix_");
        model.addAttribute("tableNames", tableNames);
        return "index";
        }
    @PostMapping("/")
    public String showSelectedTable(@RequestParam String tableSelect, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "20") int pageSize, Model model) {
        List<Map<String, Object>> selectedTable = tableCopyService.getTableData(tableSelect, pageNumber, pageSize);
        model.addAttribute("selectedTable", selectedTable);
        model.addAttribute("tableName", tableSelect);

        return "index";
    }


    @PostMapping("/copyTable")
    public String copyTable(@RequestParam String selectedTableName, RedirectAttributes redirectAttributes) {
        String copiedTableName = tableCopyService.createAndCopyUserSelectedTable(selectedTableName);
        redirectAttributes.addAttribute("tableName", copiedTableName);
        return "redirect:/editTable"; // Перенаправляем на страницу редактирования созданной копии таблицы
    }
    @GetMapping("/editTable")
    public String editTable(@RequestParam String tableName, Model model) {
        // Логика для отображения страницы редактирования таблицы с названием tableName
        return "editTablePage"; // Вернуть страницу редактирования таблицы
    }




}


