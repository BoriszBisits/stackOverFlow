package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
       return questionsDAO.getAllQuestion();
    }

    public QuestionDTO getQuestionById(String id) {
        return questionsDAO.getQuestionById(id);
    }


    public boolean deleteQuestionById(String id) {
        return questionsDAO.deleteQuestion(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
     questionsDAO.addQuestion(question);
        return 0;
    }

    public List<String> getAnswersForQuestion(String Id) {
        return questionsDAO.getAnswersForQuestion(Id);
    }

}
