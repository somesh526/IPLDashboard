package com.ipl.dashboard.utils;

import com.ipl.dashboard.data.MatchInput;
import com.ipl.dashboard.model.Match;

import java.time.LocalDate;

public class MatchUtils {

    private static final String BAT = "bat";

    public static void assignTeams(Match match, MatchInput matchInput){
        String firstInningsTeamName, secondInningsTeamName;
        String tossDecision= matchInput.getToss_decision();
        String tossWinner= matchInput.getToss_winner();

        if(tossDecision.equals(BAT)){
            firstInningsTeamName=tossWinner;
            secondInningsTeamName=tossWinner.equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
        }else{
            secondInningsTeamName=tossWinner;
            firstInningsTeamName=tossWinner.equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
        }
        match.setTeam1(firstInningsTeamName);
        match.setTeam2(secondInningsTeamName);

    }

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    public static Long stringToLong(String string) {
        return Long.parseLong(string);
    }
}
