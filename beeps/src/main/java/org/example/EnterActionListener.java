package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class EnterActionListener extends NewActionListener {
    JTextField inputField;
    public EnterActionListener(JTextField inputField){
        inputField = this.inputField;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String number = inputField.getText().trim();
        if(state==0) {
            if (!outputFilePath.equals("")) {
                if (number.matches("\\d{6}")) {
                    try (Reader reader = new FileReader(csvFilePath);
                         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                        boolean found = false;
                        for (CSVRecord record : csvParser) {
                            if (record.get(0).equals(number)) {
                                displayArea.append("Number: " + record.get(0) + ", Art: " + record.get(1) + ", Infos: " + record.get(2) + ", Zustand: " + record.get(3) + "\n");
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
                } else {
                    displayArea.append("Invalid number. Please enter a 6-digit number.\n");
                }
                inputField.setText("");
                inputField.requestFocusInWindow();
            } else {
                displayArea.append("No output directory selected \n");
            }
        } else if (state==1) {

        }
    }
}