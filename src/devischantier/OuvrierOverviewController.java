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
import db.selDto.ChantierSel;
import db.selDto.OuvrierDuChantierSel;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Utilitaire;

/**
 * FXML Controller class
 *
 * @author Vali
 */
public class OuvrierOverviewController implements Initializable {

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
    private Button nouveau;
    @FXML
    private Button editer;
    @FXML
    private Button supprimer;
    @FXML
    private Label message;
    @FXML
    private Label idChantier;
    @FXML
    private Label debutDisponibilite;
    @FXML
    private Label finDisponibilite;
    @FXML
    private Label quantite;
    @FXML
    private ListView<ChantierDto> listChantiers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        editer.setDisable(true);
        displayList();
    }

    @FXML
    private void gererNouveau(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("OuvrierFormNouveau.fxml"));
        AnchorPane ouvrierInfo;
        try {
            ouvrierInfo = (AnchorPane) loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(ouvrierInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gererEditer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevisChantier.class.getResource("OuvrierFormEditer.fxml"));
        AnchorPane ouvrierInfo;
        try {
            ouvrierInfo = (AnchorPane) loader.load();

            //passer paramètres au controller suivant
            if (id.getText().compareTo("/") != 0) {
                int idOuvrier = Integer.parseInt(id.getText());
                OuvrierFormEditerController controller = loader.<OuvrierFormEditerController>getController();
                if (idChantier.getText().compareTo("/") != 0) {
                    int idChant = Integer.parseInt(idChantier.getText());
                    OuvrierDuChantierSel sel = new OuvrierDuChantierSel(idOuvrier, idChant);
                    Collection<OuvrierDuChantierDto> ouv = FacadeDB.findOuvriersDuChantierBySel(sel);
                    controller.initVariables(idOuvrier, ouv.iterator().next().getId());
                }
                else{
                    controller.initVariables(idOuvrier, -1000);
                }

            }
            Stage stage = new Stage();
            Scene scene = new Scene(ouvrierInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException | DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gererSupprimer(ActionEvent event
    ) {
        OuvrierDto ouvrier = idNomPrenom.getSelectionModel().selectedItemProperty().get();
        if (Utilitaire.deleteOuvrier(3)) {
            message.setText("Suppression avec succès !");
        } else {
            message.setText("Erreur de suppression ...!");
        }
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
                    editer.setDisable(false);
                    id.setText(ouvrier.getId().toString());
                    nom.setText(ouvrier.getNom());
                    prenom.setText(ouvrier.getPrenom());
                    naissance.setText(ouvrier.getDateNaissance().toString());
                    telephone.setText(ouvrier.getNumeroTelephone());
                    email.setText(ouvrier.getEmail());
                    entree.setText(ouvrier.getEntreeFonction().toString());
                    cout.setText(Double.toString(ouvrier.getCout()));
                    remuneration.setText(Double.toString(ouvrier.getRemuneration()));
                    displayChantiers();
                }
            });
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayChantiers() {
        try {
            ObservableList<ChantierDto> data = FXCollections.observableArrayList();
            OuvrierDuChantierSel os = new OuvrierDuChantierSel(Integer.parseInt(id.getText()), true);
            Collection<OuvrierDuChantierDto> ocDto = FacadeDB.findOuvriersDuChantierBySel(os);
            for (OuvrierDuChantierDto oc : ocDto) {
                ChantierSel s = new ChantierSel(oc.getIdChantier());
                ChantierDto chantier = FacadeDB.findChantierBySel(s);
                data.add(chantier);
            }
            listChantiers.setItems(data);
            listChantiers.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    try {
                        ChantierDto chantier = listChantiers.getSelectionModel().selectedItemProperty().get();
                        idChantier.setText(chantier.getId().toString());
                        System.out.println(id.getText());
                        System.out.println(chantier.getId());
                        OuvrierDuChantierSel sel = new OuvrierDuChantierSel(Integer.parseInt(id.getText()), chantier.getId());
                        OuvrierDuChantierDto ouvcha = FacadeDB.findOuvrierDuChantierBySel(sel);

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date3 = format.parse(ouvcha.getDateDebut().toString());
                        SimpleDateFormat y3 = new SimpleDateFormat("yyyy");
                        int year3 = Integer.parseInt(y3.format(date3));
                        SimpleDateFormat m3 = new SimpleDateFormat("MM");
                        int month3 = Integer.parseInt(m3.format(date3));
                        SimpleDateFormat d3 = new SimpleDateFormat("dd");
                        int day3 = Integer.parseInt(d3.format(date3));
                        LocalDate dateN3 = LocalDate.of(year3, month3, day3);

                        java.util.Date date4 = format.parse(ouvcha.getDateFin().toString());
                        SimpleDateFormat y4 = new SimpleDateFormat("yyyy");
                        int year4 = Integer.parseInt(y4.format(date4));
                        SimpleDateFormat m4 = new SimpleDateFormat("MM");
                        int month4 = Integer.parseInt(m4.format(date4));
                        SimpleDateFormat d4 = new SimpleDateFormat("dd");
                        int day4 = Integer.parseInt(d4.format(date4));
                        LocalDate dateN4 = LocalDate.of(year4, month4, day4);

                        debutDisponibilite.setText(dateN3.toString());
                        finDisponibilite.setText(dateN4.toString());
                        quantite.setText(Double.toString(ouvcha.getNombreHeures()));

                    } catch (DevisChantierBusinessException ex) {
                        Logger.getLogger(OuvrierOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(OuvrierOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
