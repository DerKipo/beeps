package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvNumberHandler extends JFrame {
    //layout
    private JTextField inputField;
    private JTextArea displayArea;
    private List<String> enteredNumbers = new ArrayList<>();
    private String csvFilePath = "src/available_numbers.csv"; // The CSV file with all available numbers
    private String outputFilePath = "";
    //attributes
    private int state= -1;


    public CsvNumberHandler() {
        setTitle("beeps");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        JButton enterButton = new JButton("Enter");
        inputPanel.add(inputField);
        inputPanel.add(enterButton);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton outButton = new JButton("Out");
        JButton inButton = new JButton("In");
        buttonPanel.add(outButton);
        buttonPanel.add(inButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        enterButton.addActionListener(new EnterActionListener(inputField));
        outButton.addActionListener(new OutActionListener(inputField));
        inButton.addActionListener(new InActionListener(inputField));
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CsvNumberHandler frame = new CsvNumberHandler();
            frame.setVisible(true);
        });
    }
}