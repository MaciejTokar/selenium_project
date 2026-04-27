package springapp.utils;

import springapp.object.AdminPageCsvData;
import org.junit.jupiter.params.shadow.de.siegmar.fastcsv.reader.CsvReader;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CsvUtils {

    public static List<AdminPageCsvData> readAdminData(String fileName) {

        List<AdminPageCsvData> result = new ArrayList<>();

        try (
                InputStream is = CsvReader.class
                        .getClassLoader()
                        .getResourceAsStream(fileName);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, StandardCharsets.UTF_8))
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");

                AdminPageCsvData data = new AdminPageCsvData(
                        values[0],
                        values[1],
                        values[2],
                        values[3]
                );

                result.add(data);
            }

        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu CSV", e);
        }

        return result;
    }
}
