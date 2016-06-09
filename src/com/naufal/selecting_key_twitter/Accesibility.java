package com.naufal.selecting_key_twitter;

import java.io.BufferedReader;
import java.io.IOException;

import com.naufal.selecting_key_twitter.Readfile;
/**
 * @author ahmadluky
 * = following + reply + retweet + like + mention tweet
 */
public class Accesibility {
	
	public static void main(String[] args) throws IOException{
		Readfile read = new Readfile();
		BufferedReader br = read.read("accessibility/mention/data.txt");
		String rString;
		while ((rString = br.readLine()) != null) {
			System.out.println(rString);
		}
	}
}
