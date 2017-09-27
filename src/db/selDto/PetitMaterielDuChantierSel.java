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
public class PetitMaterielDuChantierSel {
    
    private int idPetitMaterielDuChantier;
    private int idPetitMateriel;
    private int idChantier; 

    public PetitMaterielDuChantierSel(int idPetitMaterielDuChantier) {
        this.idPetitMaterielDuChantier = idPetitMaterielDuChantier;
    }

    public PetitMaterielDuChantierSel(int idChantier, String c) {
        this.idChantier = idChantier;
    }

    public int getidPetitMaterielDuChantier() {
        return idPetitMaterielDuChantier;
    }
    
    
    
}
