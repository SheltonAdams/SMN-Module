package com.LightFeather.Supervisor.Module.service;

import com.LightFeather.Supervisor.Module.model.NewNotification;

import java.util.Collection;

public interface NewNotificationService {

    // Create a new notification and save it
    NewNotification create(NewNotification notification);
    Collection<NewNotification> list();
    NewNotification get(String Supervisor);
}
