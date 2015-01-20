package com.skeptors.dao;

import com.skeptors.model.Task;

/**
 * Created by vamshi on 1/14/15.
 */
public interface TaskDAO {
    Task createTask(Task rawTask);

    void printt();
}
