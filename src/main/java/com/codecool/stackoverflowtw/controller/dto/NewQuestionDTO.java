package com.codecool.stackoverflowtw.controller.dto;

public record NewQuestionDTO(String title){
    @Override
    public String title() {
        return title;
    }
}
