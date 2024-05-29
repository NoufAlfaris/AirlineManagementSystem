import java.awt.*;
import java.awt.event.*;
import javax.swing.*;;

//remove flight case frame
public class removeFlightFrame extends JFrame{

    JTextField FlightIDField;
    JButton removeButton, returnButton;


    public removeFlightFrame(Airline a){
        setTitle("Airline Management System: Remove Flight"); //window title
        setSize(500,300);
        setLocation(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel rTitle = new JLabel("fill the following to delete a desired flight");
        rTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,10,0); //adjust margins 
  
        panel.add(rTitle, gbc);
    

        //filling
        //flight ID
        gbc.gridy++; //downwards
        gbc.gridx = 0; //at start of page
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JLabel("Flight ID"), gbc); //add label to panel
        gbc.gridx++; //move to the right
        FlightIDField = new JTextField(10);
        panel.add(FlightIDField, gbc); //add field for input to panel

        removeButton = new JButton("Remove Flight"); //new button
        removeButton.addActionListener(new ActionListener() { //what happend when button is clicked?
            public void actionPerformed(ActionEvent e) {
                String flightID = FlightIDField.getText(); //get the flight id
                if (a.removeFlight(flightID)) //removeFlight(String id) returns true if flight with following id is found and removed, returns false otherwise
                  JOptionPane.showMessageDialog(null, "Flight removed successfully"); //show message dialog
                else{
                    JOptionPane.showMessageDialog(null, "Failed to remove flight, flight with that ID is not found");
                    return; //return to current window to enter a valid flight id
                }
               
                //return to menu window
                FlightMenuFrame nextWindow = new FlightMenuFrame(a);
                nextWindow.setVisible(true);

                //close current window
                setVisible(false);
                dispose();
            }

            
        });
        
        gbc.gridy++;
        panel.add(removeButton, gbc); //add the button to the panel

        returnButton = new JButton("Main menu"); //button
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
