package org.hbrs.se.ws20.uebung4.model;

import org.hbrs.se.ws20.prototype.uebung4.ContainerException;
import org.hbrs.se.ws20.prototype.uebung4.UserStory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * Erkenntnis aus den Übungen: die Klasse MUSS aufgesplittet werden!
 * (siehe Blueprint einer Architektur auf LEA)
 * 
 * erstellt von Julius P., H-BRS 2020, Version 1.5
 * 
 */

public class Container {
	 
	// Interne ArrayList zur Abspeicherung der Objekte
	private static List<UserStory> liste = null;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... --> RICHTIG
	private static Container instance = new Container();
	
	// URL der Datei, in der die Objekte gespeichert werden 
	final static String LOCATION = "userstories1.ser";

	/**
	 * Liefert ein Singleton zurück. Diese Methode ist thread-safe (oder...?) --> RICHTIG
	 * Nachteil: ggf. hoher Speicherbedarf, da Singleton zu Programmstart schon erzeugt
	 * @return
	 */
	public static Container getInstance() {
		return instance;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern
	 */
	private Container(){
		liste = new ArrayList<UserStory>();
	}

	/**
	 * Methode zum Hinzufügen einer Story unter Wahrung der Schlüsseleigenschaft
	 * @param r
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory r ) throws ContainerException {		
		if ( contains(r) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(r);
		
	}

	/**
	 * Prüft, ob eine Story bereits vorhanden ist
	 * @param r
	 * @return
	 */
	private boolean contains(UserStory r) {
		int ID = r.getId();
		for ( UserStory rec : liste) {
			if ( rec.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory-Objekten
	 * @return
	 */
	public static int size(){
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public static List<UserStory> getCurrentList() {
		return liste;
	}

	/**
	 * Liefert eine bestimmte Story zurück
	 * @param id
	 * @return
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory rec : liste) {
			if (id == rec.getId() ){
				return rec;
			}
		}
		return null;
	}



}
