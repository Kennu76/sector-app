package com.sample.demo.controller;

import java.util.List;

import com.sample.demo.model.MainSector;
import com.sample.demo.service.SectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sectors")
public class SectorController {
    @Autowired
    private SectorService sectorService;
    
    @GetMapping
    @ResponseBody
    public List<MainSector> sectors() {
        return sectorService.findAllMainSectors();
    }
    
}
