package com.example.avito.controllers;

import com.example.avito.models.Microcategory_tree;

import com.example.avito.service.TableCopyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.Map.Entry;

@Controller
public class CategoryTreeController {

    Microcategory_tree microcategory_tree = new Microcategory_tree();

    HashMap<String, String[]> microcategory = microcategory_tree.getMicrocategoryTree();



    @Autowired
    TableCopyService tableCopyService;


    @GetMapping("/gg")
    public String ShowCategory(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageNumber, Model model){
        int pageSize = 20; // Количество записей на одной странице

        List<Map<String, Object>> selectedTableData = tableCopyService.getTableData("baseline_matrix_1", pageNumber, pageSize);
        model.addAttribute("selectedTable", selectedTableData);
        model.addAttribute("tableName", "baseline_matrix_1");
        int totalRows = tableCopyService.getTotalRows("baseline_matrix_1"); // Общее количество записей в таблице
        int totalPages = (int) Math.ceil((double) totalRows / pageSize); // Общее количество страниц
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", totalPages);

        List<String> category =  Arrays.asList(microcategory.keySet().toArray(new String[microcategory.keySet().size()]));
        model.addAttribute("category", category);


        model.addAttribute("microcategory", microcategory.get("category"));




        //  List<Map<String, Object>> listTable = tableCopyService.getTableData("baseline_matrix_1", 0, 100000);

        Iterator<Entry<String, String[]>> iterator = microcategory.entrySet().iterator();

        //System.out.println(listTable.get(0).toString());

        Entry<String, String[]> entry = iterator.next();
        //System.out.println( "Key:" + entry.getKey());

//        while (listTable.iterator().hasNext() && iterator.hasNext()){
//            while(listTable.get(i).toString().equals("{microcategory_id=" + j +"}")){
//
//                Map<String, Object> item = listTable.get(i); // получаем элемент
//                item.put("microcategory_id", j + " " + entry.getKey()); // меняем значение поля
//                listTable.set(i, item); // сохраняем изменение
//                System.out.println(listTable.get(i).toString());
//                i++;
//            }
//            j++;
//            //System.out.println(listTable.get(i).toString());
//
//            i++;
//        }



        return "ggwp";
    }




}
