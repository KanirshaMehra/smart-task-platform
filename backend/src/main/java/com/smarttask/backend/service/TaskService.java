package com.smarttask.backend.service;

import com.smarttask.backend.entity.Task;
import com.smarttask.backend.entity.User;
import com.smarttask.backend.repository.TaskRepository;
import com.smarttask.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    public Task createtasks(Task task, String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found."));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found."));
        return taskRepository.findByUserId(user.getId());

    }

    public Task getTaskById(Long taskId, String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found."));
        Task task=taskRepository.findById(taskId)
                .orElseThrow(()->new RuntimeException("Task not found"));
        if(!task.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized");
        }
        return task;
    }

    public void deleteTaskById(Long taskId, String email) {
        System.out.println("Delete called");
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found."));
        System.out.println("User found: " + user.getEmail());
        Task task=taskRepository.findById(taskId)
                .orElseThrow(()->new RuntimeException("Task not found"));
        System.out.println("Task found: " + task.getId());
        if(!task.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized");
        }
        System.out.println("Task deleted successfully");
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long taskId,Task updatedTask, String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found."));
        Task task=taskRepository.findById(taskId)
                .orElseThrow(()->new RuntimeException("Task not found"));
        if(!task.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized");
        }
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(task);
    }
}
