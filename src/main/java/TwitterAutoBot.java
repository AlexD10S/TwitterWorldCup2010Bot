import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
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
            if(isToday(listOfMatches.get(i))) {
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

    private static boolean isToday(Match match){
        boolean isToday = false;

        LocalDateTime now = LocalDateTime.now();

        String monthToday = convertToMonth(now.getMonthValue());
        String dayToday = String.valueOf(now.getDayOfMonth());
        String monthMatch = match.getDateMatch().split("/")[0];
        String dayMatch = match.getDateMatch().split("/")[1];

        if(dayToday.equals(dayMatch) && monthToday.equals(monthMatch)){
            isToday = true;
        }
        return isToday;
    }

    private static String convertToMonth(int month){
        if(month == 6){
            return "Jun";
        }
        else if(month == 7){
            return "Jul";
        }
        else {
            //Never happens in out example
            return "Jul";
        }
    }
}
