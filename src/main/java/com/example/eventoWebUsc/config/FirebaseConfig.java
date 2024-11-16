package com.example.eventoWebUsc.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    private final Dotenv dotenv = Dotenv.configure().load(); // Carga las variables del archivo .env

    @PostConstruct
    public void initialize() {
        try {
            // Construir el JSON de credenciales usando variables de entorno cargadas con dotenv
            String firebaseConfigJson = "{"
                    + "\"type\": \"service_account\","
                    + "\"project_id\": \"" + dotenv.get("PROJECT_ID") + "\","
                    + "\"private_key_id\": \"" + dotenv.get("PRIVATE_KEY_ID") + "\","
                    + "\"private_key\": \"" + dotenv.get("PRIVATE_KEY").replace("\\n", "\n") + "\","
                    + "\"client_email\": \"" + dotenv.get("CLIENT_EMAIL") + "\","
                    + "\"client_id\": \"" + dotenv.get("CLIENT_ID") + "\","
                    + "\"auth_uri\": \"" + dotenv.get("AUTH_URI") + "\","
                    + "\"token_uri\": \"" + dotenv.get("TOKEN_URI") + "\","
                    + "\"auth_provider_x509_cert_url\": \"" + dotenv.get("AUTH_PROVIDER_X509_CERT_URL") + "\","
                    + "\"client_x509_cert_url\": \"" + dotenv.get("CLIENT_X509_CERT_URL") + "\""
                    + "}";

            // Crear InputStream a partir del JSON generado
            InputStream serviceAccount = new ByteArrayInputStream(firebaseConfigJson.getBytes());

            // Configurar Firebase
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://usc-eventos-bd.firebaseio.com") // Cambia esta URL si usas Realtime Database
                    .build();

            // Inicializar Firebase solo si no existe
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase initialized successfully");
            }

            // Prueba de escritura en Firestore
            Firestore firestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> future = firestore.collection("test").document("pruebaConexión")
                    .set(new TestData("Conexión exitosa con Firestore!"));

            // Manejar el resultado de la escritura correctamente
            future.addListener(() -> {
                try {
                    WriteResult result = future.get();
                    logger.info("Escritura en Firestore realizada con éxito en: " + result.getUpdateTime());
                } catch (InterruptedException | java.util.concurrent.ExecutionException e) {
                    logger.error("Error al escribir en Firestore", e);
                }
            }, Runnable::run);

        } catch (IOException e) {
            logger.error("Error al leer el archivo de credenciales de Firebase", e);
        } catch (IllegalArgumentException e) {
            logger.error("Error: Argumento inválido en la configuración de Firebase", e);
        } catch (Exception e) {
            logger.error("Error inesperado al inicializar Firebase", e);
        }
    }

    // Clase interna para los datos de prueba
    public static class TestData {
        private String mensaje;

        public TestData(String mensaje) {
            this.mensaje = mensaje;
        }

        // Getters y Setters (necesarios para que Firestore pueda serializar los datos)
        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}