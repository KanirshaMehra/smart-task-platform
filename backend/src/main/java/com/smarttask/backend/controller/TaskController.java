package com.smarttask.backend.controller;

import com.smarttask.backend.entity.Task;
import com.smarttask.backend.entity.User;
import com.smarttask.backend.service.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/createTask")
    public Task createTask(@RequestBody Task task){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return taskService.createtasks(task,email);
    }
@GetMapping("/getUserTasks")
    public List<Task> getUserTasks(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return taskService.getUserTasks(email);
    }

    @GetMapping("/getTaskById/{id}")
    public Task getTaskById(@PathVariable Long id){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return taskService.getTaskById(id,email);
    }

    @DeleteMapping("/deleteTaskById/{id}")
    public String deleteTaskById(@PathVariable Long id){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        taskService.deleteTaskById(id,email);
        return "Task deleted successfully";
    }

    @PutMapping("/updateTask/{id}")
    public Task updateTask(@PathVariable Long id,@RequestBody Task task){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return taskService.updateTask(id,task,email);
    }

}
