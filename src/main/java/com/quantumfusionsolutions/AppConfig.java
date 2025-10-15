package com.quantumfusionsolutions;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GenerativeModel generativeModel() throws java.io.IOException {
        String projectId = System.getenv("GOOGLE_CLOUD_PROJECT_ID");
        String location = System.getenv("GOOGLE_CLOUD_LOCATION");
        String modelName = "gemini-1.0-pro";

        if (projectId == null || location == null) {
            throw new RuntimeException("Please set the GOOGLE_CLOUD_PROJECT_ID and GOOGLE_CLOUD_LOCATION environment variables.");
        }

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            return new GenerativeModel(modelName, vertexAI);
        }
    }
}