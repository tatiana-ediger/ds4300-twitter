package twitter;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reads from the tweets CSV, and uses the given API to post tweets and times how long it takes.
 */
public class TimePostTweets {

  /**
   * Counts the time in seconds it takes to post the tweets using the given api and broadcasting or
   * not.
   *
   * @param api       the given TwitterAPI to use to post tweets
   * @param broadcast to broadcast or not
   * @return the time it takes in seconds to post tweets.
   * @throws IOException
   * @throws ParseException
   */
  public long timePostTweets(TwitterAPI api, boolean broadcast) throws IOException, ParseException {
    try (
        Reader reader = Files.newBufferedReader(Paths.get("test-tweet-table.csv"));
        CSVReader csvReader = new CSVReader(reader);
    ) {
      long startTime = new Date().getTime();
      // Reading records one by one in a string array
      String[] nextRecord;
      while ((nextRecord = csvReader.readNext()) != null) {
        int userID = Integer.valueOf(nextRecord[0]);
        String dateStr = nextRecord[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(dateStr);
        String text = nextRecord[2];
        Tweet t = new Tweet(userID, date, text);
        api.postTweet(t, broadcast);
      }
      long endTime = new Date().getTime();
      long totalTimeInMilli = endTime - startTime;
      long totalTimeSeconds = totalTimeInMilli / 1000;
      return totalTimeSeconds;
    }
  }
}
