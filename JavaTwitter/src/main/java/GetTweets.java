import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GetTweets {

  public static void main(String[] args) {

    Twitter twitter = new TwitterFactory().getInstance();
    List<Status> allTweets = new ArrayList<Status>();
    Paging p = new Paging();
    p.setCount(3000); //get 3000 tweets from each user

    try {
      allTweets.addAll(twitter.getUserTimeline("ElonMusk", p));
      allTweets.addAll(twitter.getUserTimeline("KanyeWest", p));

    } catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to get timeline: " + te.getMessage());
      System.exit(-1);
    }

    System.out.println("Welcome to Tweet Guesser.\n");

    boolean exit = false;
    Scanner scnr = new Scanner(System.in);
    int allTweetsSize = allTweets.size();
    Random random = new Random();
    int numCorrect = 0;
    int numWrong = 0;

    // basic game loop
    while (!exit) {

      int randomIndex = random.nextInt(allTweetsSize);
      Status tweet = allTweets.get(randomIndex);

      System.out.println("\nWho tweeted this?\n");
      System.out.println(tweet.getText() + "\n");

      System.out.print("Enter guess (kanyewest/elonmusk): ");
      String userGuess = scnr.nextLine();
      if (userGuess.toLowerCase().equals(tweet.getUser().getScreenName())) {
        System.out.println("Correct!");
        numCorrect++;
      } else {
        System.out.println("Wrong :(");
        numWrong++;
      }

      System.out.print("play again (y/n)? ");
      String again = scnr.nextLine();
      if (again.toLowerCase().equals("n")) {
        exit = true;
        System.out.println("\nGAME STATS:");
        System.out.println("Correct guesses: " + numCorrect);
        System.out.println("Wrong guesses: " + numWrong);
        System.out.println("Total guesses: " + (numCorrect + numWrong));
      }
    }
  }
}
