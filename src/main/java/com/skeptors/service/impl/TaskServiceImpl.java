package com.skeptors.service.impl;

import com.skeptors.dao.TaskDAO;
import com.skeptors.model.Task;
import com.skeptors.service.TaskService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by vamshi on 1/14/15.
 */
public class TaskServiceImpl implements TaskService {

    private TaskDAO taskDAO;

    @Inject
    public TaskServiceImpl(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }

    @Override
    public Task saveTask(Task task) {
        return taskDAO.createTask(task);
    }

    @Override
    public List<Task> getTaskList(){
        return taskDAO.getTaskList();
    }
}
