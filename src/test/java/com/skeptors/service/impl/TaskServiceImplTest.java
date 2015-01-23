package com.skeptors.service.impl;

import com.skeptors.dao.TaskDAO;
import com.skeptors.model.Task;
import com.skeptors.service.TaskService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TaskServiceImplTest {


    @Mock
    private TaskDAO taskDAO;

    private TaskService taskServiceToTest;

    @Before
    public void setUp() throws Exception {
//        super.setUp();
        MockitoAnnotations.initMocks(this);
        taskServiceToTest = new TaskServiceImpl(taskDAO);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveTask() throws Exception {
        Task task = new Task();
        when(taskDAO.saveTask(any(Task.class))).thenReturn(task);
        Task taskCreated = taskServiceToTest.saveTask(new Task());
        assertNotNull(taskCreated);
    }

    @Test
    public void testGetTaskList() throws Exception {
        List<Task> taskList = new ArrayList<Task>();
        Task task1 = new Task();
        task1.setDescription("desc1");
        Task task2 = new Task();
        task2.setDescription("desc2");
        taskList.add(task1);
        taskList.add(task2);

        when(taskDAO.getTaskList()).thenReturn(taskList);
        List<Task> taskList1 = taskServiceToTest.getTaskList();
        assertNotNull(taskList1);
        assertEquals(2, taskList1.size());

    }

    @Test
    public void testGetTask() throws Exception {
        Task task = new Task();
        task.setDescription("desc");
        when(taskDAO.getTask(anyLong())).thenReturn(task);
        Task task1 = taskServiceToTest.getTask(1l);
        assertNotNull(task1);
        assertEquals("desc",task1.getDescription());
    }

    @Test
    public void testDeleteTask() throws Exception {
        doNothing().when(taskDAO).deleteTask(anyLong());
        taskServiceToTest.deleteTask(1L);
    }
}