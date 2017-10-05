/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.CamionDto;
import db.exception.DevisChantierBusinessException;
import db.selDto.CamionSel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Utilitaire;

/**
 * FXML Controller class
 *
 * @author Vali
 */
public class CamionFormEditerController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField categorie;
    @FXML
    private TextField tonnage;
    @FXML
    private TextField capacite;
    @FXML
    private TextField marque;
    @FXML
    private TextField modele;
    @FXML
    private TextField chassis;
    @FXML
    private TextField carburant;
    @FXML
    private TextField prix;
    @FXML
    private Button valider;
    @FXML
    private Button annuler;
    @FXML
    private Label message;

    private int idCamion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initVariables(int idCamion) {
        this.idCamion = idCamion;

        try {
            CamionDto camion = FacadeDB.findCamionBySel(new CamionSel(idCamion));
            categorie.setText(camion.getCategorie());
            marque.setText(camion.getMarque());
            modele.setText(camion.getModele());
            chassis.setText(camion.getNumeroChassis());
            prix.setText(Double.toString(camion.getPrixHtva()));
            tonnage.setText(Integer.toString(camion.getTonnage()));
            carburant.setText(camion.getCarburant());
            capacite.setText(Double.toString(camion.getCapacite()));
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void validation(ActionEvent event) {
        try {
            double prixCamion = Double.parseDouble(prix.getText());
            int ton = Integer.parseInt(tonnage.getText());
            double cap = Double.parseDouble(capacite.getText());
            CamionDto camionUpdated = new CamionDto(idCamion, categorie.getText(), ton, cap, marque.getText(), modele.getText(), chassis.getText(), carburant.getText(), prixCamion);
            if (Utilitaire.updateCamion(camionUpdated)) {
                message.setText("Camion mis à jour avec succès !");
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.close();
            } else {
                message.setText("Erreur : le camion n'a pas pu être mis à jour ...!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            message.setText("Erreur : le camion n'a pas pu être mis à jour ! 2");
        }
    }

    @FXML
    private void annulation(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

}
