package com.skeptors;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.skeptors.dao.TaskDAO;
import com.skeptors.dao.impl.TaskDAOImpl;
import com.skeptors.service.TaskService;
import com.skeptors.service.impl.TaskServiceImpl;

/**
 * Created by vamshi on 1/20/15.
 */
public class HeroModule implements Module {
    public void configure(Binder binder) {
        binder.bind(Vehicle.class).to(FrogMobile.class);
        binder.bind(TaskDAO.class).to(TaskDAOImpl.class);
        binder.bind(TaskService.class).to(TaskServiceImpl.class);
    }
}
