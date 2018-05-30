package com.document.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.document.search.dto.SearchInformation;

import static com.document.search.DocumentHelperUtil.readDocumentContent;

public class PreprocessDocumentSearch implements SearchPattern {

	private static HashMap<String, HashMap<String, Integer>> documentDictionaryMap = null;

	/**
	 * 
	 * This method searches the user entered input 
	 * by index searching
	 */
	public List<SearchInformation> search(String searchString) throws IOException {
		List<SearchInformation> searchInformation = new ArrayList<SearchInformation>();
		for(Map.Entry<String, HashMap<String, Integer>> entry: documentDictionaryMap.entrySet()){
			String fileName = entry.getKey();
			Integer wordCount = entry.getValue().get(searchString) != null ? entry.getValue().get(searchString) : 0;
			searchInformation.add(new SearchInformation(fileName, wordCount));
		}
		return searchInformation;
		
	}
	
	/**
	 * This method reads all content in the document and saves as a key value pair in a hash map
	 * @throws IOException
	 */
	public static void preProcessDocumentContent() throws IOException{
		documentDictionaryMap = new HashMap<String, HashMap<String, Integer>>();
		for(String fileName: FILE_NAMES){
			StringBuilder documentContent = readDocumentContent(fileName);		
				HashMap<String, Integer> searchDictionary = new HashMap<String, Integer>();
				int totalCharactersInDocument = documentContent.toString().length();
				String key ="";
				
				for(int j=0;j<totalCharactersInDocument;j++){
					for(int i=0;i<totalCharactersInDocument;i++){
						if(i+j <= totalCharactersInDocument){
							key = documentContent.substring(i, i+j);
							
							if(searchDictionary.get(key) == null){
								searchDictionary.put(documentContent.toString().substring(i, i+j), 1);
							} else {
								searchDictionary.put(key, searchDictionary.get(key)+1);
							}
						}
					}
				}
				
					documentDictionaryMap.put(fileName, searchDictionary);		
		}	
		
	}
	
}
