package com.skeptors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.skeptors.model.Task;
import com.skeptors.service.TaskService;

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

    private TaskService taskService ;

    public Tasks(){
        Injector injector = Guice.createInjector(new TodoModule());
        taskService = injector.getInstance(TaskService.class);
        taskService.print();
    }

    public Task getTask(@Named("id") Long id) throws NotFoundException {
        return new Task();
    }

    public Task insertTask(Task task) {
        return taskService.saveTask(task);
    }

    public List<Task> listTasks() throws ForbiddenException{

        List<Task> taskList = new ArrayList<>();
//        if(user == null){
//            throw new ForbiddenException("user need to sign in");
//        }
        EntityManager entityManager = EMF.get().createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM " + Task.class.getName() + " t");
        List resultList = query.getResultList();
        if (resultList != null) {
            for (Task t : (List<Task>) resultList) {
                taskList.add(t);
            }
        }
        return taskList;
    }

}

