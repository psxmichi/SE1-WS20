package org.hbrs.se.ws20.uebung2;
import java.util.ArrayList;
import java.util.List;

public class Container {

    private List <Member> liste = new ArrayList<Member>();
    public void addMember(Member member) throws ContainerException {
    Integer id = member.getID();
    for(Member mem : liste){
        if(mem.getID() == id){
            ContainerException e = new ContainerException();
            throw e;
        }
    }
    }


}
