package com.miage.spacelib.ressources;

import java.io.Serializable;

public class RQuai implements Serializable {

    private Long id;
    private RStation station;
    private RNavette navette;

    
    RQuai(RStation station){
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

}
