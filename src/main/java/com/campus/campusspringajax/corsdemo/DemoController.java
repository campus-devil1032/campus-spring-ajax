package com.campus.campusspringajax.corsdemo;

import com.campus.campusspringajax.jspdemo.TodoDto;
import com.campus.campusspringajax.jspdemo.TodoV2Dto;
import com.campus.campusspringajax.jspdemo.TodoV2Request;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {

    @GetMapping("/api/data")
    public String getData() {
        return "Data from Server A";
    }
}
