/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.MateriauDto;
import db.dto.MateriauDuChantierDto;
import db.exception.DevisChantierBusinessException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Utilitaire;

/**
 * FXML Controller class
 *
 * @author demaj
 */
public class DevisAjoutMateriauController implements Initializable {

    @FXML
    private TableView<MateriauDto> idTableNom;
    @FXML
    private TableColumn<MateriauDto, String> idColonneNom;
    @FXML
    private TableColumn<MateriauDto, String> idColonneId;
    @FXML
    private Label id;
    @FXML
    private Label nom;
    @FXML
    private Label type;
    @FXML
    private Label reference;
    @FXML
    private Label fourniture;
    @FXML
    private Label production;
    @FXML
    private Label prix;
    @FXML
    private Button valider;
    @FXML
    private Label message;
    @FXML
    private Label idChantier;
    @FXML
    private DatePicker debutDisponibilite;
    @FXML
    private DatePicker finDisponibilite;
    @FXML
    private TextField quantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayList();
        valider.setDisable(true);
    }

    public void initVariables(int idChantier) {
        this.idChantier.setText(Integer.toString(idChantier));
    }

    @FXML
    private void gererValider(ActionEvent event) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(debutDisponibilite.getValue().toString());
            java.util.Date parsed = (java.util.Date) format.parse(debutDisponibilite.getValue().toString());
            java.sql.Date dateD = new Date(parsed.getTime());

            java.util.Date parsed2 = (java.util.Date) format.parse(finDisponibilite.getValue().toString());
            java.sql.Date dateF = new Date(parsed2.getTime());

            MateriauDuChantierDto materiau = new MateriauDuChantierDto(1000, Double.parseDouble(quantite.getText()), dateD, dateF, Integer.parseInt(idChantier.getText()), Integer.parseInt(id.getText()));
            Utilitaire.insertMateriauDuChantier(materiau);
            Stage stage = (Stage) valider.getScene().getWindow();
            stage.close();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void displayList() {
        idColonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idColonneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            Collection<MateriauDto> materiaus = FacadeDB.getAllMateriau();
            ObservableList<MateriauDto> data = FXCollections.observableArrayList(materiaus);
            idTableNom.setItems(data);

            idTableNom.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    MateriauDto materiau = idTableNom.getSelectionModel().selectedItemProperty().get();
                    id.setText(materiau.getId().toString());
                    nom.setText(materiau.getNom());
                    type.setText(materiau.getType());
                    reference.setText(materiau.getReference());
                    fourniture.setText(materiau.getFourniture());
                    production.setText(materiau.getSiteProduction());
                    prix.setText(Double.toString(materiau.getPrixHtva()));
                    valider.setDisable(false);
                }
            });
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
