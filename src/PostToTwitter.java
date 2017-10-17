//Stejara Dinulescu
//Posts to Twitter based on given string


import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

 

import twitter4j.*;

import twitter4j.auth.AccessToken;

import twitter4j.auth.RequestToken;

import twitter4j.conf.ConfigurationBuilder;

public class PostToTwitter {
	PostToTwitter() {
	}
	
	public void updateTwitter(String update_str) {
	
		try {
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
	
			cb.setDebugEnabled(true).setOAuthConsumerKey("JeS1CsvigISxw40AtNMlBacGY")
	
			.setOAuthConsumerSecret("Ate7NiBQ6MbwiJrLQpeJSG7GjTkG9W2KECov55WJbLRlGASMBA")
	
			.setOAuthAccessToken("912350195576446978-WhGsHxu73YslkVb7vSIu7UchUAnWwPS")
	
			.setOAuthAccessTokenSecret("s3vwFPssWqdddcjbkluzJGMMQuNPn3h59muo2By2I1Ry1");
	
			TwitterFactory tf = new TwitterFactory(cb.build());
	
			Twitter twitter = tf.getInstance();
	
	 
	
			Status status = twitter.updateStatus(update_str);
	
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
	
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read the system input.");
		}
	
	} 

}