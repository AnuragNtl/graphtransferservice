package com.clay.graphtransfer.services.graphtransfer;

import com.clay.graphtransfer.dto.Task; 

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FileBasedGraphPropertiesGenerator implements GraphPropertiesGeneratorService {
    public Map<String, String> getParserProperties(String id) {

        HashMap<String, String> properties = new HashMap<>(); 
        addFileNameFromId(id, properties);
        return properties;
    }

    private void addFileNameFromId(String id, Map<String, String> properties) {
        properties.put("fileName", id);

    }

    public Map<String, String> getOutputProperties(String id, Task task) {
        HashMap<String, String> properties = new HashMap<>(); 
        addFileNameFromId(id, properties);
        return properties;
    }

};


