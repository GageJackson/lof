package com.gagejackson.lof.Repositories.MatchOverview;

import com.gagejackson.lof.Models.MatchOverview.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository  extends JpaRepository<Team, Long> {
}
