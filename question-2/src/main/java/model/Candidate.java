package model;

import java.util.Objects;

/**
 * [0 , 0 , 0] , when received a position it'll increae the freq[count] += 1
 */
public class Candidate {
    private final String name;
    private Integer score;
    private Integer lastVoter;

    public Candidate(String name) {
        this.name = name;
        this.score = 0;
        this.lastVoter = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getLastVoter() {
        return lastVoter;
    }

    public Integer getScore() {
        return score;
    }

    public void incrementScore(int positionWeight) {
        this.score += positionWeight;
    }

    public void udpateVoter(final int voter) {
        this.lastVoter = voter;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name) &&
                Objects.equals(score, candidate.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}
