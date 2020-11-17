package org.hbrs.se.ws20.uebung2;

import org.hbrs.se.ws20.uebung2.Member;

public class MemberGenau implements Member {
    private Integer id = null;

    public MemberGenau(){
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    public Integer setID(Integer id) {
        this.id = id;
    }
}
