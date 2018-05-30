package com.document.search;

import static com.document.search.PopulateTestData.TWO_MILLION;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.document.search.dto.SearchInformation;

public class PerformanceTestForSearchAlgorithms {
	
	String[] searchStrings = null; 
	SearchPattern stringSearch = null;
	SearchPattern regexSearch = null;
	SearchPattern indexSearch = null;
	
	@Before
	public void setUp() throws Exception {		
		searchStrings = PopulateTestData.populateArrayWith2MillionSearchStrings();
		stringSearch = new StringDocumentSearch();
		regexSearch = new RegexDocumentSearch();
		indexSearch = new PreprocessDocumentSearch();
	}

	@Test
	public void testPerformanceForAlgorithms() throws IOException{
		testSearchAlgorithmPerformanceFor2MillionRecords(stringSearch);
		testSearchAlgorithmPerformanceFor2MillionRecords(regexSearch);
		PreprocessDocumentSearch.preProcessDocumentContent();
		testSearchAlgorithmPerformanceFor2MillionRecords(indexSearch);
	}
	
	@Test
	public void testBestCaseAndWorstCaseScenarios() throws IOException{
		testSearchAlgorithmBestCaseAndWorstCaseTimes(stringSearch);
		testSearchAlgorithmBestCaseAndWorstCaseTimes(regexSearch);
		PreprocessDocumentSearch.preProcessDocumentContent();
		testSearchAlgorithmBestCaseAndWorstCaseTimes(indexSearch);
	}
	
	public void testSearchAlgorithmPerformanceFor2MillionRecords(SearchPattern searchAlgorithm) throws IOException {
		double startTime = System.currentTimeMillis();
        for (int i = 0; i < TWO_MILLION; i++) {
        	List<SearchInformation> searchInformation = searchAlgorithm.search(searchStrings[i]);        	
        }
        double stopTime = System.currentTimeMillis();
        double elapsedTime = stopTime - startTime;
        String searchAlgorithmName = searchAlgorithm.getClass().getSimpleName();
        System.out.println(searchAlgorithmName + " Algorithm to Process 2 million searches is "+elapsedTime + " Milliseconds");
        double averageCase = elapsedTime/TWO_MILLION;
        System.out.println(searchAlgorithmName + " Average Case Scenario is "+ averageCase);
        System.out.println();
	}
	
	public void testSearchAlgorithmBestCaseAndWorstCaseTimes(SearchPattern searchAlgorithm) throws IOException {
		double max = Long.MIN_VALUE;
		double min = Long.MAX_VALUE;
		
        for (int i = 0; i < TWO_MILLION; i++) {
        	double startTimeForEachInput = System.currentTimeMillis();
        	List<SearchInformation> searchInformation = searchAlgorithm.search(searchStrings[i]);
        	double stopTimeForEachInput = System.currentTimeMillis();
        	
        	double diffTime = stopTimeForEachInput - startTimeForEachInput;
        	max = Math.max(diffTime, max);
        	min = Math.min(diffTime, min);
        }
        String searchAlgorithmName = searchAlgorithm.getClass().getSimpleName();
        System.out.println(searchAlgorithmName + " Worst Case time it takes to process on single record is : " + max);
        System.out.println(searchAlgorithmName + " Best Case time it takes to process on single record is : " + min);
        System.out.println();
	}

}
