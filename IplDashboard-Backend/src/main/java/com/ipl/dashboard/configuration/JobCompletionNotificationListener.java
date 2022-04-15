package com.ipl.dashboard.configuration;

import com.ipl.dashboard.Dao.TeamDao;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCompletionNotificationListener  extends JobExecutionListenerSupport {


    private final JdbcTemplate jdbcTemplate;

    private final TeamDao<Team, Match> teamDao;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, TeamDao<Team, Match> teamDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.teamDao = teamDao;
    }

//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//            log.info("!!! JOB FINISHED! Time to verify the results");
//           jdbcTemplate.query("SELECT team1,team2, date From match",
//                   (rs,row)->"Team 1 "+rs.getString(1)+" Team 2 "+rs.getString(2)+" Date "+rs.getString(3))
//                   .forEach(s-> System.out.println(s));
//        }
//    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            teamDao.createTeams();

        }
    }
}
