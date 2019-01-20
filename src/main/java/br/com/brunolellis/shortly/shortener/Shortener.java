package br.com.brunolellis.shortly.shortener;

public interface Shortener {

	/**
	 * Used to create a short version of a given url.
	 * 
	 * @param lengthyString string to shorten
	 * @return shortened version
	 */
	String shorten(String lengthyString);

}
