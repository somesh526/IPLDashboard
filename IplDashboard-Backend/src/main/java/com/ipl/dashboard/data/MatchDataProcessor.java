package com.ipl.dashboard.data;

import com.ipl.dashboard.model.Match;
import org.springframework.batch.item.ItemProcessor;

import static com.ipl.dashboard.utils.MatchUtils.*;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(MatchInput matchInput) throws Exception {

        Match match=new Match();
        match.setId(stringToLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(stringToLocalDate(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        //set Team 1 and Team2 depending on innings order
        assignTeams(match,matchInput);

        match.setMatchWinner(matchInput.getWinner());
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }
}
