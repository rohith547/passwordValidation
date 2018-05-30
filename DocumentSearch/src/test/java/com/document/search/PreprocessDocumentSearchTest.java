package com.document.search;

import static com.document.search.PopulateTestData.TWO_MILLION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.document.search.dto.SearchInformation;

public class PreprocessDocumentSearchTest {

	List<SearchInformation> expectedsearchInformationList = null;

	@Before
	public void setUp() throws Exception {
		PreprocessDocumentSearch.preProcessDocumentContent();		
		expectedsearchInformationList = PopulateTestData.populateSearchInformationList();
	}

	@Test
	public void testPreprocessDocumentSearch() throws IOException{
		
		PreprocessDocumentSearch preProcessDocSearch = new PreprocessDocumentSearch();
		List<SearchInformation> searchInformation = preProcessDocSearch.search("test");
		
		for (int i=0;i<searchInformation.size();i++) {
			assertTrue(searchInformation.get(0).getFileName().equals(expectedsearchInformationList.get(0).getFileName()));
			assertTrue(searchInformation.get(0).getWordCount().equals(expectedsearchInformationList.get(0).getWordCount()));
		}
		assertEquals(searchInformation.size(),expectedsearchInformationList.size());
	}
	
	@Test
	public void testPerprocessingDocumentSearchTest() throws IOException{
		SearchPattern sp = new PreprocessDocumentSearch();
		String[] searchStrings = PopulateTestData.populateArrayWith2MillionSearchStrings();
		long startTime = System.currentTimeMillis();
		
        for (int i = 0; i < TWO_MILLION; i++) {
        	List<SearchInformation> searchInformation = sp.search(searchStrings[i]);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Preprocessing Index Algorithm to Process 2 million searches is "+elapsedTime + " Milliseconds");
	}

}