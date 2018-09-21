package com.readoholic.model;

public class Quote {
	private String link;
	private String quotetext;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getQuotetext() {
		return quotetext;
	}
	public void setQuotetext(String quotetext) {
		this.quotetext = quotetext;
	}
	public Quote(String link, String quotetext) {
		super();
		this.link = link;
		this.quotetext = quotetext;
	}
}
