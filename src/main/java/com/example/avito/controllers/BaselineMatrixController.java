package com.example.avito.controllers;

import com.example.avito.models.BaselineMatrix;
import com.example.avito.repository.BaselineMatrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BaselineMatrixController {
    @Autowired
    BaselineMatrixRepository baselineMatrixRepository;
    @GetMapping("")
    public String showBaseline(Model model){
        List<BaselineMatrix> baseline = baselineMatrixRepository.findAll();
        model.addAttribute("BaselineMatrix",baseline);
        return "index";
    }
}
