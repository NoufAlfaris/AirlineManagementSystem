import java.awt.*;
import java.awt.event.*;
import javax.swing.*;;

public class searchFlightFrame extends JFrame{
    JTextField cityDestField;
    JButton findButton, returnButton;
    Flight bookedFlight;

    public searchFlightFrame(Airline a){
        setTitle("Airline Management System: Add Passenger");
        setSize(500,400);
        setLocation(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

         //title
         JPanel panel = new JPanel(new GridBagLayout());
         panel.setLayout(new GridBagLayout());
         JLabel titleAF = new JLabel("Please fill the following");
         titleAF.setFont(new Font("Times new Roman", Font.BOLD, 20));
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.anchor = GridBagConstraints.NORTH;
         gbc.insets = new Insets(0,0,10,0); //adjust margins 
   
         panel.add(titleAF, gbc);

         //filling
         //city dest to find flight
         gbc.gridy++;
         gbc.gridx = 0;
         gbc.anchor = GridBagConstraints.WEST;
         gbc.insets = new Insets(0, 0, 0, 0);
         panel.add(new JLabel("Flight City Destination "), gbc);
         gbc.gridx++;
         cityDestField = new JTextField(10);
         panel.add(cityDestField, gbc);

         //button to search for the flight
         findButton = new JButton("Find flight");
         findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String desiredDestination = cityDestField.getText(); //return user input
                bookedFlight = a.searchFlightByDest(desiredDestination); //search for flight
                if(bookedFlight == null){
                  JOptionPane.showMessageDialog(null, "There aren't any available flights going to " + desiredDestination); //Flight not found
                  return; //return to current window so user can enter another destination 
                }
                else{
                    //if flight found ask for passenger info
                    addPassengerFrame nextWindow = new addPassengerFrame(bookedFlight, a);
                    nextWindow.setVisible(true);
                    //close current window
                    setVisible(false);
                    dispose();
                }
                
            }

            
        });
        
        gbc.gridy++;
        panel.add(findButton, gbc);

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


         add(panel);

    }
}
