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
public class ConducteurDuChantierSel {
    
    private int idConducteurDuChantier;
    private int idMateriau;
    private int idChantier;
    
    public ConducteurDuChantierSel(int idConducteurDuChantier) {
        this.idConducteurDuChantier = idConducteurDuChantier;
    }

    public ConducteurDuChantierSel(int idChantier, String c) {
        this.idChantier = idChantier;
    }
    

    public int getIdConducteurDuChantier() {
        return idConducteurDuChantier;
    }
   
    
    
}
