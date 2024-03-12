package com.example.avito.controllers;

import com.example.avito.models.BaselineMatrix;
import com.example.avito.repository.BaselineMatrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaselineMatrixController {
    @Autowired
    BaselineMatrixRepository baselineMatrixRepository;
    @GetMapping("/")
    public String showBaseline(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "20") int size,
                               Model model) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BaselineMatrix> baselinePage = baselineMatrixRepository.findAll(pageable);
        model.addAttribute("baselinePage", baselinePage);
        return "index";
        }
    }

