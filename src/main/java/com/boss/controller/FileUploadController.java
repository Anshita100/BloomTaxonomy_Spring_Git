package com.boss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileUploadController {

	@GetMapping("/")
    public String index() {
        return "index";
    }
    
	
}
