package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.dao.model.DatabaseManager;
import com.codecool.stackoverflowtw.dao.model.SqliteConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StackoverflowTwApplication {
    static String dbFile = "C:\\Users\\PC\\codecool\\OOP\\stackOverFlow\\src\\main\\resources\\Stackoverflow.db";
    static SqliteConnector sqliteConnector = new SqliteConnector(dbFile);
    static DatabaseManager databaseManager = new DatabaseManager(sqliteConnector);


    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc(databaseManager);
    }
}
