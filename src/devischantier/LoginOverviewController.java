/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import db.business.FacadeDB;
import db.dto.ConducteurDto;
import db.dto.PatronDto;
import db.exception.DevisChantierBusinessException;
import db.selDto.ConducteurSel;
import db.selDto.PatronSel;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Utilitaire;

/**
 * FXML Controller class
 *
 * @author Vali
 */
public class LoginOverviewController implements Initializable {

    @FXML
    private TextField loginId;

    @FXML
    private TextField loginPass;

    @FXML
    private Button loginConnect;

    @FXML
    private Label loginError;

    private boolean isPatron = false;

    boolean inscription = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inscription();
    }

    private void inscription() {
        try {
            if (FacadeDB.getAllPatron().isEmpty()) {
                loginConnect.setText("s'inscrire");
                inscription = true;
            }
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void toNextScreen() {
        FXMLLoader l = new FXMLLoader();
        l.setLocation(DevisChantier.class.getResource("RootLayout.fxml"));
        BorderPane rootLayout;
        try {
            rootLayout = (BorderPane) l.load();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DevisChantier.class.getResource("MainOverview.fxml"));
            AnchorPane mainOverview;
            mainOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(mainOverview);
            if (loginId != null) {
                MainOverviewController controller = loader.<MainOverviewController>getController();
                controller.initVariables(Integer.parseInt(loginId.getText()), isPatron);
                
                RootLayoutController control = l.<RootLayoutController>getController();
                control.initVariables(Integer.parseInt(loginId.getText()), isPatron);
            }
            Scene scene = new Scene(l.getRoot());
            Stage stage = (Stage) loginConnect.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(LoginOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gererButton(ActionEvent event) {
        if (inscription == true) {
            gererInscription(event);
        } else {
            gererConnection(event);
        }
    }

    private void gererInscription(ActionEvent event) {
        ConducteurDto conducteur;
        PatronDto patron;

        //hash du password
        String hash = loginPass.getText();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(hash.getBytes(), 0, hash.length());
            hash = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        conducteur = new ConducteurDto(Integer.parseInt(loginId.getText()), hash, "0", "0", 0, "Dupont", "Dupont", new Date(1), "", new Date(1), 0);
        Utilitaire.insertConducteur(conducteur);
        patron = new PatronDto(Integer.parseInt(loginId.getText()));
        try {
            FacadeDB.addPatron(patron);
            isPatron = true;
            toNextScreen();

        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void gererConnection(ActionEvent event) {
        ConducteurDto conducteur;
        PatronDto patron;

        //hash du password
        String hash = loginPass.getText();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(hash.getBytes(), 0, hash.length());
            hash = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        ConducteurSel sel = new ConducteurSel(Integer.parseInt(loginId.getText()), hash);
        PatronSel pat = new PatronSel(Integer.parseInt(loginId.getText()));
        try {
            conducteur = FacadeDB.findConducteurBySel(sel);
            patron = FacadeDB.findPatronBySel(pat);
            if (conducteur != null) {
                if (patron != null) {
                    isPatron = true;
                }
                toNextScreen();
            } else {
                loginError.setText("Votre ID ou mot de passe est incorrect");
            }
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
