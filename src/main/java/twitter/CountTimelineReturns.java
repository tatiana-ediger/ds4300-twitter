package twitter;

import java.util.List;
import java.util.Random;

/**
 * Once all the data has been loaded into the databases, uses the given API to count the amount of
 * timeline requests that are processed every second.
 */
public class CountTimelineReturns {


  public double countTimelineRetrievals(TwitterAPI api, boolean broadcast) {

    Random rand = new Random();
    int count = 0;
    long t = System.currentTimeMillis();
    long end = t + 5000;
    while (System.currentTimeMillis() < end) {
      int userID = rand.nextInt(10000);
      api.getTimeline(userID, broadcast);
      count += 1;
    }
    return count / 5.0;
  }
}
