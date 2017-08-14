/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.business.FacadeDB;
import db.exception.DevisChantierBusinessException;

import db.selDto.EnginDuChantierSel;
import db.selDto.EnginSel;
import db.dto.EnginDto;
import db.dto.EnginDuChantierDto;

import db.selDto.MateriauDuChantierSel;
import db.selDto.MateriauSel;
import db.dto.MateriauDto;
import db.dto.MateriauDuChantierDto;

import db.selDto.PetitMaterielDuChantierSel;
import db.selDto.PetitMaterielSel;
import db.dto.PetitMaterielDto;
import db.dto.PetitMaterielDuChantierDto;

import db.selDto.CodeReferenceDuChantierSel;
import db.selDto.CodeReferenceSel;
import db.dto.CodeReferenceDto;
import db.dto.CodeReferenceDuChantierDto;

import db.selDto.OuvrierDuChantierSel;
import db.selDto.OuvrierSel;
import db.dto.OuvrierDto;
import db.dto.OuvrierDuChantierDto;

import java.sql.Date;
import java.util.Calendar;




/**
 *
 * @author Vali
 */
public class Utilitaire {
    
    /*Engins*/
    public static double MontantEngins(EnginSel engin, EnginDuChantierSel sel){
        double montant = 0;
        double quantite = 0;
        double nombreHeures = 0;
        double montantTot = 0;
        
        try {
            EnginDto eng = FacadeDB.findEnginBySel(engin);
            montant = eng.getPrixHeure();
            
            EnginDuChantierDto engCha= FacadeDB.findEnginDuChantierBySel(sel);
            quantite = engCha.getQuantite();
            nombreHeures = engCha.getNombreHeures();
            montantTot = nombreHeures*quantite*montant;
            System.out.println(montantTot);
                    
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static double MontantMateriaux(MateriauSel sel1, MateriauDuChantierSel sel2){
        double montant = 0;
        double quantite = 0;
        double montantTot = 0;
        
        try {
            MateriauDto eng = FacadeDB.findMateriauBySel(sel1);
            montant = eng.getPrixHtva();
            
            MateriauDuChantierDto matCha= FacadeDB.findMateriauDuChantierBySel(sel2);
            quantite = matCha.getQuantite();
            montantTot = quantite*montant;
            System.out.println(montantTot);
                    
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static double MontantPetitsMateriels(PetitMaterielSel sel1, PetitMaterielDuChantierSel sel2){
        double montant = 0;
        double quantite = 0;
        double montantTot = 0;
        
        try {
            PetitMaterielDto eng = FacadeDB.findPetitMaterielBySel(sel1);
            montant = eng.getPrixHtva();
            
            PetitMaterielDuChantierDto pema= FacadeDB.findPetitMaterielDuChantierBySel(sel2);
            quantite = pema.getQuantite();
            montantTot = quantite*montant;
            System.out.println(montantTot);
                    
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }    
    
    public static double MontantCodesReferences(CodeReferenceSel sel1, CodeReferenceDuChantierSel sel2){
        double montant = 0;
        double quantite = 0;
        double montantTot = 0;
        
        try {
            CodeReferenceDto eng = FacadeDB.findCodeReferenceBySel(sel1);
            montant = eng.getPrixHtva();
            
            CodeReferenceDuChantierDto pema= FacadeDB.findCodeReferenceDuChantierBySel(sel2);
            quantite = pema.getQuantite();
            montantTot = quantite*montant;
            System.out.println(montantTot);
                    
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }  

    public static Date CalculAge(OuvrierSel sel1){
        Date dateNai = null;

        
        try {
            OuvrierDto ouv = FacadeDB.findOuvrierBySel(sel1);
            dateNai = ouv.getDateNaissance();
            System.out.println(dateNai);

                    
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return dateNai;
    }  
    
}

