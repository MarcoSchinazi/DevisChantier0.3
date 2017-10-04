/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.selDto.EnginDuChantierSel;
import db.selDto.EnginSel;

import db.selDto.MateriauDuChantierSel;
import db.selDto.MateriauSel;

import db.selDto.PetitMaterielDuChantierSel;
import db.selDto.PetitMaterielSel;

import db.selDto.CodeReferenceDuChantierSel;
import db.selDto.CodeReferenceSel;

import db.selDto.OuvrierDuChantierSel;
import db.selDto.OuvrierSel;

import db.selDto.ConducteurDuChantierSel;
import db.selDto.ConducteurSel;

import db.selDto.VoitureDuChantierSel;
import db.selDto.VoitureSel;

import db.selDto.CamionDuChantierSel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Utilitaire;
import static javafx.application.Application.launch;

/**
 *
 * @author Vali
 */
public class Test extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        launch(args);

        //COUT ENGIN
        EnginSel eng = new EnginSel(1);
        EnginDuChantierSel sel = new EnginDuChantierSel(1);
        Utilitaire.montantEngins(eng, sel);

        //COUT MATERIAU
        MateriauSel mat = new MateriauSel(1);
        MateriauDuChantierSel macha = new MateriauDuChantierSel(1);
        Utilitaire.montantMateriaux(mat, macha);
        
        //COUT PETIT MATERIEL 
        PetitMaterielSel pemat = new PetitMaterielSel(1);
        PetitMaterielDuChantierSel pemacha = new PetitMaterielDuChantierSel(1);
        Utilitaire.montantPetitsMateriels(pemat, pemacha);

        //COUT CODE REFERENCE
        CodeReferenceSel core = new CodeReferenceSel(1);
        CodeReferenceDuChantierSel corecha = new CodeReferenceDuChantierSel(1);
        Utilitaire.montantCodesReferences(core, corecha);
        //AVEC SUPPLEMENT
        CodeReferenceSel core1 = new CodeReferenceSel(1);
        CodeReferenceDuChantierSel corecha1 = new CodeReferenceDuChantierSel(1);
        Utilitaire.supplement(core1, corecha1, 10);
        
        //COUT OUVRIER
        OuvrierDuChantierSel ouvcha = new OuvrierDuChantierSel(1);
        Utilitaire.montantOuvriers(ouvcha);
        
        //COUT CONDUCTEUR
        ConducteurSel con = new ConducteurSel(1);
        ConducteurDuChantierSel concha = new ConducteurDuChantierSel(1);
        Utilitaire.montantConducteurs(con, concha);
        
        //COUT VOITURE
        VoitureSel voi = new VoitureSel(1);
        VoitureDuChantierSel voicha = new VoitureDuChantierSel(1);
        Utilitaire.montantVoitures(voi, voicha);
        
        //COUT CAMION
        CamionDuChantierSel camcha = new CamionDuChantierSel(1);
        Utilitaire.montantCamions(camcha); 
        System.out.println((Utilitaire.montantCamions(camcha))*10);
        System.out.println(Utilitaire.montantCamions(camcha));
        
        //AGE
        OuvrierSel ouv2 = new OuvrierSel(1);
        Utilitaire.calculAge(ouv2);
        
        // METHODE CRUD >

        //EnginDto engin = new EnginDto(2, "Tracteur", "Creuser la terre", "BL504", true, 105);
        //Utilitaire.insertEngin(engin);
        //regarder dans la DB l'ID exacte, 6 est obsolète. 
        //Utilitaire.deleteEngin(6);

        //-EnginDto enginUpdated = new EnginDto(7, "Tracteur", "Creuser la pierre", "BL504", false, 55);
        //-Utilitaire.updateEngin(enginUpdated);
        
        //CamionDto camion = new CamionDto(5, "B", 1, 1500, "Audi", "Charger", "254795625", "Diesel", 177);
        //Utilitaire.insertCamion(camion);
        //CamionDto camionUpdated = new CamionDto(3, "B", 1, 1800, "Audi", "Charger", "254795625", "Essence", 177);
        //Utilitaire.updateCamion(camionUpdated);
        //Utilitaire.deleteCamion(3);
        
        //DevisDto devis = new DevisDto(4, "Projet Zaventem 2", "Consultation", new java.sql.Date(2017,01,15), 1);
        //Utilitaire.insertDevis(devis);
        //DevisDto devisUpdated = new DevisDto(4, "Hello", "Waw", new java.sql.Date(2017-01-15), 1);
        //Utilitaire.updateDevis(devisUpdated);
        //Utilitaire.deleteDevis(4);
        
        //ChantierDto chantier = new ChantierDto(3, 1, 1, "Bruxelles", "Parc Cinquentenaire", "Mauvais temps", new Date(2017-02-01), new Date(2017-03-01), null, new Date(2017-03-05), null, false);
        //Utilitaire.insertChantier(chantier);
        //ChantierDto chantierUpdated = new ChantierDto(4, "Hello", "Waw", new java.sql.Date(2017-01-15), 1);
        //Utilitaire.updateChantier(chantierUpdated);
        //Utilitaire.deleteChantier(3);
        
        //ClientDto client = new ClientDto(5, "Marc", "Zotte", new java.sql.Date(1982-02-02), "0485321", "marc@beno.com");
        //Utilitaire.insertClient(client);
        //regarder dans la DB l'ID exacte, 6 est obsolète. 
        //Utilitaire.deleteEngin(6);


        //-EnginDto enginUpdated = new EnginDto(7, "Tracteur", "Creuser la pierre", "BL504", false, 55);
        //-Utilitaire.updateEngin(enginUpdated);
        
        //Use stmt.setDate(1, new java.sql.Date(cal.getTimeInMillis()))
        
        
        
        
    }*/

}
