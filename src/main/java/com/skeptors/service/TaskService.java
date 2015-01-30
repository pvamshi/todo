package com.skeptors.service;

import com.skeptors.model.Task;
import com.skeptors.model.Task2;

import java.util.List;

/**
 * Created by vamshi on 1/14/15.
 */
public interface TaskService {
    Task saveTask(Task task);

    List<Task> getTaskList();

    List<Task2> getTask2List();

    Task getTask(Long id);

    void deleteTask(Long id);

    Task2 getTask2(String key);

    Task2 insertTask2(Task2 task2);
}
