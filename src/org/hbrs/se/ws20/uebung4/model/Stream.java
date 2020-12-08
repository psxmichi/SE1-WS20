package org.hbrs.se.ws20.uebung4.model;

import org.hbrs.se.ws20.prototype.uebung4.ContainerException;

import java.io.*;
import java.util.List;

import static org.hbrs.se.ws20.uebung4.model.Container.*;

public class Stream {
    /*
     * Methode zum Speichern der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
     *
     */
    private void store() throws org.hbrs.se.ws20.prototype.uebung4.ContainerException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            // Hier vermutet Herr P. ein bekanntes Design Pattern, ist sich aber auch nicht sicher
            // (welches denn...?) --> Decorator (nicht behandelt im WS 20/21)
            fos = new FileOutputStream( LOCATION );
            oos = new ObjectOutputStream(fos);

            oos.writeObject( getCurrentList() );
            System.out.println( size() + " User Stories wurden erfolgreich gespeichert!");
        }
        catch (IOException e) {
            //  Delegation in den aufrufendem Context
            // (Anwendung Pattern "Chain Of Responsibility)
            throw new ContainerException("Fehler im Laden");
        }
    }

    /*
     * Methode zum Laden der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte geladen.
     *
     */
    public void load() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream( LOCATION );
            ois = new ObjectInputStream(fis);

            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                getCurrentList();
            }
            System.out.println("Es wurden " + Container.size() + " User Stories erfolgreich reingeladen!");
        }
        catch (IOException e) {
            System.out.println("LOG: Datei konnte nicht gefunden werden!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("LOG: Liste konnte nicht extrahiert werden (ClassNotFound)!");
        }
        finally {
            if (ois != null) try { ois.close(); } catch (IOException e) {}
            if (fis != null) try { fis.close(); } catch (IOException e) {}
        }
    }


}
