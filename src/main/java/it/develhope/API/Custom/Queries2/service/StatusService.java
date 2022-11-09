package it.develhope.API.Custom.Queries2.service;

import it.develhope.API.Custom.Queries2.entities.Status;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StatusService {
    public Status randomStatus(){
        Random r = new Random();
        return Status.values()[r.nextInt(Status.values().length)];
    }
}
