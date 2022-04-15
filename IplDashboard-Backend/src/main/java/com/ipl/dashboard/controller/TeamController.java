package com.ipl.dashboard.controller;

import com.ipl.dashboard.Dao.TeamDao;
import com.ipl.dashboard.Repository.TeamRepository;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Team;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamDao<Team, Match> teamsDao;

    private final TeamRepository teamRepository;

    public TeamController(TeamDao<Team, Match> teamsDao, TeamRepository teamRepository) {
        this.teamsDao = teamsDao;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }


    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        return teamsDao.findByName(teamName);
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesByTeamAndYear(@PathVariable String teamName, @RequestParam int year)
    {
        return teamsDao.findByNameAndYear(teamName, year);
    }
}
