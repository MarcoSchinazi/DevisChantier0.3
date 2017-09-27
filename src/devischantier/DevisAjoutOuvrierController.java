/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.ChantierDto;
import db.dto.OuvrierDto;
import db.dto.OuvrierDuChantierDto;
import db.exception.DevisChantierBusinessException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Utilitaire;

/**
 * FXML Controller class
 *
 * @author Vali
 */
public class DevisAjoutOuvrierController implements Initializable {

    @FXML
    private TableView<OuvrierDto> idNomPrenom;
    @FXML
    private TableColumn<OuvrierDto, String> idNom;
    @FXML
    private TableColumn<OuvrierDto, String> idPrenom;
    @FXML
    private Label id;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label naissance;
    @FXML
    private Label telephone;
    @FXML
    private Label email;
    @FXML
    private Label entree;
    @FXML
    private Label cout;
    @FXML
    private Label remuneration;
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
        // TODO
        displayList();
        valider.setDisable(true);
    }

    public void initVariables(int idChantier) {
        this.idChantier.setText(Integer.toString(idChantier));
    }

    private void displayList() {
        idNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        try {
            Collection<OuvrierDto> ouvriers = FacadeDB.getAllOuvrier();
            ObservableList<OuvrierDto> data = FXCollections.observableArrayList(ouvriers);
            idNomPrenom.setItems(data);

            idNomPrenom.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    OuvrierDto ouvrier = idNomPrenom.getSelectionModel().selectedItemProperty().get();
                    id.setText(ouvrier.getId().toString());
                    nom.setText(ouvrier.getNom());
                    prenom.setText(ouvrier.getPrenom());
                    naissance.setText(ouvrier.getDateNaissance().toString());
                    telephone.setText(ouvrier.getNumeroTelephone());
                    email.setText(ouvrier.getEmail());
                    entree.setText(ouvrier.getEntreeFonction().toString());
                    cout.setText(Double.toString(ouvrier.getCout()));
                    remuneration.setText(Double.toString(ouvrier.getRemuneration()));
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

            OuvrierDuChantierDto ouvrier = new OuvrierDuChantierDto(1000, dateD, dateF, Double.parseDouble(quantite.getText()),
                    Integer.parseInt(idChantier.getText()), Integer.parseInt(id.getText()));
            Utilitaire.insertOuvrierDuChantier(ouvrier);
            Stage stage = (Stage) valider.getScene().getWindow();
            stage.close();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
