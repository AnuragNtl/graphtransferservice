package com.clay.graphtransfer.services.graphtransfer;


import com.clay.graphtransfer.dto.Task; 

import java.util.List;

public interface GraphTransferService {

    public Task getTaskById(String id);

    public List<String> getAllTaskIds();

    public Task updateTask(String id, Task task);
};

