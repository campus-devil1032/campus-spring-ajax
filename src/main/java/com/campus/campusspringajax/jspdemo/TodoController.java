package com.campus.campusspringajax.jspdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {
    private final List<TodoDto> todoDtoList = new ArrayList<>();

    @GetMapping("/todos")
    @ResponseBody
    public List<TodoDto> getTodoList() {
        return todoDtoList;
    }

    @PostMapping("/todo")
    @ResponseBody
    public void addTodo(@RequestParam String task) {
        int id = todoDtoList.size() + 1;
        TodoDto todoDto = new TodoDto(id, task);
        todoDtoList.add(todoDto);
    }

}
