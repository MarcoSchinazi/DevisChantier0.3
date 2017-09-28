/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vali
 */
public class RootLayoutController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Menu navigation;
    @FXML
    private MenuItem mainWindow;
    @FXML
    private MenuItem close;
    @FXML
    private Menu ressources;
    @FXML
    private MenuItem chantiers;
    @FXML
    private MenuItem materiaux;
    @FXML
    private MenuItem petitsMateriels;
    @FXML
    private MenuItem codesReferences;
    @FXML
    private MenuItem engins;
    @FXML
    private MenuItem camions;
    @FXML
    private MenuItem voitures;
    @FXML
    private MenuItem conducteurs;
    @FXML
    private MenuItem ouvriers;
    @FXML
    private MenuItem client;
    @FXML
    private MenuItem devis;
    @FXML
    private Menu aPropos;
    @FXML
    private MenuItem informations;

    private boolean isPatron;
    private int loginId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initVariables(int id, boolean isPatron) {
        this.isPatron = isPatron;
        this.loginId = id;

        if (!isPatron) {
            conducteurs.setVisible(false);
        }
    }

    @FXML
    private void closeApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void goToWindow(ActionEvent event) {
        FXMLLoader l = new FXMLLoader();
        l.setLocation(DevisChantier.class.getResource("RootLayout.fxml"));
        BorderPane rootLayout;
        try {
            rootLayout = (BorderPane) l.load();
            Scene scene = new Scene(l.getRoot());
            RootLayoutController control = l.<RootLayoutController>getController();
            control.initVariables(loginId, isPatron);

            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.setScene(scene);

            FXMLLoader loader = new FXMLLoader();
            MenuItem item = (MenuItem) event.getSource();
            loader.setLocation(DevisChantier.class.getResource(item.getId() + ".fxml"));
            AnchorPane mainOverview = (AnchorPane) loader.load();
            if(item.getId().compareTo("MainOverview") == 0){
                MainOverviewController controller = loader.<MainOverviewController>getController();
                controller.initVariables(loginId, isPatron);
            }
            rootLayout.setCenter(mainOverview);
        } catch (IOException ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
