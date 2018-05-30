package com.document.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.document.search.dto.SearchInformation;
import com.document.sort.WordRelevanceComparator;

public class DocumentHelperUtil {
	
	static final String FILE_LOCATION_FOLDER = "documents//";
	
	/**
	 * 
	 * @param fileName name of the file that needs to be read
	 * @return content of the file
	 * @throws IOException
	 */
	
	public static StringBuilder readDocumentContent(String fileName) throws IOException{
		String line = "";
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;
		try{
			bufferedReader = new BufferedReader(new FileReader(FILE_LOCATION_FOLDER+fileName));
			while((line = bufferedReader.readLine()) != null){
				sb.append(line);
			}
		}finally{
			bufferedReader.close();
		}
		return sb;
	}
	
	/**
	 * This method prints the file information on the console
	 * @param fileInformation that needs to be printed
	 */
	public static void printSearchMatches(List<SearchInformation> fileInformation){
		Collections.sort(fileInformation,new WordRelevanceComparator());
		for(SearchInformation fileDetails : fileInformation){
			System.out.println(fileDetails.getFileName() + " - " + fileDetails.getWordCount() + " Matches");
		}
	}
}
