package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionsDaoJdbc implements QuestionsDAO {

    private static final String DB_URL = "jdbc:sqlite:src\\main\\resources\\Stackoverflow.db";

    @Override
    public int addQuestion(NewQuestionDTO question) {
        String query = "INSERT INTO QUESTIONS(id, title)Values(?,?)";
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(UUID.randomUUID()));
            preparedStatement.setString(2, question.title());
            preparedStatement.executeUpdate();

            connection.close();

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
        List<QuestionDTO> questionList = new ArrayList<>();

        String query = "SELECT * FROM Questions";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("Id");
                String title = resultSet.getString("Title");
                QuestionDTO questionDTO = new QuestionDTO(id, title);
                questionList.add(questionDTO);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
