package com.personal.budget.clients.google;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SheetsServiceConfig {

//    private static final String APPLICATION_NAME = "budget";
//
//    @Bean
//    public Sheets sheets(Credential credential) throws IOException, GeneralSecurityException {
//        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), credential)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//
//    @Bean
//    public Credential credential(@Value("${application.google-secret}") String googleSecret) throws IOException {
//        byte[] base64Decoded = Base64.getDecoder().decode(googleSecret.getBytes());
//        return GoogleCredential.fromStream(new ByteArrayInputStream(base64Decoded)).createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
//    }
}
