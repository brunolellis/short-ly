package br.com.brunolellis.shortly.url.repository;

import java.util.Optional;

import br.com.brunolellis.shortly.url.Url;

public interface UrlRepository {
	
	Url save(Url url);
	
	Optional<Url> findByShortUrl(String shortUrl);

	void incrementCounter(Url url);
}
