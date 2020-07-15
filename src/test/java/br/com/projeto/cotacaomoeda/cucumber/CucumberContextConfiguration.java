package br.com.projeto.cotacaomoeda.cucumber;


import br.com.projeto.cotacaomoeda.CotacaoMoedaApplication;
import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {CotacaoMoedaApplication.class, CucumberContextConfiguration.Beans.class}, loader = SpringBootContextLoader.class)
public class CucumberContextConfiguration {

    public static RestTemplate restTemplate;


    @PostConstruct
    public void init(){

    }

    @Before
    public void setUp(){}

    @Configuration
    public static class Beans{
        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder builder) {
            return builder.rootUri("http://localhost:8080").interceptors(new HeaderRequestInterceptor("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJpcG9ja2V0LWFwaSIsImF1ZCI6Imlwb2NrZXQtYWRtaW4iLCJzdWIiOiI1MjIuNzIwLjUyMS0zNCIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwicm9sIjpbXX0.mrEE49ec4yq9AGeSA4TgPh6Sgk7nYqXc8wimhMsqrMi3r889aQUusVTEq-3wbRe8heRrbK7YCD7VSTMw3DQDbw")).build();
        }
    }

    private static class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

        private final String headerName;

        private final String headerValue;

        public HeaderRequestInterceptor(String headerName, String headerValue) {
            this.headerName = headerName;
            this.headerValue = headerValue;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException, IOException {
            request.getHeaders().set(headerName, headerValue);
            return execution.execute(request, body);
        }
    }
}

