package com.dsi.todo.controller;

import org.springframework.ui.Model;
import com.dsi.todo.dto.TaskCreateDto;
import com.dsi.todo.model.Task;
import com.dsi.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/todos")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String findAll(Model model) {
        List<Task> todoList =  taskService.findAll();
        model.addAttribute("todos", todoList);
        return "todo/list";
    }
    @PostMapping
    public String createTodo(@ModelAttribute TaskCreateDto taskCreateDto) {
        Date today = new Date();
        Task newTask = new Task(taskCreateDto.getTitle(), taskCreateDto.getDescription(), taskCreateDto.getPriority(), taskCreateDto.getStatus(), today.toString(), today.toString());
        taskService.save(newTask);
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoById(@PathVariable("id") Long id) {
        Optional<Task> optionalTodo = taskService.findById(id);

        if (optionalTodo.isEmpty()) {
            return "error";
        }

        Task todo = optionalTodo.get();

        taskService.delete(todo);
        return "redirect:/todos";
    }

}
