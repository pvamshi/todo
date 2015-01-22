package com.skeptors.service;

import com.skeptors.model.Task;

import java.util.List;

/**
 * Created by vamshi on 1/14/15.
 */
public interface TaskService {
    Task saveTask(Task task);

    List<Task> getTaskList();

    Task getTask(Long id);

    void deleteTask(Long id);
}
