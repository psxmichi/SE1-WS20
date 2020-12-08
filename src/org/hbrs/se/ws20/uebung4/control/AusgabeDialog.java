package org.hbrs.se.ws20.uebung4.control;

import org.hbrs.se.ws20.prototype.uebung4.UserStory;
import org.hbrs.se.ws20.uebung4.model.Container;

import java.util.Collections;

public class AusgabeDialog {

    /**
     * Diese Methode realisiert die Ausgabe.
     */
    public static void startAusgabe() {

        // Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
        // ausgeben. Allerdings weiss der Student hier nicht weiter

        // [Sortierung ausgelassen]
        Collections.sort(Container.getCurrentList() );

        // Klassische Ausgabe ueber eine For-Each-Schleife
        for (UserStory us : Container.getCurrentList()) {
            System.out.println(us.toString());
        }

        // [Variante mit forEach-Methode / Streams (--> Kapitel 9)? Gerne auch mit Beachtung der neuen US1
        // (Filterung Aufwand > x)
        Container.getCurrentList().stream().filter( userStory -> userStory.getAufwand() > 4  )   // Filter
                .filter( userStory -> userStory.getPrio() < 2.0 )
                .sorted(  (us1, us2)  -> Double.compare( us1.getPrio() , us2.getPrio() ) ) // MAP
                .forEach( userStory -> System.out.println( userStory.toString() ) ); // Reduce
    }
}
