package com.document.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import com.document.search.dto.SearchInformation;

public class PopulateTestData {
	
	
	public final static int TWO_MILLION = 2000000; 
	public final static String[] SearchStrings = {"file", "test", "Spanish", "test", "political", "social", 
													"Hitchhiker's", "Guide to the Galaxy is a comedy science fiction series created by Douglas Adams", 
													"fictional", "eccentric", "electronic travel guide", "HHGTTG", "Galaxy", "as used on fan websites", 
													"incarnations", "Warp", "hypothetical", "propulsion", "system in many works", "notably", "spacecraft", 
													"continue", "Legionnaires", "Franco-Prussian War", "18th", "Second", "something"
													};

	public static List<SearchInformation> populateSearchInformationList(){
		List<SearchInformation> searchInformationList = new ArrayList<SearchInformation>();
		SearchInformation searchInformation = new SearchInformation("File1.txt",5);
		SearchInformation searchInformation2 = new SearchInformation("File2.txt",15);
		SearchInformation searchInformation3 = new SearchInformation("File3.txt",10);
		searchInformationList.add(searchInformation);
		searchInformationList.add(searchInformation2);
		searchInformationList.add(searchInformation3);
		return searchInformationList;
	}
	
	public static String[] populateArrayWith2MillionSearchStrings(){
		String[] randomStrings = new String[TWO_MILLION];
		Random rand = new Random();
		for(int i=0;i<TWO_MILLION;i++){
			int randomNumber = rand.nextInt(50);
			if(randomNumber > 25){
				randomStrings[i] = RandomStringUtils.randomAlphabetic(10);
			} else {
				randomStrings[i] = SearchStrings[randomNumber];
			}
		}
		return randomStrings;
	}
}
