package model;

import java.util.Comparator;

public class CandidateComparator implements Comparator<Candidate> {

    @Override
    public int compare(Candidate candidate1, Candidate candidate2) {
        if (candidate1.getScore() != candidate2.getScore())
            return candidate1.getScore() - candidate2.getScore();
        else
            return candidate2.getLastVoter() - candidate1.getLastVoter();
    }
}
