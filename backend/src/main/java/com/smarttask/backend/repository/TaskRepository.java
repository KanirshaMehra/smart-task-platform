package com.smarttask.backend.repository;


import com.smarttask.backend.entity.Task;
import com.smarttask.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> fingByUser(Long userId);
}
