/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devischantier;

import db.business.FacadeDB;
import db.dto.DevisDto;
import db.dto.OuvrierDuChantierDto;
import db.exception.DevisChantierBusinessException;
import db.selDto.OuvrierDuChantierSel;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Utilitaire;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import db.dto.CamionDuChantierDto;
import db.dto.ChantierDto;
import db.dto.CodeReferenceDuChantierDto;
import db.dto.ConducteurDuChantierDto;
import db.dto.EnginDuChantierDto;
import db.dto.MateriauDuChantierDto;
import db.dto.PetitMaterielDuChantierDto;
import db.dto.VoitureDuChantierDto;
import db.selDto.CamionDuChantierSel;
import db.selDto.CodeReferenceDuChantierSel;
import db.selDto.ConducteurDuChantierSel;
import db.selDto.EnginDuChantierSel;
import db.selDto.MateriauDuChantierSel;
import db.selDto.PetitMaterielDuChantierSel;
import db.selDto.VoitureDuChantierSel;

/**
 * FXML Controller class
 *
 * @author Vali
 */
public class DevisOverviewController implements Initializable {

    @FXML
    private TableView<DevisDto> idDesignationId;
    @FXML
    private TableColumn<DevisDto, String> idDesignation;
    @FXML
    private TableColumn<DevisDto, String> idIdentification;
    @FXML
    private Label idDevis;
    @FXML
    private Label idChantier;
    @FXML
    private Label statut;
    @FXML
    private Label date;
    @FXML
    private Label designation;
    @FXML
    private Button nouveau;
    @FXML
    private Button editer;
    @FXML
    private Button supprimer;
    @FXML
    private Label message;
    @FXML
    private Label ouvrier;
    @FXML
    private Label camion;
    @FXML
    private Label engin;
    @FXML
    private Label voiture;
    @FXML
    private Label petitMateriel;
    @FXML
    private Label conducteur;
    @FXML
    private Label total;
    @FXML
    private Button pdf;
    @FXML
    private Label messagePdf;
    @FXML
    private Label codeReference;
    @FXML
    private Label materiau;
    @FXML
    private Label totalTva;

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
        loader.setLocation(DevisChantier.class.getResource("DevisFormNouveau.fxml"));
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
        loader.setLocation(DevisChantier.class.getResource("DevisFormEditer.fxml"));
        AnchorPane enginInfo;
        try {
            enginInfo = (AnchorPane) loader.load();

            //passer paramètres au controller suivant
            if (idDevis != null) {
                DevisFormEditerController controller = loader.<DevisFormEditerController>getController();
                controller.initVariables(Integer.parseInt(idDevis.getText()));
            }
            Stage stage = new Stage();
            Scene scene = new Scene(enginInfo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gererSupprimer(ActionEvent event) {
        DevisDto devis = idDesignationId.getSelectionModel().selectedItemProperty().get();
        try {
            OuvrierDuChantierSel oSel = new OuvrierDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<OuvrierDuChantierDto> ouvriers = FacadeDB.findOuvriersDuChantierBySel(oSel);
            for (OuvrierDuChantierDto ouv : ouvriers) {
                Utilitaire.deleteOuvrierDuChantier(ouv.getId());
            }
            CamionDuChantierSel cSel = new CamionDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<CamionDuChantierDto> camions = FacadeDB.findCamionsDuChantierBySel(cSel);
            for (CamionDuChantierDto cam : camions) {
                Utilitaire.deleteCamionDuChantier(cam.getId());
            }
            MateriauDuChantierSel mSel = new MateriauDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<MateriauDuChantierDto> materiaux = FacadeDB.findMateriauxDuChantierBySel(mSel);
            for (MateriauDuChantierDto mat : materiaux) {
                Utilitaire.deleteMateriauDuChantier(mat.getId());
            }
            EnginDuChantierSel eSel = new EnginDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<EnginDuChantierDto> engins = FacadeDB.findEnginsDuChantierBySel(eSel);
            for (EnginDuChantierDto eng : engins) {
                Utilitaire.deleteEnginDuChantier(eng.getId());
            }
            PetitMaterielDuChantierSel pSel = new PetitMaterielDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<PetitMaterielDuChantierDto> pMateriels = FacadeDB.findPetitsMaterielsDuChantierBySel(pSel);
            for (PetitMaterielDuChantierDto pMat : pMateriels) {
                Utilitaire.deletePetitMaterielDuChantier(pMat.getId());
            }
            VoitureDuChantierSel vSel = new VoitureDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<VoitureDuChantierDto> voitures = FacadeDB.findVoituresDuChantierBySel(vSel);
            for (VoitureDuChantierDto voit : voitures) {
                Utilitaire.deleteVoitureDuChantier(voit.getId());
            }
            CodeReferenceDuChantierSel rSel = new CodeReferenceDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<CodeReferenceDuChantierDto> codes = FacadeDB.findCodesReferencesDuChantierBySel(rSel);
            for (CodeReferenceDuChantierDto ref : codes) {
                Utilitaire.deleteCodeReferenceDuChantier(ref.getId());
            }
            ConducteurDuChantierSel condSel = new ConducteurDuChantierSel(devis.getIdChantier(), "argFactice");
            Collection<ConducteurDuChantierDto> conducteurs = FacadeDB.findConducteursDuChantierBySel(condSel);
            for (ConducteurDuChantierDto cond : conducteurs) {
                Utilitaire.deleteConducteurDuChantier(cond.getId());
            }
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
        if (Utilitaire.deleteDevis(devis.getId())) {
            message.setText("Suppression avec succès !");
        } else {
            message.setText("Erreur de suppression ...!");
        }

    }

    private void displayList() {
        idDesignation.setCellValueFactory(new PropertyValueFactory<>("designationDevis"));
        idIdentification.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            Collection<DevisDto> devis = FacadeDB.getAllDevis();
            ObservableList<DevisDto> data = FXCollections.observableArrayList(devis);
            idDesignationId.setItems(data);

            idDesignationId.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    DevisDto devis = idDesignationId.getSelectionModel().selectedItemProperty().get();
                    editer.setDisable(false);
                    idDevis.setText(devis.getId().toString());
                    designation.setText(devis.getDesignationDevis());
                    statut.setText(devis.getStatut());
                    date.setText(devis.getDateDevis().toString());
                    idChantier.setText(Integer.toString(devis.getIdChantier()));
                    
                    OuvrierDuChantierSel sel = new OuvrierDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantOuvrier = Utilitaire.montantOuvriers(sel);
                    ouvrier.setText(montantOuvrier.toString());

                    CamionDuChantierSel cs = new CamionDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantCamion = Utilitaire.montantCamions(cs);
                    camion.setText(montantCamion.toString());

                    VoitureDuChantierSel vs = new VoitureDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantVoiture = Utilitaire.montantVoitures(vs);
                    voiture.setText(montantVoiture.toString());

                    EnginDuChantierSel es = new EnginDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantEngin = Utilitaire.montantEngins(es);
                    engin.setText(montantEngin.toString());

                    MateriauDuChantierSel ms = new MateriauDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantMateriau = Utilitaire.montantMateriaux(ms);
                    materiau.setText(montantMateriau.toString());

                    PetitMaterielDuChantierSel pms = new PetitMaterielDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantPetitMateriel = Utilitaire.montantPetitsMateriels(pms);
                    petitMateriel.setText(montantPetitMateriel.toString());

                    ConducteurDuChantierSel cos = new ConducteurDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantConducteur = Utilitaire.montantConducteurs(cos);
                    conducteur.setText(montantConducteur.toString());

                    CodeReferenceDuChantierSel cods = new CodeReferenceDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantCodeReference = Utilitaire.montantCodesReferences(cods);
                    codeReference.setText(montantCodeReference.toString());
                    
                    Double montantTotalHtva = (montantOuvrier + montantCamion + montantVoiture + montantEngin + montantPetitMateriel + montantMateriau + montantConducteur + montantCodeReference);
                    Double montantTotal = montantTotalHtva + montantTotalHtva * 0.21;
                    total.setText(montantTotalHtva.toString());
                    totalTva.setText(montantTotal.toString());
            
                    
                                                                     

                }
            });
        } catch (DevisChantierBusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void creerPdf(ActionEvent event) throws DocumentException {
        {
            Document document = new Document(PageSize.A4);
            try {
                PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Public/DevisChantier.pdf"));
                document.open();

                FontFactory.register("C:/Windows/FONTS/ARIAL.TTF");

                Font fonte = FontFactory.getFont("arial");

                Font maFonte = new Font(fonte);
                maFonte.setColor(36, 68, 92);
                maFonte.setSize(11);

                Font maFonte2 = new Font(fonte);
                maFonte2.setColor(88, 100, 115);
                maFonte2.setSize(14);

                Paragraph p0 = new Paragraph("Melin\nChaussée Provinciale, 85\n1341 Ottignies\nTEL : 010/61.28.47\nFAX : 010/61.13.27\nEmail : info@melinsa.be ", maFonte);
                p0.setAlignment(Element.ALIGN_RIGHT);
                document.add(p0);

                Paragraph p2 = new Paragraph("Devis n°" + idDevis.getText() + "\nDésignation : " + designation.getText() + "\nStatut : " + statut.getText() + "\nDate du devis : " + date.getText());
                p0.setAlignment(Element.ALIGN_LEFT);
                document.add(p2);
                
                Paragraph p3 = new Paragraph("Numéro :\nNom :\nPrénom :\nTéléphone :\nEmail :\n ", maFonte);
                p3.setAlignment(Element.ALIGN_LEFT);
                document.add(p3);

            } catch (DocumentException de) {
                de.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            // LOGO MELIN
            Image image = null;
            try {
                image = Image.getInstance("src/images/melin.jpg");
                image.setAbsolutePosition(37, 720);
            } catch (BadElementException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                document.add(image);
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            PdfPTable table = new PdfPTable(3);

            PdfPCell c1 = new PdfPCell(new Phrase("Ressources"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("TVA"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Montant HTVA"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            table.setHeaderRows(1);

            table.addCell("Engins");
            table.addCell("21%");
            table.addCell(engin.getText());

            table.addCell("Matériaux");
            table.addCell("21%");
            table.addCell(materiau.getText());

            table.addCell("Petits matériels");
            table.addCell("21%");
            table.addCell(petitMateriel.getText());

            table.addCell("Codes références");
            table.addCell("21%");
            table.addCell(codeReference.getText());

            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");

            table.addCell("Voitures");
            table.addCell("21%");
            table.addCell(voiture.getText());

            table.addCell("Camions");
            table.addCell("21%");
            table.addCell(camion.getText());

            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");

            table.addCell("Ouvriers");
            table.addCell(" ");
            table.addCell(ouvrier.getText());

            table.addCell("Conducteurs");
            table.addCell(" ");
            table.addCell(conducteur.getText());

            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");

            table.addCell("Total HTVA");
            table.addCell(" ");
            table.addCell(total.getText());

            table.addCell("Total TVAC");
            table.addCell(" ");
            table.addCell(totalTva.getText());

            document.add(table);

            messagePdf.setText("Devis créé avec succès vers : C:/Users/Public/DevisChantier.pdf");
            document.close();
        }
    }

}
