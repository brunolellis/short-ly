package br.com.brunolellis.shortly.url.repository.jdbc;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.brunolellis.shortly.url.Url;
import br.com.brunolellis.shortly.url.repository.UrlRepository;

@Repository
public class JdbcUrlRepository implements UrlRepository {

	private static final String INSERT = "insert into urls(short_url, url, visits) values(?, ?, 0)";
	private static final String UPDATE = "update urls SET visits = visits + 1 WHERE short_url = ?";
	private static final String FIND_BY_SHORT_URL = "select * from urls where short_url = ?";
	private static final String COUNT_BY_SHORT_URL = "select count(1) from urls where short_url = ?";

	private JdbcTemplate jdbcTemplate;

	public JdbcUrlRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Url save(Url url) {
		if (existsByShortUrl(url.getShortUrl())) {
			return url;
		}

		if (url.getId() == null) {
			return create(url);
		}

		return update(url);
	}

	private Url create(Url url) {
		jdbcTemplate.update(INSERT, new Object[] { url.getShortUrl(), url.getUrl() });
		return findByShortUrl(url.getShortUrl()).get();
	}

	private Url update(Url url) {
		jdbcTemplate.update(UPDATE, new Object[] { url.getShortUrl() });
		return findByShortUrl(url.getShortUrl()).get();
	}

	private boolean existsByShortUrl(String shortUrl) {
		int count = jdbcTemplate.queryForObject(COUNT_BY_SHORT_URL, new Object[] { shortUrl }, Integer.class);
		return count > 0;
	}

	@Override
	public Optional<Url> findByShortUrl(String shortUrl) {
		try {
			Url url = jdbcTemplate.queryForObject(FIND_BY_SHORT_URL, new Object[] { shortUrl }, new UrlRowMapper());
			return Optional.ofNullable(url);

		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void incrementCounter(Url url) {
		update(url);
	}

}
