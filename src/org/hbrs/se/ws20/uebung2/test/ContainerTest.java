package org.hbrs.se.ws20.uebung2.test;

import org.hbrs.se.ws20.solutions.uebung2.Container;
import org.hbrs.se.ws20.solutions.uebung2.ContainerException;
import org.hbrs.se.ws20.uebung2.control.Member;
import org.hbrs.se.ws20.uebung2.control.MemberGenau;

/**
 * @author mlehma2s
 */
public class ContainerTest {
        public static void main (String[] args)  {
            ContainerTest test = new ContainerTest();
            try {
                test.start();
            } catch (ContainerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        public void start() throws ContainerException {

            // Test-Objekte anlegen
            Member r1 = new MemberGenau(12);
            Member r2 = new MemberGenau(32);
            Member r3 = new MemberGenau(112);
            Member r4 = new MemberGenau(1211);
            Member r5 = new MemberGenau(934);

            // Den Container anlegen
            Container store = new Container();


            // Testfall 1 - Check auf leeren Store
            vergleich ( "Testfall 1 - Pruefung auf leeren Store" , 0 , store.size()  );

            store.addMember(r1);
            store.addMember(r2);
            store.addMember(r3);
            store.addMember(r4);


            // Testfall 2 - Pruefen, ob vier Objekte eingefuegt wurden
            vergleich ( "Testfall 2 - Prüfen, ob vier Objekte eingefuegt wurden" , 4 , store.size()  );

            store.addMember(r5);

            // Testfall 3 - Pruefen, ob fuenftes Objekt eingefuegt wurde
            vergleich ( "Testfall 3 - Prüfen, ob fuenftes Objekt eingefuegt wurde" , 5 , store.size()  );

            String result = store.deleteMember(12);
            // System.out.println( result );

            // Testfall 4 - Pruefen, ob Objekt geloescht wurde
            vergleich ( "Testfall 4 - Prüfen, ob Objekt geloescht wurde" ,  4 , store.size()  );

            result = store.deleteMember(12222);
            System.out.println( result );

            // Testfall 5 - Pruefen, ob ein Objekt faelschlicherweise nicht geloescht wurde
            vergleich ( "Testfall 5 - Pruefen, ob ein Objekt faelschlicherweise nicht geloescht wurde" , 4 , store.size()  );

            try {
                store.addMember( r2 );
            } catch (ContainerException e) {

                e.printStackTrace();

            } finally {

                // Testfall 6 - Pruefen, ob ein Objekt faelschlicherweise nicht doppelt eingefuegt wurde
                vergleich ( "Testfall 6 - Pruefen, ob ein Objekt faelschlicherweise nicht doppelt eingefuegt wurde" , 4 , store.size()  );
            }

            // Test der Dump-Funktion (ohne Kontrolle)
            store.dump();
        }

        /*
         * Private Methode zur Ausgabe, ob ein Testfall erfolgreich war.
         * Dazu werden die zu ueberpruefenenden SOLL und IST-Werte uebergeben
         * sowie eine Angabe ueber den Testfall.
         *
         */
        private void vergleich( String titel, int soll , int ist ){
            System.out.print( titel + ": \n");
            if (soll == ist ) {
                System.out.print("Soll (" + soll + ") = IST (" + soll + ") --> Test ERFOLGREICH");
            } else {
                System.out.print("Soll (" + soll + ") != IST (" + soll + ") --> Test NICHT ERFOLGREICH");
            }
            System.out.println("\n");
        }
    }