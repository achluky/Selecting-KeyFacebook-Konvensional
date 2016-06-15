package com.naufal.selecting_key_twitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import com.naufal.util.io.*;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

/**
 * @author ahmadluky
 * = following + reply + retweet + like + mention tweet
 */
public class Accesibility {
	// mention
	public static String read() throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader("./data-in/accessibility/mention/data.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		}
	}
	// reply
	public static Multimap<String, String> read_reply(Multimap<String, String> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader("./data-in/accessibility/reply/data.txt"))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
		        String[] s= sCurrentLine.split(";");
		        myMultimap.put(s[0], s[1]);
			}
		    return myMultimap;
		}
	}
	//meretweet
	public static Multimap<String, String> read_retweet(Multimap<String, String> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader("./data-in/accessibility/retweet/data.txt"))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
		        String[] s= sCurrentLine.split(";");
		        myMultimap.put(s[0], s[1]);
			}
		    return myMultimap;
		}
	}
	public static void main(String[] args) throws IOException{
		//mention
        System.out.println("Starting Job Mention");
        long startTime = new Date().getTime();
	    Iterable<String> words = Splitter.on(",").trimResults().split(read());
	    Multiset<String> wordsMultiset = HashMultiset.create();
	    for (String string : words) {   
	        wordsMultiset.add(string.toLowerCase());
	    }
	    Set<String> result = wordsMultiset.elementSet();
	    for (String string : result) {
	        FileUtils.writefile(""+string+" \t "+wordsMultiset.count(string)+"", "./data-out/accessibility/mention/data.txt");
	    }
        final double duration = (System.currentTimeMillis() - startTime)/1000.0;
        System.out.println("Job mention Finished in " + duration + " seconds");

        //reply
        System.out.println("Starting Job Reply");
        long startTimeReply = new Date().getTime();
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        Multimap<String, String> rst = read_reply(myMultimap);
        for(String key : rst.keySet()) {
        	Collection<String> r = myMultimap.get(key);
            FileUtils.writefile(""+key+"\t"+r.size()+"", "./data-out/accessibility/reply/data.txt");
        }
        final double durationReply = (System.currentTimeMillis() - startTimeReply)/1000.0;
        System.out.println("Job reply Finished in " + durationReply + " seconds");
        
        // meretweet
        System.out.println("Starting Job meretweet");
        long startTimeMeretweet = new Date().getTime();
        Multimap<String, String> myMultimapMeretweet = ArrayListMultimap.create();
        Multimap<String, String> rstMeretweet = read_retweet(myMultimapMeretweet);
        for(String key : rstMeretweet.keySet()) {
        	Collection<String> r = myMultimapMeretweet.get(key);
            FileUtils.writefile(""+key+"\t"+r.size()+"", "./data-out/accessibility/retweet/data.txt");
        }
        final double durationMeretweet = (System.currentTimeMillis() - startTimeMeretweet)/1000.0;
        System.out.println("Job meretweet Finished in " + durationMeretweet + " seconds");
        
        // like
        
        // following
        
	}
}
