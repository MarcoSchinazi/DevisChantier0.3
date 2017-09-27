/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.ChantierDto;
import db.dto.DevisDto;
import db.exception.DevisChantierBusinessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Utilitaire;

/**
 * FXML Controller class
 *
 * @author Vali
 */
public class DevisFormController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField statut;
    @FXML
    private DatePicker dateDevis;
    @FXML
    private TextField designation;
    @FXML
    private Button valider;
    @FXML
    private Button annuler;
    @FXML
    private Label message;
    @FXML
    private ListView<ChantierDto> listChantiers;

    private int idChantier;
    @FXML
    private Button ajoutOuvrier;
    @FXML
    private Button ajoutConducteur;
    @FXML
    private Button ajoutEngin;
    @FXML
    private Button ajoutMateriau;
    @FXML
    private Button ajoutPetitMateriel;
    @FXML
    private Button ajoutCodeReference;
    @FXML
    private Button ajoutVoiture;
    @FXML
    private Button ajoutCamion;
    @FXML
    private Button ajoutChantier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayChantiers();
        ajoutOuvrier.setDisable(true);
        ajoutEngin.setDisable(true);
        ajoutMateriau.setDisable(true);
        ajoutPetitMateriel.setDisable(true);
        ajoutCodeReference.setDisable(true);
        ajoutVoiture.setDisable(true);
        ajoutCamion.setDisable(true);
        ajoutConducteur.setDisable(true);
    }

    @FXML
    private void validation(ActionEvent event) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = (java.util.Date) format.parse(dateDevis.getValue().toString());
            java.sql.Date date = new Date(parsed.getTime());

            DevisDto devis = new DevisDto(10000, designation.getText(), statut.getText(), date, idChantier);
            if (Utilitaire.insertDevis(devis)) {
                message.setText("Devis ajouté avec succès !");
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.close();
            } else {
                message.setText("Erreur : le devis n'a pas pu être ajouté ...!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            message.setText("Erreur : le devis n'a pas pu être ajouté !");
        }
    }

    private void displayChantiers() {
        try {
            Collection<ChantierDto> chantiers = FacadeDB.getAllChantier();
            ObservableList<ChantierDto> data = FXCollections.observableArrayList(chantiers);
            listChantiers.setItems(data);
            listChantiers.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    try {
                        ChantierDto chantier = listChantiers.getSelectionModel().selectedItemProperty().get();
                        idChantier = chantier.getId();
                        Collection<DevisDto> devis = FacadeDB.getAllDevis();
                        boolean exist = false;
                        for (DevisDto dev : devis) {
                            if (chantier.getId() == dev.getIdChantier()) {
                                exist = true;
                            }
                        }
                        if (!exist) {
                            ajoutOuvrier.setDisable(false);
                            ajoutEngin.setDisable(false);
                            ajoutMateriau.setDisable(false);
                            ajoutPetitMateriel.setDisable(false);
                            ajoutCodeReference.setDisable(false);
                            ajoutVoiture.setDisable(false);
                            ajoutCamion.setDisable(false);
                            ajoutConducteur.setDisable(false);
                            valider.setDisable(false);
                        } else {
                            ajoutOuvrier.setDisable(true);
                            ajoutEngin.setDisable(true);
                            ajoutMateriau.setDisable(true);
                            ajoutPetitMateriel.setDisable(true);
                            ajoutCodeReference.setDisable(true);
                            ajoutVoiture.setDisable(true);
                            ajoutCamion.setDisable(true);
                            ajoutConducteur.setDisable(true);
                            valider.setDisable(true);
                        }
                    } catch (DevisChantierBusinessException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            });
            
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void annulation(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goToDevisAjoutOuvrier(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutOuvrier.fxml"));
        AnchorPane ouvrierInfo;
        try {
            ouvrierInfo = (AnchorPane) loader.load();

            DevisAjoutOuvrierController controller = loader.<DevisAjoutOuvrierController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(ouvrierInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToDevisAjoutConducteur(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutConducteur.fxml"));
        AnchorPane conducteurInfo;
        try {
            conducteurInfo = (AnchorPane) loader.load();

            DevisAjoutConducteurController controller = loader.<DevisAjoutConducteurController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(conducteurInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToDevisAjoutEngin(ActionEvent event) { 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutEngin.fxml"));
        AnchorPane enginInfo;
        try {
            enginInfo = (AnchorPane) loader.load();

            DevisAjoutEnginController controller = loader.<DevisAjoutEnginController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(enginInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToDevisAjoutMateriau(ActionEvent event) { 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutMateriau.fxml"));
        AnchorPane materiauInfo;
        try {
            materiauInfo = (AnchorPane) loader.load();

            DevisAjoutMateriauController controller = loader.<DevisAjoutMateriauController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(materiauInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToDevisAjoutPetitMateriel(ActionEvent event) { 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutPetitMateriel.fxml"));
        AnchorPane petitMaterielInfo;
        try {
            petitMaterielInfo = (AnchorPane) loader.load();

            DevisAjoutPetitMaterielController controller = loader.<DevisAjoutPetitMaterielController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(petitMaterielInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void goToDevisAjoutCodeReference(ActionEvent event) { 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutCodeReference.fxml"));
        AnchorPane codeReferenceInfo;
        try {
            codeReferenceInfo = (AnchorPane) loader.load();

            DevisAjoutCodeReferenceController controller = loader.<DevisAjoutCodeReferenceController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(codeReferenceInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void goToDevisAjoutVoiture(ActionEvent event) { 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutVoiture.fxml"));
        AnchorPane voitureInfo;
        try {
            voitureInfo = (AnchorPane) loader.load();

            DevisAjoutVoitureController controller = loader.<DevisAjoutVoitureController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(voitureInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void goToDevisAjoutCamion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("DevisAjoutCamion.fxml"));
        AnchorPane camionInfo;
        try {
            camionInfo = (AnchorPane) loader.load();

            DevisAjoutCamionController controller = loader.<DevisAjoutCamionController>getController();
            controller.initVariables(idChantier);

            Stage stage = new Stage();
            Scene scene = new Scene(camionInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
