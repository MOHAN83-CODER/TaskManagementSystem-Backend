package com.mohan.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohan.taskmanager.entity.Task;
import com.mohan.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public List<Task> getTasksByUserId(Long userId) {
    return taskRepository.findByUserId(userId);
}

    // Add a new task
    public Task saveTask(Task task) {

    System.out.println("========== ADD TASK ==========");
    System.out.println("Title      : " + task.getTitle());
    System.out.println("Description: " + task.getDescription());
    System.out.println("Status     : " + task.getStatus());
    System.out.println("Priority   : " + task.getPriority());
    System.out.println("Category   : " + task.getCategory());
    System.out.println("Due Date   : " + task.getDueDate());
    System.out.println("User ID    : " + task.getUserId());

    return taskRepository.save(task);
}
    // Update an existing task
    public Task updateTask(Long id, Task updatedTask) {

        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setCategory(updatedTask.getCategory());
            task.setDueDate(updatedTask.getDueDate());

            return taskRepository.save(task);
        }

        return null;
    }

    // Delete a task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}