/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.CamionDto;
import db.dto.CamionDuChantierDto;
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
public class DevisAjoutCamionController implements Initializable {

    @FXML
    private TableView<CamionDto> table;
    @FXML
    private TableColumn<CamionDto, String> colonneId;
    @FXML
    private TableColumn<CamionDto, String> colonneMarque;
    @FXML
    private Label id;
    @FXML
    private Label categorie;
    @FXML
    private Label marque;
    @FXML
    private Label modele;
    @FXML
    private Label chassis;
    @FXML
    private Label carburant;
    @FXML
    private Label prix;
    @FXML
    private Label tonnage;
    @FXML
    private Label capacite;
    @FXML
    private Button valider;
    @FXML
    private Label message;
    @FXML
    private Label idChantier;
    @FXML
    private TextField quantite;
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

    private void displayList() {
        colonneMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        colonneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            Collection<CamionDto> camions = FacadeDB.getAllCamion();
            ObservableList<CamionDto> data = FXCollections.observableArrayList(camions);
            table.setItems(data);

            table.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    CamionDto camion = table.getSelectionModel().selectedItemProperty().get();
                    id.setText(camion.getId().toString());
                    categorie.setText(camion.getCategorie());
                    marque.setText(camion.getMarque());
                    modele.setText(camion.getModele());
                    chassis.setText(camion.getNumeroChassis());
                    prix.setText(Double.toString(camion.getPrixHtva()));
                    tonnage.setText(Integer.toString(camion.getTonnage()));
                    carburant.setText(camion.getCarburant());
                    capacite.setText(Double.toString(camion.getCapacite()));
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

            CamionDuChantierDto camion = new CamionDuChantierDto(1000, dateD, dateF, Double.parseDouble(quantite.getText()),
                    Integer.parseInt(idChantier.getText()), Integer.parseInt(id.getText()));
            Utilitaire.insertCamionDuChantier(camion);
            Stage stage = (Stage) valider.getScene().getWindow();
            stage.close();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
