/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import java.util.Collection;
import db.db.DBManager;
import db.exception.DevisChantierDbException;
import db.exception.DevisChantierBusinessException;
import db.dto.CamionDto;
import db.selDto.CamionSel;
import db.dto.CamionDuChantierDto;
import db.selDto.CamionDuChantierSel;
import db.dto.ChantierDto;
import db.selDto.ChantierSel;
import db.dto.ClientDto;
import db.selDto.ClientSel;
import db.dto.CodeReferenceDto;
import db.selDto.CodeReferenceSel;  
import db.dto.CodeReferenceDuChantierDto;
import db.selDto.CodeReferenceDuChantierSel;    
import db.dto.ConducteurDto;
import db.selDto.ConducteurSel;  
import db.dto.ConducteurDuChantierDto;
import db.selDto.ConducteurDuChantierSel;    
import db.dto.DevisDto;
import db.selDto.DevisSel;  
import db.dto.EnginDto;
import db.selDto.EnginSel;   
import db.dto.EnginDuChantierDto;
import db.selDto.EnginDuChantierSel;  
import db.dto.MateriauDto;
import db.selDto.MateriauSel;  
import db.dto.MateriauDuChantierDto;
import db.selDto.MateriauDuChantierSel;  
import db.dto.OuvrierDto;
import db.selDto.OuvrierSel;  
import db.dto.OuvrierDuChantierDto;
import db.selDto.OuvrierDuChantierSel;  
import db.dto.PatronDto;
import db.selDto.PatronSel;   
import db.dto.PetitMaterielDto;
import db.selDto.PetitMaterielSel;   
import db.dto.PetitMaterielDuChantierDto;
import db.selDto.PetitMaterielDuChantierSel;   
import db.dto.VoitureDto;
import db.selDto.VoitureSel;   
import db.dto.VoitureDuChantierDto;
import db.selDto.VoitureDuChantierSel; 

/**
 *
 * @author Vali
 * Classe reprenant les méthodes destinées à l'utilisateur
 */
public class FacadeDB {
    
