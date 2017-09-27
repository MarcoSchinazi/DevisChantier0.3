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
public class MateriauDuChantierSel {

    private int idMateriauDuChantier;
    private int idMateriau;
    private int idChantier;

    public MateriauDuChantierSel(int idMateriauDuChantier) {
        this.idMateriauDuChantier = idMateriauDuChantier;
    }

    public MateriauDuChantierSel(int idChantier, String c) {
        this.idChantier = idChantier;
    }
    

    public int getIdMateriauDuChantier() {
        return idMateriauDuChantier;
    }

}
