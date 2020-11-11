package org.hbrs.se.ws20.uebung1.test;

import org.hbrs.se.ws20.uebung1.control.GermanTranslator;
import org.hbrs.se.ws20.uebung1.control.*;
import org.hbrs.se.ws20.uebung1.view.*;

/**
 * @author mlehma2s
 */

public class ClientTest {
    private Translator translator;
    private Client client;

        //Design Pattern Dependency Injection
    public ClientTest() {

        translator = new GermanTranslator();
        client = new Client();

        client.setTranslator( translator );
    }

    public void test(){

        //Tests die funktionieren
        client.display(1);
        client.display(5);
        client.display(3);

        //Tests die nicht möglich sind
        client.display(12);
        client.display(-2);
        client.display(0);

    }

    public static void main(String[] args){
        ClientTest ct = new ClientTest();
        ct.test();
    }
}
