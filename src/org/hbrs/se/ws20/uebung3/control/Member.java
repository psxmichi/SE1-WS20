package org.hbrs.se.ws20.uebung3.control;

public interface Member {
	
	// ID ist in einem abgeleiteten Objekt über eine Konstruktor-Methode zu belegen 
	// --> Primaerschluessel zur Unterscheidung aller Member-Objekte
	Integer getID();
	
	public String toString();

}
 