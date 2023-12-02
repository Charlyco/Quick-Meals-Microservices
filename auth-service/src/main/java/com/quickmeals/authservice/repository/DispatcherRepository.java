package com.quickmeals.authservice.repository;

import com.quickmeals.authservice.entities.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispatcherRepository extends JpaRepository<Dispatcher, Integer> {

    Optional<Dispatcher> findDispatcherByDispatcherTag(String dispatcherTag);
}
