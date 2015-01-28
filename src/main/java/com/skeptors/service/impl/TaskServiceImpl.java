package com.skeptors.service.impl;

import com.skeptors.dao.TaskDAO;
import com.skeptors.model.Task;
import com.skeptors.service.TaskService;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Created by vamshi on 1/14/15.
 */
public class TaskServiceImpl implements TaskService {

    private TaskDAO taskDAO;

    @Inject
    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public Task saveTask(Task task) {
        return taskDAO.saveTask(task);
    }

    @Override
    public List<Task> getTaskList() {
        List<Task> taskList = taskDAO.getTaskList();
        Collections.sort(taskList);
        return taskList;
    }

    @Override
    public Task getTask(Long id) {
        return taskDAO.getTask(id);
    }

    @Override
    public void deleteTask(Long id){
        taskDAO.deleteTask(id);
    }

}
