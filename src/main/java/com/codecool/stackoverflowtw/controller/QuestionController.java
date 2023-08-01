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

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return null;
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

    @PostMapping("/processQuestion")
    public String processQuestion(@RequestBody String title) {
        NewQuestionDTO newQuestionDTO=new NewQuestionDTO(title);
        System.out.println(newQuestionDTO.title());
        questionService.addNewQuestion(newQuestionDTO);

        return "Your data was received on the server!";
    }

}
