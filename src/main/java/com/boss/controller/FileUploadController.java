package com.boss.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*import com.boss.model.BloomTaxonomyLevel;
import com.boss.service.QuestionAnalysisService;
*/
@Controller
public class FileUploadController {
	    @GetMapping("/")
	    public String index() {
	        return "index";
	    }
	    @PostMapping("/upload")
	    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
	        if (file.isEmpty()) {
	            model.addAttribute("message", "Please select a file to upload.");
	            return "index";
	        }

	        // Process the file
	       /* QuestionAnalysisService service = new QuestionAnalysisService();
	        Map<String, BloomTaxonomyLevel> analysisResult = service.analyzeFile(file);
	        model.addAttribute("analysisResult", analysisResult);
	        */
	        return "result";
	    }
}
