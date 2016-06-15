package com.naufal.twitterFitur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.naufal.util.io.FileUtils;

import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author ahmadluky
 */
public class GetLike {
    public static void main(String[] args) throws IOException, TwitterException, InterruptedException {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setJSONStoreEnabled(true)
          .setOAuthConsumerKey("Nr7dUL3PHHYvCNEPh3DS5a95W")
          .setOAuthConsumerSecret("6txZQKO27elUDG1ks3iVzb5PDlZbg1qnZGQMYrrZho6KYESyk8")
          .setOAuthAccessToken("58413351-T5liW3MibXGPjOVzafjQiP0cg3rqWOqPmcNmWguO1")
          .setOAuthAccessTokenSecret("J8Gtzkmvo0MWVUG8gWy599LzAIdx4kILpRkiEcktiQ2jP"); 
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        try(BufferedReader br = new BufferedReader(new FileReader("./data/data-akun.txt"))) {
			String akun;
			while ((akun = br.readLine()) != null) {
				TwitterResponse responseUser = twitter.showUser(akun);
				RateLimitStatus status = responseUser.getRateLimitStatus();
				System.out.println(status.toString());
				if(status.getRemaining() == 0) {
			        try {
			            Thread.sleep(status.getSecondsUntilReset() * 1000);
			        }
			        catch(InterruptedException e) {
			        	System.out.println(e);
			        }
			    }
				User user = twitter.showUser(akun);
				if (user.getStatus() != null) {
		            System.out.println("@" + user.getScreenName() +
		            				   "\n Description : "+ user.getDescription() +
		                               "\n Location : " + user.getLocation() +
		                               "\n Lang :" + user.getLang()  +
		                               "\n Flolower :" + user.getFollowersCount() +
		                               "\n Following :" + user.getFriendsCount() +
		                               "\n Likes/Favorited :" + user.getFavouritesCount() +
		                               "\n Image :"   + user.getBiggerProfileImageURLHttps()
		                              );
		            FileUtils.writefile(""+akun+"\t"+user.getFavouritesCount()+"", "./data/data-akun-like.txt");
			        Thread.sleep(1000);
		        } else {
		            System.out.println("Error : @" + user.getScreenName());
		        }
				
			}
		}
        
        
        
    }
}
