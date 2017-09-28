/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.OuvrierDto;
import db.dto.OuvrierDto;
import db.dto.OuvrierDuChantierDto;
import db.exception.DevisChantierBusinessException;
import db.selDto.OuvrierDuChantierSel;
import db.selDto.OuvrierSel;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class OuvrierFormEditerController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField telephone;
    @FXML
    private TextField email;
    @FXML
    private TextField cout;
    @FXML
    private TextField remuneration;
    @FXML
    private DatePicker entree;
    @FXML
    private DatePicker naissance;
    @FXML
    private Button valider;
    @FXML
    private Button annuler;
    @FXML
    private Label message;

    private int idOuvrier;
    private int idOuvrierDuChantier;

    @FXML
    private DatePicker debutDisponibilite;
    @FXML
    private DatePicker finDisponibilite;
    @FXML
    private TextField quantite;
    @FXML
    private Label idChantier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initVariables(int idOuvrier, int idOuvrierDuChantier) {
        this.idOuvrier = idOuvrier;
        this.idOuvrierDuChantier = idOuvrierDuChantier;
        try {
            OuvrierDto ouvrier = FacadeDB.findOuvrierBySel(new OuvrierSel(idOuvrier));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = format.parse(ouvrier.getDateNaissance().toString());
            SimpleDateFormat y = new SimpleDateFormat("yyyy");
            int year = Integer.parseInt(y.format(date));
            SimpleDateFormat m = new SimpleDateFormat("MM");
            int month = Integer.parseInt(m.format(date));
            SimpleDateFormat d = new SimpleDateFormat("dd");
            int day = Integer.parseInt(d.format(date));
            LocalDate dateN = LocalDate.of(year, month, day);

            java.util.Date date2 = format.parse(ouvrier.getDateNaissance().toString());
            SimpleDateFormat y2 = new SimpleDateFormat("yyyy");
            int year2 = Integer.parseInt(y2.format(date2));
            SimpleDateFormat m2 = new SimpleDateFormat("MM");
            int month2 = Integer.parseInt(m2.format(date2));
            SimpleDateFormat d2 = new SimpleDateFormat("dd");
            int day2 = Integer.parseInt(d2.format(date2));
            LocalDate dateN2 = LocalDate.of(year2, month2, day2);

            nom.setText(ouvrier.getNom());
            prenom.setText(ouvrier.getPrenom());
            naissance.setValue(dateN);
            telephone.setText(ouvrier.getNumeroTelephone());
            email.setText(ouvrier.getEmail());
            entree.setValue(dateN2);
            cout.setText(Double.toString(ouvrier.getCout()));
            remuneration.setText(Double.toString(ouvrier.getRemuneration()));

            if (idOuvrierDuChantier != -1000) {
                OuvrierDuChantierDto ouvrierChantier = FacadeDB.findOuvrierDuChantierBySel(new OuvrierDuChantierSel(idOuvrierDuChantier));

                java.util.Date date3 = format.parse(ouvrierChantier.getDateDebut().toString());
                SimpleDateFormat y3 = new SimpleDateFormat("yyyy");
                int year3 = Integer.parseInt(y3.format(date3));
                SimpleDateFormat m3 = new SimpleDateFormat("MM");
                int month3 = Integer.parseInt(m3.format(date3));
                SimpleDateFormat d3 = new SimpleDateFormat("dd");
                int day3 = Integer.parseInt(d3.format(date3));
                LocalDate dateN3 = LocalDate.of(year3, month3, day3);

                java.util.Date date4 = format.parse(ouvrierChantier.getDateFin().toString());
                SimpleDateFormat y4 = new SimpleDateFormat("yyyy");
                int year4 = Integer.parseInt(y4.format(date4));
                SimpleDateFormat m4 = new SimpleDateFormat("MM");
                int month4 = Integer.parseInt(m4.format(date4));
                SimpleDateFormat d4 = new SimpleDateFormat("dd");
                int day4 = Integer.parseInt(d4.format(date4));
                LocalDate dateN4 = LocalDate.of(year4, month4, day4);

                debutDisponibilite.setValue(dateN3);
                finDisponibilite.setValue(dateN4);
                quantite.setText(Double.toString(ouvrierChantier.getNombreHeures()));
                idChantier.setText(Integer.toString(ouvrierChantier.getIdChantier()));
            }

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(OuvrierFormEditerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void validation(ActionEvent event) {
        try {
            double cout1 = Double.parseDouble(cout.getText());
            double remuneration1 = Double.parseDouble(remuneration.getText());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = (java.util.Date) format.parse(entree.getValue().toString());
            java.sql.Date date = new Date(parsed.getTime());

            java.util.Date parsed2 = (java.util.Date) format.parse(naissance.getValue().toString());
            java.sql.Date date2 = new Date(parsed2.getTime());

            OuvrierDto ouvrier = new OuvrierDto(idOuvrier, remuneration1, nom.getText(), prenom.getText(), date2, telephone.getText(), email.getText(), date, cout1);

            if (Utilitaire.updateOuvrier(ouvrier)) {
                message.setText("Ouvrier ajouté avec succès !");
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.close();
            } else {
                message.setText("Erreur : le ouvrier n'a pas pu être ajouté ...!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            message.setText("Erreur : le ouvrier n'a pas pu être ajouté !");
        }
    }

    @FXML
    private void annulation(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

}
