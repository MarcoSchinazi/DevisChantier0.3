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
public class CodeReferenceDuChantierSel {
    
    private int idCodeReferenceDuChantier;    
    private int idCodeRef;
    private int idChantier;

    public CodeReferenceDuChantierSel(int idCodeReferenceDuChantier) {
        this.idCodeReferenceDuChantier = idCodeReferenceDuChantier;
    }

    public CodeReferenceDuChantierSel(int idChantier, String c) {
        this.idChantier = idChantier;
    }
    

    public int getIdCodeReferenceDuChantier() {
        return idCodeReferenceDuChantier;
    }
    
    
    
}
