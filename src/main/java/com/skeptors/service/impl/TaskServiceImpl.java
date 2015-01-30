package com.skeptors.service.impl;

import com.skeptors.dao.TaskDAO;
import com.skeptors.model.Task;
import com.skeptors.model.Task2;
import com.skeptors.service.TaskService;

import javax.inject.Inject;
import java.util.ArrayList;
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
    public List<Task2> getTask2List() {
        List<Task2> taskList = taskDAO.getTask2List();
        List<Task2> tasks  = new ArrayList<>();
        for(Task2 task2: taskList){
            if(task2.getParent()==null){
                traverseChildren(task2);
                tasks.add(task2);
            }
        }
        return tasks;
    }
    private void traverseChildren(Task2 task2){
        List<Task2> children = task2.getChildren();
        for(Task2 task : children){
            traverseChildren(task);
        }
    }

    @Override
    public Task getTask(Long id) {
        return taskDAO.getTask(id);
    }

    @Override
    public void deleteTask(Long id){
        taskDAO.deleteTask(id);
    }

    @Override
    public Task2 getTask2(String key){
        return taskDAO.getTask2(key);
    }

    @Override
    public Task2 insertTask2(Task2 task2){
        return taskDAO.insertTask2(task2);
    }
}
