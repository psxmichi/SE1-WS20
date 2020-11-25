package org.hbrs.se.ws20.uebung3.control;

import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se.ws20.uebung3.view.MemberView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlehma2s
 */

public class Container {

	/*
	 * Leerer Konstruktor
	 */
	// CR1 nur noch innerhalb der Klasse Zugriff
	private Container(){
	}

	private static Container instanz = null;

    //Singleton Pattern Anwendungsfall: Datenverwaltung
	// Vorteil: Erzeugung des Objekts bei Bedarf
	// Nachteil:Probleme bei parallelen Zugriff von Client-Objekten
	// Multi-Threading -> Lösung ist nicht thread-safe
	// Eine Thread Safe Lösung: synchronized. Kann immer nur von einem Client benutzt werden
	public static synchronized Container getInstance() {
	    if(instanz == null) {
	        instanz = new Container();
        }
	    return instanz;
    }
	/*
	 * Interne ArrayList zur Abspeicherung der Objekte
	 * Alternative: HashMap oder Set. HashMap hat vor allem Probleme
	 * bei der Bewahrung der Konsistenz vom Key und Value (siehe TestStore, letzter Test)
	 */
	private List<Member> liste = new ArrayList<Member>();

	// CR2
	public void store() throws PersistenceException {
		PersistenceStrategyStream.save();
	}

	public void load() throws PersistenceException {
		PersistenceStrategyStream.load(liste);
	}

	/*
	 * Methode zum Hinzufuegen einer Member.
	 * @throws ContainerException
	 */
	public void addMember ( Member r ) throws ContainerException {
		if ( contains( r ) == true ) {
			ContainerException ex = new ContainerException();
			ex.addID ( r.getID() );
			throw ex;
		}
		liste.add( r );

	}

	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 *
	 */
	private boolean contains(Member r) {
		Integer ID = r.getID();
		for ( Member rec : liste) {
			// wichtig: Check auf die Values innerhalb der Integer-Objekte!
			if ( rec.getID().intValue() == ID.intValue() ) {
				return true;
			}
		}
		return false;

		// liste.contains(r), falls equals-Methode in Member ueberschrieben.
	}
	/*
	 * Methode zum Loeschen einer Member
	 *
	 */
	public String deleteMember( Integer id ) {
		Member rec = getMember( id );
		if (rec == null) return "Member nicht enthalten - ERROR"; else {
			liste.remove(rec);
			return "Member mit der ID " + id + " konnte geloescht werden";
		}
	}

	/*
	 * Methode zur Bestimmung der Anzahl der von Member-Objekten
	 * Aufruf der Methode size() aus List
	 *
	 */
	public int size(){
		return liste.size();
	}

	public void getCurrentList(List<Member> l){
		MemberView.dump(l);
	}
	/*
	 * Interne Methode zur Ermittlung einer Member
	 *
	 */
	private Member getMember(Integer id) {
		for ( Member rec : liste) {
			if (id == rec.getID().intValue() ){
				return rec;
			}
		}
		return null;
	}

}
