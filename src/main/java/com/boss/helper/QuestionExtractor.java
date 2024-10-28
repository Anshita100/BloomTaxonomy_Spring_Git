package com.boss.helper;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionExtractor {

    public List<String> extractQuestions(MultipartFile file) throws IOException {
        List<String> questions = new ArrayList<>();
        StringBuilder currentQuestion = new StringBuilder();

        // Open the DOCX file using XWPFDocument
        try (XWPFDocument document = new XWPFDocument(file.getInputStream())) {
            // Loop through all paragraphs in the document
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                // Extract the text of each paragraph
                String paragraphText = paragraph.getText().trim();

                // Check if the paragraph starts with a new question format like "Q1", "Q2", etc.
                if (isNewQuestion(paragraphText)) {
                    // If a new question is detected, add the previous question to the list
                    if (currentQuestion.length() > 0) {
                        questions.add(currentQuestion.toString().trim()); 
                        currentQuestion.setLength(0);  // Reset for the new question
                    }
                }
                // Append non-empty paragraphs to the current question
                if (!paragraphText.isEmpty()) {
                    currentQuestion.append(paragraphText).append(" "); // Use space instead of newline for better formatting
                }
            }

            // Add the last question to the list after finishing the loop
            if (currentQuestion.length() > 0) {
                questions.add(currentQuestion.toString().trim()); 
            }
        }

        // If no questions were extracted, throw an error
        if (questions.isEmpty()) {
            throw new IOException("No questions found in the DOCX file. Ensure questions are properly numbered.");
        }

        return questions;  // Return the list of questions
    }

    // A method to check if a paragraph is the start of a new question
    private boolean isNewQuestion(String text) {
        // Regex to match "Q" followed by a number (e.g., "Q1", "Q2", "Q3", "Que1", "Que2", etc.)
        return text.matches("^(Q|Que)\\d+[:]?.*");
    }
}




/*package com.boss.helper;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionExtractor {

    public List<String> extractQuestions(MultipartFile file) throws IOException {
        List<String> questions = new ArrayList<>();
        StringBuilder currentQuestion = new StringBuilder();

        // Open the DOCX file using XWPFDocument
        try (XWPFDocument document = new XWPFDocument(file.getInputStream())) {

            // Loop through all paragraphs in the document
            for (XWPFParagraph paragraph : document.getParagraphs()) {

                // Extract the text of each paragraph
                String paragraphText = paragraph.getText().trim();

                // Check if the paragraph starts with a new question format like "Q1", "Q2", etc.
                if (isNewQuestion(paragraphText)) {
                    // If a new question is detected, add the previous question to the list
                    if (currentQuestion.length() > 0) {
                        questions.add(currentQuestion.toString().trim());
                        currentQuestion.setLength(0);  // Reset for the new question
                    }
                } else if (paragraphText.isEmpty() && currentQuestion.length() > 0) {
                    // Skip empty paragraphs but add the question if this is the end of the file
                    continue;
                } else if (!paragraphText.isEmpty()) {
                    // Append non-empty paragraphs to the current question
                    currentQuestion.append(paragraphText).append("\n");
                }
            }

            // Add the last question to the list after finishing the loop
            if (currentQuestion.length() > 0) {
                questions.add(currentQuestion.toString().trim());
            }
        }

        // If no questions were extracted, throw an error
        if (questions.isEmpty()) {
            throw new IOException("No questions found in the DOCX file. Ensure questions are properly numbered.");
        }

        return questions;  // Return the list of questions
    }

    // A method to check if a paragraph is the start of a new question
    private boolean isNewQuestion(String text) {
        // Regex to match "Q" followed by a number (e.g., "Q1", "Q2", "Q3",Que1,Que2,Que1:,Que2:)
    //	return text.matches("^(Q|Que)\\d+[:]?.*");
    	return text.matches("^(Q|Que)\\\\d+[:]?.*");


    }
}
*/