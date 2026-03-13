package com.hackerrank.gevents.repository;

import com.hackerrank.gevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
   List<Event> findAllByRepoIdOrderByIdAsc(Integer repoId);
}
