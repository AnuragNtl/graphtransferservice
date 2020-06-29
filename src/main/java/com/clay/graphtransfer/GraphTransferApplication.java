package com.clay.graphtransfer;

import com.clay.graphstorage.converter.GraphOutput;
import com.clay.graphstorage.converter.GraphParser;
import com.clay.graphstorage.loader.GraphManager;
import com.clay.graphstorage.loader.GraphOutputConfig;
import com.clay.graphstorage.loader.GraphSourceConfig;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class GraphTransferApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GraphTransferApplication.class, args);
	}

};