    /*Camion*/
    public static Collection<CamionDto> getAllCamion() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CamionDto> col = CamionBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Camions inaccessible! \n" + msg);
            }
        }
            
    }

    public static CamionDto findCamionBySel(CamionSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CamionDto> col = CamionBL.findBySel(sel);
            CamionDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Camion par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addCamion(CamionDto caDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = CamionBL.add(caDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du camion impossible ! \n" + msg);
            }
        }
    }

    public static void updateCamion(CamionDto caDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CamionBL.update(caDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du camion impossible ! \n" + msg);
            }
        }
    }

    public static void deleteCamion(int idCamion) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CamionBL.delete(idCamion);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du camion impossible ! \n" + msg);
            }
        }
    }    
   
    /*CamionDuChantier*/
    public static Collection<CamionDuChantierDto> getAllCamionDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CamionDuChantierDto> col = CamionDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des CamionDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static CamionDuChantierDto findCamionDuChantierBySel(CamionDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CamionDuChantierDto> col = CamionDuChantierBL.findBySel(sel);
            CamionDuChantierDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("CamionDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }
    
    public static Collection<CamionDuChantierDto> findCamionsDuChantierBySel(CamionDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CamionDuChantierDto> col = CamionDuChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("CamionDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addCamionDuChantier(CamionDuChantierDto cachaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = CamionDuChantierBL.add(cachaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du CamionDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateCamionDuChantier(CamionDuChantierDto cachaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CamionDuChantierBL.update(cachaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du CamionDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteCamionDuChantier(int idCamionDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CamionDuChantierBL.delete(idCamionDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du CamionDuChantier impossible ! \n" + msg);
            }
        }
    }     
    
    /*Chantier*/
    public static Collection<ChantierDto> getAllChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ChantierDto> col = ChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Chantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static ChantierDto findChantierBySel(ChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ChantierDto> col = ChantierBL.findBySel(sel);
            ChantierDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Chantier par ID introuvable ! \n" + msg);
            }
        }
    }
    
    public static Collection<ChantierDto> findChantiersBySel(ChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ChantierDto> col = ChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Chantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addChantier(ChantierDto chaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = ChantierBL.add(chaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Chantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateChantier(ChantierDto chaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ChantierBL.update(chaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Chantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteChantier(int idChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ChantierBL.delete(idChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Chantier impossible ! \n" + msg);
            }
        }
    }       

    /*Client*/
    public static Collection<ClientDto> getAllClient() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ClientDto> col = ClientBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Clients inaccessible! \n" + msg);
            }
        }
            
    }

    public static ClientDto findClientBySel(ClientSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ClientDto> col = ClientBL.findBySel(sel);
            ClientDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Client par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addClient(ClientDto cliDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = ClientBL.add(cliDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Client impossible ! \n" + msg);
            }
        }
    }

    public static void updateClient(ClientDto cliDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ClientBL.update(cliDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Client impossible ! \n" + msg);
            }
        }
    }

    public static void deleteClient(int idClient) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ClientBL.delete(idClient);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Client impossible ! \n" + msg);
            }
        }
    } 
 
    /*CodeReference*/
    public static Collection<CodeReferenceDto> getAllCodeReference() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CodeReferenceDto> col = CodeReferenceBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des CodeReferences inaccessible! \n" + msg);
            }
        }
            
    }

    public static CodeReferenceDto findCodeReferenceBySel(CodeReferenceSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CodeReferenceDto> col = CodeReferenceBL.findBySel(sel);
            CodeReferenceDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("CodeReference par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addCodeReference(CodeReferenceDto corefDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = CodeReferenceBL.add(corefDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du CodeReference impossible ! \n" + msg);
            }
        }
    }

    public static void updateCodeReference(CodeReferenceDto corefDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CodeReferenceBL.update(corefDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du CodeReference impossible ! \n" + msg);
            }
        }
    }

    public static void deleteCodeReference(int idCodeReference) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CodeReferenceBL.delete(idCodeReference);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du CodeReference impossible ! \n" + msg);
            }
        }
    }      

    /*CodeReferenceDuChantier*/
    public static Collection<CodeReferenceDuChantierDto> getAllCodeReferenceDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CodeReferenceDuChantierDto> col = CodeReferenceDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des CodeReferenceDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static Collection<CodeReferenceDuChantierDto> findCodesReferencesDuChantierBySel(CodeReferenceDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CodeReferenceDuChantierDto> col = CodeReferenceDuChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("CodeReferenceDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addCodeReferenceDuChantier(CodeReferenceDuChantierDto corefchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = CodeReferenceDuChantierBL.add(corefchaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du CodeReferenceDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateCodeReferenceDuChantier(CodeReferenceDuChantierDto corefchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CodeReferenceDuChantierBL.update(corefchaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du CodeReferenceDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteCodeReferenceDuChantier(int idCodeReferenceDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CodeReferenceDuChantierBL.delete(idCodeReferenceDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du CodeReferenceDuChantier impossible ! \n" + msg);
            }
        }
    }     

    /*Conducteur*/
    public static Collection<ConducteurDto> getAllConducteur() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ConducteurDto> col = ConducteurBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Conducteurs inaccessible! \n" + msg);
            }
        }
            
    }

    public static ConducteurDto findConducteurBySel(ConducteurSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ConducteurDto> col = ConducteurBL.findBySel(sel);
            ConducteurDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Conducteur par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addConducteur(ConducteurDto conDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = ConducteurBL.add(conDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Conducteur impossible ! \n" + msg);
            }
        }
    }

    public static void updateConducteur(ConducteurDto conDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ConducteurBL.update(conDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Conducteur impossible ! \n" + msg);
            }
        }
    }

    public static void deleteConducteur(int idConducteur) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ConducteurBL.delete(idConducteur);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Conducteur impossible ! \n" + msg);
            }
        }
    }         


    /*ConducteurDuChantier*/
    public static Collection<ConducteurDuChantierDto> getAllConducteurDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ConducteurDuChantierDto> col = ConducteurDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des ConducteurDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static Collection<ConducteurDuChantierDto> findConducteursDuChantierBySel(ConducteurDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<ConducteurDuChantierDto> col = ConducteurDuChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("ConducteurDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addConducteurDuChantier(ConducteurDuChantierDto conchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = ConducteurDuChantierBL.add(conchaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du ConducteurDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateConducteurDuChantier(ConducteurDuChantierDto conchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ConducteurDuChantierBL.update(conchaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du ConducteurDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteConducteurDuChantier(int idConducteurDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            ConducteurDuChantierBL.delete(idConducteurDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du ConducteurDuChantier impossible ! \n" + msg);
            }
        }
    }   

 

    /*Devis*/
    public static Collection<DevisDto> getAllDevis() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<DevisDto> col = DevisBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Devis inaccessible! \n" + msg);
            }
        }
            
    }

    public static DevisDto findDevisBySel(DevisSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<DevisDto> col = DevisBL.findBySel(sel);
            DevisDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Devis par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addDevis(DevisDto devDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = DevisBL.add(devDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Devis impossible ! \n" + msg);
            }
        }
    }

    public static void updateDevis(DevisDto devDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            DevisBL.update(devDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Devis impossible ! \n" + msg);
            }
        }
    }

    public static void deleteDevis(int idDevis) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            DevisBL.delete(idDevis);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Devis impossible ! \n" + msg);
            }
        }
    }    



    /*Engin*/
    public static Collection<EnginDto> getAllEngin() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<EnginDto> col = EnginBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Engins inaccessible! \n" + msg);
            }
        }
            
    }

    public static EnginDto findEnginBySel(EnginSel eng) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<EnginDto> col = EnginBL.findBySel(eng);
            EnginDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Engin par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addEngin(EnginDto engDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = EnginBL.add(engDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Engin impossible ! \n" + msg);
            }
        }
    }

    public static void updateEngin(EnginDto engDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            EnginBL.update(engDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Engin impossible ! \n" + msg);
            }
        }
    }

    public static void deleteEngin(int idEngin) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            EnginBL.delete(idEngin);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Engin impossible ! \n" + msg);
            }
        }
    }         
    


    /*EnginDuChantier*/
    public static Collection<EnginDuChantierDto> getAllEnginDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<EnginDuChantierDto> col = EnginDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des EnginDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static Collection<EnginDuChantierDto> findEnginsDuChantierBySel(EnginDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<EnginDuChantierDto> col = EnginDuChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("EnginDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addEnginDuChantier(EnginDuChantierDto engchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = EnginDuChantierBL.add(engchaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du EnginDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateEnginDuChantier(EnginDuChantierDto engchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            EnginDuChantierBL.update(engchaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du EnginDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteEnginDuChantier(int idEnginDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            EnginDuChantierBL.delete(idEnginDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du EnginDuChantier impossible ! \n" + msg);
            }
        }
    }     

  

    /*Materiau*/
    public static Collection<MateriauDto> getAllMateriau() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<MateriauDto> col = MateriauBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Materiaus inaccessible! \n" + msg);
            }
        }
            
    }

    public static MateriauDto findMateriauBySel(MateriauSel mat) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<MateriauDto> col = MateriauBL.findBySel(mat);
            MateriauDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Materiau par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addMateriau(MateriauDto maDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = MateriauBL.add(maDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Materiau impossible ! \n" + msg);
            }
        }
    }

    public static void updateMateriau(MateriauDto maDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            MateriauBL.update(maDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Materiau impossible ! \n" + msg);
            }
        }
    }

    public static void deleteMateriau(int idMateriau) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            MateriauBL.delete(idMateriau);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Materiau impossible ! \n" + msg);
            }
        }
    }     
 
  

    /*MateriauDuChantier*/
    public static Collection<MateriauDuChantierDto> getAllMateriauDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<MateriauDuChantierDto> col = MateriauDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des MateriauDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static Collection<MateriauDuChantierDto> findMateriauxDuChantierBySel(MateriauDuChantierSel matcha) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<MateriauDuChantierDto> col = MateriauDuChantierBL.findBySel(matcha);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("MateriauDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addMateriauDuChantier(MateriauDuChantierDto machaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = MateriauDuChantierBL.add(machaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du MateriauDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateMateriauDuChantier(MateriauDuChantierDto machaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            MateriauDuChantierBL.update(machaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du MateriauDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteMateriauDuChantier(int idMateriauDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            MateriauDuChantierBL.delete(idMateriauDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du MateriauDuChantier impossible ! \n" + msg);
            }
        }
    }         
    
    /*Ouvrier*/
    public static Collection<OuvrierDto> getAllOuvrier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<OuvrierDto> col = OuvrierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Ouvriers inaccessible! \n" + msg);
            }
        }
            
    }

    public static OuvrierDto findOuvrierBySel(OuvrierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<OuvrierDto> col = OuvrierBL.findBySel(sel);
            OuvrierDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ouvrier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addOuvrier(OuvrierDto ouvDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = OuvrierBL.add(ouvDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Ouvrier impossible ! \n" + msg);
            }
        }
    }

    public static void updateOuvrier(OuvrierDto ouvDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            OuvrierBL.update(ouvDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Ouvrier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteOuvrier(int idOuvrier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            OuvrierBL.delete(idOuvrier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Ouvrier impossible ! \n" + msg);
            }
        }
    }  

    /*OuvrierDuChantier*/
    public static Collection<OuvrierDuChantierDto> getAllOuvrierDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<OuvrierDuChantierDto> col = OuvrierDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des OuvrierDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static OuvrierDuChantierDto findOuvrierDuChantierBySel(OuvrierDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<OuvrierDuChantierDto> col = OuvrierDuChantierBL.findBySel(sel);
            OuvrierDuChantierDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("OuvrierDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }
    
    public static Collection<OuvrierDuChantierDto> findOuvriersDuChantierBySel(OuvrierDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<OuvrierDuChantierDto> col = OuvrierDuChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("OuvrierDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addOuvrierDuChantier(OuvrierDuChantierDto ouvchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = OuvrierDuChantierBL.add(ouvchaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du OuvrierDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateOuvrierDuChantier(OuvrierDuChantierDto ouvchaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            OuvrierDuChantierBL.update(ouvchaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du OuvrierDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteOuvrierDuChantier(int idOuvrierDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            OuvrierDuChantierBL.delete(idOuvrierDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du OuvrierDuChantier impossible ! \n" + msg);
            }
        }
    }     

    /*Patron*/
    public static Collection<PatronDto> getAllPatron() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<PatronDto> col = PatronBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Patrons inaccessible! \n" + msg);
            }
        }
            
    }

    public static PatronDto findPatronBySel(PatronSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<PatronDto> col = PatronBL.findBySel(sel);
            PatronDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Patron par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addPatron(PatronDto patDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = PatronBL.add(patDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Patron impossible ! \n" + msg);
            }
        }
    }

    public static void updatePatron(PatronDto patDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            PatronBL.update(patDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Patron impossible ! \n" + msg);
            }
        }
    }

    public static void deletePatron(int idPatron) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            PatronBL.delete(idPatron);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Patron impossible ! \n" + msg);
            }
        }
    }     

    /*PetitMateriel*/
    public static Collection<PetitMaterielDto> getAllPetitMateriel() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<PetitMaterielDto> col = PetitMaterielBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des PetitMateriels inaccessible! \n" + msg);
            }
        }
            
    }

    public static PetitMaterielDto findPetitMaterielBySel(PetitMaterielSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<PetitMaterielDto> col = PetitMaterielBL.findBySel(sel);
            PetitMaterielDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("PetitMateriel par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addPetitMateriel(PetitMaterielDto pemaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = PetitMaterielBL.add(pemaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du PetitMateriel impossible ! \n" + msg);
            }
        }
    }

    public static void updatePetitMateriel(PetitMaterielDto pemaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            PetitMaterielBL.update(pemaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du PetitMateriel impossible ! \n" + msg);
            }
        }
    }

    public static void deletePetitMateriel(int idPetitMateriel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            PetitMaterielBL.delete(idPetitMateriel);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du PetitMateriel impossible ! \n" + msg);
            }
        }
    }     

    /*PetitMaterielDuChantier*/
    public static Collection<PetitMaterielDuChantierDto> getAllPetitMaterielDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<PetitMaterielDuChantierDto> col = PetitMaterielDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des PetitMaterielDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static Collection<PetitMaterielDuChantierDto> findPetitsMaterielsDuChantierBySel(PetitMaterielDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<PetitMaterielDuChantierDto> col = PetitMaterielDuChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("PetitMaterielDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addPetitMaterielDuChantier(PetitMaterielDuChantierDto pemachaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = PetitMaterielDuChantierBL.add(pemachaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du PetitMaterielDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updatePetitMaterielDuChantier(PetitMaterielDuChantierDto pemachaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            PetitMaterielDuChantierBL.update(pemachaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du PetitMaterielDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deletePetitMaterielDuChantier(int idPetitMaterielDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            PetitMaterielDuChantierBL.delete(idPetitMaterielDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du PetitMaterielDuChantier impossible ! \n" + msg);
            }
        }
    }     

    /*Voiture*/
    public static Collection<VoitureDto> getAllVoiture() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<VoitureDto> col = VoitureBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Voitures inaccessible! \n" + msg);
            }
        }
            
    }

    public static VoitureDto findVoitureBySel(VoitureSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<VoitureDto> col = VoitureBL.findBySel(sel);
            VoitureDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Voiture par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addVoiture(VoitureDto voiDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = VoitureBL.add(voiDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du Voiture impossible ! \n" + msg);
            }
        }
    }

    public static void updateVoiture(VoitureDto voiDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            VoitureBL.update(voiDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du Voiture impossible ! \n" + msg);
            }
        }
    }

    public static void deleteVoiture(int idVoiture) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            VoitureBL.delete(idVoiture);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du Voiture impossible ! \n" + msg);
            }
        }
    }     

    /*VoitureDuChantier*/
    public static Collection<VoitureDuChantierDto> getAllVoitureDuChantier() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<VoitureDuChantierDto> col = VoitureDuChantierBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des VoitureDuChantiers inaccessible! \n" + msg);
            }
        }
            
    }

    public static Collection<VoitureDuChantierDto> findVoituresDuChantierBySel(VoitureDuChantierSel sel) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<VoitureDuChantierDto> col = VoitureDuChantierBL.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("VoitureDuChantier par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addVoitureDuChantier(VoitureDuChantierDto voichaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = VoitureDuChantierBL.add(voichaDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du VoitureDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void updateVoitureDuChantier(VoitureDuChantierDto voichaDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            VoitureDuChantierBL.update(voichaDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du VoitureDuChantier impossible ! \n" + msg);
            }
        }
    }

    public static void deleteVoitureDuChantier(int idVoitureDuChantier) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            VoitureDuChantierBL.delete(idVoitureDuChantier);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du VoitureDuChantier impossible ! \n" + msg);
            }
        }
    }         
    
}
