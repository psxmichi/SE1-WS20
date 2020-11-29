package org.hbrs.se.ws20.uebung3.test;

import org.hbrs.se.ws20.solutions.uebung3.Container;
import org.hbrs.se.ws20.solutions.uebung3.ContainerException;
import org.hbrs.se.ws20.solutions.uebung3.Member;
import org.hbrs.se.ws20.solutions.uebung3.MemberKonkret;
import org.hbrs.se.ws20.solutions.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.solutions.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se.ws20.solutions.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @mlehma2s
 */
class ContainerTest {
    private Container container;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
    }

    @Test
    void testNoStrategeySet() {
        try {
            container.setPersistenceStrategie(null);
            container.store();

        } catch (PersistenceException e) {
            System.out.println("Message: " + e.getMessage() );
            assertEquals( e.getMessage() , "Strategy not initialized" );
            assertEquals(  e.getExceptionTypeType() , PersistenceException.ExceptionType.NoStrategyIsSet );
        }
    }

    @Test
    void testMongoDBNotImplemented() {
        try {
            container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );
            container.store();
        } catch (PersistenceException e) {
            System.out.println("Message: " + e.getMessage() );
            assertEquals( e.getMessage() , "Not implemented!" );
            assertEquals(  e.getExceptionTypeType() , PersistenceException.ExceptionType.ImplementationNotAvailable );
        }
    }

    @Test
    void testWrongLocationOfFile() {
        try {
            PersistenceStrategyStream<org.hbrs.se.ws20.solutions.uebung3.Member> strat = new PersistenceStrategyStream<org.hbrs.se.ws20.solutions.uebung3.Member>();

            // FileStreams do not like directories, so try this out ;-)
            strat.setLOCATION("/Users/saschaalda/tmp");
            container.setPersistenceStrategie( strat );
            container.store();

        } catch (PersistenceException e) {
            System.out.println("Message: " + e.getMessage() );
            assertEquals( e.getMessage() , "Error in opening the connection, File could not be found" );
            assertEquals(  PersistenceException.ExceptionType.ConnectionNotAvailable  ,
                    e.getExceptionTypeType() ) ;
        }
    }

    @Test
    void testStoreAndLoad() {
        try {
            container.setPersistenceStrategie( new PersistenceStrategyStream<Member>() );
            container.addMember(new MemberKonkret(1));

            assertEquals( 1 , container.size() );
            container.store();

            container.deleteMember(1);
            assertEquals(0 , container.size() );

            container.load();
            assertEquals( 1 , container.size() );

        } catch (PersistenceException | ContainerException e) {
            System.out.println("Message: " + e.getMessage() );
        }
    }
}