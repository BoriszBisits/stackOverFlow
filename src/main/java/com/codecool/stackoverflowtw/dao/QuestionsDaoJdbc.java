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
    public QuestionDTO getQuestionById(String id) {
        String query = "SELECT * FROM Questions WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String questionId = resultSet.getString("Id");
                String title = resultSet.getString("Title");
                return new QuestionDTO(questionId, title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean deleteQuestion(String id) {
        String query = "DELETE FROM QUESTIONS WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(id));
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
    @Override
    public List<String> getAnswersForQuestion(String questionId) {
        List<String> answers = new ArrayList<>();
        String query = "SELECT Answer FROM Answer WHERE Id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String answer = resultSet.getString("Answer");
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answers;
    }

}
