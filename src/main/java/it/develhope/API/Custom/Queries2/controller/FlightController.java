package it.develhope.API.Custom.Queries2.controller;

import it.develhope.API.Custom.Queries2.entities.Flight;
import it.develhope.API.Custom.Queries2.entities.Status;
import it.develhope.API.Custom.Queries2.repository.FlightRepository;
import it.develhope.API.Custom.Queries2.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;

    @PostMapping
    public void createAll(@RequestParam(required = false) String n) {
        flightService.create(n);
    }

    @GetMapping
    public List<Flight> getAll() {
        List<Flight> fligths = flightRepository.findAll();
        return fligths;
    }

    @GetMapping("/order")
    public Page<Flight> pageFligth(@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size){
        return flightService.pageFlight(page,size);
    }

    @GetMapping("/onetime")
    public List<Flight> getFligthOneTime(){
        return flightService.getFlightOneTime();
    }


    @GetMapping("/status")
    public List<Flight> findByStatus(@RequestParam(required = false) Status status, @RequestParam(required = false) Status status1){
        return flightRepository.findByStatus(status,status1);
    }
}

