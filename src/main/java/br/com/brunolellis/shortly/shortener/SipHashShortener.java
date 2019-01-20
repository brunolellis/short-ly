package br.com.brunolellis.shortly.shortener;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

@Service
public class SipHashShortener implements Shortener {

	@Override
	public String shorten(String lengthyString) {
		Objects.requireNonNull(lengthyString, "lengthyString to be shortened cannot be null");

		return Hashing.sipHash24()
				.hashString(lengthyString, Charsets.UTF_8).toString();
	}

}
