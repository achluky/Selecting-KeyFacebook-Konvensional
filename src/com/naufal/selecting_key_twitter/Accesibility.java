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
 * = following + reply + retweet + like + mention tweet
 */
public class Accesibility {
	public static String FILE_MENTION = "./data-in/accessibility/mention/data.txt";
    public static Multimap<String, Integer> myMultimapMention = ArrayListMultimap.create();
    public static Multimap<String, Integer> rstMention;
    public static Multimap<String, Integer> rstMention_= ArrayListMultimap.create();
	public static String FILE_REPLY = "./data-in/accessibility/reply/data.txt";
    public static Multimap<String, Integer> myMultimaReply = ArrayListMultimap.create();
    public static Multimap<String, Integer> rstReply;
    public static Multimap<String, Integer> rstReply_= ArrayListMultimap.create();
	public static String FILE_RETWEET = "./data-in/accessibility/retweet/data.txt";
    public static Multimap<String, Integer> myMultimaRetweet = ArrayListMultimap.create();
    public static Multimap<String, Integer> rstRetweet;
    public static Multimap<String, Integer> rstRetweet_= ArrayListMultimap.create();
	public static String FILE_LIKE = "./data-in/accessibility/like/data.txt";
    public static Multimap<String, Integer> myMultimaLike = ArrayListMultimap.create();
    public static Multimap<String, Integer> rstLike;
	public static String FILE_FOLLOWING = "./data-in/accessibility/following/follower-unique.txt";
    public static Multimap<String, Integer> myMultimaFollowing = ArrayListMultimap.create();
    public static Multimap<String, Integer> rstFollowing;
    
	// mention
    public static Multimap<String, Integer> readMention(Multimap<String, Integer> myMultimapP) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_MENTION))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
		        String[] s= sCurrentLine.split(";");
		        if (s.length>1) {
		        	String[] x = s[1].split(",");
			 	    myMultimapP.put(s[0], x.length);
				}
			}
		    return myMultimapP;
		}
	}
	public static void mention() throws FileNotFoundException, IOException{
		rstMention = readMention(myMultimapMention);
		for(String key : rstMention.keySet()) {
			Collection<Integer> valueMention= myMultimapMention.get(key);
	        Integer sum=0;
	        for (Iterator<Integer> iterator = valueMention.iterator(); iterator.hasNext();) {
				sum += (Integer) iterator.next();
			}
	        FileUtils.writefile(""+key+"\t"+sum+"", "./data-out/accessibility/mention/data.txt");
	        //System.out.println("Mention : "+key+"\t"+valueMention.toString()+"\t"+sum);
	        rstMention_.put(key, sum);
		}
	}
	
	// reply
	public static Multimap<String, Integer> readReply(Multimap<String, Integer> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_REPLY))) {
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
	public static void reply() throws FileNotFoundException, IOException{
		rstReply = readReply(myMultimaReply);
		for(String key : rstReply.keySet()) {
			Collection<Integer> valueReply = myMultimaReply.get(key);
			Integer sum=0;
	        for (Iterator<Integer> iterator = valueReply.iterator(); iterator.hasNext();) {
				sum += (Integer) iterator.next();
			}
	        //System.out.println("reply : "+key+"\t"+sum+"\t"+valueReply.toString());
			FileUtils.writefile(""+key+"\t"+sum+"", "./data-out/accessibility/reply/data.txt");
	        rstReply_.put(key, sum);
		}
	}
	
	//meretweet
	public static Multimap<String, Integer> readRetweet(Multimap<String, Integer> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_RETWEET))) {
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
	public static void retweet() throws FileNotFoundException, IOException{
		rstRetweet = readRetweet(myMultimaRetweet);
		for(String key : rstRetweet.keySet()) {
			Collection<Integer> valueRetweet = myMultimaRetweet.get(key);
			Integer sum=0;
	        for (Iterator<Integer> iterator = valueRetweet.iterator(); iterator.hasNext();) {
				sum += (Integer) iterator.next();
			}
	        //System.out.println("retweet : "+key+"\t"+sum+"");
			FileUtils.writefile(""+key+"\t"+sum+"", "./data-out/accessibility/retweet/data.txt");
			rstRetweet_.put(key, sum);
		}
	}
	
	//like
	public static Multimap<String, Integer> readLike(Multimap<String, Integer> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_LIKE))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
		        String[] s= sCurrentLine.split(";");
			    myMultimap.put(s[0], Integer.parseInt(s[1]));
		        FileUtils.writefile(""+s[0]+"\t"+Integer.parseInt(s[1])+"", "./data-out/accessibility/like/data.txt");
			}
		    return myMultimap;
		}
	}
	public static void like() throws FileNotFoundException, IOException{
		rstLike = readLike(myMultimaLike);
	}
	
	//following
	public static Multimap<String, Integer> readFollowing(Multimap<String, Integer> myMultimap) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_FOLLOWING))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
		        String[] s= sCurrentLine.split(";");
			    myMultimap.put(s[0], Integer.parseInt(s[1]));
		        FileUtils.writefile(""+s[0]+"\t"+Integer.parseInt(s[1])+"", "./data-out/accessibility/following/data.txt");
			}
		    return myMultimap;
		}
	}
	public static void following() throws FileNotFoundException, IOException{
		rstFollowing = readFollowing(myMultimaFollowing);
	}
	
	public static void main(String[] args) throws IOException{
        System.out.println("Starting Job Exsternal Comunication");
        long startTime = new Date().getTime();
        
		//--praprosesing
		mention();
		reply();
		retweet();
        like();
        following();
        
        //--calclulation
        int i=1;
		for(String key : rstFollowing.keySet()) {
			Collection<Integer> valueMention = rstMention_.get(key);
			Collection<Integer> valueReply= rstReply_.get(key);
			Collection<Integer> valueRetweet= rstRetweet_.get(key);
			Collection<Integer> valueLike= rstLike.get(key);
			Collection<Integer> valueFollowiing= rstFollowing.get(key);
			System.out.println(i++ +"\t"+key+"");
			int mention_;
			if (valueMention.size()==0) 
				mention_ = 0;
			else
				mention_ =  valueMention.iterator().next();
			
			int reply_;
			if (valueReply.size()==0) 
				reply_ = 0;
			else
				reply_ =  valueReply.iterator().next();
			
			int retweet_;
			if (valueRetweet.size()==0) 
				retweet_ = 0;
			else
				retweet_ =  valueRetweet.iterator().next();

			int like_;
			if (valueLike.size()==0) 
				like_ = 0;
			else
				like_ =  valueLike.iterator().next();
			
			Double accsesibility = (double) ((valueFollowiing.iterator().next()+
												reply_+
												retweet_+
												like_+
												mention_)/5);
			System.out.println(""+key+"\t"+accsesibility+"");
	        FileUtils.writefile(""+key+"\t"+accsesibility+"", "./data-out/accessibility/result/data.txt");
		}
		
	    final double duration = (System.currentTimeMillis() - startTime)/1000.0;
	    System.out.println("Job Exsternal Comunication Finished in " + duration + " seconds");
			
	}
}
