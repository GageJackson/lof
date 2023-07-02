package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository  extends JpaRepository<Match, Long> {
}
