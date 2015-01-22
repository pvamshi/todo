package com.skeptors.dao;

import com.skeptors.model.Task;

import java.util.List;

/**
 * Created by vamshi on 1/14/15.
 */
public interface TaskDAO {
    Task saveTask(Task rawTask);

    List<Task> getTaskList();

    Task getTask(Long id);

    void deleteTask(Long id);
}
