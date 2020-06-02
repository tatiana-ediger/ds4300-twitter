package twitter;


import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.management.AttributeList;
import redis.clients.jedis.Jedis;

/**
 * Tests the Twitter API
 */
public class TwitterTester {

  private static TwitterAPI api = new MySQLTwitterAPI();
  private static RedisTwitterAPI rta = new RedisTwitterAPI();

  //private static TwitterAPI api = new RedisTwitterAPI();


  /**
   * Runs the program
   *
   * @param args the given arguments
   */
  public static void main(String[] args) throws IOException, ParseException {

//    api.connect("jdbc:mysql://localhost:3306/twitter?serverTimezone=EST5EDT", "root",
//        "root_pass"); // mysql connection settings
//    api.clear();
//    rta.clear();

    // This was the code I used to generate the users and their followers:
    LoadsUsers loadsUsers = new LoadsUsers(rta);
    loadsUsers.loadUsers();
//    LoadsUsers loadsUsers2 = new LoadsUsers(rta);
//    loadsUsers2.loadUsers();


//     Generates the tweet-table with 1 million tweets
//    GenerateTweets rg = new GenerateTweets();
//    rg.createUserCSV(1000000, "test-tweet-table.csv");

    TimePostTweets tpt = new TimePostTweets();
    CountTimelineReturns ctr = new CountTimelineReturns();


    // Timing tweet posts:
//    System.out.println("Seconds to post 1000000 tweets using SQL: "
//        + tpt.timePostTweets(api, false));
//    System.out.println("Number of timelines retrieved per second using SQL: "
//        + ctr.countTimelineRetrievals(api, false));

//    System.out.println("Seconds to post 1000000 tweets using Redis without broadcasting: "
//        + tpt.timePostTweets(rta, false));
//    System.out.println("Number of timelines retrieved per second using Redis without broadcasting: "
//        + ctr.countTimelineRetrievals(rta, false));

    System.out.println("Seconds to post 1000000 tweets using Redis with broadcasting: "
        + tpt.timePostTweets(rta, true));
    System.out.println("Number of timelines retrieved per second using Redis with broadcasting: "
        + ctr.countTimelineRetrievals(rta, true));



//    api.close();
  }
}



