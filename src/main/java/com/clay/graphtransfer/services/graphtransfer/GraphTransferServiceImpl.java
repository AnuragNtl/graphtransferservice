package com.clay.graphtransfer.services.graphtransfer;


import com.clay.graphstorage.converter.GraphOutput;
import com.clay.graphstorage.converter.GraphParser;
import com.clay.graphstorage.entities.Graph;
import com.clay.graphstorage.entities.Node;
import com.clay.graphstorage.entities.NodeProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clay.graphtransfer.dto.Task;

@Service
@Slf4j
public class GraphTransferServiceImpl implements GraphTransferService {


    @Autowired
    @Qualifier("graphOutput")
    private GraphOutput graphOutput;

    @Autowired
    @Qualifier("graphParser")
    private GraphParser graphParser;


    @Autowired
    private GraphPropertiesGeneratorService graphPropertiesGeneratorService;


    @Override
    public Task getTaskById(String id) {

       Graph graph = graphParser.loadGraph(graphPropertiesGeneratorService.getParserProperties(id));
       log.info("Got graph : {} ", graph);
       Task task = convertNodeToTask(graph.getRootNode()); 
       log.info("Equivalent task is : {}", task);
       return task;
    } 

    private Task convertNodeToTask(Node rootNode) {

        return rootNode.convert(node -> {
            Task task = new Task();
            task.setTaskList(new ArrayList<>());
            return task;
        },
        (task, connectsTo) -> task.getTaskList().addAll(connectsTo),
        (task, entry) -> task.setProperty(entry.getKey(), entry.getValue()));
    }

    @Override
    public List<String> getAllTaskIds() {

        return graphParser.getAllGraphIds();
    }
    
    private Node convertTaskToNode(Graph graph, Task fromTask) {

        return Node.createFrom(task -> new Node(graph), 
                task -> task.getTaskList() == null ? new ArrayList<>() : new ArrayList<>(task.getTaskList()),
                task -> task.allProperties(),
                fromTask);

    }

    private Graph getEquivalentGraph(Task task) {

        Graph graph = new Graph();
        Node rootNode = convertTaskToNode(graph, task);
        graph.setRootNode(rootNode);
        return graph;
    }

    @Override
    public Task updateTask(String id, Task task) {
        log.info("Equivalent task : {}", task);
        Graph graph = getEquivalentGraph(task);
        log.info("Equivalent Graph : {}", graph);
        graphOutput.outputGraph(graph, graphPropertiesGeneratorService.getOutputProperties(id, task));
        return task;
    } 
};

