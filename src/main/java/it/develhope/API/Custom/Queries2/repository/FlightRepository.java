package it.develhope.API.Custom.Queries2.repository;

import it.develhope.API.Custom.Queries2.entities.Flight;
import it.develhope.API.Custom.Queries2.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 public interface FlightRepository extends JpaRepository <Flight,Long> {

 @Query("SELECT f FROM Fligth f where f.status = ?1 AND f.status = ?2 ")
 public List<Flight> findByStatus(Status status, Status status1);
}
