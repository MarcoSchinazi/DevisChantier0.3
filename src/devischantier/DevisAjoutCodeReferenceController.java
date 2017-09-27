/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.CodeReferenceDto;
import db.dto.CodeReferenceDuChantierDto;
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
public class DevisAjoutCodeReferenceController implements Initializable {

    @FXML
    private TableView<CodeReferenceDto> idTableReference;
    @FXML
    private TableColumn<CodeReferenceDto, String> idColonneReference;
    @FXML
    private Label id;
    @FXML
    private Label reference;
    @FXML
    private Label type;
    @FXML
    private Label prix;
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
    @FXML
    private Button valider;

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
        idColonneReference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        try {
            Collection<CodeReferenceDto> codeReferences = FacadeDB.getAllCodeReference();
            ObservableList<CodeReferenceDto> data = FXCollections.observableArrayList(codeReferences);
            idTableReference.setItems(data);

            idTableReference.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    CodeReferenceDto codeReference = idTableReference.getSelectionModel().selectedItemProperty().get();
                    id.setText(codeReference.getId().toString());
                    reference.setText(codeReference.getReference());
                    type.setText(codeReference.getTypeTravail());
                    prix.setText(Double.toString(codeReference.getPrixHtva()));
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

            CodeReferenceDuChantierDto codeReference = new CodeReferenceDuChantierDto(1000, Double.parseDouble(quantite.getText()), Integer.parseInt(idChantier.getText()), Integer.parseInt(id.getText()));
            Utilitaire.insertCodeReferenceDuChantier(codeReference);
            Stage stage = (Stage) valider.getScene().getWindow();
            stage.close();
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
