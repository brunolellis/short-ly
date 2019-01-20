package br.com.brunolellis.shortly.url.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.brunolellis.shortly.url.Url;

public class UrlRowMapper implements RowMapper<Url> {

	public Url mapRow(ResultSet rs, int rowNum) {
		try {
			Url url = new Url();
			url.setId(rs.getLong("id"));
			url.setShortUrl(rs.getString("short_url"));
			url.setUrl(rs.getString("url"));
			url.setVisits(rs.getLong("visits"));
			return url;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}