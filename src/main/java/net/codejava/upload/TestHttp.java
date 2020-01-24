package net.codejava.upload;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.net.*;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.concurrent.Executors;

public class TestHttp {
    HttpClient httpClient = HttpClient.newBuilder().authenticator(new Authenticator() {
        @Override
        public PasswordAuthentication requestPasswordAuthenticationInstance(String host, InetAddress addr, int port, String protocol, String prompt, String scheme, URL url, RequestorType reqType) {
            return super.requestPasswordAuthenticationInstance(host, addr, port, protocol, prompt, scheme, url, reqType);
        }
    }).connectTimeout(Duration.ofSeconds(42))
            .cookieHandler(CookieHandler.getDefault())
            .executor(Executors.newSingleThreadExecutor())
            .followRedirects(HttpClient.Redirect.NORMAL)
            .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com",8080)))
            .sslContext(SSLContext.getDefault())
            .sslParameters(new SSLParameters())
            .version(HttpClient.Version.HTTP_2)
            .build();
}
