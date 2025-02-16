package com.personal.budget.clients.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;

@Configuration
public class SheetsServiceConfig {

    private static final String APPLICATION_NAME = "budget";

    @Bean
    public Sheets sheets(Credential credential) throws IOException, GeneralSecurityException {
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Bean
    public Credential credential(@Value("${application.google-secret}") String googleSecret) throws IOException {
        byte[] base64Decoded = Base64.getDecoder().decode(googleSecret.getBytes());
        return GoogleCredential.fromStream(new ByteArrayInputStream(base64Decoded)).createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
    }
}
