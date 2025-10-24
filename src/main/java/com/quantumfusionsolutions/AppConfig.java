//package com.quantumfusionsolutions;
//
//import com.google.cloud.vertexai.generativeai.GenerativeModel;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    public GenerativeModel generativeModel() {
//        // no longer needed
//        return null;
//    }
//}
//





















//package com.quantumfusionsolutions;
//import com.google.cloud.vertexai.VertexAI;
//import com.google.cloud.vertexai.generativeai.GenerativeModel;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//
//    @Value("${google.cloud.project-id}")
//    private String projectId;
//
//    @Value("${google.cloud.location}")
//    private String location;
//
//    private static final String MODEL_NAME = "gemini-1.5-pro";
//
//    @Bean(destroyMethod = "close")
//    public VertexAI vertexAI() {
//        if (projectId == null || location == null) {
//            throw new IllegalStateException("google.cloud.project-id and google.cloud.location must be set in application.properties or environment.");
//        }
//        return new VertexAI(projectId, location);
//    }
//
//
///** it is asking to enable the autopay even for learning purpose   */
////    @Bean
////    public GenerativeModel generativeModel(VertexAI vertexAI) {
////        return new GenerativeModel(MODEL_NAME, vertexAI);
////    }
//}
