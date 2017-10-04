/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.InetAddress;
import static javafx.application.Application.launch;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author Vali
 */
public class DevisChantier extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion des devis");

        // initRootLayout();
        showLoginOverview();
    }

    /**
     * Shows the Client overview inside the root layout.
     */
    public void showLoginOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DevisChantier.class.getResource("LoginOverview.fxml"));
            AnchorPane loginOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            //rootLayout.setCenter(loginOverview);
            Scene scene = new Scene(loginOverview);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        startServer();
        launch(args);
    }

    private static void startServer() {
        /*
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby:" + "DevisChantier" + ";create=true", "root", "root");
        */
        System.setProperty("derby.drda.startNetworkServer", "true");
        try {
            // start derby in port 20000
            NetworkServerControl server = new NetworkServerControl(InetAddress.getByName("localhost"),
                    1527,
                    "root",
                    "root");
            java.io.PrintWriter consoleWriter = new java.io.PrintWriter(System.out, true);
            server.start(consoleWriter);
            System.out.println("=====    Started/Connected DB    =====");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
