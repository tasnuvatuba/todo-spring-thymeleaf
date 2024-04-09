package com.dsi.todo.service;
import com.dsi.todo.model.Task;
import com.dsi.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(Task Task) {
        return taskRepository.save(Task);
    }

    public void delete(Task Task) {
        taskRepository.delete(Task);
    }


}
