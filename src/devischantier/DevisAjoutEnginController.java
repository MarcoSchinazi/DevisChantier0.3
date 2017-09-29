/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.EnginDto;
import db.dto.EnginDuChantierDto;
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
public class DevisAjoutEnginController implements Initializable {

    @FXML
    private TableView<EnginDto> idTableNom;
    @FXML
    private TableColumn<EnginDto, String> idColonneNom;
    @FXML
    private Label id;
    @FXML
    private Label nom;
    @FXML
    private Label type;
    @FXML
    private Label reference;
    @FXML
    private Label prix;
    @FXML
    private Button valider;
    @FXML
    private Label message;
    @FXML
    private Label idChantier;
    @FXML
    private TextField quantite;
    @FXML
    private TextField nbrHeures;
    @FXML
    private DatePicker debutDisponibilite;
    @FXML
    private DatePicker finDisponibilite;

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
    private void displayList() {
        idColonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        try {
            Collection<EnginDto> engins = FacadeDB.getAllEngin();
            ObservableList<EnginDto> data = FXCollections.observableArrayList(engins);
            idTableNom.setItems(data);

            idTableNom.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    EnginDto engin = idTableNom.getSelectionModel().selectedItemProperty().get();
                    id.setText(engin.getId().toString());
                    nom.setText(engin.getNom());
                    type.setText(engin.getType());
                    reference.setText(engin.getReference());
                    prix.setText(Double.toString(engin.getPrixHeure()));
                    valider.setDisable(false);
                }
            });
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
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

            if (dateD.compareTo(dateF) > 0) {
                message.setText("les dates sont impossibles");
                throw new IllegalArgumentException("les dates sont impossibles");
            }

            EnginDuChantierDto engin = new EnginDuChantierDto(1000, Double.parseDouble(quantite.getText()), Double.parseDouble(nbrHeures.getText()), dateD, dateF, Integer.parseInt(idChantier.getText()), Integer.parseInt(id.getText()));
            Utilitaire.insertEnginDuChantier(engin);
            Stage stage = (Stage) valider.getScene().getWindow();
            stage.close();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
