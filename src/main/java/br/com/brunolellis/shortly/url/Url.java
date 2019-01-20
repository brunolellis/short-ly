package br.com.brunolellis.shortly.url;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Url implements Serializable { 
	// TODO: change Serializable to improve perfomance

	private static final long serialVersionUID = 4156287387052804214L;

	private static final Pattern VALID_URL = Pattern
			.compile("^(https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

	private Long id;
	private String shortUrl;
	private String url;
	private Long visits;

	public Url() {
	}

	@JsonIgnore
	public boolean isValid() {
		if (url == null || url.isBlank() || !VALID_URL.matcher(url).matches()) {
			return false;
		}

		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getUrl() {
		return url;
	}

	/**
	 * an url must have a prefix. defaults to "http" prefix in case it is missing.
	 * @param url
	 */
	public void setUrl(String url) {
		if (url != null) {
			String lowerCaseUrl = url.toLowerCase();
			if (!lowerCaseUrl.startsWith("http://") && !lowerCaseUrl.startsWith("https://")) {
				url = "http://" + url;
			}
		}
		this.url = url;
	}

	public Long getVisits() {
		return visits;
	}

	public void setVisits(Long visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", shortUrl=" + shortUrl + ", url=" + url + ", visits=" + visits + "]";
	}

}
