package br.com.brunolellis.shortly.url.api;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brunolellis.shortly.url.Url;
import br.com.brunolellis.shortly.url.repository.UrlRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UrlApiTest {
 
	@LocalServerPort
	private int localPort;
	
    @MockBean
    private UrlRepository urlRepository;

	@Before
	public void setUp() {
		RestAssured.port = localPort;
	}
    
    @Test
    public void shouldReturnLocationAnd301() throws Exception {
        String id = "e009ea9008a915be";
		String longUrl = "http://google.com";
		
		given(urlRepository.findByShortUrl(id))
                .willReturn(Optional.of(new Url(id, longUrl)));
 
        doNothing().when(urlRepository).incrementCounter(any());
        
		given()
				.redirects().follow(false)
				.get("/{id}", id)
			.then()
				.statusCode(301)
				.header("Location", longUrl);
    }
    
    @Test
    public void redirectToFinalUrl() throws Exception {
        given(urlRepository.findByShortUrl("e009ea9008a915be"))
                .willReturn(Optional.of(new Url("e009ea9008a915be", "http://google.com")));
 
        doNothing().when(urlRepository).incrementCounter(any());
        
		given()
				.redirects().follow(true)
				.get("/{id}", "e009ea9008a915be")
			.then()
				.statusCode(200)
				.contentType(ContentType.HTML);
    }
 
 
}