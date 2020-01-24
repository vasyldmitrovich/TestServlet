package net.codejava.upload;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.Executors;

public class TestHttp {
    HttpClient client = HttpClient.newBuilder().authenticator(new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("username","password".toCharArray());
        }
    })
            .connectTimeout(Duration.ofSeconds(42))
            .cookieHandler(CookieHandler.getDefault())
            .executor(Executors.newSingleThreadExecutor())
            .followRedirects(HttpClient.Redirect.NORMAL)
            .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com",8080)))
            .sslContext(SSLContext.getDefault())
            .sslParameters(new SSLParameters())
            .version(HttpClient.Version.HTTP_2)
            .build();

    public TestHttp() throws NoSuchAlgorithmException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://openjdk.java.net"))
        .GET()
        .header("User-Agent","Java 11")
        .build();
    try {
        HttpResponse <String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println(response.body());
        } else {
            System.out.println("Something wrong here!");
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }

    }
}
