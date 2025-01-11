package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;


public class Listener implements KeyListener {
    private final String csvFilePath= "available_numbers.csv";
    private JTextArea textout;
    private String outFilePath;
    private int state;
    private String number;
    private JTextField inputField;
    public Listener (int state, JTextField input,String outFilePath,JTextArea textout){
        state=this.state;
        inputField=input;
        outFilePath=this.outFilePath;
        textout=this.textout;
        initialiseHeader();
    }

    public boolean checkNumber(){
        if (number.matches("\\d{6}")) {
            try (Reader reader = new FileReader(csvFilePath);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                boolean found = false;
                for (CSVRecord record : csvParser) {
                    if (record.get(0).equals(number)) {
                        found = true;
                        return true;
                    }
                }

                append("Number " + number + " not found in CSV.\n");
                return false;

            } catch (IOException ex) {
                append("Error reading CSV file: " + ex.getMessage() + "\n");
                return false;
            }
        } else {
            append("Number not in acceptable format (6-digits)");
            return false;
        }
    }

    public void addNumber(){
        if (checkNumber()){
            try (Writer writer = new FileWriter(outFilePath);
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
                    csvPrinter.printRecord(number);
                append("Output saved to " + outFilePath + "\n");
            } catch (IOException ex) {
                append("Error writing to output CSV file: " + ex.getMessage() + "\n");
            }
        }
        append("Adding unsuccesful");

    }

    public void printNumber(){
        if (number.matches("\\d{6}")) {
            try (Reader reader = new FileReader(csvFilePath);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                boolean found = false;
                for (CSVRecord record : csvParser) {
                    if (record.get(0).equals(number)) {
                        append("Number: " + record.get(0) + ", Art: " + record.get(1) + ", Infos: " + record.get(2) + ", Zustand: " + record.get(3) + "\n");
                    }
                }
                append("Number " + number + " not found in CSV.\n");
            } catch (IOException ex) {
                append("Error reading CSV file: " + ex.getMessage() + "\n");
            }
        } else {
            append("Invalid number. Please enter a 6-digit number.\n");
        }
    }

    public void initialiseHeader(){

        try (Writer writer = new FileWriter(outFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("out","back");
            append("Header initialized in " + outFilePath + "\n");
        } catch (IOException ex) {
            append("Error writing to output CSV file: " + ex.getMessage() + "\n");
        }
    }
    public void append(String outputText){
        textout.append(outputText);
    }

    public void sve(){

    }
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        number = inputField.getText().trim();
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            if (state==0 &&checkNumber()){
            append("klarmachen zum ENTERn");
                addNumber();
                printNumber();
                sve();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    Action doNothing = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            //do nothing
        }
    };
inputField.getInputMap().put(KeyStroke.getKeyStroke("F2"),
                            "doNothing");
component.getActionMap().put("doNothing",
                             doNothing);
}
