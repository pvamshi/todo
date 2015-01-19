package com.skeptors.service.impl;

import com.skeptors.dao.TaskDAO;
import com.skeptors.dao.impl.TaskDAOImpl;
import com.skeptors.model.Task;
import com.skeptors.service.TaskService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vamshi on 1/14/15.
 */
public class TaskServiceImpl implements TaskService {

    private TaskDAO taskDAO;

    public TaskServiceImpl(){
        this.taskDAO = new TaskDAOImpl();
    }

    @Override
    public Task saveTask(Task task) {
        return taskDAO.createTask(task);
    }

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }


}
