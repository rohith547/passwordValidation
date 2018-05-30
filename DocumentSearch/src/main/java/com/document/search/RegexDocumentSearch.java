package com.document.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.document.search.dto.SearchInformation;
import static com.document.search.DocumentHelperUtil.printSearchMatches;
import static com.document.search.DocumentHelperUtil.readDocumentContent;

public class RegexDocumentSearch implements SearchPattern{

	/**
	 * This method serves user to search a string in a given set of documents 
	 * by using regex patterns
	 */
	public List<SearchInformation> search(String regex) throws IOException {
	
		List<SearchInformation> searchInformation = new ArrayList<SearchInformation>();
		
		for(String fileName: FILE_NAMES){
				StringBuilder documentContent = readDocumentContent(fileName);
				Integer wordCount = getWordCount(documentContent,regex);
				searchInformation.add(new SearchInformation(fileName,wordCount));		
		}
			return searchInformation;
	
	}
	
	/**
	 * 
	 * This method returns number of times the word appeared in the document
	 * @param documentContent content of the document
	 * @param regex regular expression pattern that needs to be searched
	 * @return
	 */
	private static Integer getWordCount(StringBuilder documentContent,String regex){
		int wordCount = 0;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(documentContent.toString());
		
		while(matcher.find()){
			wordCount++;
		}
		return wordCount;
	}

}
