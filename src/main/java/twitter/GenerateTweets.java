package twitter;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class GenerateTweets {

  /**
   * Generates a random string whose length is in the range of 0 to 140
   *
   * @param n the max length of the string to be generated
   * @return the random string
   */
  public String getRandomString(int n) {
    String acceptableChars = "abcdef ghijklmn opqrst uv";
    StringBuilder sb = new StringBuilder(n);
    Random rand = new Random();
    int tweetLength = rand.nextInt(n);
    for (int i = 0; i < tweetLength; i++) {
      // generate a random number between 0 to acceptableChars variable length
      int index = (int) ((int) (acceptableChars.length()) * Math.random());

      // add character one by one in end of sb
      sb.append(acceptableChars.charAt(index));
    }
    return sb.toString();
  }

  public Date getRandomDate() {
    long beginTime = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
    long endTime = Timestamp.valueOf("2020-05-20 00:00:00").getTime();
    long diff = endTime - beginTime + 1;
    long randTime = beginTime + (long) (Math.random() * diff);

    Date randomDate = new Date(randTime);

    return randomDate;
  }

  public int getRandomUser() {
    Random rand = new Random();
    int user = rand.nextInt(10000);
    return user;
  }

  /**
   * Creates a CSV file of tweets
   *
   * @param numEntries the desired number of entries
   * @param csvName    the name of the csv file generated
   * @throws IOException
   */
  public void createUserCSV(int numEntries, String csvName) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(csvName));
    for (int i = 0; i < numEntries + 1; i++) {
      int user = this.getRandomUser();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      Date date = this.getRandomDate();
      String strDate = sdf.format(date);
      String text = this.getRandomString(140) + 's';
      String line[] = {Integer.toString(user), strDate, text};
      // writing data to the csv file
      writer.writeNext(line);
    }
    writer.flush();
  }

}
