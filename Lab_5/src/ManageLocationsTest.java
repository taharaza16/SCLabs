import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Created by Taha-PC on 4/19/2017.
 */
public class ManageLocationsTest {
    @Test
    public void addRound() throws Exception {
        ManageLocations ME = new ManageLocations();

        String cvsSplitBy = ",";

        String userid = "8455e61b1999decaaf9d5803f0e36ef60fbe4dbf";
        String scheme = "CO-CHC";
        float totaltime = (float) 133.047;
        String totalstate = "FALSE";
        float t1time = (float) 23.598;
        String t1state = "TRUE";
        float t2time = (float) 20.671;
        String t2state = "TRUE";
        float t3time = (float)20.784;
        String t3state = "FALSE";
        float t4time = (float) 14.847;
        String t4state = "FALSE";
        float t5time = (float) 18.917;
        String t5state = "TRUE";
        float t6time = 0;
        String t6state = null;
        float t7time = 0;
        String t7state = null;


        assertEquals(1,ME.addRound(userid,scheme,totaltime,totalstate,t1time,t1state,t2time,t2state,t3time,t3state,t4time,t4state,t5time,t5state,t6time,t6state,t7time,t7state));
    }

}