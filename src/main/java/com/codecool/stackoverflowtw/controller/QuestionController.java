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
    public ModelAndView getAllQuestions() {
        List<QuestionDTO> questions = questionService.getAllQuestions();
        ModelAndView modelAndView = new ModelAndView("allQuestion");
        modelAndView.addObject("questions", questions);
        return modelAndView;
    }
  /*  @GetMapping("/all")
    public ModelAndView getAllQuestions() {
        List<QuestionDTO> questions = questionService.getAllQuestions();

        ModelAndView modelAndView = new ModelAndView("allQuestion");
        modelAndView.addObject("questions", questions);
        return modelAndView;
    }*/

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
    public String processQuestion(@RequestBody String title) {
        // Here, you can handle the data received from the input field (questionDTO) as needed.
        // For example, you can save the question to the database using the questionService.
        //System.out.println(title);
        NewQuestionDTO newQuestionDTO=new NewQuestionDTO(title);
        System.out.println(newQuestionDTO.title());
        questionService.addNewQuestion(newQuestionDTO);

        // Return the processed data, or any other response you want to send back to the client.
        return "Your data was received on the server!";
    }

}
