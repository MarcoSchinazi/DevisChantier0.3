/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.VoitureDto;
import db.dto.VoitureDuChantierDto;
import db.exception.DevisChantierBusinessException;
import db.selDto.VoitureDuChantierSel;
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
public class DevisAjoutVoitureController implements Initializable {

    @FXML
    private TableView<VoitureDto> idMarqueModele;
    @FXML
    private TableColumn<VoitureDto, String> idMarque;
    @FXML
    private TableColumn<VoitureDto, String> idModele;
    @FXML
    private Label id;
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
        idMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        idModele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        try {
            Collection<VoitureDto> voitures = FacadeDB.getAllVoiture();
            ObservableList<VoitureDto> data = FXCollections.observableArrayList(voitures);
            idMarqueModele.setItems(data);

            idMarqueModele.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    VoitureDto voiture = idMarqueModele.getSelectionModel().selectedItemProperty().get();
                    id.setText(voiture.getId().toString());
                    marque.setText(voiture.getMarque());
                    modele.setText(voiture.getModele());
                    chassis.setText(voiture.getNumeroChassis());
                    prix.setText(Double.toString(voiture.getPrixHtva()));
                    carburant.setText(voiture.getCarburant());
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

            VoitureDuChantierDto voiture = new VoitureDuChantierDto(1000, Integer.parseInt(idChantier.getText()), Integer.parseInt(id.getText()), dateD, dateF,
                    Integer.parseInt(quantite.getText()));
            Utilitaire.insertVoitureDuChantier(voiture);
            Stage stage = (Stage) valider.getScene().getWindow();
            stage.close();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
