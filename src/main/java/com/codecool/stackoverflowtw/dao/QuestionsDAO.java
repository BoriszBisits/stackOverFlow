package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    int addQuestion(NewQuestionDTO question);
    List<QuestionDTO> getAllQuestion();
    List<String> getAnswersForQuestion(String questionId);
    QuestionDTO getQuestionById(String id);
    boolean deleteQuestion(String id);
    int addAnswer(String questionId, String answer);
}
