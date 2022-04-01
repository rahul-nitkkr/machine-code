import model.CandidateComparator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestElectionCampaign {

    @Test
    public void testElectionCmapaign() {
        List<String> voter1 = List.of("A", "B");
        List<String> voter2 = List.of("B", "C");
        List<String> voter3 = List.of("B", "C");

        ElectionCampaign ec = new ElectionCampaign(new CandidateComparator());
        System.out.println(ec.findRanking(List.of(voter1, voter2, voter3)));
        System.out.println(ec.getWinner(List.of(voter1, voter2, voter3)));
    }

    @Test
    public void testElectionCmapaign_TieBSAed() {
        List<String> voter1 = List.of("A");
        List<String> voter2 = List.of("B", "C");
        List<String> voter3 = List.of("B", "C");
        List<String> voter4 = List.of("A");

        ElectionCampaign ec = new ElectionCampaign(new CandidateComparator());
        System.out.println(ec.findRanking(List.of(voter1, voter2, voter3 , voter4)));
        System.out.println(ec.getWinner(List.of(voter1, voter2, voter3 , voter4)));
    }
}
