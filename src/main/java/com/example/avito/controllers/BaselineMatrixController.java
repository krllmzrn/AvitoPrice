package com.example.avito.controllers;

import com.example.avito.models.Location_tree;
import com.example.avito.models.Microcategory_tree;
import com.example.avito.service.TableCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaselineMatrixController {

    Microcategory_tree microcategory_tree = new Microcategory_tree();
    HashMap<String, String[]> microcategory = microcategory_tree.getMicrocategoryTree();

    Location_tree locationTree = new Location_tree();
    HashMap<String, String[]> location = locationTree.getLocationTree();

    @Autowired
    TableCopyService tableCopyService;

    @GetMapping("/")
    public String showTables(Model model) {
        List<String> tableNames = tableCopyService.findTablesStartingWith("baseline_matrix_");
        model.addAttribute("tableNames", tableNames);
        return "index";
    }

    @GetMapping("/showSelectedTable")
    public String showSelectedTablePage(@RequestParam String tableName, @RequestParam(defaultValue = "0") int pageNumber, Model model) {
        int pageSize = 20; // Количество записей на одной странице
        List<Map<String, Object>> selectedTableData = tableCopyService.getTableData(tableName, pageNumber, pageSize);
        model.addAttribute("selectedTable", selectedTableData);
        model.addAttribute("tableName", tableName);
        int totalRows = tableCopyService.getTotalRows(tableName); // Общее количество записей в таблице
        int totalPages = (int) Math.ceil((double) totalRows / pageSize); // Общее количество страниц
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", totalPages);


        List<String> category =  Arrays.asList(microcategory.keySet().toArray(new String[microcategory.keySet().size()]));
        model.addAttribute("category", category);

        List<String> locationTree = Arrays.asList(location.keySet().toArray(new String[location.keySet().size()]));
        model.addAttribute("Region", locationTree);

        //model.addAttribute("microcategory", microcategory.get())

        return "baselineTable"; // Перенаправляем на страницу с выбранной таблицей и пагинацией
    }
    @PostMapping("/showSelectedTable")
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


