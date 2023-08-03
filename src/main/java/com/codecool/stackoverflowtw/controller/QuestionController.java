package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public ModelAndView getAllQuestions() {
        List<QuestionDTO> questions = questionService.getAllQuestions();
        ModelAndView modelAndView = new ModelAndView("allQuestion");
        modelAndView.addObject("questions", questions);
        return modelAndView;
    }
    @GetMapping("/{id}/answer")
    public ModelAndView getQuestionWithAnswers(@PathVariable String id) {
        QuestionDTO question = questionService.getQuestionById(id);
        List<String> answers = questionService.getAnswersForQuestion(id);

        ModelAndView modelAndView = new ModelAndView("questionWithAnswers");
        modelAndView.addObject("question", question);
        modelAndView.addObject("answers", answers);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewQuestionDTO question) {
        return 0;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable String id) {
        boolean isDeleted = questionService.deleteQuestionById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Question deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found.");
        }
    }

    @GetMapping("/addQuestion")
    public ModelAndView addQuestion() {
        ModelAndView modelAndView = new ModelAndView("addQuestion");
        return modelAndView;
    }

    @RestController
    @RequestMapping("answers")
    public class AnswerController {

        private final QuestionService questionService;

        @Autowired
        public AnswerController(QuestionService questionService) {
            this.questionService = questionService;
        }

        @PostMapping
        public int addAnswer(@RequestBody Map<String, String> requestBody) {
            String questionId = requestBody.get("questionId");
            String answer = requestBody.get("answer");
            return questionService.addAnswer(questionId, answer);
        }
    }


    @PostMapping("/processAnswer")
    public ResponseEntity<String> addAnswer(@RequestParam String questionId, @RequestParam String answer) {
        // Assuming you have a method in the service to add an answer for a specific question
        // You can use a similar approach as you did for adding a question in `QuestionService`.
        // Make sure to update the service and DAO accordingly to handle answer addition.

        // For demonstration purposes, we can simply return success response.
        return ResponseEntity.ok("Answer added successfully.");
    }


    @GetMapping("/{id}/addAnswer")
    public ModelAndView addAnswerForm(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("addAnswer");
        modelAndView.addObject("questionId", id);
        return modelAndView;
    }


    @PostMapping("/processQuestion")
    public String processQuestion(@RequestBody String title) {
        System.out.println(title);
        NewQuestionDTO newQuestionDTO=new NewQuestionDTO(title);
        String[] parts = title.substring(1, title.length() - 1).split(":");

        // Extract the value from the second part
        String value = parts[1].replaceAll("\"", "");
        System.out.println(value);
        NewQuestionDTO newQuestionDTO1=new NewQuestionDTO(value);


        questionService.addNewQuestion(newQuestionDTO1);

        return "Your data was received on the server!";
    }

}
