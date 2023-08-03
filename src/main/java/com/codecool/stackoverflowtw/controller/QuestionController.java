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

    @PostMapping("/addAnswer")
    public ResponseEntity<String> addAnswer(@RequestBody Map<String, String> requestBody) {
        String questionId = requestBody.get("questionId");
        String answer = requestBody.get("answer");
        int result = questionService.addAnswer(questionId, answer);
        if (result == 0) {
            String response = "Answer added successfully!";
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the answer.");
        }
    }

    @GetMapping("/{id}/addAnswer")
    public ModelAndView addAnswerForm(@PathVariable String id) {
        QuestionDTO question = questionService.getQuestionById(id);
        ModelAndView modelAndView = new ModelAndView("addAnswer");
        modelAndView.addObject("questionId", id);
        modelAndView.addObject("question", question);
        return modelAndView;
    }

    @PostMapping("/processQuestion")
    public String processQuestion(@RequestBody String title) {
        System.out.println(title);
        NewQuestionDTO newQuestionDTO=new NewQuestionDTO(title);
        String[] parts = title.substring(1, title.length() - 1).split(":");

        String value = parts[1].replaceAll("\"", "");
        System.out.println(value);
        NewQuestionDTO newQuestionDTO1=new NewQuestionDTO(value);

        questionService.addNewQuestion(newQuestionDTO1);

        return "You added a new question!";
    }

}
