package org.hbrs.se.ws20.uebung2.control;

import java.util.ArrayList;
import java.util.List;

public class Container {
    //Funktionale Anforderung 1
    private List <Member> liste = new ArrayList<Member>();
    public void addMember(Member member) throws ContainerException {
    Integer id = member.getID();
        for(Member mem : liste) {
            if(mem.getID() == id) {
                ContainerException e = new ContainerException();
                throw e;
            }
        }
    }
    //Funktionale Anforderung 2
    public String deleteMember(Integer id) {
        Member m = null;
    for(Member mem : liste) {
        if (mem.getID() == id){
            m = mem;
        }
    }
    if(m == null) {
        return "Member konnte nicht gelöscht werden";
    }
    else {
        return " Der Member mit der ID: " + id + " konnte gelöscht werden";
    }
    }


}
