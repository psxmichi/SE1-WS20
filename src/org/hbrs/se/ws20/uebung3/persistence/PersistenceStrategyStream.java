package org.hbrs.se.ws20.uebung3.persistence;

import org.hbrs.se.ws20.uebung3.control.Member;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategy;

import java.io.*;
import java.util.List;

import static java.util.Collections.list;

/**
 *
 * @author mlehma2s
 */

public class PersistenceStrategyStream<Member, FileInputStream extends InputStream> implements PersistenceStrategy<Member> {
    private Object Member;

    public static void save() {
        save();
    }
    
    public static void load(List<org.hbrs.se.ws20.uebung3.control.Member> liste){
        list();
    }

    private static void list() {
    }

    //CR2
    @Override
    public void openConnection() throws PersistenceException, IOException {
        FileInputStream fis = new FileInputStream(" a location to a file");
        ObjectInputStream ois = new ObjectInputStream(fis);
    }

    @Override
    public void closeConnection() throws PersistenceException {
    //Innerhalb der Methode closeConnection wäre dies in einem finally Block korrekt
    fis.close();
    ois.close();
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException {
        OutputStream fos = null;
        try {
            try {
                fos = new FileOutputStream("Filename");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        finally {
            try {
            fos.close();
            } catch ( Exception e )
            { e.printStackTrace();
            }
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public static List<Member> load() throws PersistenceException, IOException {
        // Some Coding hints ;-)
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        List<Member> newList =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        //fis = new FileInputStream( " a location to a file" );
        //ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        openConnection();
        try {
            Object obj = ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (Member instanceof List<?>) {
            newList = (List) Member;
            return newList;
        }
        // and finally close the streams (guess where this could be...?)
        return null;
    }
}
