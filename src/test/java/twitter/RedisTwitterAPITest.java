package twitter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class RedisTwitterAPITest {

  private static RedisTwitterAPI rta = new RedisTwitterAPI();

  @Test
  public void testNoBroadcast() {

    rta.clear();

    // testing redis
    rta.addFollower(2, 1);
    rta.addFollower(3, 1);
    rta.addFollower(4, 1);

    Tweet tweet1 = new Tweet(1, 2, new Date(43854), "tweet");
    Tweet tweet2 = new Tweet(2, 2, new Date(45453), "348543843854358");
    Tweet tweet3 = new Tweet(3, 3, new Date(54364), "tweet3");
    Tweet tweet4 = new Tweet(4, 3, new Date(44563), "tweet4");
    Tweet tweet5 = new Tweet(5, 3, new Date(52346), "tweet5");
    Tweet tweet6 = new Tweet(6, 3, new Date(12354), "tweet6");
    Tweet tweet7 = new Tweet(7, 3, new Date(32454), "tweet7");
    Tweet tweet8 = new Tweet(8, 4, new Date(23465), "tweet8");
    Tweet tweet9 = new Tweet(9, 4, new Date(73457), "tweet9");
    Tweet tweet10 = new Tweet(10, 4, new Date(32454), "tweet10");
    Tweet tweet11 = new Tweet(11, 4, new Date(34623), "tweet11");
    Tweet tweet12 = new Tweet(12, 4, new Date(75437), "tweet12");
    Tweet tweet13 = new Tweet(13, 4, new Date(32444), "tweet13");

    Set<Integer> user1following = new HashSet<>();
    user1following.add(2);
    user1following.add(3);
    user1following.add(4);

    Set<Integer> follows2 = new HashSet<>();
    follows2.add(1);
    assertEquals(follows2, rta.getFollowers(2));
    assertEquals(user1following, rta.getFollowees(1));

    List<Tweet> user2tweets = new ArrayList<>();
    user2tweets.add(tweet1);
    user2tweets.add(tweet2);

    rta.postTweet(tweet1, false);
    rta.postTweet(tweet2, false);

    assertEquals(tweet1, tweet1);
    System.out.println(rta.getUsersTweets(2));
    assertEquals(user2tweets.toString(), rta.getUsersTweets(2).toString());


    rta.postTweet(tweet3, false);
    rta.postTweet(tweet4, false);
    rta.postTweet(tweet5, false);
    rta.postTweet(tweet6, false);
    rta.postTweet(tweet7, false);

    List<Tweet> user3tweets = new ArrayList<>();
    user3tweets.add(tweet3);
    user3tweets.add(tweet4);
    user3tweets.add(tweet5);
    user3tweets.add(tweet6);
    user3tweets.add(tweet7);

    // The following assertEquals works, but in different orders
    // assertEquals(user3tweets.toString(), rta.getUsersTweets(3));

    rta.postTweet(tweet8, false);
    rta.postTweet(tweet9, false);
    rta.postTweet(tweet10, false);
    rta.postTweet(tweet11, false);
    rta.postTweet(tweet12, false);
    rta.postTweet(tweet13, false);

    List<Tweet> user4tweets = new ArrayList<>();
    user4tweets.add(tweet8);
    user4tweets.add(tweet9);
    user4tweets.add(tweet10);
    user4tweets.add(tweet11);
    user4tweets.add(tweet12);
    user4tweets.add(tweet13);

    // The following assertEquals works, but in different orders
//     assertEquals(user4tweets.toString(), rta.getUsersTweets(4).toString());

    System.out.println(rta.getTimeline(1, false));

    rta.clear();
  }

  @Test
  public void testBroadcast() {

    rta.clear();

    // testing redis
    rta.addFollower(2, 1);
    rta.addFollower(3, 1);
    rta.addFollower(4, 1);

    Tweet tweet1 = new Tweet(1, 2, new Date(43854), "tweet");
    Tweet tweet2 = new Tweet(2, 2, new Date(86486), "tweet2");
    Tweet tweet3 = new Tweet(3, 3, new Date(54364), "tweet3");
    Tweet tweet4 = new Tweet(4, 3, new Date(44563), "tweet4");
    Tweet tweet5 = new Tweet(5, 3, new Date(52346), "tweet5");
    Tweet tweet6 = new Tweet(6, 3, new Date(12354), "tweet6");
    Tweet tweet7 = new Tweet(7, 3, new Date(32454), "tweet7");
    Tweet tweet8 = new Tweet(8, 4, new Date(23465), "tweet8");
    Tweet tweet9 = new Tweet(9, 4, new Date(73457), "tweet9");
    Tweet tweet10 = new Tweet(10, 4, new Date(32454), "tweet10");
    Tweet tweet11 = new Tweet(11, 4, new Date(34623), "tweet11");
    Tweet tweet12 = new Tweet(12, 4, new Date(75437), "tweet12");
    Tweet tweet13 = new Tweet(13, 4, new Date(32444), "tweet13");

    Set<Integer> user1following = new HashSet<>();
    user1following.add(2);
    user1following.add(3);
    user1following.add(4);

    Set<Integer> follows2 = new HashSet<>();
    follows2.add(1);
    assertEquals(follows2, rta.getFollowers(2));
    assertEquals(user1following, rta.getFollowees(1));

    List<Tweet> user2tweets = new ArrayList<>();
    user2tweets.add(tweet1);
    user2tweets.add(tweet2);

    rta.postTweet(tweet1, true);
    rta.postTweet(tweet2, true);

    assertEquals(tweet1, tweet1);
    assertEquals(user2tweets.toString(), rta.getUsersTweets(2).toString());

    rta.postTweet(tweet3, true);
    rta.postTweet(tweet4, true);
    rta.postTweet(tweet5, true);
    rta.postTweet(tweet6, true);
    rta.postTweet(tweet7, true);

    List<Tweet> user3tweets = new ArrayList<>();
    user3tweets.add(tweet3);
    user3tweets.add(tweet4);
    user3tweets.add(tweet5);
    user3tweets.add(tweet7);
    user3tweets.add(tweet7);

    // The following assertEquals works, but in different orders
    // assertEquals(user3tweets.toString(), rta.getUsersTweets(3));

    rta.postTweet(tweet8, true);
    rta.postTweet(tweet9, true);
    rta.postTweet(tweet10, true);
    rta.postTweet(tweet11, true);
    rta.postTweet(tweet12, true);
    rta.postTweet(tweet13, true);

    List<Tweet> user4tweets = new ArrayList<>();
    user4tweets.add(tweet8);
    user4tweets.add(tweet9);
    user4tweets.add(tweet10);
    user4tweets.add(tweet11);
    user4tweets.add(tweet12);
    user4tweets.add(tweet13);

    // The following assertEquals works, but in different orders
    // assertEquals(user4tweets.toString(), rta.getUsersTweets(4).toString());

    //System.out.println(rta.getTimeline(1, true));

    rta.clear();
  }

}