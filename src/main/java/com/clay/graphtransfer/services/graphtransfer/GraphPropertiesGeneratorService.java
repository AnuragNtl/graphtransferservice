package com.clay.graphtransfer.services.graphtransfer;

import com.clay.graphtransfer.dto.Task; 

import java.util.Map;

public interface GraphPropertiesGeneratorService {

    public Map<String, String> getParserProperties(String id);

    public Map<String, String> getOutputProperties(String id, Task task);
};

