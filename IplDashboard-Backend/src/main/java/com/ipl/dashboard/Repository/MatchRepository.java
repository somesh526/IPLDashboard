package com.ipl.dashboard.Repository;

import com.ipl.dashboard.model.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository  extends JpaRepository<Match, Long> {

    @Query(nativeQuery = true, value = "SELECT team1 , COUNT(*) from Match GROUP BY team1")
    List<Object[]> findTeam1Statistics();

    @Query(nativeQuery = true, value = "SELECT team2 , COUNT(*) from Match GROUP BY team2")
    List<Object[]> findTeam2Statistics();

    @Query(nativeQuery = true, value = "SELECT matchWinner , COUNT(*) from Match GROUP BY matchWinner")
    List<Object[]> findWinStatistics();

    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    @Query("SELECT m from Match m where (m.team1 = :teamName or m.team2 = :teamName)  and m.date between :startDate and :endDate order by date desc")
    List<Match> findByTeamInYear(@Param("teamName") String teamName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
