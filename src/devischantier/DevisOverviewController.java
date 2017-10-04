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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import db.dto.CamionDuChantierDto;
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
    @FXML
    private Label tva;
    @FXML
    private Label tvac;
    @FXML
    private Label marge;

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
                    ouvrier.setText(String.format("%.2f", montantOuvrier));

                    CamionDuChantierSel cs = new CamionDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantCamion = Utilitaire.montantCamions(cs);
                    //camion.setText(montantCamion.toString());
                    camion.setText(String.format("%.2f", montantCamion));

                    VoitureDuChantierSel vs = new VoitureDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantVoiture = Utilitaire.montantVoitures(vs);
                    //voiture.setText(montantVoiture.toString());
                    voiture.setText(String.format("%.2f", montantVoiture));

                    EnginDuChantierSel es = new EnginDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantEngin = Utilitaire.montantEngins(es);
                    //engin.setText(montantEngin.toString());
                    engin.setText(String.format("%.2f", montantEngin));

                    MateriauDuChantierSel ms = new MateriauDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantMateriau = Utilitaire.montantMateriaux(ms);
                    //materiau.setText(montantMateriau.toString());
                    materiau.setText(String.format("%.2f", montantMateriau));

                    PetitMaterielDuChantierSel pms = new PetitMaterielDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantPetitMateriel = Utilitaire.montantPetitsMateriels(pms);
                    //petitMateriel.setText(montantPetitMateriel.toString());
                    petitMateriel.setText(String.format("%.2f", montantPetitMateriel));

                    ConducteurDuChantierSel cos = new ConducteurDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantConducteur = Utilitaire.montantConducteurs(cos);
                    //conducteur.setText(montantConducteur.toString());
                    conducteur.setText(String.format("%.2f", montantConducteur));

                    CodeReferenceDuChantierSel cods = new CodeReferenceDuChantierSel(Integer.parseInt(idChantier.getText()), "argumentFactice");
                    Double montantCodeReference = Utilitaire.montantCodesReferences(cods);
                    //codeReference.setText(montantCodeReference.toString());
                    codeReference.setText(String.format("%.2f", montantCodeReference));

                    Double montantTotalHtva = (montantOuvrier + montantCamion + montantVoiture + montantEngin + montantPetitMateriel + montantMateriau + montantConducteur + montantCodeReference);
                    Double montantTva = montantTotalHtva * 0.21;
                    Double montantTotal = montantTotalHtva + montantTva;
                    Double montantMarge = montantTotal * 0.20;

                    Double montantTotal2 = montantTotal + montantMarge;

                    total.setText(String.format("%.2f", montantTotalHtva));

                    tva.setText(String.format("%.2f", montantTva));
                    tvac.setText(String.format("%.2f", montantTotal));
                    marge.setText(String.format("%.2f", montantMarge));
                    totalTva.setText(String.format("%.2f", montantTotal2));

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
                maFonte2.setColor(243, 227, 4);
                maFonte2.setSize(20);

                Paragraph p0 = new Paragraph("Melin\nChaussée Provinciale, 85\n1341 Ottignies\nTEL : 010/61.28.47\nFAX : 010/61.13.27\nEmail : info@melinsa.be ", maFonte);
                p0.setAlignment(Element.ALIGN_RIGHT);
                document.add(p0);

                Paragraph p2 = new Paragraph("DEVIS\n", maFonte2);
                p2.setAlignment(Element.ALIGN_CENTER);
                document.add(p2);

                Paragraph p3 = new Paragraph("Devis n°" + idDevis.getText() + "\nDésignation : " + designation.getText() + "\nStatut : " + statut.getText() + "\nDate du devis : " + date.getText() + "\n\n");
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
            c1.setBackgroundColor(BaseColor.ORANGE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("TVA"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.ORANGE);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Montant HTVA"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.ORANGE);
            table.addCell(c1);

            table.setHeaderRows(1);

            c1 = new PdfPCell(new Phrase("Engins"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("21 %"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(engin.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Matériaux"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("21 %"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(materiau.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Petits matériels"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("21 %"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(petitMateriel.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Codes références"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("21 %"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(codeReference.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");

            c1 = new PdfPCell(new Phrase("Voitures"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("21 %"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(voiture.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Camions"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("21 %"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(camion.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");

            c1 = new PdfPCell(new Phrase("Ouvriers"));
            table.addCell(c1);
            table.addCell(" ");
            c1 = new PdfPCell(new Phrase(ouvrier.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Conducteurs"));
            table.addCell(c1);
            table.addCell(" ");
            c1 = new PdfPCell(new Phrase(conducteur.getText() + " €"));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(c1);

            PdfPTable table2 = new PdfPTable(2);

            PdfPCell c2 = new PdfPCell(new Phrase(" "));
            c2.setBorder(0);
            table2.addCell(c2);

            c2 = new PdfPCell(new Phrase(" "));
            c2.setBorder(0);
            table2.addCell(c2);

            table2.setHeaderRows(1);

            table2.addCell("Prix de revient (HTVA)");
            c2 = new PdfPCell(new Phrase(total.getText() + " €"));
            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(c2);

            table2.addCell("TVA 21%");
            c2 = new PdfPCell(new Phrase(tva.getText() + " €"));
            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(c2);

            table2.addCell("Prix de revient (TVAC)");
            c2 = new PdfPCell(new Phrase(tvac.getText() + " €"));
            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(c2);

            table2.addCell("Marge 20%");
            c2 = new PdfPCell(new Phrase(marge.getText() + " €"));
            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(c2);

            c2 = new PdfPCell(new Phrase("Total du devis"));
            c2.setBackgroundColor(BaseColor.ORANGE);
            table2.addCell(c2);
            c2 = new PdfPCell(new Phrase(totalTva.getText() + " €"));
            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c2.setBackgroundColor(BaseColor.ORANGE);
            table2.addCell(c2);

            document.add(table);
            document.add(table2);

            messagePdf.setText("Devis créé avec succès vers : C:/Users/Public/DevisChantier.pdf");
            document.close();
        }
    }

}
