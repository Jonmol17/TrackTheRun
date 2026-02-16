package com.app.TrackTheRun.Repositories;

import com.app.TrackTheRun.Entities.Runs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunsRepository extends JpaRepository<Runs, Long> {
}
