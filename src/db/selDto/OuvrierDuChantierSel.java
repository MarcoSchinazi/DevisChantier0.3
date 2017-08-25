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
public class OuvrierDuChantierSel {
    
    private int idOuvrierDuChantier;
    private int idOuvrier;
    private int idChantier;

    public OuvrierDuChantierSel(int idOuvrierDuChantier) {
        this.idOuvrierDuChantier = idOuvrierDuChantier;
    }

    public OuvrierDuChantierSel(int idOuvrier, int idChantier) {
        this.idOuvrier = idOuvrier;
        this.idChantier = idChantier;
    }
    public OuvrierDuChantierSel(int idOuvrier, boolean b) {
        this.idOuvrier = idOuvrier;
    }
    
    public OuvrierDuChantierSel(int idChantier, String c) {
        this.idChantier = idChantier;
    }

    public int getIdOuvrierDuChantier() {
        return idOuvrierDuChantier;
    }

    public int getIdOuvrier() {
        return idOuvrier;
    }

    public int getIdChantier() {
        return idChantier;
    }
    
    
    
}
