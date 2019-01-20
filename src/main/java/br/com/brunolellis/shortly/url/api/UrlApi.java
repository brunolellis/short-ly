package br.com.brunolellis.shortly.url.api;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunolellis.shortly.url.Url;
import br.com.brunolellis.shortly.url.service.UrlService;

@RestController
public class UrlApi {

	private UrlService service;

	public UrlApi(UrlService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Url> findByShortUrl(@PathVariable String id) {
		Optional<Url> url = service.findById(id);
		if (url.isPresent()) {
			return ResponseEntity.status(MOVED_PERMANENTLY)
					.header(LOCATION, url.get().getUrl())
					.build();
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/shorten")
	public Url shorten(@RequestBody Url url) {
		Url newUrl = service.create(url);
		return newUrl;
	}

}
