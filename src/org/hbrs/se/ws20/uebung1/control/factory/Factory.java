package org.hbrs.se.ws20.uebung1.control.factory;

import org.hbrs.se.ws20.uebung1.control.GermanTranslator;
/**
 * @author mlehma2s
 */
/**
 * Factory zur Erzeugung von konsistenten Translator-Objekten
 * Anwendung von Factory Method Pattern
 * Problem: Inkonsistente Erzeugung von Objekten
 */
public class Factory {

    public static GermanTranslator createGermanTranslator() {

        return new GermanTranslator();
    }
}
