package org.hbrs.se.ws20.uebung3.view;

import org.hbrs.se.ws20.uebung3.control.Container;
import org.hbrs.se.ws20.uebung3.control.Member;
import org.hbrs.se.ws20.uebung3.control.MemberKonkret;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author mlehma2s
 */
public class MemberView {

    public static void main (String [] args) {
        // Test-Objekte anlegen
        Member r1 = new MemberKonkret(12);
        Member r2 = new MemberKonkret(32);
        Member r3 = new MemberKonkret(112);
        Member r4 = new MemberKonkret(1211);
        Member r5 = new MemberKonkret(934);

        // Den Container anlegen
        Container store = new Container();
    }
    /*
     * Methode zur Ausgabe aller IDs der Member-Objekte. Es werden verschiedene Varianten vorgestellt!
     * Fuer eine ordnungsgemaesse Ausgabe sollten die unpassenden Varianten und und Loesungen
     * natuerlich auskommentiert werden.
     *
     */
    //CR3 dump Klasse umgezogen
    public static void dump(List<Member> liste){
        System.out.println("Ausgabe aller Member-Objekte: ");

        // Loesung mit Iterator:
        Iterator<Member> i = liste.iterator();
        while (  i.hasNext() ) {
            Member p = i.next();
            System.out.println("ID: " + p.getID() );
        }

        // Loesung Nr. 1 mit For each Schleife: Sequentielle Bearbeitung der Schleife
        for ( Member p : liste ) {
            System.out.println( p.toString()  );
        }

        // Loesung Nr. 2:
        // Implementierung mit forEach: eine Iteration wird hier nicht mehr
        // explizit beschrieben, sondern der Implementierung der Methode
        // forEach überlassen! Consumer: eine Parametrisierung eines Objekts mit einer Anweisung (Funktion),
        // die eine Aktion auf einem Element vom Typ T (hier: Member) darstellt.
        // Consumer<T> ist ein Functional Interface


        liste.forEach( new Consumer<Member>() {
            @Override
            public void accept(Member p) {
                // Die Funktion
                System.out.println( p.toString()  );
            }
        });

        // Loesung Nr. 3:
        // Abkürzende Schreibweise für forEach mit einem LambaAusdruck
        // Ein Objekt wird mit einer Anweisung (Funktion) parametrisiert
        liste.forEach( (Member element) -> System.out.println(element));

        // Der Type Member kann auf der linken Seite weggelassen werden!
        liste.forEach( element -> System.out.println( element ) );


        // Loesung Nr. 4:
        // Vorteil: Möglichkeit der parallelen Verarbeitung der Liste möglich
        // Erhöht die Performance der Anwendung
        liste.parallelStream().forEach( element -> System.out.println(element) );

        // Ueber die Streaming-Funktion koennen weitere Filterungen durchgefuehrt werden
        // Dieses Vorgehen wird auch als Pattern Filter-Map-Reduce bezeichnet (vgl. Kapitel 4 bzw. 6
        // sowie die Uebung Nr. 4:
        // Variante 4.1 (mit zwei Filtern und mit foreach als Reduzierung (reduce)):
        System.out.println("Ausgabe als Stream:");
        liste.stream() // Parallelisierung hier moeglich mit .parallelstream()
                .filter( element -> element.getID() > 20 )
                .filter(element -> element.getID() < 1000 )
                .forEach(element -> System.out.println(element) );

        // Variante 4.2 (mit zwei Filtern und einer Reduzierung (reduce) auf eine Liste ohne foreach):
        List<Integer> newListe = liste.stream() // Parallelisierung hier moeglich mit .parallelstream()
                .filter( element -> element.getID() > 20 )
                .filter(element -> element.getID() < 1000 )
                .map( element -> element.getID() )
                .collect( Collectors.toList() );

        System.out.println( newListe );

        // Variante fuer die Variante Nr. 4.2:
        // Achtung: diese Variante ist im Gegensatz zur 4.2 nicht parallelisierbar!
        List<Integer> newListe2 = new ArrayList<Integer>();
        for ( Member p : liste ) {
            if (p.getID() > 20 && p.getID()<1000 ) {
                newListe2.add(p.getID());
            }
        }
        System.out.println(newListe2);

    }

}
