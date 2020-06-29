
package com.clay.graphtransfer.config;

import com.clay.graphstorage.converter.GraphOutput;
import com.clay.graphstorage.converter.GraphParser;
import com.clay.graphstorage.loader.GraphManager;
import com.clay.graphstorage.loader.GraphOutputConfig;
import com.clay.graphstorage.loader.GraphSourceConfig;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GraphStorageProviderConfig {

    @Value("#{${graphStorage.properties}}")
    private Map<String, String> graphStorageCommonProperties;

    @Value("${graphStorage.provider.parser}")
    private String graphStorageProviderParserClass;

    @Value("${graphStorage.provider.output}")
    private String graphStorageProviderOutputClass;

    @Autowired
    private GraphManager graphManager;

    @Bean
    public GraphManager getGraphManager() {

        GraphSourceConfig graphSourceConfig = new GraphSourceConfig();
        graphSourceConfig.setGraphParserClassPath(graphStorageProviderParserClass);
        graphSourceConfig.setProperties(graphStorageCommonProperties);

        GraphOutputConfig graphOutputConfig = new GraphOutputConfig();
        graphOutputConfig.setGraphOutputClassPath(graphStorageProviderParserClass);
        graphOutputConfig.setProperties(graphStorageCommonProperties);
        return GraphManager.getConfiguredLoader(graphSourceConfig, graphOutputConfig);
    }

    @Bean("graphParser")
    public GraphParser getGraphParser() {
        return graphManager.getGraphParser();
    }
    @Bean("graphOutput")
    public GraphOutput getGraphOutput(GraphManager graphManager) {
        return graphManager.getGraphOutput();
    }

};

