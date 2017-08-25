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
public class CamionDuChantierSel {

    private int idCamionDuChantier;
    private int idChantier;

    public CamionDuChantierSel(int idCamionDuChantier) {
        this.idCamionDuChantier = idCamionDuChantier;
    }

    public int getIdCamionDuChantier() {
        return idCamionDuChantier;
    }

    public CamionDuChantierSel(int idChantier, String s) {
        this.idChantier = idChantier;
    }

    public int getIdChantier() {
        return idChantier;
    }

}
