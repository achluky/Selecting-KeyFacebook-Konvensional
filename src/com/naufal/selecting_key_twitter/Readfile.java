package com.naufal.selecting_key_twitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Readfile {
	
	public BufferedReader read(String name_file){
		try (BufferedReader br = new BufferedReader(new FileReader("./data-in/"+name_file)))
		{
			return br;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
