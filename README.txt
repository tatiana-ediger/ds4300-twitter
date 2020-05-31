The file twitter.sql creates the database twitter, and then creates the tables: tweet, follower.

In the attached src folder: 
- Going to main/java/twitter contains all the interfaces/implementations of the API
	- to run/execute the code and get the data on api calls/sec run main in TwitterTester.java
	- TwitterAPI.java is the main interface
	- MySQLTwitterAPI.java and RedisTwitterAPI.java are the two implementations
	- Tweet.java is a class to represent a tweet
	- CountTimelineReturns.java - code with function on how to count timeline returns
	- GenerateTweets.java - generates one million random tweets
	- GenerateUsers.java - generates ten thousand random users each of which have 0-100 followers
	- TimePostTweets.java - code with function on timing postTweets
	- LoadsUsers.java - loads users into respective API implementations

- Going to test/java/twitter contains all the tests used to test the implementations of the Twitter API

In the attached pom.xml file includes all dependencies to run this code.

README: this file
