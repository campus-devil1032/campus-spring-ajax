package com.campus.campusspringajax.jspdemo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {
    private final List<TodoDto> todoList = new ArrayList<>();
    private final List<TodoV2Dto> todoV2List = new ArrayList<>();

    @GetMapping("/todos")
    @ResponseBody
    public List<TodoDto> getTodoList() {
        return todoList;
    }

    @PostMapping("/todo")
    @ResponseBody
    public void addTodo(@RequestParam String task) {
        int id = todoList.size() + 1;
        TodoDto todoDto = new TodoDto(id, task);
        todoList.add(todoDto);
    }

    @GetMapping("/v2/todos")
    @ResponseBody
    public List<TodoV2Dto> getTodoV2List() {
        return todoV2List;
    }

    @PostMapping("/v2/todo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addTodo(@RequestBody TodoV2Request request) {
        String task = request.getTask();
        int id = todoV2List.size() + 1;
        todoV2List.add(new TodoV2Dto(id, task));
    }
}
