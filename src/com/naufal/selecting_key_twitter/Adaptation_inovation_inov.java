package com.naufal.selecting_key_twitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.naufal.util.io.FileUtils;

/**
 * @author ahmadluky
 * formulasi : Inovasi = tweet + retweet_per_tweet
 */
public class Adaptation_inovation_inov {
	public static String FILE_TWEET = "./data-in/adaptation_innovation/tweet/data.txt";
    public static Multimap<String, Integer> tweet = ArrayListMultimap.create();
    public static Multimap<String, Integer> rstTweet;
    public static Multimap<String, Integer> rstTweet_= ArrayListMultimap.create();
	public static String FILE_RETWEET = "./data-in/adaptation_innovation/retweet/data_new.txt";
    public static Multimap<String, Integer> retweet = ArrayListMultimap.create();
    public static Multimap<String, Integer> rstRetweet;
    public static Multimap<String, Integer> rstRetweet_= ArrayListMultimap.create();
	public static Multimap<String, Integer> readTweet(Multimap<String, Integer> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_TWEET))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
		        String[] s= sCurrentLine.split(";");
		        if (s[1].equals("TRUE")) {
			        myMultimap.put(s[0], 1);
				}else{
					myMultimap.put(s[0], 0);
				}
			}
		    return myMultimap;
		}
	}
	public static void tweet() throws FileNotFoundException, IOException{
		rstTweet = readTweet(tweet);
		for(String key : rstTweet.keySet()) {
			Collection<Integer> valueTweet = tweet.get(key);
			Integer sum=0;
	        for (Iterator<Integer> iterator = valueTweet.iterator(); iterator.hasNext();) {
				sum += (Integer) iterator.next();
			}
	        rstTweet.put(key, sum);
		}
	}
	public static Multimap<String, Integer> readRetweet(Multimap<String, Integer> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_RETWEET))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
					sCurrentLine = sCurrentLine.replace("@", "");
					myMultimap.put(sCurrentLine, 1);
			}
		    return myMultimap;
		}
	}
	public static void retweet() throws FileNotFoundException, IOException{
		rstRetweet = readRetweet(retweet);
		for(String key : rstRetweet.keySet()) {
			Collection<Integer> valueTweet = rstRetweet.get(key);
			Integer sum=0;
	        for (Iterator<Integer> iterator = valueTweet.iterator(); iterator.hasNext();) {
				sum += (Integer) iterator.next();
			}
	        rstRetweet.put(key, sum);
		}
	}
	public static void main(String[] args) throws IOException{
        System.out.println("Starting Job Adaptasi Inovasi - Inovasi");
        long startTime = new Date().getTime();
        tweet();
        retweet();
        int i =0;
        for(String key : rstTweet.keySet()) 
		{
			Collection<Integer> valueTweet= rstTweet.get(key);
			Collection<Integer> valueRetweet= rstRetweet.get(key);
			int tweet_count = 0;
			if (valueTweet.size()==0) {
				tweet_count = 0;
			} else {
				@SuppressWarnings("rawtypes")
				Iterator v = valueTweet.iterator();
				while(v.hasNext()){
					tweet_count = tweet_count + (int) v.next();
				}
			}
			int retweet_count = 0;
			if (valueRetweet.size()==0) {
				retweet_count = 0;
			} else {
				@SuppressWarnings("rawtypes")
				Iterator rv = valueRetweet.iterator();
				while(rv.hasNext()){
					retweet_count = retweet_count + (int) rv.next();
				}
			}
			Double adaptation_inovation_inov = (double) (tweet_count + retweet_count);
			FileUtils.writefile(""+key+"\t"+adaptation_inovation_inov+"", "./data-out/adaptation_innovation/result/data-2.txt");
	        System.out.println(i++ +"\t"+key+": \t"+adaptation_inovation_inov);
		}
	    final double duration = (System.currentTimeMillis() - startTime)/1000.0;
	    System.out.println("Job Exsternal Comunication Finished in " + duration + " seconds");
			
	}
}