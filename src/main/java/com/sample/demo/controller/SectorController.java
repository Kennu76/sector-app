package com.sample.demo.controller;

import java.util.List;

import com.sample.demo.model.MainSector;
import com.sample.demo.usecases.GetMainSectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value = "/sectors")
@CrossOrigin
@AllArgsConstructor
public class SectorController {
    private GetMainSectors sectorService;

    @GetMapping
    @ResponseBody
    public List<MainSector> sectors() {
        return sectorService.execute();
    }

}
