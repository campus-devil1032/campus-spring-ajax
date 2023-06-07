package com.campus.campusspringajax.jspdemo;

import lombok.Data;

@Data
public class TodoDto {
    private int id;
    private String task;

    public TodoDto(int id, String task) {
        this.id = id;
        this.task = task;
    }
}
