import java.awt.*;
import java.awt.event.*;
import javax.swing.*;;


public class FlightMenuFrame extends JFrame {
    
    public FlightMenuFrame(Airline a){
    
        setTitle("Airline Management System: Airline Operations");
        setSize(400,300); //frame size
        setLocation(500, 250); //middle of my screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel menuTitle1 = new JLabel("Please choose an operation from");
        JLabel menuTitle2 = new JLabel("the drop down menu");
        menuTitle1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        menuTitle2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,10,0); //adjust margins
        panel.add(menuTitle1, gbc);
        gbc.gridy++;
        panel.add(menuTitle2, gbc);

        String[] operations = {"Add flight", "Remove flight", "Add passenger to a flight", "Remove a passenger from a flight", "Save and Display Airline information"};

        //create a combobox from operations
        JComboBox<String> airlineOperationCombo = new JComboBox<>(operations);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(airlineOperationCombo, gbc);

        //create a button to choose an operation
        JButton chooseButton = new JButton("Choose");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String selectedOperation = (String) airlineOperationCombo.getSelectedItem();
                if (selectedOperation.equalsIgnoreCase(operations[0])){
                    addFlightFrame nextWindow = new addFlightFrame(a);
                    nextWindow.setVisible(true);
                }
                else if(selectedOperation.equalsIgnoreCase(operations[1])){
                    removeFlightFrame nextWindow = new removeFlightFrame(a);
                    nextWindow.setVisible(true);
                }
                else if(selectedOperation.equalsIgnoreCase(operations[2])){
                    searchFlightFrame nextWindow = new searchFlightFrame(a);
                    nextWindow.setVisible(true);
                }
                else if(selectedOperation.equalsIgnoreCase(operations[3])){
                    removePassengerFrame nextWindow = new removePassengerFrame(a);
                    nextWindow.setVisible(true);
                }
                else if(selectedOperation.equalsIgnoreCase(operations[4])){
                    displayFrame nextWindow = new displayFrame(a);
                    nextWindow.setVisible(true);
                }

                 //close the current window
                setVisible(false);
                 dispose();
            }
        });
        
        gbc.gridy++;
        panel.add(chooseButton, gbc);

        add(panel);


}
}
