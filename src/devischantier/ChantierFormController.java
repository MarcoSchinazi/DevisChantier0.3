/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.ChantierDto;
import db.dto.ClientDto;
import db.exception.DevisChantierBusinessException;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class ChantierFormController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField localisation;
    @FXML
    private TextField designation;
    @FXML
    private TextField commentaire;
    @FXML
    private DatePicker debutPrevue;
    @FXML
    private DatePicker debutEffective;
    @FXML
    private DatePicker finPrevue;
    @FXML
    private DatePicker finEffective;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private CheckBox validation;
    @FXML
    private Button valider;
    @FXML
    private Button annuler;
    @FXML
    private Label message;
    @FXML
    private ListView<ClientDto> listClients;

    private int idClient;
    @FXML
    private Label message2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayClients();
    }

    @FXML
    private void gererValidation(ActionEvent event) {

    }

    @FXML
    private void validation(ActionEvent event) {
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed1 = (java.util.Date) format.parse(dateCreation.getValue().toString());
            java.sql.Date date1 = new Date(parsed1.getTime());

            java.util.Date parsed2 = (java.util.Date) format.parse(debutPrevue.getValue().toString());
            java.sql.Date date2 = new Date(parsed2.getTime());

            java.util.Date parsed3 = (java.util.Date) format.parse(debutEffective.getValue().toString());
            java.sql.Date date3 = new Date(parsed3.getTime());

            java.util.Date parsed4 = (java.util.Date) format.parse(finPrevue.getValue().toString());
            java.sql.Date date4 = new Date(parsed4.getTime());

            java.util.Date parsed5 = (java.util.Date) format.parse(finEffective.getValue().toString());
            java.sql.Date date5 = new Date(parsed5.getTime());

            if (date2.compareTo(date4) > 0) {
                message2.setText("les dates sont impossibles");
                throw new IllegalArgumentException("les dates sont impossibles");
            }

            if (date3.compareTo(date5) > 0) {
                message2.setText("les dates sont impossibles");
                throw new IllegalArgumentException("les dates sont impossibles");
            }

            ChantierDto chantier = new ChantierDto(10000, idClient, localisation.getText(), designation.getText(), commentaire.getText(), date1, date2, date3, date4, date5, validation.isSelected());
            if (Utilitaire.insertChantier(chantier)) {
                message2.setText("Chantier ajouté avec succès !");
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.close();
            } else {
                message2.setText("Erreur : le chantier n'a pas pu être ajouté ...!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            message.setText("Erreur : le chantier n'a pas pu être ajouté !");
        }
    }

    private void displayClients() {
        try {
            Collection<ClientDto> clients = FacadeDB.getAllClient();
            ObservableList<ClientDto> data = FXCollections.observableArrayList(clients);
            listClients.setItems(data);
            listClients.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    ClientDto client = listClients.getSelectionModel().selectedItemProperty().get();
                    idClient = client.getId();
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

}
