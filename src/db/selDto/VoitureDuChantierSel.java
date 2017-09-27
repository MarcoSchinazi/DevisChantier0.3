/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.selDto;

import java.sql.Date;

/**
 *
 * @author Vali
 */
public class VoitureDuChantierSel {
    
    private int idVoitureDuChantier;
    private Date date;
    private int idVoiture;
    private int idChantier;

    public VoitureDuChantierSel(int idVoitureDuChantier) {
        this.idVoitureDuChantier = idVoitureDuChantier;
    }

    public VoitureDuChantierSel(int idVoitureDuChantier, Date aujourdhui) {
        this.idVoitureDuChantier = idVoitureDuChantier;
        this.date = aujourdhui;
    }

    public VoitureDuChantierSel(int idChantier, String c) {
        this.idChantier = idChantier;
    }
    

    public VoitureDuChantierSel(Date aujourdhui) {
        this.date = aujourdhui;
    }

    public java.sql.Date getDate() {
        return date;
    }
        
    public int getIdVoitureDuChantier() {
        return idVoitureDuChantier;
    }
    
    
    
}
