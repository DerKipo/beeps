package org.example;

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

    private String csvFilePath = "src/available_numbers.csv"; // The CSV file with all available numbers
    private String outputFilePath;
    //attributes
    private int state= -1;

    //listener
    private Listener listener;


    public CsvNumberHandler() {
        setTitle("beeps");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        inputPanel.add(inputField);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        state = selectMode();


        setVisible(true);
        selectFileSelector();
        addKeyListener(new Listener(state, inputField,outputFilePath,displayArea));
        //listener=new Listener(state, inputField,outputFilePath,displayArea);
    }
    public int selectMode(){
        int inout = JOptionPane.showOptionDialog(null,"Neue Veranstaltung?","mode selector",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);

        if (inout==0){
            return 0; //out
        }else {
            return 1; //in
        }
    }
    public void selectOutFilePath(){

        String filename = "";
        while(filename.equals("")){
            filename= JOptionPane.showInputDialog("enter filename","output");
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fileChooser.showOpenDialog(CsvNumberHandler.this);
        outputFilePath = fileChooser.getSelectedFile().getPath();
        outputFilePath=outputFilePath + "/"+filename+".csv";
    }
    public void selectInFilePath(){

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Csv-Files","csv");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        //fileChooser.showOpenDialog(CsvNumberHandler.this);
        outputFilePath = fileChooser.getSelectedFile().getPath();
    }

    public void selectFileSelector(){
        if(state==0){
            selectOutFilePath();
        } else if (state==1) {
            selectInFilePath();
        }
        else{
            displayArea.append("state error (maybe we found dark matter)");
        }
    }


    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
            CsvNumberHandler frame = new CsvNumberHandler();
            frame.setVisible(true);
        //});
    }
}