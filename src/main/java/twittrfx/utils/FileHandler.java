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


import twittrfx.PresentationModel;
import twittrfx.models.BirdPM;

public class FileHandler {

    private final String FILE_NAME = "birds_of_switzerland.tsv";
    private static final String DELIMITER = "\t";

    public List<BirdPM> readFromFile() {
        try (BufferedReader reader = getReader(FILE_NAME)) {
            return reader.lines()
                .skip(1) // erste Zeile ist die Headerzeile; ueberspringen
                .map(line -> new BirdPM(line.split(DELIMITER, 22))) // aus jeder Zeile ein Objekt machen
                .collect(Collectors.toList()); // alles aufsammeln
        } catch (IOException e) {
            throw new IllegalStateException("failed");
        }
    }

    public void save(List<BirdPM> birds) {
        try (BufferedWriter writer = getWriter(FILE_NAME)) {
            writer.write(
                "name\timage\tshort_description\tpopulation_size\tmaximum_life_span_in_years\ttop_speed_in_kmh\tweight\tlength\twingspan\tcontinents\tdiet\tseasonal_behavior\tindependent_age\tpopulation_trend\tpopulation_status\tincubation_period");
            writer.newLine();
            PresentationModel.birds.stream()
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
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(reader);
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
