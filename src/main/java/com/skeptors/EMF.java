package com.skeptors;

/**
 * Created by vamshi on 9/1/15.
 */
import javax.persistence.EntityManagerFactory;
        import javax.persistence.Persistence;

public final class EMF {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
