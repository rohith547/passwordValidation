package com.document.search;

import java.io.IOException;
import java.util.List;

import com.document.search.dto.SearchInformation;

public interface SearchPattern {
	
	static final String [] FILE_NAMES = {"File1.txt","File2.txt", "File3.txt"};

	public List<SearchInformation> search(String searchString) throws IOException;
}
