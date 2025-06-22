package explainability;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import explainability.utils.TextUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class A_StackOverflowExtractorToPython {

    public static void main(String[] args) throws IOException {
        processCSV("corpus-stack-overfl");

    public static void processCSV(String inputFile, String outputFile) throws IOException {
        File file = new File(outputFile);

        int validLineCount = 0;
        int errorLineCount = 0;

        // Configure the CSVReader with lenient settings
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(inputFile))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(',')
                        .withQuoteChar('"')
                        .withEscapeChar(CSVWriter.NO_ESCAPE_CHARACTER)  // No escape character to be more lenient
                        .build())
                .build();
             CSVWriter writer = new CSVWriter(new FileWriter(file, false), ',', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                     CSVWriter.NO_ESCAPE_CHARACTER, "\n")) {

            // Write the header
            writer.writeNext(new String[]{"Tag", "Title", "Detail", "Date"});

            // Skip the header of the input file
            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    if (nextLine.length >= 5) {
                        String tag = nextLine[1];
                        String date = nextLine[2];
                        String title = nextLine[3];
                        String body = nextLine[4];

                        // Concatenate title and body
                        String concatenatedText = title + " " + body;

                        // Clean the text
                        String cleanedText = TextUtils.cleanExtractedText(concatenatedText);

                        // Write the processed line
            writer.writeNext(new String[]{tag, title, cleanedText, date});
                        validLineCount++;
                    } else {
                        errorLineCount++; // Count lines with insufficient fields
                    }
                } catch (Exception e) {
                    errorLineCount++; // Count lines with parsing issues
                }
            }
        } catch (CsvValidationException e) {
            throw new IOException("Error validating CSV: " + e.getMessage(), e);
        }

        // Calculate total lines and percentages
        int totalLines = validLineCount + errorLineCount;
        double validPercentage = (totalLines > 0) ? (validLineCount * 100.0 / totalLines) : 0;
        double errorPercentage = (totalLines > 0) ? (errorLineCount * 100.0 / totalLines) : 0;

        // Print the summary
        System.out.println("Processing Summary:");
        System.out.println("Total Lines: " + totalLines);
        System.out.println("Valid Lines: " + validLineCount + " (" + String.format("%.2f", validPercentage) + "%)");
        System.out.println("Error Lines: " + errorLineCount + " (" + String.format("%.2f", errorPercentage) + "%)");
    }
}
