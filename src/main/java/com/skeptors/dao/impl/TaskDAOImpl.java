package com.skeptors.dao.impl;

import com.skeptors.EMF;
import com.skeptors.dao.TaskDAO;
import com.skeptors.model.Task;
import com.skeptors.model.Task2;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamshi on 1/14/15.
 */
@Singleton
public class TaskDAOImpl implements TaskDAO {

    @Override
    public Task saveTask(Task rawTask) {
        EntityManager em = em();
        try {
            em.persist(rawTask);
        } finally {
            em.close();
        }
        return rawTask;
    }

    @Override
    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();
        /*if(user == null){
            throw new ForbiddenException("user need to sign in");
        }*/
        EntityManager entityManager = EMF.get().createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT t FROM " + Task.class.getName() + " t ");
            List resultList = query.getResultList();
            if (resultList != null) {
                for (Task t : (List<Task>) resultList) {
                    taskList.add(t);
                }
            }
        } finally {
            entityManager.close();
        }
        return taskList;
    }

    @Override
    public List<Task2> getTask2List() {
        List<Task2> taskList = new ArrayList<>();
        /*if(user == null){
            throw new ForbiddenException("user need to sign in");
        }*/
        EntityManager entityManager = EMF.get().createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT t FROM " + Task2.class.getName() + " t ");
            List resultList = query.getResultList();
            if (resultList != null) {
                for (Task2 t : (List<Task2>) resultList) {
                    taskList.add(t);
                }
            }
        } finally {
            entityManager.close();
        }
        return taskList;
    }
    @Override
    public Task getTask(Long id) {
        EntityManager em = em();
        Task task;
        try{
            task = em.find(Task.class, id);
        }finally {
            em.close();
        }
        return task;
    }

    @Override
    public void deleteTask(Long id) {
        EntityManager em = em();
        try {
            Task taskToDelete = em.find(Task.class, id);
            em.remove(taskToDelete);
        } finally {
            em.close();
        }
    }

    @Override
    public Task2 insertTask2(Task2 task){
        EntityManager em = em();
        try{
            em.persist(task);
        }finally {
            em.close();
        }
        return task;
    }

    @Override
    public Task2 getTask2(String key){
        EntityManager em = em();
        Task2 task2 = null;
        try{
            task2=em.find(Task2.class,key);
        }finally {
            em.close();
        }
        return task2;
    }
    private static EntityManager em() {
        return EMF.get().createEntityManager();
    }
}
