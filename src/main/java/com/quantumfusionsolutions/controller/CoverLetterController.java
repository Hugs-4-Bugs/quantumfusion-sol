//package com.quantumfusionsolutions.controller;
//
//import com.quantumfusionsolutions.dto.CoverLetterRequest;
//import com.quantumfusionsolutions.service.CoverLetterService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/cover-letter")
//public class CoverLetterController {
//
//    @Autowired
//    private CoverLetterService coverLetterService;
//
//    @PostMapping("/generate")
//    public String generateCoverLetter(@RequestBody CoverLetterRequest request) throws IOException {
//        return coverLetterService.generateCoverLetter(request.getUserId(), request.getJobId());
//    }
//}
