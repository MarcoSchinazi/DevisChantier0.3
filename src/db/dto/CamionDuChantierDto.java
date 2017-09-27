/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dto;

import java.sql.Date;

/**
 *
 * @author Vali
 */
public class CamionDuChantierDto extends EntityDto<Integer> {
    
    private Date debutDisponibilite;
    private Date finDisponibilite;
    private int idChantier;
    private int idCamion;
    private double nombreHeures;

    public CamionDuChantierDto(int idCamionDuChantier, Date debutDisponibilite, Date finDisponibilite, double nombreHeures, int idChantier, int idCamion ) {
        this.id = idCamionDuChantier;
        this.debutDisponibilite = debutDisponibilite;
        this.finDisponibilite = finDisponibilite;
        this.idChantier = idChantier;
        this.idCamion = idCamion;
        this.nombreHeures = nombreHeures;
    }

    public Date getDebutDisponibilite() {
        return debutDisponibilite;
    }

    public Date getFinDisponibilite() {
        return finDisponibilite;
    }

    public int getIdChantier() {
        return idChantier;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public double getNombreHeures() {
        return nombreHeures;
    }

    
    
    
    
    
}
