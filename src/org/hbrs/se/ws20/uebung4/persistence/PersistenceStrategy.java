package org.hbrs.se.ws20.uebung4.persistence;

import java.io.IOException;
import java.util.List;

/**
 * Interface for defining basic methods for a persistence mechanism
 * Each concrete algorithm (i.e. strategy) must implement this method
 * This interface corresponds to the abstract strategy w.r.t. to the
 * Strategy Design Pattern (GoF).
 *
 * @param <E>
 */
public interface PersistenceStrategy<E> {
    public void openConnection() throws PersistenceException, IOException, org.hbrs.se.ws20.uebung4.persistence.PersistenceException;
    public void closeConnection() throws PersistenceException, org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
    public void save(List<E> member) throws PersistenceException;
    public List<E> load() throws PersistenceException, IOException;
}
