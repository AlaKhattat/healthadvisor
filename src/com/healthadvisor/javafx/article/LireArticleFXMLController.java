
package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.entities.Article_Votes;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.service.impl.GestionArticle;
import com.healthadvisor.service.impl.GestionArticleVotes;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import com.itextpdf.text.Element;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.sql.Date;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

public class LireArticleFXMLController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label cont;
    @FXML
    private Label desc;
    @FXML
    private Label idmed;
    @FXML
    private ImageView imgView;
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private JFXButton pdfBut;
    @FXML
    private Rating rating;
    @FXML
    private Label tagsL;
    @FXML
    private JFXComboBox validCombo;
    @FXML
    private JFXButton validBut;
    @FXML
    private JFXButton modif;
    @FXML
    private AnchorPane anchor;

    private String retour;
    private String url;
    private int id;

    private static Font titreFont = new Font(Font.FontFamily.TIMES_ROMAN, 45, Font.BOLD);
    private static Font redigFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.ORANGE);
    private static Font descFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC);
    private static Font contFont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL);

    Date d2 = new Date(1970, 9, 9);
    Patient p=new Patient(); //SESSION PATIENT
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validCombo.getItems().addAll("Valider", "Retirer");
        back.setVisible(true);
    }

    @FXML
    private void savePdf(ActionEvent event) throws IOException {
        try {
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Tarek\\Desktop\\"+titre.getText() + ".pdf"));

            } catch (DocumentException ex) {
                Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.open();
            Paragraph p = new Paragraph();
            Paragraph ptitre = new Paragraph(titre.getText().toUpperCase(), titreFont);
            ptitre.setAlignment(Element.ALIGN_CENTER);
            p.add(ptitre);
            addEmptyLine(p, 1);
            com.itextpdf.text.Image pimage = com.itextpdf.text.Image.getInstance(url);
            pimage.setAlignment(Element.ALIGN_CENTER);
            Paragraph predig = new Paragraph(idmed.getText(), redigFont);
            predig.setAlignment(Element.ALIGN_RIGHT);
            addEmptyLine(p, 2);
            p.add(pimage);
            addEmptyLine(p, 2);
            p.add(predig);
            doc.add(p);
            doc.newPage();
            Paragraph p2 = new Paragraph();
            addEmptyLine(p2, 4);
            Paragraph pdesc = new Paragraph(desc.getText(), descFont);
            addEmptyLine(p2, 1);
            p2.add(pdesc);
            Paragraph pcont = new Paragraph(cont.getText(), contFont);
            addEmptyLine(p2, 1);
            p2.add(pcont);
            doc.add(p2);
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int nb) {
        for (int i = 0; i < nb; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @FXML
    private void calculRating(MouseEvent event) {
        GestionArticle ga = new GestionArticle();
        GestionArticleVotes gav = new GestionArticleVotes();
        double note = rating.getRating();
        if (gav.rechArticleLogin(id, p.getLogin()) == true) {
            gav.modifierArticleVotes(id, p.getLogin(), note);
            ga.calcul(id);
        } else {
            Article_Votes av = new Article_Votes(ga.rechercheRef(id), p, note);
            gav.ajouterArticleVotes(av);
            ga.calcul(id);
        }
    }

    @FXML
    private void validArticle(ActionEvent event) {
        GestionArticle ga = new GestionArticle();
        String statut = validCombo.getSelectionModel().getSelectedItem().toString();
        if (statut == "Valider") {
            Boolean b = ga.isValid(id);
            if (b.equals(false)) {
                ga.validationArticle(id, "Validé");
            } else {
                System.out.println("déja validé !");
            }
        } else if (statut == "Retirer") {
            Boolean b = ga.isValid(id);
            if (b.equals(true)) {
                ga.validationArticle(id, "Retiré");
            } else {
                System.out.println("déja retiré !");
            }
        }
    }

    @FXML
    private void redirectBack(MouseEvent event) {
        if (retour.equals("mes")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticlesFXML.fxml"));
            try {
                Parent root;
                root = loader.load();
                MesArticlesFXMLController mes = loader.getController();
                Scene scene = anchor.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(MesArticlesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (retour.equals("navig")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NaviguerArticlesFXML.fxml"));
            try {
                Parent root;
                root = loader.load();
                NaviguerArticlesFXMLController nav = loader.getController();
                Scene scene = anchor.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(NaviguerArticlesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void redirectModif(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifArticleFXML.fxml"));
        try {
            GestionArticle ga = new GestionArticle();
            Article art = ga.rechercheRef(id);
            Parent root;
            root = loader.load();
            ModifArticleFXMLController cnt = loader.getController();
            cnt.setRef(id);
            cnt.setContF(cont.getText());
            cnt.setImgView(art.getImage());
            cnt.setDescCombo(desc.getText());
            cnt.setTagsF(tagsL.getText());
            cnt.setTitreF(titre.getText());
            cnt.setRetour(retour);
            cnt.affichage = true;
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    public void setCont(String cont) {
        this.cont.setText(cont);
    }

    public void setDesc(String desc) {
        this.desc.setText(desc);
    }

    public void setIdmed(String idmed) {
        this.idmed.setText("Par : " + idmed);
    }

    public void setRating(Double rating) {
        this.rating.setRating(rating);
    }

    public void setTagsL(String tagsL) {
        this.tagsL.setText(tagsL);
    }

    public void setImg(String url) {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imgView.setImage(img_produit);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }

    public FontAwesomeIconView getBack() {
        return back;
    }
    
    

}
