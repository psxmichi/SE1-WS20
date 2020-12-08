package org.hbrs.se.ws20.uebung4.control;

import org.hbrs.se.ws20.prototype.uebung4.Container;
import org.hbrs.se.ws20.prototype.uebung4.ContainerException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class EingabeDialog {
    /*
     * Diese Methode realisiert eine Eingabe ueber einen Scanner
     * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
     * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
     */
    public void startEingabe() throws ContainerException, Exception {

        String strInput = null;

        // Initialisierung des Eingabe-View
        Scanner scanner = new Scanner( System.in );

        // Ausgabe eines Texts zur Begruessung
        System.out.println("Prio-Tool V1.5 by Julius P. (dedicated to all my friends)");

        while ( true ) {
            System.out.print( "> "  );

            strInput = scanner.nextLine();

            // Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");

            // 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
            if ( strings[0].equals("help") ) {
                System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump, enter, store, load, exit");
            }
            // Auswahl der bisher implementierten Befehle:
            if ( strings[0].equals("dump") ) {
                AusgabeDialog.startAusgabe();
            }
            // Auswahl der bisher implementierten Befehle:
            if ( strings[0].equals("enter") ) {
                // Daten einlesen ...
                //this.addUserStory( userStory ) um das Objekt in Liste einzufügen
            }

            if (  strings[0].equals("store")  ) {
                this.store();
            }
            if ( strings[0].equals("load") ) {
                Container.getInstance().load();
            }
        } // Ende der Schleife
    }
    /*
     * Methode zum Speichern der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
     *
     */
    private void store() throws ContainerException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            // Hier vermutet Herr P. ein bekanntes Design Pattern, ist sich aber auch nicht sicher
            // (welches denn...?) --> Decorator (nicht behandelt im WS 20/21)
            fos = new FileOutputStream( Container.LOCATION );
            oos = new ObjectOutputStream(fos);

            oos.writeObject( Container.getCurrentList() );
            System.out.println( Container.size() + " User Stories wurden erfolgreich gespeichert!");
        }
        catch (IOException e) {
            //  Delegation in den aufrufendem Context
            // (Anwendung Pattern "Chain Of Responsibility)
            throw new ContainerException("Fehler im Laden");
        }
    }
}
