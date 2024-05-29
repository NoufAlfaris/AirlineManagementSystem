import java.awt.*;
import java.awt.event.*;
import javax.swing.*;;

public class removePassengerFrame extends JFrame{

    JTextField FlightIDField;
    JButton removeButton, returnButton;
    String nationalID;

    public removePassengerFrame(Airline a){
        setTitle("Airline Management System: Remove Passenger");
        setSize(500,300);
        setLocation(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel rTitle = new JLabel("fill the following to remove a passenger");
        rTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,10,0); //adjust margins 
  
        panel.add(rTitle, gbc);

        //filling
        //flight id
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JLabel("Flight ID"), gbc); //add label to panel
        gbc.gridx++;
        FlightIDField = new JTextField(10);
        panel.add(FlightIDField, gbc); //add field for input to panel

        removeButton = new JButton("Remove Passenger");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String flightID = FlightIDField.getText();

                Flight f1 = a.searchFlightByID(flightID);
               if(f1==null){ //flight doesnt exist
                    JOptionPane.showMessageDialog(null, "No such flight exist");
                    return; //return to window so user can enter a different flight id
               }
                else{
                    nationalID = JOptionPane.showInputDialog(null, "Enter the passenger's national ID");
                    try{
                        if(nationalID.length() != 10)
                            throw new IdException("National ID must consist of 10 Numbers");
                    }
                    catch(IdException ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                        return; //return to main window so they can re-enter
                    }
                    if(f1.removePassenger(nationalID))
                        JOptionPane.showMessageDialog(null, "Passenger removed successfully.");
                    else
                        JOptionPane.showMessageDialog(null, "The passenger doesn't have a booking on the flight.");
                    
               
               } //end outer else

               //return to menu window
               FlightMenuFrame nextWindow = new FlightMenuFrame(a);
               nextWindow.setVisible(true);

               //close current window
               setVisible(false);
               dispose();
            }
        });

        gbc.gridy++;
        panel.add(removeButton, gbc); //add button to panel


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