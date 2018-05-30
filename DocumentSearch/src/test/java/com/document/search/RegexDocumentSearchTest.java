package com.document.search;

import static com.document.search.PopulateTestData.TWO_MILLION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.document.search.dto.SearchInformation;

public class RegexDocumentSearchTest {
	
	List<SearchInformation> expectedsearchInformationList = null;

	@Before
	public void setUp() throws Exception {
		expectedsearchInformationList = PopulateTestData.populateSearchInformationList();
	}

	@Test
	public void testStringDocumentSearch() throws IOException{
		SearchPattern sp = new RegexDocumentSearch();
		List<SearchInformation> searchInformation = sp.search("test");
		
		for (int i=0;i<searchInformation.size();i++) {
			assertTrue(searchInformation.get(0).getFileName().equals(expectedsearchInformationList.get(0).getFileName()));
			assertTrue(searchInformation.get(0).getWordCount().equals(expectedsearchInformationList.get(0).getWordCount()));
		}
		assertEquals(searchInformation.size(),expectedsearchInformationList.size());		
  }
	
	@Test
	public void testRegexDocumentSearchPerformance() throws IOException{
		SearchPattern sp = new RegexDocumentSearch();
		String[] searchStrings = PopulateTestData.populateArrayWith2MillionSearchStrings();;
		long startTime = System.currentTimeMillis();

        for (int i = 0; i < TWO_MILLION; i++) {
        	List<SearchInformation> searchInformation = sp.search(searchStrings[i]);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Regex Algorithm to Process 2 million searches is "+elapsedTime + " Milliseconds");

	}
	
}
