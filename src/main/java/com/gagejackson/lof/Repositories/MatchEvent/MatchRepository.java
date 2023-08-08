package com.gagejackson.lof.Repositories.MatchEvent;

import com.gagejackson.lof.Models.MatchOverview.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository  extends JpaRepository<Match, Long> {
    Match findByGameId(long gameId);
}
