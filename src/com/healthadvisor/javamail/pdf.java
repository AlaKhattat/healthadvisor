
package com.healthadvisor.javamail;


import com.healthadvisor.entities.Produit;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.ArrayList;
import java.util.List;


public class pdf  {

   

    private static Font titreFont = new Font(Font.FontFamily.TIMES_ROMAN, 45, Font.BOLD);
    private static Font redigFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.ORANGE);
    private static Font descFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC);
    private static Font contFont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL);

 
    public static void savePdf(String Titre,List<ArrayList> lst,String prix_total)  {
        try {
            Document doc = new Document();
            
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(Titre + ".pdf"));

            } catch (DocumentException ex) {
                Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.open();
            Paragraph p = new Paragraph();
            Paragraph ptitre = new Paragraph(Titre.toUpperCase(), titreFont);
            ptitre.setAlignment(Element.ALIGN_CENTER);
            p.add(ptitre);
            addEmptyLine(p, 3);
           // com.itextpdf.text.Image pimage = com.itextpdf.text.Image.getInstance("C:\\wamp64\\www\\HealthAdvisorImages\\4x4-dune-merzouga.jpg");
            //pimage.setAlignment(Element.ALIGN_CENTER);
            //Paragraph predig = new Paragraph("hahahahahahaahahaha", redigFont);
            Paragraph p1=new Paragraph("Monsieur/Madame, \n" +
           "Ceci est la facrure de la commande que vous avez passeé sur notre plateforme Health Advisor.\n" +
           "Details de la facture :", descFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            addEmptyLine(p1, 1);
           /* p.add(pimage);
            addEmptyLine(p, 2);
            p.add(predig);
            doc.add(p);
            doc.newPage();
            Paragraph p2=new Paragraph();
            addEmptyLine(p2, 4);
            Paragraph pdesc = new Paragraph("descriptionnnnnnnnnnnnnnnnnnnnnnnnnn", descFont);
            addEmptyLine(p2, 1);
            p2.add(pdesc);
            Paragraph pcont = new Paragraph("kakakakakakaakkakakkaakakakakakakaakak", contFont);
            addEmptyLine(p2, 1);*/
           
           
             PdfPTable table = new PdfPTable(new float[] { 2, 1, 2 });
	  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	  table.addCell("Nom produit");
          table.addCell("Quantite");
          table.addCell("Prix total");
	  table.setHeaderRows(1);
	  PdfPCell[] cells = table.getRow(0).getCells(); 
	  for (int j=0;j<cells.length;j++){
	     cells[j].setBackgroundColor(BaseColor.GRAY);
	  }
          for (int i=0;i<lst.size();i++){
    	     table.addCell(lst.get(i).get(0).toString());
             table.addCell(lst.get(i).get(1).toString());
             table.addCell(lst.get(i).get(2).toString());
          }
           table.addCell(prix_total);
           // p2.add(pcont);
           doc.add(p);
           doc.add(p1);
           doc.add(table);
            //doc.add(p2);
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    

}
