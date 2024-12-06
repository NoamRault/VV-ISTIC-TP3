package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void testPrepareSocket_withSupportedAndEnabledProtocols() {
        // Création du mock de SSLSocket
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Définition des protocoles pris en charge et activés
        when(mockSocket.getSupportedProtocols()).thenReturn(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" });
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[] { "TLSv1" });

        // Création de l'instance de TLSSocketFactory et préparation du socket
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(mockSocket);

        // Vérification que les protocoles activés ont été définis correctement
        verify(mockSocket).setEnabledProtocols(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" });
    }

    @Test
    public void testPrepareSocket_withNoEnabledProtocols() {
        // Création du mock de SSLSocket
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Définition des protocoles pris en charge et activés
        when(mockSocket.getSupportedProtocols()).thenReturn(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" });
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[] {});

        // Création de l'instance de TLSSocketFactory et préparation du socket
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(mockSocket);

        // Vérification que les protocoles activés ont été définis correctement
        verify(mockSocket).setEnabledProtocols(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" });
    }

    @Test
    public void testPrepareSocket_withNoSupportedProtocols() {
        // Création du mock de SSLSocket
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Définition des protocoles pris en charge et activés
        when(mockSocket.getSupportedProtocols()).thenReturn(new String[] {});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[] {"TLSv1.1", "TLSv1" });

        // Création de l'instance de TLSSocketFactory et préparation du socket
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(mockSocket);

        // Vérification que les protocoles activés ont été définis correctement
        verify(mockSocket).setEnabledProtocols(new String[] { "TLSv1.1", "TLSv1" });
    }


    @Test
    public void testPrepareSocket_withNothing() {
        // Création du mock de SSLSocket
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Définition des protocoles pris en charge et activés comme vides
        when(mockSocket.getSupportedProtocols()).thenReturn(new String[] {});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[] {});

        // Création de l'instance de TLSSocketFactory et préparation du socket
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(mockSocket);

        // Vérification que setEnabledProtocols n'a pas été appelé
        verify(mockSocket, never()).setEnabledProtocols(any(String[].class));
    }

}