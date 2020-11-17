package org.hbrs.se.ws20.uebung2.control;

import org.hbrs.se.ws20.uebung2.test.MemberGenau;

import java.util.ArrayList;
import java.util.Iterator;
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

    // Funktionale Anforderung 3
    public void dump() {
        Iterator<Member> i = liste.iterator();
            while(i.hasNext()) {
                Member m = i.next();
                System.out.println("Member (id=" + m.getID() + ")");
            }
    }
    //Funktionale Anforderung 4
    public int size(){
        return liste.size();
    }

}
