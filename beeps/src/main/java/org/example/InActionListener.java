package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class InActionListener extends NewActionListener{
    JTextField inputField;
    public InActionListener(JTextField inputField){
        inputField = this.inputField;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (state==1){
            state=-1;
        }
        else{
            state = 1;
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Csv-Files","csv");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(CsvNumberHandler.this);
        String flp = fileChooser.getSelectedFile().getPath();
        String number = inputField.getText().trim();
        try (Reader reader = new FileReader(flp);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                if (record.get(0).equals(number)) {

                    try (Reader r2 = new FileReader("available_numbers.csv");
                         CSVParser csvParser2 = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                        boolean found = false;
                        for (CSVRecord record2 : csvParser) {
                            if (record2.get(0).equals(number)) {
                                displayArea.append("Number: " + record2.get(0) + ", Art: " + record2.get(1) + ", Infos: " + record2.get(2) + ", Zustand: " + record2.get(3) + "\n");
                                enteredNumbers.add(number);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            displayArea.append("Number " + number + " not found in CSV.\n");
                        }
                    } catch (IOException ex) {
                        displayArea.append("Error reading CSV file: " + ex.getMessage() + "\n");
                    }

                    break;
                }
            }
        } catch (IOException ex) {
            displayArea.append("Error reading CSV file: " + ex.getMessage() + "\n");
        }

        int result = fileChooser.showOpenDialog(CsvNumberHandler.this);

            /*if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                List<String> inNumbers = new ArrayList<>();
                try (Reader reader = new FileReader(selectedFile);
                     CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                    for (CSVRecord record : csvParser) {
                        inNumbers.add(record.get(0));
                    }

                    try (Reader csvReader = new FileReader(csvFilePath);
                         CSVParser csvMainParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                        List<CSVRecord> remainingRecords = new ArrayList<>();
                        for (CSVRecord record : csvMainParser) {
                            if (!inNumbers.contains(record.get(0))) {
                                remainingRecords.add(record);
                            }
                        }

                        displayArea.setForeground(Color.RED);
                        displayArea.setText("Remaining Numbers:\n");
                        for (CSVRecord record : remainingRecords) {
                            displayArea.append(record.toString() + "\n");
                        }

                    } catch (IOException ex) {
                        displayArea.append("Error reading main CSV file: " + ex.getMessage() + "\n");
                    }

                } catch (IOException ex) {
                    displayArea.append("Error reading input CSV file: " + ex.getMessage() + "\n");
                }
            }*/
    }
}
