package com.skeptors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static com.skeptors.OfyService.ofy;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(
        name = "hello",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
@ApiClass(resource = "hello")
public class Hellos {

    public static ArrayList<HelloGreeting> greetings = new ArrayList<HelloGreeting>();

    public HelloGreeting getHello(@Named("id") Integer id) throws NotFoundException {
        try {
            return greetings.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Greeting not found with an index: " + id);
        }
    }

    @ApiMethod(name = "insertTask",httpMethod = "POST")
    public Car insertTask(HttpServletRequest request){
        Car car = new Car();
        car.setName("benz");
        EntityManager em = EMF.get().createEntityManager();
        try{
            em.persist(car);
        }finally {
            em.close();
        }
//        ofy().save().entity(car).now();

        return car;
//        Task task = new Task();
//        task.setText("some task");
//        PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
//        try {
//            persistenceManager.makePersistent(task);
//        } finally {
//            persistenceManager.close();
//        }
//        return task;
    }

    public ArrayList<HelloGreeting> listGreeting() {
        return greetings;
    }


}

