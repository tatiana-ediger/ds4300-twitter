package twitter;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Represents an implementation of a Twitter API using MySQL (relational database) Connects to a
 * JDBC and carries out SQL commands
 */
public class MySQLTwitterAPI implements TwitterAPI {

  private Connection con;
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  @Override
  public void postTweet(Tweet t, boolean broadcast) {
    String sql =
        "INSERT INTO tweet (user_id, tweet_ts, tweet_text) VALUES (" + t.getUserID() + ",'" + sdf
            .format(t.getTimeStamp()) + "','" + t.getTweet() + "')";
    // The desired Date Format: 'yyyy-mm-dd hh:mm:ss'
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addFollower(int userID, int followerID) {

    String sql = "INSERT INTO follower VALUES (" + userID + "," + followerID + ")";

    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Set<Integer> getFollowers(int userID) {
    String sql = "SELECT follower_id FROM follower WHERE user_id=" + userID;
    Set<Integer> rslt = new HashSet<Integer>();

    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int followerID = rs.getInt("follower_id");
        rslt.add(followerID);
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rslt;
  }

  @Override
  public Set<Integer> getFollowees(int followerID) {
    String sql = "SELECT user_id FROM follower WHERE follower_id=" + followerID;
    Set<Integer> rslt = new HashSet<Integer>();

    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int userID = rs.getInt("user_id");
        rslt.add(userID);
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rslt;
  }

  @Override
  public List<Tweet> getUsersTweets(int userID) {
    String sql =
        "SELECT tweet_id, user_id, tweet_ts, tweet_text FROM tweet WHERE user_id=" + userID;
    List<Tweet> rslt = new ArrayList<>();

    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int tweetID = rs.getInt("tweet_id");
        int uID = rs.getInt("user_id");
        Date date = rs.getDate("tweet_ts");
        String text = rs.getString("tweet_text");
        Tweet t = new Tweet(tweetID, uID, date, text);
        rslt.add(t);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rslt;
  }

  @Override
  public List<Tweet> getTimeline(int userID, boolean broadcast) {
    String sql = "SELECT tweet_id, tweet.user_id, tweet_ts, tweet_text "
        + "FROM tweet JOIN follower ON tweet.user_id = follower.user_id "
        + "WHERE follower_id = " + userID + " ORDER BY tweet.tweet_ts DESC LIMIT 10";
    List<Tweet> rslt = new ArrayList<>();

    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int tweetID = rs.getInt("tweet_id");
        int uID = rs.getInt("user_id");
        Date date = rs.getDate("tweet_ts");
        String text = rs.getString("tweet_text");
        Tweet t = new Tweet(tweetID, uID, date, text);
        rslt.add(t);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rslt;
  }


  @Override
  public void clear() {
    String clearTweets = "TRUNCATE tweet";
    String clearFollowers = "TRUNCATE follower";

    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(clearTweets);
      stmt.executeUpdate(clearFollowers);
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void connect(String url, String user, String pw) {
    if (con == null) {
      try {
        con = DriverManager.getConnection(url, user, pw);
      } catch (SQLException e) {
        System.err.println(e.getMessage());
        System.exit(1);
      }
    }
  }


  @Override
  public void close() {
    try {
      con.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }

}
