package com.mohan.taskmanager.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mohan.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
}