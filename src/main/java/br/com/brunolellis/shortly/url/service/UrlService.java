package br.com.brunolellis.shortly.url.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brunolellis.shortly.shortener.Shortener;
import br.com.brunolellis.shortly.url.Url;
import br.com.brunolellis.shortly.url.repository.UrlRepository;

@Service
@Transactional
public class UrlService {
	
	private Shortener shortener;
	private UrlRepository repository;
	
	public UrlService(Shortener shortener, UrlRepository repository) {
		this.shortener = shortener;
		this.repository = repository;
	}
	
	public Optional<Url> findById(String key) {
		Optional<Url> url = repository.findByShortUrl(key);
		incrementCounter(url);
		
		return url;
	}

	private void incrementCounter(Optional<Url> url) {
		if (url.isPresent()) {
			repository.incrementCounter(url.get());
		}
	}

	public Url create(Url url) {
		if (!url.isValid()) {
			throw new IllegalArgumentException("The url is not valid");
		}
		
		String shortenedUrl = shortener.shorten(url.getUrl());
		url.setShortUrl(shortenedUrl);
		
		repository.save(url);
		
		return url;
	}
}
