package twitter;

import java.io.FileWriter;
import com.opencsv.CSVWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 * Generates the user's CSV
 */
public class GenerateUsers {

  private ArrayList<Integer> userIDs = new ArrayList<>();

  /**
   * Creates an instance of GenerateUsers.
   */
  public GenerateUsers() {
    for (int i = 0; i < 10000; i++) {
      userIDs.add(i);
    }
    Collections.shuffle(userIDs);
  }

  /**
   * Generates users and their follows randomly and writes to a CSV.
   *
   * @throws IOException
   */
  public void createUserCSV() throws IOException {
    // Instantiating CSVWriter class
    CSVWriter writer = new CSVWriter(new FileWriter("users-table.csv"));
    Random rand = new Random();

    for (Integer user : userIDs) {
      int numberFollowers = rand.nextInt(100);
      HashSet<Integer> followersSoFar = new HashSet<>();

      for (int i = 0; i < numberFollowers; i++) {
        int followerIndex = rand.nextInt(10000);
        int follower = this.userIDs.get(followerIndex);
        if (follower != user && !followersSoFar.contains(follower)) {
          followersSoFar.add(follower);
          String line[] = {Integer.toString(user), Integer.toString(follower)};
          // writing data to the csv file
          writer.writeNext(line);
        }
      }
    }

    writer.flush();
  }


}