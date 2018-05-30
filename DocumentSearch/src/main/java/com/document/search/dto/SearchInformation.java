package com.document.search.dto;

public class SearchInformation {

		private String fileName;
		private Integer wordCount;
		
		public SearchInformation(String fileName, int wordCount) {
			super();
			this.fileName = fileName;
			this.wordCount = wordCount;
		}
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public Integer getWordCount() {
			return wordCount;
		}
		public void setWordCount(Integer wordCount) {
			this.wordCount = wordCount;
		}
	
}
