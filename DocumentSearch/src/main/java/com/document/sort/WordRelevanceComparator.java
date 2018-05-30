package com.document.sort;

import java.util.Comparator;

import com.document.search.dto.SearchInformation;

public class WordRelevanceComparator implements Comparator<SearchInformation> {

	public int compare(SearchInformation searchInformation1, SearchInformation searchInformation2) {
		return searchInformation2.getWordCount() - searchInformation1.getWordCount();
	}

}
