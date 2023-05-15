package com.LightFeather.Supervisor.Module.service;

import com.LightFeather.Supervisor.Module.model.Supervisors;

import java.util.List;

public interface SupervisorService {
    // Java interface that declares the methods for retrieving and saving supervisor data,


   List<Supervisors> getAllSupervisors();



   void saveSupervisor(Supervisors supervisor);
}
