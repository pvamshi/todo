package com.skeptors.dao.impl;

import com.skeptors.EMF;
import com.skeptors.dao.TaskDAO;
import com.skeptors.model.Task;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Created by vamshi on 1/14/15.
 */
@Singleton
public class TaskDAOImpl implements TaskDAO {

    @Override
    public Task createTask(Task rawTask){
        EntityManager em = em();
        try{
            em.persist(rawTask);
        }finally {
            em.close();
        }
        return rawTask;
    }

    @Override
    public void printt() {
        System.out.println("\n\n\n\n\n----------- ################## ------------\n\nn\n\n\n\n");
    }

    private static EntityManager em(){
        return EMF.get().createEntityManager();
    }
}
