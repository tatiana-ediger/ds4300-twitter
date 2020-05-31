{\rtf1\ansi\ansicpg1252\cocoartf1671
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww13540\viewh9140\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 README:\
\
The file twitter.sql creates the database twitter, and then creates the tables: tweet, follower.\
\
In the attached src folder: \
- Going to main/java/twitter contains all the interfaces/implementations of the API\
	- to run/execute the code and get the data on api calls/sec run main in TwitterTester.java\
	- TwitterAPI.java is the main interface\
	- MySQLTwitterAPI.java and RedisTwitterAPI.java are the two implementations\
	- Tweet.java is a class to represent a tweet\
	- CountTimelineReturns.java - code with function on how to count timeline returns\
	- GenerateTweets.java - generates one million random tweets\
	- GenerateUsers.java - generates ten thousand random users each of which have 0-100 followers\
	- TimePostTweets.java - code with function on timing postTweets\
	- LoadsUsers.java - loads users into respective API implementations\
\
- Going to test/java/twitter contains all the tests used to test the implementations of the Twitter API\
\
In the attached pom.xml file includes all dependencies to run this code.\
\
README: this file}
