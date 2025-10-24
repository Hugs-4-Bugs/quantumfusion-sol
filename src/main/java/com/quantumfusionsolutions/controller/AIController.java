//package com.quantumfusionsolutions.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/ai")
//public class AIController {
//
//    // Constructor no longer needs GenerativeModel
//    public AIController() {}
//
//    @PostMapping("/generate")
//    public ResponseEntity<String> generateText(@RequestBody Map<String, String> request) {
//        String prompt = request.get("prompt");
//
//        // Mock response — AI disabled for now
//        String response = "AI functionality temporarily disabled. Your input: " + prompt;
//
//        return ResponseEntity.ok(response);
//    }
//}

















//package com.quantumfusionsolutions.controller;
//
//import com.google.cloud.vertexai.generativeai.GenerativeModel;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/ai")
//public class AIController {
//
//    private final GenerativeModel generativeModel;
//
//    public AIController(GenerativeModel generativeModel) {
//        this.generativeModel = generativeModel;
//    }
//
//    @PostMapping("/generate")
//    public ResponseEntity<String> generateText(@RequestBody Map<String, String> request) throws IOException {
//        String prompt = request.get("prompt");
//
//        // Call AI model
//        String response = generativeModel.generateContent(prompt); // pseudo-method
//
//        return ResponseEntity.ok(response);
//    }
//}
