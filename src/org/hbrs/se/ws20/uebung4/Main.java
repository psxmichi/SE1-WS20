package org.hbrs.se.ws20.uebung4;

import org.hbrs.se.ws20.uebung4.view.*;
import org.hbrs.se.ws20.uebung4.model.*;
import org.hbrs.se.ws20.uebung4.control.*;

public class Main {
    /**
     * Start-Methoden zum Starten des Programms
     * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
     */
    public static void main (String[] args) throws Exception  {
        Container con = Container.getInstance();
        con.startEingabe();
    }
}
