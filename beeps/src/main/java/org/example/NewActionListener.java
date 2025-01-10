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

public class NewActionListener implements ActionListener {
    public String csvFilePath = "available_numbers.csv";
    public NewActionListener(){

    }

    public boolean checkAvailableNumbers(String number){
        if (number.matches("\\d{6}")) {
                try (Reader reader = new FileReader(csvFilePath);
                     CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                    for (CSVRecord record : csvParser) {
                        if (record.get(0).equals(number)) {
                            return true;
                        }
                    }
                    return false;
                } catch (IOException ex) {
                    System.out.println("Error reading CSV file: " + ex.getMessage());
                    return false;
                }
            } else {
                System.out.println("Invalid number. Please enter a 6-digit number.");
                return false;
        }
    }

    public String printNumberMeta(String number){
        if (number.matches("\\d{6}")) {
            try (Reader reader = new FileReader(csvFilePath);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                for (CSVRecord record : csvParser) {
                    if (record.get(0).equals(number)) {
                        return("Number: " + record.get(0) + ", Art: " + record.get(1) + ", Infos: " + record.get(2) + ", Zustand: " + record.get(3) + "\n");

                    }

                }
                return "Number not part of inventory";
            } catch (IOException ex) {
                return("Error reading CSV file: " + ex.getMessage());
            }
        } else {
            return("Invalid number. Please enter a 6-digit number.");

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
