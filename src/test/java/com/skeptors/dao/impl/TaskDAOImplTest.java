package com.skeptors.dao.impl;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.skeptors.EMF;
import com.skeptors.dao.TaskDAO;
import com.skeptors.model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskDAOImplTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    private TaskDAO taskDAO;

    @Before
    public void setup(){
        helper.setUp();
        taskDAO = new TaskDAOImpl();
    }

    public void tearDown(){
        helper.tearDown();
    }

    @Test
    public void testSaveTask() throws Exception {
        Task rawTask = new Task();
        rawTask.setDescription("some test");
        Task task = taskDAO.saveTask(rawTask);
        Assert.assertNotNull(task.getId());
        Assert.assertEquals("some test",task.getDescription());
    }

    @Test
    public void testGetTaskList() throws Exception {
        final String DESC = "desscription test";
        EntityManager entityManager = EMF.get().createEntityManager();


        entityManager.getTransaction().begin();
        Task task = new Task();
        task.setDescription(DESC);
        entityManager.persist(task);
        entityManager.getTransaction().commit();

        Task task1 = new Task();
        task1.setDescription(DESC + 2);
        entityManager.getTransaction().begin();
        entityManager.persist(task1);
        entityManager.getTransaction().commit();


        List<Task> tasks = taskDAO.getTaskList();
        Assert.assertNotNull(tasks);
        Assert.assertEquals(2, tasks.size());
    }

    @Test
    public void testGetTask() throws Exception {

        final String DESC = "desscription test";
        Task task = new Task();
        task.setDescription(DESC);
        EntityManager entityManager = EMF.get().createEntityManager();
        entityManager.persist(task);
        Task createdTask = entityManager.merge(task);
        Task fetchedTask = taskDAO.getTask(createdTask.getId());
        Assert.assertNotNull(fetchedTask);
        Assert.assertNotNull(fetchedTask.getId());
        Assert.assertEquals(DESC,fetchedTask.getDescription());
    }

    @Test
    public void testDeleteTask() throws Exception {

        final String DESC = "desscription test";
        Task task = new Task();
        task.setDescription(DESC);
        EntityManager entityManager = EMF.get().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        Task createdTask = entityManager.merge(task);
        entityManager.getTransaction().commit();
        entityManager.close();

        Assert.assertNotNull(createdTask);
        Assert.assertNotNull(createdTask.getId());
        taskDAO.deleteTask(createdTask.getId());
        EntityManager entityManager2 = EMF.get().createEntityManager();
        Task fetchedTask = entityManager2.find(Task.class, createdTask.getId());
        Assert.assertNull(fetchedTask);
    }
}