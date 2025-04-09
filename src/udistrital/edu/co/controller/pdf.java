package udistrital.edu.co.controller;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import udistrital.edu.co.model.Politico;

import java.io.IOException;

public class pdf {
    public static void agregarContenidoAlPDF(PDDocument doc, Politico[] arr1, Politico[] arr2, Politico[][] matriz1, Politico[][] matriz2,
                                             String[] algoritmos, long[][] datos1, long[][] datos2) throws IOException, IOException {

        PDPage pagina = new PDPage(PDRectangle.LETTER);
        doc.addPage(pagina);

        PDPageContentStream contenido = new PDPageContentStream(doc, pagina);
        float margin = 50;
        float width = pagina.getMediaBox().getWidth();
        float height = pagina.getMediaBox().getHeight();
        float y = height - margin;
        float leading = 20f;

        contenido.beginText();
        contenido.setFont(PDType1Font.HELVETICA, 12);
        contenido.newLineAtOffset(margin, y);

        contenido.showText("Arreglo 1:");
        contenido.newLineAtOffset(0, -leading);
        y -= leading;

        for (Politico linea : arr1) {
            contenido.showText(String.valueOf(linea.getValor_a_robar()));
            contenido.newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenido.newLineAtOffset(0, -leading);
        y -= leading;
        contenido.showText("Arreglo 2:");
        contenido.newLineAtOffset(0, -leading);
        y -= leading;

        for (Politico linea : arr2) {
            contenido.showText(String.valueOf(linea.getValor_a_robar()));
            contenido.newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenido.newLineAtOffset(0, -leading);
        y -= leading;
        contenido.showText("Matriz 1:");
        contenido.newLineAtOffset(0, -leading);
        y -= leading;

        for (Politico[] fila : matriz1) {
            StringBuilder lineaTexto = new StringBuilder();
            for (int i = 0; i < fila.length; i++) {
                lineaTexto.append(fila[i].getValor_a_robar());
                if (i < fila.length - 1) lineaTexto.append(" | ");
            }
            contenido.showText(lineaTexto.toString());
            contenido.newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenido.newLineAtOffset(0, -leading);
        y -= leading;
        contenido.showText("Matriz 2:");
        contenido.newLineAtOffset(0, -leading);
        y -= leading;

        for (Politico[] fila : matriz2) {
            StringBuilder lineaTexto = new StringBuilder();
            for (int i = 0; i < fila.length; i++) {
                lineaTexto.append(fila[i].getValor_a_robar());
                if (i < fila.length - 1) lineaTexto.append(" | ");
            }
            contenido.showText(lineaTexto.toString());
            contenido.newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenido.endText();
        contenido.close();

        // Crear una nueva página para las dos tablas
        PDPage tablaPagina = new PDPage(PDRectangle.LETTER);
        doc.addPage(tablaPagina);
        PDPageContentStream tablaContenido = new PDPageContentStream(doc, tablaPagina);

        float yStart = tablaPagina.getMediaBox().getHeight() - margin;
        float rowHeight = 20;
        float tableWidth = tablaPagina.getMediaBox().getWidth() - 2 * margin;
        float colWidth = tableWidth / 4;
        float textMargin = 5;
        float yPos = yStart;

        tablaContenido.setFont(PDType1Font.HELVETICA_BOLD, 12);
        String[] headers = {"Algoritmo", "Tiempo", "Iteraciones", "Comparaciones"};

        for (int i = 0; i < headers.length; i++) {
            tablaContenido.beginText();
            tablaContenido.newLineAtOffset(margin + i * colWidth + textMargin, yPos);
            tablaContenido.showText(headers[i]);
            tablaContenido.endText();
        }

        yPos -= rowHeight;
        tablaContenido.setFont(PDType1Font.HELVETICA, 12);

        for (int i = 0; i < algoritmos.length; i++) {
            tablaContenido.beginText();
            tablaContenido.newLineAtOffset(margin + textMargin, yPos);
            tablaContenido.showText(algoritmos[i]);
            tablaContenido.endText();

            for (int j = 0; j < 3; j++) {
                tablaContenido.beginText();
                tablaContenido.newLineAtOffset(margin + (j + 1) * colWidth + textMargin, yPos);
                tablaContenido.showText(String.valueOf(datos1[i][j]));
                tablaContenido.endText();
            }

            yPos -= rowHeight;
        }

        yPos -= rowHeight;
        tablaContenido.setFont(PDType1Font.HELVETICA_BOLD, 12);

        for (int i = 0; i < headers.length; i++) {
            tablaContenido.beginText();
            tablaContenido.newLineAtOffset(margin + i * colWidth + textMargin, yPos);
            tablaContenido.showText(headers[i]);
            tablaContenido.endText();
        }

        yPos -= rowHeight;
        tablaContenido.setFont(PDType1Font.HELVETICA, 12);

        for (int i = 0; i < algoritmos.length; i++) {
            tablaContenido.beginText();
            tablaContenido.newLineAtOffset(margin + textMargin, yPos);
            tablaContenido.showText(algoritmos[i]);
            tablaContenido.endText();

            for (int j = 0; j < 3; j++) {
                tablaContenido.beginText();
                tablaContenido.newLineAtOffset(margin + (j + 1) * colWidth + textMargin, yPos);
                tablaContenido.showText(String.valueOf(datos2[i][j]));
                tablaContenido.endText();
            }

            yPos -= rowHeight;
        }

        tablaContenido.stroke();
        tablaContenido.close();
    }
}
