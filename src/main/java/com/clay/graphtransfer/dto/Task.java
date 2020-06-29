package com.clay.graphtransfer.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Task {

    private List<Task> taskList;

    private Map<String, Object> properties = new HashMap<String, Object>();

    @JsonAnySetter
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getProperty() {
        return properties;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
    
    public Map<String, Object> allProperties() {
        return properties;
    }
};

