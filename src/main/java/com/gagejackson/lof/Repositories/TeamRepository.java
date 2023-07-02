package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository  extends JpaRepository<Team, Long> {
}
