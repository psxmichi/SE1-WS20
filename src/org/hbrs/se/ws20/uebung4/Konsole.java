package org.hbrs.se.ws20.uebung4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Konsole {
    private BufferedReader input = null;

    public Konsole(){
        input = new BufferedReader( new InputStreamReader(System.in ) );
    }

    public String readLine(String befehl){
        String strInput = null;
        System.out.print( befehl );
            try {
                strInput = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return strInput;
        }
        public String readLine() {
            return this.readLine("> ");
        }
}
