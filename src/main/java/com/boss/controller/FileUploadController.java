package com.boss.controller;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boss.helper.QuestionExtractor;

import com.boss.service.QuestionAnalysisService;


@Controller
public class FileUploadController {
	
	@Autowired
    private QuestionExtractor questionExtractor;
	
	@Autowired
	QuestionAnalysisService questionAnalysisService; 

	@GetMapping("/")
    public String index() {
        return "index";
    }
    
	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
        	
			List<String> questionsliStrings=questionExtractor.extractQuestions(file);
			 
			model.addAttribute("questions", questionsliStrings);  // Pass list to the view
			
			// Map<String, BloomTaxonomyLevel> analysisResult = questionAnalysisService.analyzeFile(file);
		    // model.addAttribute("analysisResult", analysisResult);
		        
		        return "result";
			 
		} 
        
        
        catch (IOException e) {
        	
			//e.printStackTrace();
        	model.addAttribute("error", e.getMessage());
		}
        
        return "result";
    }
}
