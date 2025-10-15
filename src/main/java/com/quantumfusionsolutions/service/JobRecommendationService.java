package com.quantumfusionsolutions.service;

import com.quantumfusionsolutions.model.Job;
import com.quantumfusionsolutions.model.UserProfile;
import com.quantumfusionsolutions.repository.JobRepository;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRecommendationService {

    @Autowired
    private JobRepository jobRepository;

    private final GenerativeModel generativeModel;

    @Autowired
    public JobRecommendationService(GenerativeModel generativeModel) {
        this.generativeModel = generativeModel;
    }

    public List<Job> recommendJobs(UserProfile userProfile) throws IOException {
        List<Job> allJobs = jobRepository.findAll();

        String prompt = String.format(
                "Given the user profile below, recommend the top 3 most suitable jobs from the following list. " +
                        "Return the response as a comma-separated list of job IDs. Do not include any other text in the response.\n\n" +
                        "**User Profile:**\n" +
                        "Skills: %s\n" +
                        "Experience: %s\n" +
                        "Education: %s\n\n" +
                        "**Available Jobs:**\n" +
                        "%s",
                String.join(", ", userProfile.getSkills()),
                userProfile.getExperience(),
                userProfile.getEducation(),
                allJobs.stream()
                        .map(job -> String.format("Job ID: %s, Title: %s, Description: %s", job.getId(), job.getTitle(), job.getDescription()))
                        .collect(Collectors.joining("\n"))
        );

        GenerateContentResponse response = generativeModel.generateContent(prompt);
        String recommendedJobIds = ResponseHandler.getText(response).trim();
        List<String> recommendedJobIdList = List.of(recommendedJobIds.split(","));
        return jobRepository.findAllById(recommendedJobIdList);
    }
}
