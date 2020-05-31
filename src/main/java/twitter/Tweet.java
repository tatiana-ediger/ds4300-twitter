package twitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents an individual tweet. Each tweet has a tweetID, userID, timestamp and contents.
 */
public class Tweet {

  private int tweetID;
  private int userID;
  private Date timeStamp;
  private String tweet;

  /**
   * Constructs a Tweet the tweetID is generated incrementally in the database
   *
   * @param userID    the ID of the user who posted the tweet
   * @param timeStamp when the tweet was posted
   * @param tweet     the contents of the tweet
   */
  public Tweet(int userID, Date timeStamp, String tweet) {
    this.tweetID = -1;
    this.userID = userID;
    this.timeStamp = timeStamp;
    this.tweet = tweet;
  }

  /**
   * Constructs a tweet
   *
   * @param tweetID   when you want to specify the ID of the tweet
   * @param userID    the ID of the user who posted the tweet
   * @param timeStamp when the tweet was posted
   * @param tweet     the contents of the tweet
   */
  public Tweet(int tweetID, int userID, Date timeStamp, String tweet) {
    this.tweetID = tweetID;
    this.userID = userID;
    this.timeStamp = timeStamp;
    this.tweet = tweet;
  }

  /**
   * Generates a list of ~ 1 million randomly generated tweets to be read into the database
   *
   * @return the list of randomly generated tweets
   */
  public ArrayList<Tweet> tweetGenerator() {
    return null;
  }

  public int getTweetID() {
    return tweetID;
  }

  public void setTweetID(int tweetID) {
    this.tweetID = tweetID;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getTweet() {
    return tweet;
  }

  public void setTweet(String tweet) {
    this.tweet = tweet;
  }

  @Override
  public String toString() {
    return tweetID + ":" + userID + ":" + timeStamp.getTime() + ":"
        + tweet;  // 100:5:234876283764:Hello World
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    } else {
      Tweet objTweet = (Tweet) obj;
      return this.getTweetID() == objTweet.getTweetID() && this.getUserID() == objTweet
          .getTweetID();
    }
  }

  @Override
  public int hashCode() {
    return this.getTweetID();
  }
}
