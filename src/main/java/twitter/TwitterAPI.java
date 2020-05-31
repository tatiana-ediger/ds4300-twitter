package twitter;

import java.util.List;
import java.util.Set;

/**
 * Represents a Twitter API that can post tweets, add/get followers
 */
public interface TwitterAPI {

  /**
   * Posts a tweet
   *
   * @param t         the given tweet
   * @param broadcast to broadcast or not
   */
  void postTweet(Tweet t, boolean broadcast);

  /**
   * Adds a follower to a user
   *
   * @param userID     the user who will gain a follower
   * @param followerID the follower the user gains
   */
  void addFollower(int userID, int followerID);

  /**
   * Returns the given user's list of followers
   *
   * @param userID the user whose followers we will retrieve
   * @return the given user's followers
   */
  Set<Integer> getFollowers(int userID);

  /**
   * Returns all the individuals this user follows
   *
   * @param followerID the user whose followees we want to find
   * @return the given user's followees
   */
  Set<Integer> getFollowees(int followerID);

  /**
   * Returns the given user's list of tweets
   *
   * @param userID the given user
   * @return the given user's tweets
   */
  List<Tweet> getUsersTweets(int userID);

  /**
   * Returns the given user's timeline, which contains the 10 most recent tweets posted by all users
   * that this user follows.
   *
   * @param userID    the user whose timeline we will retrieve
   * @param broadcast to broadcast or not
   * @return the user's timeline.
   */
  List<Tweet> getTimeline(int userID, boolean broadcast);

  /**
   * Clears the entire database
   */
  void clear();

  /**
   * Connects to the given database
   *
   * @param url  the url of the database to connect to
   * @param user the user of the database
   * @param pw   the password for the database
   */
  void connect(String url, String user, String pw);

  /**
   * Closes the connection with the database
   */
  void close();

}
