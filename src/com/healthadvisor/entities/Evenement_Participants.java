
package com.healthadvisor.entities;

public class Evenement_Participants {
    
    private int idEvent;
    private String pLog;

    public Evenement_Participants(Evenement e, Patient p) {
        this.idEvent = e.getId();
        this.pLog = p.getLogin();
    }

    public Evenement_Participants() {
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getpLog() {
        return pLog;
    }

    public void setpLog(String pLog) {
        this.pLog = pLog;
    }

    @Override
    public String toString() {
        return "Evenement_Participants{" + "idEvent=" + idEvent + ", pLog=" + pLog + '}';
    }

    
    

    
    
    
    
}
