package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    int addQuestion(NewQuestionDTO question);
    QuestionDTO getQuestionById(int id);
    boolean deleteQuestion(int id);
    List<QuestionDTO> getAllQuestion();
}
