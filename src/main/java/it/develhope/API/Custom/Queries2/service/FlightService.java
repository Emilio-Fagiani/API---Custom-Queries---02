package it.develhope.API.Custom.Queries2.service;

import it.develhope.API.Custom.Queries2.entities.Flight;
import it.develhope.API.Custom.Queries2.entities.Status;
import it.develhope.API.Custom.Queries2.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class FlightService {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    StatusService statusService;

    public void create(@RequestParam(required = false) String n) {
        Random random = new Random();
        if (n != null) {
            int n1 = Integer.parseInt(n);
            for (int i = 0; i < n1; i++) {
                String randomAttributeFlight = random.ints(10, 97, 122)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                Flight flight = flightRepository.save(new Flight(randomAttributeFlight, randomAttributeFlight, randomAttributeFlight, statusService.randomStatus()));
            }
        } else {
            for (int i = 0; i < 100; i++) {
                String randomAttributeFlight = random.ints(10, 97, 122)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                Flight flight = flightRepository.save(new Flight(randomAttributeFlight, randomAttributeFlight, randomAttributeFlight, statusService.randomStatus()));
            }
        }
    }
    public Page<Flight> pageFlight(@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size) {
        Pageable pageable = null;
        if (page.isPresent() && size.isPresent()) {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "fromAirport"));
            pageable = PageRequest.of(page.get(), size.get(), sort);
            Page<Flight> fligthPage = flightRepository.findAll(pageable);
            return fligthPage;
        } else {
            Page<Flight> fligthPage1 = Page.empty();
            return fligthPage1;
        }
    }

    public List<Flight> getFlightOneTime() {
        List<Flight> flights = flightRepository.findAll();
        List<Flight> flights1 = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getStatus() == Status.ONTIME) {
                flights1.add(flights.get(i));
            }
        }
        return flights1;
    }

    public List<Flight> findByStatus(Status status, Status status1) {
        List<Flight> flights= flightRepository.findByStatus(status, status1);
        return flights;
    }
}
