//package com.quantumfusionsolutions.service;
//
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class CoverLetterService {
//
//    public String generateCoverLetter(String userId, String jobId) {
//        return "AI cover letter generation temporarily disabled";
//    }
//}






















//package com.quantumfusionsolutions.service;
//
//import com.quantumfusionsolutions.model.Job;
//import com.quantumfusionsolutions.model.UserProfile;
//import com.quantumfusionsolutions.repository.JobRepository;
//import com.quantumfusionsolutions.repository.UserProfileRepository;
//import com.google.cloud.vertexai.api.GenerateContentResponse;
//import com.google.cloud.vertexai.generativeai.GenerativeModel;
//import com.google.cloud.vertexai.generativeai.ResponseHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class CoverLetterService {
//
//    @Autowired
//    private UserProfileRepository userProfileRepository;
//
//    @Autowired
//    private JobRepository jobRepository;
//
//    private final GenerativeModel generativeModel;
//
//    @Autowired
//    public CoverLetterService(GenerativeModel generativeModel) {
//        this.generativeModel = generativeModel;
//    }
//
//    public String generateCoverLetter(String userId, String jobId) throws IOException {
//        UserProfile userProfile = userProfileRepository.findByUserId(userId);
//        Job job = jobRepository.findById(jobId).orElse(null);
//
//        if (userProfile == null || job == null) {
//            throw new RuntimeException("User profile or job not found");
//        }
//
//        String prompt = String.format(
//                "Generate a professional cover letter for a job application based on the following details:\n\n" +
//                "**Applicant's Profile:**\n" +
//                "Name: %s\n" +
//                "Skills: %s\n" +
//                "Experience: %s\n" +
//                "Education: %s\n\n" +
//                "**Job Details:**\n" +
//                "Job Title: %s\n" +
//                "Company: %s\n" +
//                "Job Description: %s\n\n" +
//                "Please write a cover letter that is tailored to this specific job, highlighting how the applicant's skills and experience make them a strong candidate.",
//                userProfile.getFullName(),
//                String.join(", ", userProfile.getSkills()),
//                userProfile.getExperience(),
//                userProfile.getEducation(),
//                job.getTitle(),
//                job.getCompany(),
//                job.getDescription()
//        );
//
//        GenerateContentResponse response = generativeModel.generateContent(prompt);
//        return ResponseHandler.getText(response);
//    }
//}
