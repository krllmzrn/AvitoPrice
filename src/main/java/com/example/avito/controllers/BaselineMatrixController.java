package com.example.avito.controllers;

import com.example.avito.models.BaselineMatrix;
import com.example.avito.repository.BaselineMatrixRepository;
import com.example.avito.service.TableCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BaselineMatrixController {

    @Autowired
    TableCopyService tableCopyService;

    @Autowired
    BaselineMatrixRepository baselineMatrixRepository;
    @GetMapping("/")
    public String showBaseline(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "20") int size,
                               Model model) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BaselineMatrix> baselinePage = baselineMatrixRepository.findAll(pageable);
        model.addAttribute("baselinePage", baselinePage);

        // эту строчку делитать можно я ее так затестил
        // tableCopyService.createTableCopy("qwezxc", "baseline_matrix_1");

        return "index";
        }

//    @Autowired
//    TableCopyService tableCopyService;
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public String updateTable(@RequestParam(defaultValue = "0") int page,
//                              @RequestParam(defaultValue = "20") int size,
//                              Model model) {
//        PageRequest pageable = PageRequest.of(page, size);
//        Page<BaselineMatrix> baselinePage = baselineMatrixRepository.findAll(pageable);
//        model.addAttribute("baselinePage", baselinePage);
//        tableCopyService.createTableCopy("zxc", "qwe");
//
//        return "test";
//    }

}


