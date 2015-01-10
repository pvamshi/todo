package com.skeptors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(
        name = "todo",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
@ApiClass(resource = "task")
public class Tasks {


    public Task getTask(@Named("id") Long id) throws NotFoundException {
        return new Task();
    }

    @ApiMethod(httpMethod = "POST")
    public Task insertTask() {
        Task task = new Task();
        task.setDescription("first");
        EntityManager em = EMF.get().createEntityManager();
        try {
            em.persist(task);
        } finally {
            em.close();
        }
        return task;
    }


    public List<Task> listTasks() {

        EntityManager entityManager = EMF.get().createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM " + Task.class.getName() + " t");
        List<Task> taskList = new ArrayList<>();
        List resultList = query.getResultList();
        if (resultList != null) {
            for (Task t : (List<Task>) resultList) {
                taskList.add(t);
            }
        }
        return taskList;
    }
//    public ArrayList
// <HelloGreeting> listGreeting() {
//        return greetings;
//    }


}

