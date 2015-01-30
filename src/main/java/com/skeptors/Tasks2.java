package com.skeptors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.skeptors.model.Task2;
import com.skeptors.service.TaskService;

import javax.inject.Named;
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
@ApiClass(resource = "task2")
public class Tasks2 {

    private TaskService taskService;

    public Tasks2() {
        Injector injector = Guice.createInjector(new TodoModule());
        taskService = injector.getInstance(TaskService.class);
    }


    public Task2 insertTask2(Task2 task2){
        return taskService.insertTask2(task2);
    }

    public void getTask2(@Named("key") String key){
        taskService.getTask2(key);
    }


    public List<Task2> listTasks() throws ForbiddenException {
        return taskService.getTask2List();
    }
}

