import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class displayFrame extends JFrame{

    JTextArea displayArea;
    JButton displayButton, saveButton, returnButton;

    public displayFrame(Airline a){
        setTitle("Airline Management System: Display and Save");
        setSize(500,400);
        setLocation(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        
        panel.setLayout(new GridBagLayout());
        JLabel titleDS = new JLabel("Display");
        titleDS.setFont(new Font("Times new Roman", Font.BOLD, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH; //coordinates
        gbc.insets = new Insets(0,0,20,0); //adjust margins 
  
        panel.add(titleDS, gbc);

        //create display area
        displayArea = new JTextArea();
        displayArea.setEditable(false); //dont allow user to change the content displayed
        //adjust display area dimension
        displayArea.setRows(10);
        displayArea.setColumns(40);
        

        //allow display area to be scrollable
        JScrollPane scrollDisplayArea = new JScrollPane(displayArea); 
        //adjust size of scrolling in display area 
        scrollDisplayArea.setPreferredSize(new Dimension(400, 200));
        gbc.gridy++;
        gbc.insets = new Insets(0,0,0,0); //adjust margins 
    
        panel.add(scrollDisplayArea, gbc); //add the scroll pane (which includes the display) to the panel

        //create button to display
        displayButton = new JButton("Display airline info");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               displayArea.setText(""); //clear previous entry
               displayArea.setText(a.toString()); //display airline info
            }
        });

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(0,0,0,0); //adjust margins
        panel.add(displayButton, gbc);
        
        //add save option
        
        saveButton = new JButton("Save Airline info");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    
                    //writing object to the chosen file
                    File fwriteobj = new File("AirlineObject.dat");
                    FileOutputStream fosobj = new FileOutputStream(fwriteobj);
                    ObjectOutputStream oos = new ObjectOutputStream(fosobj);
                    
                    oos.writeObject(a); // saves airline object to the chosen file
                    
                    oos.close();
                    
                    //reading object from the chosen file
                    File freadobj  = new File ("AirlineObject.dat");
                    FileInputStream fisobj = new FileInputStream(freadobj);
                    ObjectInputStream ois = new ObjectInputStream(fisobj);
                    
                    
                    //creating airline object from the file, so we can use it later
                    Airline arln = (Airline) ois.readObject();
                    
                    ois.close();
                    
        
                    //ask user to choose the file location
                    JFileChooser fileChooser = new JFileChooser();
                    int userSelection = fileChooser.showSaveDialog(null);
                
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        //write the content in the file user wants
                        PrintWriter pw = new PrintWriter(fileToSave);
                        pw.println(arln.toString());
                        pw.close();
                        // make sure file has .txt extension, if not add it 
                        if (!fileToSave.getName().toLowerCase().endsWith(".txt"))
                            fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
                        
                        
                    }
                  
        
                } catch (ClassNotFoundException cnfe) { // checked exception
                    JOptionPane.showMessageDialog(null, cnfe.getMessage());
                    return; // return to window
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, ioe.getMessage());
                    return; // return to window
                }
                
            }
        });


        gbc.gridy++;
        panel.add(saveButton, gbc);


        returnButton = new JButton("Main menu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // return to menu window
                
                FlightMenuFrame nextWindow = new FlightMenuFrame(a);
                nextWindow.setVisible(true);

                //close current window
                setVisible(false);
                dispose();
            }
        });
        gbc.gridy++;
        panel.add(returnButton, gbc);
        

        add(panel); //add panel to frame
    }
}