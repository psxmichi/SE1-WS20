package org.hbrs.se.ws20.uebung1.view;

import org.hbrs.se.ws20.uebung1.control.GermanTranslator;
import org.hbrs.se.ws20.uebung1.control.factory.Factory;

class Client {

	/*
	 * Methode zur Ausgabe einer Zahl auf der Console
	 */
	public void display( int aNumber ){
		// In dieser Methode soll die Methode translateNumber 
		// mit dem übergegebenen Wert der Variable aNumber 
		// aufgerufen werden.
		// Strenge Implementierung gegen das Interface Translator gewuenscht!

		// ist zwar falsch aber egal
		GermanTranslator translator = Factory.createGermanTranslator(); // new GermanTranslator();
		String result = translator.translateNumber(1);

		System.out.println("Das Ergebnis der Berechnung: " +
				"[das Ergebnis an dieser Stelle]" + result );

	}
}




