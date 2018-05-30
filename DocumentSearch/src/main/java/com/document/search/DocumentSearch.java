package com.document.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.Messaging.SyncScopeHelper;

import static com.document.search.PreprocessDocumentSearch.preProcessDocumentContent;
import static com.document.search.DocumentHelperUtil.printSearchMatches;

/**
 * This class serves as an entry point that takes input (search string) from
 * the user and searches the entered string in a set of documents
 * then returns all the matches of the string
 *
 */
public class DocumentSearch 
{
	private static String searchString;
	private static int searchMethod;
	
	/**
	 * This method prompts user to enter input 
	 * and search file contents through one of the search patterns
	 * @param args
	 * @throws IOException
	 */
    public static void main( String[] args ) throws IOException
    {
    	 
    	preProcessDocumentContent();
    	long maxMemory = Runtime.getRuntime().maxMemory();
    			System.out.println(maxMemory);
		boolean repeatSearch = true;
		
		do{
			getSearchPhraseAndMethodFromUser();
			
			switch(searchMethod){
			case 1:{
				SearchPattern sp = new StringDocumentSearch();
				printSearchMatches(sp.search(searchString));
				break;
			}
			case 2:{
				SearchPattern sp = new RegexDocumentSearch();
				printSearchMatches(sp.search(searchString));
				break;
			}
			case 3:{
				SearchPattern sp = new PreprocessDocumentSearch();
				printSearchMatches(sp.search(searchString));
				break;
			}
			default:{
				repeatSearch = false;
				System.out.println("please check the input");
			}
			}
			
		}while(repeatSearch);
    }
    
    /**
     * This method read the input entered by the user
     * @throws IOException
     */
    public static void getSearchPhraseAndMethodFromUser() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Search term: ");
		searchString = reader.readLine();
		System.out.println("Search Method \n 1) String Match 2) Regular Expression 3) Indexed ");
		searchMethod = Integer.parseInt(reader.readLine());
	}
}
