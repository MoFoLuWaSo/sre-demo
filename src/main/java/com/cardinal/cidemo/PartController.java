package com.cardinal.cidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parts")
public class PartController {
    private final PartDao partDao;

    @Autowired
    public PartController(PartDao partDao) {
        this.partDao = partDao;
    }

    @GetMapping
    public List<Part> getAllParts() {
        return partDao.getParts();
    }
}
