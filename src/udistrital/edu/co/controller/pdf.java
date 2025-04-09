package udistrital.edu.co.controller;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import udistrital.edu.co.model.Politico;

import java.io.IOException;
import java.util.function.BiConsumer;

public class pdf {
    public static void agregarContenidoAlPDF(PDDocument doc, Politico[] arr1, Politico[] arr2, Politico[][] matriz1, Politico[][] matriz2,
                                              String[] algoritmos, long[][] datos1, long[][] datos2) throws IOException {

        float margin = 50;
        float leading = 20f;
        float width = PDRectangle.LETTER.getWidth();
        float height = PDRectangle.LETTER.getHeight();
        float y = height - margin;

        PDPage pagina = new PDPage(PDRectangle.LETTER);
        doc.addPage(pagina);

        PDPageContentStream contenido = new PDPageContentStream(doc, pagina);
        contenido.setFont(PDType1Font.HELVETICA, 12);
        contenido.beginText();
        contenido.newLineAtOffset(margin, y);

        // Método auxiliar para verificar y crear nueva página si no hay espacio suficiente
        BiConsumer<PDDocument, PDPageContentStream[]> nuevaPaginaSiEsNecesario = (documento, contenedor) -> {
            try {
                contenedor[0].endText();
                contenedor[0].close();
                PDPage nuevaPagina = new PDPage(PDRectangle.LETTER);
                doc.addPage(nuevaPagina);
                PDPageContentStream nuevoContenido = new PDPageContentStream(doc, nuevaPagina);
                nuevoContenido.setFont(PDType1Font.HELVETICA, 12);
                nuevoContenido.beginText();
                nuevoContenido.newLineAtOffset(margin, height - margin);
                contenedor[0] = nuevoContenido;
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        PDPageContentStream[] contenedor = new PDPageContentStream[]{contenido};

        contenido.showText("Arreglo 1:");
        y -= leading;
        contenido.newLineAtOffset(0, -leading);

        for (Politico p : arr1) {
            if (y <= margin) {
                y = height - margin;
                nuevaPaginaSiEsNecesario.accept(doc, contenedor);
                contenedor[0].newLineAtOffset(0, 0);
            }
            contenedor[0].showText(String.valueOf(p.getValor_a_robar()));
            contenedor[0].newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenedor[0].showText("Arreglo ordenado:");
        y -= leading;
        contenedor[0].newLineAtOffset(0, -leading);

        for (Politico p : arr2) {
            if (y <= margin) {
                y = height - margin;
                nuevaPaginaSiEsNecesario.accept(doc, contenedor);
            }
            contenedor[0].showText(String.valueOf(p.getValor_a_robar()));
            contenedor[0].newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenedor[0].showText("Matriz 1:");
        y -= leading;
        contenedor[0].newLineAtOffset(0, -leading);

        for (Politico[] fila : matriz1) {
            if (y <= margin) {
                y = height - margin;
                nuevaPaginaSiEsNecesario.accept(doc, contenedor);
            }

            StringBuilder lineaTexto = new StringBuilder();
            for (int i = 0; i < fila.length; i++) {
                if (fila[i] != null) {
                    lineaTexto.append(fila[i].getValor_a_robar()).append(" ").append(fila[i].getEdad());
                } else {
                    lineaTexto.append("null");
                }
                if (i < fila.length - 1) lineaTexto.append(" | ");
            }

            contenedor[0].showText(lineaTexto.toString());
            contenedor[0].newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenedor[0].showText("Matriz ordenada:");
        y -= leading;
        contenedor[0].newLineAtOffset(0, -leading);

        for (Politico[] fila : matriz2) {
            if (y <= margin) {
                y = height - margin;
                nuevaPaginaSiEsNecesario.accept(doc, contenedor);
            }

            StringBuilder lineaTexto = new StringBuilder();
            for (int i = 0; i < fila.length; i++) {
                if (fila[i] != null) {
                    lineaTexto.append(fila[i].getValor_a_robar()).append(" ").append(fila[i].getEdad());
                } else {
                    lineaTexto.append("null");
                }
                if (i < fila.length - 1) lineaTexto.append(" | ");
            }

            contenedor[0].showText(lineaTexto.toString());
            contenedor[0].newLineAtOffset(0, -leading);
            y -= leading;
        }

        contenedor[0].endText();
        contenedor[0].close();

        // Página con las tablas
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
