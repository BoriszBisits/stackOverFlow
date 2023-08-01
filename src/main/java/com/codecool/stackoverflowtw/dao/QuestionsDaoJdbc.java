package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class QuestionsDaoJdbc implements QuestionsDAO {
    private DatabaseManager databaseManager;


    public QuestionsDaoJdbc(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public int addQuestion(NewQuestionDTO question) {
        String query = "INSERT INTO QUESTIONS(id, title)Values(?,?)";
        try{
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(UUID.randomUUID()));
            preparedStatement.setString(2,question.title());
            preparedStatement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            return -1;
        }

    }

    @Override
    public QuestionDTO getQuestionById(int id) {
        return null;
    }

    @Override
    public boolean deleteQuestion(int id) {
        return false;
    }

    @Override
    public List<QuestionDTO> getAllQuestion() {
        return null;
    }
}
