package com.cweater.cweater.sheduled;

import com.cweater.cweater.entities.User;
import com.cweater.cweater.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DeleteInactiveUser {
    @Autowired
    private UserRepo repo;

    ScheduledExecutorService sheduler = Executors.newSingleThreadScheduledExecutor();

    public void sheduledDeleteUser(Long user_id) {
        Runnable task = () -> checkIfUserActive(user_id);

        sheduler.schedule(task, 60, TimeUnit.SECONDS);
    }

    private void checkIfUserActive(Long id) {
        User user = repo.findById(id).get();
        if (!user.isActive()) repo.delete(user);
    }
}
