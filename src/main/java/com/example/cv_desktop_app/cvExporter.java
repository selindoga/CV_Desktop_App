package com.example.cv_desktop_app;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class cvExporter {

    private void writeLineWithHalfBold(PDPageContentStream cont, float fontSize, String boldText, String regularText)
            throws IOException {

        cont.setLeading(1.5f * fontSize);

        cont.setFont(PDType1Font.TIMES_ROMAN, fontSize);

        // Set the font style to bold for the first line of text
        cont.setFont(PDType1Font.TIMES_BOLD, fontSize);
        cont.showText(boldText);

        // Set the font style back to the default for the rest of the lines of text
        cont.setFont(PDType1Font.TIMES_ROMAN, fontSize);
        cont.showText(regularText);

        cont.newLine();
    }

    private void writeLine(PDPageContentStream cont, float fontSize, boolean isBold, String text) throws IOException {

        cont.setLeading(1.5f * fontSize);

        if (isBold) {
            cont.setFont(PDType1Font.TIMES_BOLD, fontSize);
        } else {
            cont.setFont(PDType1Font.TIMES_ROMAN, fontSize);
        }

        cont.showText(text);
        cont.newLine();
    }

    public void exportToPdf(Person person, String path) throws IOException {

        try (PDDocument doc = new PDDocument()) {
            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                cont.beginText();

                cont.setFont(PDType1Font.TIMES_ROMAN, 12);

                cont.newLineAtOffset(25, 700);

                writeLineWithHalfBold(cont, 12, "Name:", " " + person.getName());

                writeLineWithHalfBold(cont, 12, "Surname:", " " + person.getSurname());

                writeLineWithHalfBold(cont, 12, "Birthday:", " " + person.getBirthday());

                writeLineWithHalfBold(cont, 12, "Education Info:", " " + person.getEducationInfo());

                writeLineWithHalfBold(cont, 12, "Skills:", " " + person.getSkills());

                writeLineWithHalfBold(cont, 12, "Experience:", " " + person.getExperience());

                writeLineWithHalfBold(cont, 12, "Publications:", " " + person.getPublications());

                cont.newLine();
                writeLine(cont, 12, true, "Contact Info:");

                for (int i = 0; i < person.getContactInfo().size(); i++) {
                    writeLine(cont, 12, true, "Contact " + (i + 1) + ":");
                    writeLineWithHalfBold(cont, 12, "Phone:", " " + person.getContactInfo().get(i).getTelephoneNo());
                    writeLineWithHalfBold(cont, 12, "Address:", " " + person.getContactInfo().get(i).getAddress());
                    writeLineWithHalfBold(cont, 12, "Email:", " " + person.getContactInfo().get(i).getEmail());
                    cont.newLine();
                }

                cont.endText();

            } catch (Exception e) {
                e.printStackTrace();
            }

            doc.save(path);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}