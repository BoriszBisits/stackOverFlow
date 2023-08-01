package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
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
    public boolean deleteQuestionById(@PathVariable int id) {
        return false;
    }


  /*  @ResponseBody
    public String processData(@RequestBody NewQuestionDTO questionDTO) {
        addNewQuestion(questionDTO);

        System.out.println(questionDTO);
        return "addQuestion";
    }*/

    @GetMapping("/addQuestion")
    public ModelAndView addQuestion() {
        ModelAndView modelAndView = new ModelAndView("addQuestion");
        return modelAndView;
    }

    @PostMapping("/processQuestion")
    public ModelAndView processQuestion(@ModelAttribute("questionDTO") NewQuestionDTO questionDTO) {
        // Here, you can handle the data received from the input field (questionDTO) as needed.
        // For example, you can save the question to the database using the questionService.
        System.out.println(questionDTO);

        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("questionText", questionDTO.title());
        return modelAndView;

    }

}
