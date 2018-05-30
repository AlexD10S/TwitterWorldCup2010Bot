import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class TwitterAutoBot {
    private static ArrayList<Match> listOfMatches = new ArrayList<Match>();

    public static void main(String[] args) {
        String file = "/Users/alex/Documents/Projects/TwitterBotWorldCup/src/main/resources/tweets.txt";
        listOfMatches = Parser.getListOfMatches(file);

        sendAllTweets();

        //tweetLines();

    }

    private static void sendAllTweets() {
        String line;
        for(int i=0;i<listOfMatches.size();i++) {
            sendTweet(listOfMatches.get(i).tweetMatch());
            try {
                System.out.println("Sleeping for 30 minutes...");
                //Thread.sleep(1800000); // every 30 minutes
                Thread.sleep(180000); // every 3 minutes
                // Thread.sleep(10000); // every 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //TODO: Send just the tweet corresponding to the current day/
    private static void sendJustDaulyTweets(){
        
    }

    private static void sendTweet(String line) {
        Twitter twitter = TwitterFactory.getSingleton();
        Status status;
        try {
            status = twitter.updateStatus(line);
            System.out.println(status);
        } catch (TwitterException e) {;
            e.printStackTrace();
        }
    }
}
