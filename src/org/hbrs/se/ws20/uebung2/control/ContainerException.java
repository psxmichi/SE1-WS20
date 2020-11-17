package org.hbrs.se.ws20.uebung2.control;

/**
 * @author mlehma2s
 */

public class ContainerException extends Throwable {
    private Integer id;

    @Override
    public void printStackTrace() {
        // TODO Auto-generated method stub
        System.out.println("Das Member-Objekt mit der ID " + this.id + " ist bereits vorhanden!");
    }
}
