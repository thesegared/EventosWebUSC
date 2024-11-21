package com.example.eventoWebUsc.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
public class FirebaseConfig {

    public FirebaseConfig() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/eventoswebusc-firebase-adminsdk-m9uz1-b91326e751.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://eventoswebusc-default-rtdb.firebaseio.com/") // Tu URL aquí
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase inicializado correctamente.");
            }
        } catch (Exception e) {
            System.err.println("Error al inicializar Firebase: " + e.getMessage());
        }
    }
}
