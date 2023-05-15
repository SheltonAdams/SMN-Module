package com.LightFeather.Supervisor.Module.repo;

import com.LightFeather.Supervisor.Module.model.NewNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewSupervisorRepo extends JpaRepository<NewNotification,Integer> {
    NewNotification findBySupervisor(String Supervisor);
    NewNotification findByName(String FirstName);
}
