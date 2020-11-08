package org.hbrs.se.ws20.uebung1.view;

import org.hbrs.se.ws20.uebung1.control.GermanTranslator;
import org.hbrs.se.ws20.uebung1.control.Translator;
import org.hbrs.se.ws20.uebung1.control.factory.Factory;
/**
 * @author mlehma2s
 */
public class Client {

	private Translator translator;

	/*
	 * Methode zur Ausgabe einer Zahl auf der Console
	 */
	public void display( int aNumber ){
		// In dieser Methode soll die Methode translateNumber 
		// mit dem übergegebenen Wert der Variable aNumber 
		// aufgerufen werden.
		// Strenge Implementierung gegen das Interface Translator gewuenscht!

		Translator translator = Factory.createGermanTranslator(); // new GermanTranslator();
		String result = translator.translateNumber(aNumber);
		System.out.println("Das Ergebnis der Berechnung: " + result );

		//translator = Factory.createEnglishTranslator()
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}
}




