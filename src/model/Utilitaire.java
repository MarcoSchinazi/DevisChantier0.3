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

import db.selDto.ConducteurDuChantierSel;
import db.selDto.ConducteurSel;
import db.dto.ConducteurDto;
import db.dto.ConducteurDuChantierDto;

import db.selDto.VoitureDuChantierSel;
import db.selDto.VoitureSel;
import db.dto.VoitureDto;
import db.dto.VoitureDuChantierDto;

import db.selDto.CamionDuChantierSel;
import db.selDto.CamionSel;
import db.dto.CamionDto;
import db.dto.CamionDuChantierDto;

import db.dto.DevisDto;

import db.dto.ClientDto;

import db.dto.ChantierDto;

import java.sql.Date;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class Utilitaire {

    /*Engins*/
    public static double montantEngins(EnginSel engin, EnginDuChantierSel sel) {
        double montant = 0;
        double quantite = 0;
        double nombreHeures = 0;
        double montantTot = 0;

        try {
            EnginDto eng = FacadeDB.findEnginBySel(engin);
            montant = eng.getPrixHeure();

            EnginDuChantierDto engCha = FacadeDB.findEnginDuChantierBySel(sel);
            quantite = engCha.getQuantite();
            nombreHeures = engCha.getNombreHeures();
            montantTot = nombreHeures * quantite * montant;

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertEngin(EnginDto eng) {
        try {
            FacadeDB.addEngin(eng);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteEngin(int idEng) {
        try {
            FacadeDB.deleteEngin(idEng);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateEngin(EnginDto eng) {
        try {
            FacadeDB.updateEngin(eng);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*EnginsDuChantier*/
    public static boolean insertEnginDuChantier(EnginDuChantierDto engcha) {
        try {
            FacadeDB.addEnginDuChantier(engcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteEnginDuChantier(int idEngcha) {
        try {
            FacadeDB.deleteEnginDuChantier(idEngcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateEnginDuChantier(EnginDuChantierDto engcha) {
        try {
            FacadeDB.updateEnginDuChantier(engcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Materiaux*/
    public static double montantMateriaux(MateriauSel sel1, MateriauDuChantierSel sel2) {
        double montant = 0;
        double quantite = 0;
        double montantTot = 0;

        try {
            MateriauDto eng = FacadeDB.findMateriauBySel(sel1);
            montant = eng.getPrixHtva();

            MateriauDuChantierDto matCha = FacadeDB.findMateriauDuChantierBySel(sel2);
            quantite = matCha.getQuantite();
            montantTot = quantite * montant;

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertMateriau(MateriauDto mat) {
        try {
            FacadeDB.addMateriau(mat);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteMateriau(int idMat) {
        try {
            FacadeDB.deleteMateriau(idMat);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateMateriau(MateriauDto mat) {
        try {
            FacadeDB.updateMateriau(mat);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*MateriauxDuChantier*/
    public static boolean insertMateriauDuChantier(MateriauDuChantierDto matcha) {
        try {
            FacadeDB.addMateriauDuChantier(matcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteMateriauDuChantier(int idMatcha) {
        try {
            FacadeDB.deleteEnginDuChantier(idMatcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateMateriauDuChantier(MateriauDuChantierDto matcha) {
        try {
            FacadeDB.updateMateriauDuChantier(matcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Petits Materiels*/
    public static double montantPetitsMateriels(PetitMaterielSel sel1, PetitMaterielDuChantierSel sel2) {
        double montant = 0;
        double quantite = 0;
        double montantTot = 0;

        try {
            PetitMaterielDto eng = FacadeDB.findPetitMaterielBySel(sel1);
            montant = eng.getPrixHtva();

            PetitMaterielDuChantierDto pema = FacadeDB.findPetitMaterielDuChantierBySel(sel2);
            quantite = pema.getQuantite();
            montantTot = quantite * montant;

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertPetitMateriel(PetitMaterielDto pemat) {
        try {
            FacadeDB.addPetitMateriel(pemat);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deletePetitMateriel(int idPemat) {
        try {
            FacadeDB.deletePetitMateriel(idPemat);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updatePetitMateriel(PetitMaterielDto pemat) {
        try {
            FacadeDB.updatePetitMateriel(pemat);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*PetitsMaterielsDuChantier*/
    public static boolean insertPetitMaterielDuChantier(PetitMaterielDuChantierDto pematcha) {
        try {
            FacadeDB.addPetitMaterielDuChantier(pematcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deletePetitMaterielDuChantier(int idPematcha) {
        try {
            FacadeDB.deletePetitMaterielDuChantier(idPematcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updatePetitMaterielDuChantier(PetitMaterielDuChantierDto pematcha) {
        try {
            FacadeDB.updatePetitMaterielDuChantier(pematcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Codes References*/
    public static double montantCodesReferences(CodeReferenceSel sel1, CodeReferenceDuChantierSel sel2) {
        double montant = 0;
        double quantite = 0;
        double montantTot = 0;

        try {
            CodeReferenceDto eng = FacadeDB.findCodeReferenceBySel(sel1);
            montant = eng.getPrixHtva();

            CodeReferenceDuChantierDto pema = FacadeDB.findCodeReferenceDuChantierBySel(sel2);
            quantite = pema.getQuantite();
            montantTot = quantite * montant;

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertCodeReference(CodeReferenceDto core) {
        try {
            FacadeDB.addCodeReference(core);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteCodeReference(int idpemat) {
        try {
            FacadeDB.deleteCodeReference(idpemat);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateCodeReference(CodeReferenceDto core) {
        try {
            FacadeDB.updateCodeReference(core);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static double supplement(CodeReferenceSel sel1, CodeReferenceDuChantierSel sel2, int supp) {
        double montant = 0;
        double quantite = 0;
        double montantTot = 0;
        try {
            CodeReferenceDto eng = FacadeDB.findCodeReferenceBySel(sel1);
            montant = eng.getPrixHtva();
            CodeReferenceDuChantierDto pema = FacadeDB.findCodeReferenceDuChantierBySel(sel2);
            quantite = pema.getQuantite();
            montantTot = montant * quantite * supp;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(montantTot);
        return montantTot;
    }

    /*CodesReferencesDuChantier*/
    public static boolean insertCodeReferenceDuChantier(CodeReferenceDuChantierDto corecha) {
        try {
            FacadeDB.addCodeReferenceDuChantier(corecha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteCodeReferenceDuChantier(int idCorecha) {
        try {
            FacadeDB.deleteCodeReferenceDuChantier(idCorecha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateCodeReferenceDuChantier(CodeReferenceDuChantierDto corecha) {
        try {
            FacadeDB.updateCodeReferenceDuChantier(corecha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Ouvriers*/
    public static Date calculAge(OuvrierSel sel1) {
        Date creation = null;
        try {
            OuvrierDto ouv = FacadeDB.findOuvrierBySel(sel1);
            creation = ouv.getDateNaissance();

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return creation;
    }

    public static double montantOuvriers(OuvrierDto dto, OuvrierDuChantierDto dto2) {
        double cout = 0;
        double nombreHeures = 0;
        double montantTot = 0;
 
        cout = dto.getRemuneration();
        nombreHeures = dto2.getNombreHeures();
        montantTot = cout * nombreHeures;

        return montantTot;
    }

    public static Double montantOuvriers(OuvrierDuChantierSel sel) {
        Double montantTot = 0.0;
        try {
            Collection<OuvrierDuChantierDto> ouvChantier = FacadeDB.findOuvriersDuChantierBySel(sel);
            for (OuvrierDuChantierDto o : ouvChantier) {
                double nombreHeures = 0;
                double cout = 0;
                OuvrierSel s = new OuvrierSel(o.getIdOuvrier());
                OuvrierDto ouvrier = FacadeDB.findOuvrierBySel(s);
                cout = ouvrier.getRemuneration();
                nombreHeures = o.getNombreHeures();
                montantTot += cout * nombreHeures;
            }
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertOuvrier(OuvrierDto ouv) {
        try {
            FacadeDB.addOuvrier(ouv);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteOuvrier(int idOuv) {
        try {
            FacadeDB.deleteOuvrier(idOuv);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateOuvrier(OuvrierDto ouv) {
        try {
            FacadeDB.updateOuvrier(ouv);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*OuvrierDuChantier*/
    public static boolean insertOuvrierDuChantier(OuvrierDuChantierDto ouvcha) {
        try {
            FacadeDB.addOuvrierDuChantier(ouvcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteOuvrierDuChantier(int idOuvcha) {
        try {
            FacadeDB.deleteOuvrierDuChantier(idOuvcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateOuvrierDuChantier(OuvrierDuChantierDto ouvcha) {
        try {
            FacadeDB.updateOuvrierDuChantier(ouvcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Conducteurs*/
    public static double montantConducteurs(ConducteurSel sel1, ConducteurDuChantierSel sel2) {
        double cout = 0;
        double nombreHeures = 0;
        double montantTot = 0;

        try {
            ConducteurDto con = FacadeDB.findConducteurBySel(sel1);
            cout = con.getRemuneration();

            ConducteurDuChantierDto concha = FacadeDB.findConducteurDuChantierBySel(sel2);
            nombreHeures = concha.getNombreHeures();
            montantTot = cout * nombreHeures;

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertConducteur(ConducteurDto con) {
        try {
            FacadeDB.addConducteur(con);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteConducteur(int idCon) {
        try {
            FacadeDB.deleteConducteur(idCon);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateConducteur(ConducteurDto con) {
        try {
            FacadeDB.updateConducteur(con);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*ConducteurDuChantier*/
    public static boolean insertConducteurDuChantier(ConducteurDuChantierDto concha) {
        try {
            FacadeDB.addConducteurDuChantier(concha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteConducteurDuChantier(int idConcha) {
        try {
            FacadeDB.deleteConducteurDuChantier(idConcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateConducteurDuChantier(ConducteurDuChantierDto concha) {
        try {
            FacadeDB.updateConducteurDuChantier(concha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Clients*/
    public static boolean insertClient(ClientDto cli) {
        try {
            FacadeDB.addClient(cli);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteClient(int idCli) {
        try {
            FacadeDB.deleteClient(idCli);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateClient(ClientDto cli) {
        try {
            FacadeDB.updateClient(cli);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Voitures*/
    public static double montantVoitures(VoitureSel voit, VoitureDuChantierSel sel) {
        double montant = 0;
        double nombreJours = 0;
        double montantTot = 0;

        try {
            VoitureDto eng = FacadeDB.findVoitureBySel(voit);
            montant = eng.getPrixHtva();

            VoitureDuChantierDto voiCha = FacadeDB.findVoitureDuChantierBySel(sel);
            nombreJours = voiCha.getNombreJours();
            montantTot = nombreJours * montant;

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertVoiture(VoitureDto voi) {
        try {
            FacadeDB.addVoiture(voi);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteVoiture(int idVoi) {
        try {
            FacadeDB.deleteVoiture(idVoi);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateVoiture(VoitureDto voi) {
        try {
            FacadeDB.updateVoiture(voi);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*VoituresDuChantier*/
    public static boolean insertVoitureDuChantier(VoitureDuChantierDto voicha) {
        try {
            FacadeDB.addVoitureDuChantier(voicha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteVoitureDuChantier(int idVoicha) {
        try {
            FacadeDB.deleteVoitureDuChantier(idVoicha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateVoitureDuChantier(VoitureDuChantierDto voicha) {
        try {
            FacadeDB.updateVoitureDuChantier(voicha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public static Double montantCamions(CamionDuChantierSel sel) {
        Double montantTot = 0.0;
        try {
            Collection<CamionDuChantierDto> camChantier = FacadeDB.findCamionsDuChantierBySel(sel);
            for (CamionDuChantierDto o : camChantier) {
                double nombreHeures = 0;
                double cout = 0;
                CamionSel s = new CamionSel(o.getIdCamion());
                CamionDto camion = FacadeDB.findCamionBySel(s);
                cout = camion.getPrixHtva();
                nombreHeures = o.getNombreHeures();
                montantTot += cout * nombreHeures;
            }
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return montantTot;
    }

    public static boolean insertCamion(CamionDto cam) {
        try {
            FacadeDB.addCamion(cam);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteCamion(int idCam) {
        try {
            FacadeDB.deleteCamion(idCam);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateCamion(CamionDto cam) {
        try {
            FacadeDB.updateCamion(cam);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*CamionsDuChantier*/
    public static boolean insertCamionDuChantier(CamionDuChantierDto camcha) {
        try {
            FacadeDB.addCamionDuChantier(camcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteCamionDuChantier(int idCamcha) {
        try {
            FacadeDB.deleteCamionDuChantier(idCamcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateCamionDuChantier(CamionDuChantierDto camcha) {
        try {
            FacadeDB.updateCamionDuChantier(camcha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Devis*/
    public static boolean insertDevis(DevisDto dev) {
        try {
            FacadeDB.addDevis(dev);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteDevis(int idDev) {
        try {
            FacadeDB.deleteDevis(idDev);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateDevis(DevisDto dev) {
        try {
            FacadeDB.updateDevis(dev);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /*Chantier*/
    public static boolean insertChantier(ChantierDto cha) {
        try {
            FacadeDB.addChantier(cha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean deleteChantier(int idCha) {
        try {
            FacadeDB.deleteChantier(idCha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static boolean updateChantier(ChantierDto cha) {
        try {
            FacadeDB.updateChantier(cha);
            return true;
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
