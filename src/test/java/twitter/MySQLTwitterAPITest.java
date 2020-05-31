package twitter;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class MySQLTwitterAPITest {

  private static MySQLTwitterAPI api = new MySQLTwitterAPI();

  @Test
  public void testSQLTwitterAPI() throws ParseException {
    api.connect("jdbc:mysql://localhost:3306/twitter?serverTimezone=EST5EDT",
        "root", "root_pass"); // mysql connection settings
    api.clear();

// testing redis
    api.addFollower(2, 1);
    api.addFollower(3, 1);
    api.addFollower(4, 1);

    String sd1 = "12-12-2018";
    String sd2 = "16-10-2016";
    String sd3 = "16-01-2016";
    String sd4 = "01-01-2020";
    String sd5 = "01-02-2020";
    String sd6 = "01-03-2020";
    String sd7 = "01-01-2015";
    String sd8 = "11-11-2016";
    String sd9 = "09-12-2014";
    String sd10 = "14-05-2016";
    String sd11 = "06-12-2019";
    String sd12 = "21-04-2017";
    String sd13 = "03-05-2020";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    Tweet tweet1 = new Tweet(1, 2, sdf.parse(sd1), "tweet");
    Tweet tweet2 = new Tweet(2, 2, sdf.parse(sd2), "tweet2");
    Tweet tweet3 = new Tweet(3, 3, sdf.parse(sd3), "tweet3");
    Tweet tweet4 = new Tweet(4, 3, sdf.parse(sd4), "tweet4");
    Tweet tweet5 = new Tweet(5, 3, sdf.parse(sd5), "tweet5");
    Tweet tweet6 = new Tweet(6, 3, sdf.parse(sd6), "tweet6");
    Tweet tweet7 = new Tweet(7, 3, sdf.parse(sd7), "tweet7");
    Tweet tweet8 = new Tweet(8, 4, sdf.parse(sd8), "tweet8");
    Tweet tweet9 = new Tweet(9, 4, sdf.parse(sd9), "tweet9");
    Tweet tweet10 = new Tweet(10, 4, sdf.parse(sd10), "tweet10");
    Tweet tweet11 = new Tweet(11, 4, sdf.parse(sd11), "tweet11");
    Tweet tweet12 = new Tweet(12, 4, sdf.parse(sd12), "tweet12");
    Tweet tweet13 = new Tweet(13, 4, sdf.parse(sd13), "tweet13");

    Set<Integer> user1following = new HashSet<>();
    user1following.add(2);
    user1following.add(3);
    user1following.add(4);

    Set<Integer> follows2 = new HashSet<>();
    follows2.add(1);
    assertEquals(follows2, api.getFollowers(2));
    assertEquals(user1following, api.getFollowees(1));

    List<Tweet> user2tweets = new ArrayList<>();
    user2tweets.add(tweet1);
    user2tweets.add(tweet2);

    api.postTweet(tweet1, false);
    api.postTweet(tweet2, false);

    assertEquals(tweet1, tweet1);
    assertEquals(user2tweets.toString(), api.getUsersTweets(2).toString());

    api.postTweet(tweet3, false);
    api.postTweet(tweet4, false);
    api.postTweet(tweet5, false);
    api.postTweet(tweet6, false);
    api.postTweet(tweet7, false);

    List<Tweet> user3tweets = new ArrayList<>();
    user3tweets.add(tweet3);
    user3tweets.add(tweet4);
    user3tweets.add(tweet5);
    user3tweets.add(tweet6);
    user3tweets.add(tweet7);

    // The following assertEquals works, but in different orders
    // assertEquals(user3tweets.toString(), api.getUsersTweets(3));

    api.postTweet(tweet8, false);
    api.postTweet(tweet9, false);
    api.postTweet(tweet10, false);
    api.postTweet(tweet11, false);
    api.postTweet(tweet12, false);
    api.postTweet(tweet13, false);

    List<Tweet> user4tweets = new ArrayList<>();
    user4tweets.add(tweet8);
    user4tweets.add(tweet9);
    user4tweets.add(tweet10);
    user4tweets.add(tweet11);
    user4tweets.add(tweet12);
    user4tweets.add(tweet13);

    assertEquals(user4tweets.toString(), api.getUsersTweets(4).toString());

    // System.out.println(api.getTimeline(1, false));

    api.clear();
  }

}