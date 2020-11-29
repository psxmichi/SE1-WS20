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
    private static Object Member;
    // URL der Datei, in der die Objekte gespeichert werden
    private String LOCATION = "objects.ser";
    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    private java.io.FileInputStream fis = null;
    private ObjectInputStream ois = null;

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
        try {
            fos = new FileOutputStream( LOCATION );
            // fis = new FileInputStream( LOCATION );
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, File could not be found");
        }
        try {
            oos = new ObjectOutputStream( fos );
            // ois = new ObjectInputStream(  fis  );
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, problems with the stream");
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            // Closing the outputstreams for storing
            if (oos != null) oos.close();
            if (fos != null) fos.close();

            // Closing the inputstreams for loading
            if (ois != null) ois.close();
            if (fis != null) fis.close();
        } catch( IOException e ) {
            // Lazy solution: catching the exception of any closing activity ;-)
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "error while closing connections");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> list) throws PersistenceException {
        // ensure that the connection is open
        this.closeConnection();
        // Schreibe Objekte in den Stream
        try {
            System.out.println(  list.size() + " User Stories wurden erfolgreich gespeichert!");
            oos.writeObject( list );

        }
        catch (IOException e) {
            // Koennte man ausgeben für interne Debugs: e.printStackTrace();
            // Chain of Responsibility: Hochtragen der Exception in Richtung Ausgabe (UI)
            // Uebergabe in ein lesbares Format fuer den Benutzer
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure
                    , "Fehler beim Speichern der Datei!");
        }
        finally {
            this.closeConnection();
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException, IOException {
        // Load the objects from stream
        List<Member> list = null;

        try {
            // Create Streams here instead using "this.openConnection();"
            fis = new java.io.FileInputStream( LOCATION );
            ois = new ObjectInputStream( fis );

            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                list = (List) obj;
            }
            System.out.println("LOG: Es wurden " + list.size() + " User Stories erfolgreich reingeladen!");
            return list;
        }
        catch (IOException e) {
            // Sup-Optimal, da Exeception in Form eines unlesbaren Stake-Traces ausgegeben wird
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure
                    , "Fehler beim Laden der Datei!");
        }
        catch (ClassNotFoundException e) {
            // Chain of Responsbility erfuellt, durch Throw der Exceotion kann UI
            // benachrichtigt werden!
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure
                    , "Fehler beim Laden der Datei! Class not found!");
        }
        finally {
            this.closeConnection();
        }
    }
}
