package twittrfx.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import twittrfx.models.BirdPM;

public class FileHandler {
    private final ObservableList<BirdPM> allBirds = FXCollections.observableArrayList();

    private static final String FILE_NAME = "birds_of_switzerland.tsv";
    private static final String DELIMITER = "\t";

    public FileHandler() {
        allBirds.addAll(readFromFile());
    }

    private List<BirdPM> readFromFile() {
        try (BufferedReader reader = getReader(FILE_NAME)) {
            return reader.lines()
                .skip(1) // erste Zeile ist die Headerzeile; ueberspringen
                .map(line -> new BirdPM(line.split(DELIMITER, 22))) // aus jeder Zeile ein Objekt machen
                .collect(Collectors.toList()); // alles aufsammeln
        } catch (IOException e) {
            throw new IllegalStateException("failed");
        }
    }

    public void save() {
        try (BufferedWriter writer = getWriter(FILE_NAME)) {
            writer.write(
                // TODO change this to the correct header line
                "Gemeinde-Nr.\tBFS Gemeinden\tKanton\tFDP\tCVP\tSPS\tSVP\tLPS\tEVP\tCSP\tGLP\tPdA Sol.\tFGA\tGPS\tSD\tEDU\tFPS\tLega\tÜbrige\tWahlberechtigte\tWählende");
            writer.newLine();
            allBirds.stream()
                .map(resultat -> resultat.infoAsLine(DELIMITER))
                .forEach(line -> {
                    try {
                        writer.write(line.toString());
                        writer.newLine();
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                });
        } catch (IOException e) {
            throw new IllegalStateException("save failed");
        }
    }

    private BufferedReader getReader(String fileName) {
        InputStream inputStream = getClass().getResourceAsStream(fileName);  // damit kann man vom File lesen
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); // lesen von Text-File
        return new BufferedReader(reader);  // damit man zeilenweise lesen kann
    }

    private BufferedWriter getWriter(String fileName) {
        try {
            String file = getClass().getResource(fileName).getFile();
            return new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("wrong file " + fileName);
        }
    }
    
}
