package twitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import redis.clients.jedis.Jedis;
import java.util.HashSet;
import java.util.Set;


/**
 * Represents a Twitter API implementation using Redis (key-value data stores)
 */
public class RedisTwitterAPI implements TwitterAPI {

  public Jedis jedis = new Jedis();

  /**
   * Increments the tweetID in order to create unique id for every new tweet
   *
   * @return the next tweet ID
   */
  private int getNextTweetID() {
    long next = jedis.incr("nextTweetID");
    return (int) next;
  }

  /**
   * Posts the tweet by adding it to t's userID's userTweets, and if broadcast=true, adds it to the
   * userTimeline of all the individuals follower's
   *
   * @param t         the given tweet
   * @param broadcast to broadcast or not
   */
  public void postTweet(Tweet t, boolean broadcast) {

    int next = getNextTweetID();
    t.setTweetID(next);
    jedis.set("tweet:" + next, t.toString());
    int tweetsUser = t.getUserID();
    jedis.sadd("usertweets:" + tweetsUser, String.valueOf(next));

    if (broadcast == true) {
      Set<Integer> userFollowers = this.getFollowers(t.getUserID());
      for (Integer follower : userFollowers) {
        jedis.zadd("usertimeline:" + follower, t.getTimeStamp().getTime(), t.toString());
      }
    }
  }

  @Override
  public void addFollower(int userID, int followerID) {
    jedis.sadd("followers:" + userID, String.valueOf(followerID));
    // Whenever you add a follower to a user, you also specify that that follower follows the user
    jedis.sadd("follows:" + followerID, String.valueOf(+userID));
  }

  @Override
  public Set<Integer> getFollowers(int userID) {
    // will return a list of the user id's of all this user's followers
    Set<String> fset = jedis.smembers("followers:" + userID);

    // convert set of strings to ints
    Set<Integer> rslt = new HashSet<Integer>();
    for (String f : fset) {
      rslt.add(Integer.valueOf(f));
    }

    return rslt;
  }

  @Override
  public Set<Integer> getFollowees(int followerID) {
    // will return a list of the user id's of everyone this user follows
    Set<String> fset = jedis.smembers("follows:" + followerID);

    // convert set of strings to ints
    Set<Integer> rslt = new HashSet<Integer>();
    for (String f : fset) {
      rslt.add(Integer.valueOf(f));
    }

    return rslt;
  }

  @Override
  public List<Tweet> getUsersTweets(int userID) {
    Set<String> tweetIDSet = jedis.smembers("usertweets:" + userID);
    Set<String> tset = new HashSet<>();
    for (String tweet : tweetIDSet) {
      tset.add(jedis.get("tweet:" + tweet));
    }
    return this.parseTweets(tset);
  }

  @Override
  public List<Tweet> getTimeline(int userID, boolean broadcast) {
    if (broadcast) {
      Set<String> topTen = jedis.zrange("usertimeline:" + userID, 0, 9);
      return this.parseTweets(topTen);
    } else {
      Set<Integer> follows = this.getFollowees(userID);
      for (Integer user : follows) {
        List<Tweet> userTweets = this.getUsersTweets(user);
        for (Tweet tweet : userTweets) {
          jedis.zadd("usertimeline:" + userID, tweet.getTimeStamp().getTime(), tweet.toString());
        }
      }
      Set<String> followeesTopTen = jedis.zrange("usertimeline:" + userID, 0, 9);
      return this.parseTweets(followeesTopTen);
    }
  }

  /**
   * Parses the given set of strings into tweets.
   *
   * @param toParse the set of strings to parse into tweet format
   * @return the list of parsed tweets
   */
  private List<Tweet> parseTweets(Set<String> toParse) {

    List<Tweet> tweets = new ArrayList<>();

    // parses a tweet from string format (tweetID:userID:date:contents) into an actual tweet
    for (String t : toParse) {
      String[] result = t.split(":");
      int tweet_id = Integer.parseInt(result[0]);
      int user_id = Integer.parseInt(result[1]);
      Date date = new Date(Integer.parseInt(result[2]));
      String contents = result[3];
      tweets.add(new Tweet(tweet_id, user_id, date, contents));
    }

    return tweets;
  }

  @Override
  public void connect(String url, String user, String pw) {
    jedis = new Jedis(url);
  }

  @Override
  public void close() {
  }

  @Override
  public void clear() {
    jedis.flushAll();
  }
}
