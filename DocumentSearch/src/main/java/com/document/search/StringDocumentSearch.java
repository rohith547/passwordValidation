package com.document.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.document.search.dto.SearchInformation;
import static com.document.search.DocumentHelperUtil.printSearchMatches;
import static com.document.search.DocumentHelperUtil.readDocumentContent;

public class StringDocumentSearch implements SearchPattern{

	/**
	 * This method serves user to search a input string in a given set of documents 
	 * by using string search
	 */
	public List<SearchInformation> search(String searchString) throws IOException {
	
		List<SearchInformation> searchInformation = new ArrayList<SearchInformation>();		
		for(String fileName: FILE_NAMES){
				StringBuilder documentContent = readDocumentContent(fileName);
				Integer wordCount = getWordCount(documentContent,searchString);
				searchInformation.add(new SearchInformation(fileName,wordCount));
		}
		return searchInformation;
	}
	
	/**
	 * This method returns number of times the word appeared in the document
	 * @param documentContent content of the document
	 * @param searchString string that needs to be searched
	 * @return
	 */
	private static Integer getWordCount(StringBuilder documentContent,String searchString){
		int lengthOfSearchWord = searchString.length();
		int wordCount = 0;
		int totalCharactersInDocument = documentContent.toString().length();
		
		for(int i=0; i< totalCharactersInDocument; i++){
			if(i+lengthOfSearchWord <= totalCharactersInDocument){
				int count = 0;
				for(int j=i;j<i+lengthOfSearchWord;j++){
					if(documentContent.charAt(j) == searchString.charAt(count)){
						count++;
						if(count == lengthOfSearchWord){
							wordCount++;
						}
					}else{
						break;
					}
				}
			}
		}
		
		return wordCount;
	}

}
