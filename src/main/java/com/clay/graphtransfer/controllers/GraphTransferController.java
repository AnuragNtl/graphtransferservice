package com.clay.graphtransfer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clay.graphtransfer.dto.Task;
import com.clay.graphtransfer.services.graphtransfer.GraphTransferService;

@RestController
@RequestMapping("/graph")
public class GraphTransferController {

    @Autowired
    private GraphTransferService graphTransferService;

    @GetMapping("/{id}")
    public Task getGraph(@PathVariable("id") String id) {
        return graphTransferService.getTaskById(id);
    }

    @GetMapping
    public List<String> getAllTaskNames() {
        return graphTransferService.getAllTaskIds();
    }

    @PutMapping("/{id}")
    public Task updateGraph(@PathVariable("id") String id, 
            @RequestBody Task task) {
        return graphTransferService.updateTask(id, task);
    }
    
};

