package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class OutActionListener extends NewActionListener{
    JTextField inputField;
    public OutActionListener(JTextField inputField){
        inputField = this.inputField;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (state==0){
            state=-1;
        }
        else{
            state = 0;
        }

        if(outputFilePath.equals("")) {
            String filename = "";
            while(filename.equals("")){
                filename= JOptionPane.showInputDialog("enter filename","output");
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(CsvNumberHandler.this);
            outputFilePath = fileChooser.getSelectedFile().getPath();
            outputFilePath=outputFilePath + "/"+filename+".csv";
        }
        try (Writer writer = new FileWriter(outputFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            for (String number : enteredNumbers) {
                csvPrinter.printRecord(number);
            }
            displayArea.append("Output saved to " + outputFilePath + "\n");
        } catch (IOException ex) {
            displayArea.append("Error writing to output CSV file: " + ex.getMessage() + "\n");
        }
    }
}
