package com.naufal.selecting_key_twitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.naufal.util.io.FileUtils;

/**
 * 
 * @author ahmadluky
 * komunikasi eksternal = popularity + tff
 */
public class Exsternal_communication {
	public static String FILE_FOLLOWER_FOLLOWING = "./data-in/comunication_exsternal/follower-following-unique.txt";
	public static String FILE_FOLLOWER_FOLLOWING_OUT = "./data-out/comunication_exsternal/follower-following-unique.txt";
	
    public static Multimap<String, Double> myMultimapPopulatiry = ArrayListMultimap.create();
    public static Multimap<String, Double> myMultimapTff = ArrayListMultimap.create();
    public static Multimap<String, Double> rstPopularity;
	public static  Multimap<String, Double> rstTff;
	
	public static Multimap<String, Double> readPopularity(Multimap<String, Double> myMultimapP) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_FOLLOWER_FOLLOWING))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				//calculation popularity | "1- " "e" ^"-λ.F1"  dengan λ=1
		        String[] s= sCurrentLine.split(",");
		        Integer d = Integer.parseInt(s[1])+Integer.parseInt(s[2]);
		        Double popularityValue;
		        if (d==0) {
			        popularityValue = 0.0;
				}else{
			        popularityValue =  Double.parseDouble(s[1])/d.doubleValue();
				}
		        // System.out.println("popularity : \t"+s[0]+"\t"+s[1]+"\t"+s[2]+"\t"+popularityValue+"");
		 	    myMultimapP.put(s[0], popularityValue);
			}
		    return myMultimapP;
		}
	}
	public static void popularity() throws FileNotFoundException, IOException{
		rstPopularity = readPopularity(myMultimapPopulatiry);
	}
	
	public static Multimap<String, Double> readTff(Multimap<String, Double> myMultimapT) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(FILE_FOLLOWER_FOLLOWING))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				//calculation popularity Nagmoti
		        String[] s= sCurrentLine.split(",");
		        Double tff_r;
		        if (Integer.parseInt(s[2])==0) {
		        	tff_r=0.0;
				}else{
					tff_r=Double.parseDouble(s[1])/Double.parseDouble(s[2]);
				}
		        myMultimapT.put(s[0], tff_r);
			}
		    return myMultimapT;
		}
	}
	public static void tff() throws IOException{
		rstTff = readTff(myMultimapTff);
	}
	
	public static void main(String[] args) throws IOException{
        System.out.println("Starting Job Exsternal Comunication");
        long startTime = new Date().getTime();
        
		// - praprosesing -
		popularity();
		tff();
		
		// - claculation -
		for(String key : rstPopularity.keySet()) {
			Collection<Double> valuePopularity = myMultimapPopulatiry.get(key);
			Collection<Double> valueTff = myMultimapTff.get(key);
			Double co_exs;
			if (valueTff.iterator().next()==0.0) {
				co_exs = 0.0;
			}else{
				co_exs = (valuePopularity.iterator().next()+ valueTff.iterator().next())/2;
			}
	        //System.out.println("Eksternal Comunication : "+key+"\t"+valuePopularity.iterator().next()+"\t"+valueTff.iterator().next()+"\t"+co_exs+"");
			FileUtils.writefile(""+key+"\t"+co_exs+"", FILE_FOLLOWER_FOLLOWING_OUT);
		}
		
        final double duration = (System.currentTimeMillis() - startTime)/1000.0;
        System.out.println("Job Exsternal Comunication Finished in " + duration + " seconds");
	}
}
