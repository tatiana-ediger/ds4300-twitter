drop database if exists twitter;
create database twitter;
use twitter;

-- tweets posted by users
drop table if exists tweet;
create table tweet
(
    tweet_id   int primary key auto_increment,
    user_id    int,
    tweet_ts   datetime,
    tweet_text varchar(140)
);

create index user_id on tweet(user_id);

-- who follows whom
-- the user 'follower_id' follows the user 'user_id'
drop table if exists follower;
create table follower (
    user_id int,
    follower_id int
);

