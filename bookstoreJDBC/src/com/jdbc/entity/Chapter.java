package com.jdbc.entity;

public class Chapter {

	private String title;
	private Integer chapterNumber;

	public Chapter() {
		super();
	}

	public Chapter(String title, Integer chapterNumber) {
		super();
		this.title = title;
		this.chapterNumber = chapterNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(Integer chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	@Override
	public String toString() {
		return "Tittle : " + title + " and ChapterNumber :" + chapterNumber;
	}

}
