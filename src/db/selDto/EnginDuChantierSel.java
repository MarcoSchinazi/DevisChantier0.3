/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.selDto;

/**
 *
 * @author Vali
 */
public class EnginDuChantierSel {
    
    private int idEnginDuChantier;
    private int idEngin;
    private int idChantier;

    public EnginDuChantierSel(int idEnginDuChantier) {
        this.idEnginDuChantier = idEnginDuChantier;
    }

    public EnginDuChantierSel(int idChantier, String c) {
        this.idChantier = idChantier;
    }
    

    public int getIdEnginDuChantier() {
        return idEnginDuChantier;
    }
    
    
    
}
