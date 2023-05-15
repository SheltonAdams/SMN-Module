package com.LightFeather.Supervisor.Module.configure;

import com.LightFeather.Supervisor.Module.model.Supervisors;
import com.LightFeather.Supervisor.Module.service.SupervisorService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SupervisorServiceImpl implements SupervisorService {
@Bean
public SupervisorService supervisorService(){
    return new SupervisorServiceImpl(new RestTemplate());
}
    private static final String API_URL = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";
    private final RestTemplate restTemplate;

    public SupervisorServiceImpl(RestTemplate restTemplate){
       this.restTemplate = restTemplate;
    }


    @Override
    public List<Supervisors> getAllSupervisors() {
        Supervisors[] supervisors = restTemplate.getForObject(API_URL, Supervisors[].class);

        List<Supervisors> supervisorList = new ArrayList<>();
        Collections.addAll(supervisorList, supervisors);

        // Remove numeric jurisdictions from supervisor jurisdictions
        supervisorList.forEach(s -> s.setJurisdiction(s.getJurisdiction().replaceAll("\\d", "")));

        // Sort supervisors by jurisdiction, last name, and first name
        supervisorList.sort((s1, s2) -> {
            int result = s1.getJurisdiction().compareTo(s2.getJurisdiction());
            if (result == 0) {
                result = s1.getLastName().compareTo(s2.getLastName());
                if (result == 0) {
                    result = s1.getFirstName().compareTo(s2.getFirstName());
                }
            }
            return result;
        });
        // Format supervisors as "Jurisdiction - Lastname, Firstname"
        return supervisorList.stream()
                .map(s -> {
                    Supervisors supervisor = new Supervisors();
                    supervisor.setName(String.format("%s - %s, %s", s.getJurisdiction(), s.getLastName(), s.getFirstName()));
                    return supervisor;
                })
                .collect(Collectors.toList());
    }

        @Override
            public void saveSupervisor(Supervisors supervisor) {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Supervisors> request = new HttpEntity<>(supervisor, headers);
                String url = "/submit";
                restTemplate.postForObject(url, request, String.class);
        }

    public void printSortedSupervisors(List<Supervisors> supervisors) {
        //List<Supervisors> sortedSupervisors = getAllSupervisors();
        System.out.println("Sorted list of supervisors:");
        supervisors.forEach(s -> System.out.println(s.getName()));
    }
        public static void main (String[]args){
            SupervisorServiceImpl supervisorService = new SupervisorServiceImpl(new RestTemplate());
            List<Supervisors> sortedSupervisors = supervisorService.getAllSupervisors();
            supervisorService.printSortedSupervisors(sortedSupervisors);
        }

}
