package twitter;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Loads users from users-table.csv using the given API.
 */
public class LoadsUsers {

  private TwitterAPI api;

  /**
   * Creates an instance of a LoadUsers with the given api.
   *
   * @param api the given api to use.
   */
  LoadsUsers(TwitterAPI api) {
    this.api = api;
  }

  /**
   * Loads the users generated randomly using the given API.
   *
   * @throws IOException
   */
  public void loadUsers() throws IOException {
    try (
        Reader reader = Files.newBufferedReader(Paths.get("users-table.csv"));
        CSVReader csvReader = new CSVReader(reader);
    ) {
      // Reading records one by one in a string array
      String[] nextRecord;
      while ((nextRecord = csvReader.readNext()) != null) {
        int userID = Integer.parseInt(nextRecord[0]);
        int followerID = Integer.parseInt(nextRecord[1]);
        api.addFollower(userID, followerID);
      }
    }
  }
}
