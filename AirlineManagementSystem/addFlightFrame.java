import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class addFlightFrame extends JFrame{

    JTextField FlightIDField, flightTypeField, countryDestField, cityDestField, durationField, maxField;
    JButton dateButton, createButton, returnButton;
    String dateString = "";
    Flight fAdded;

    public addFlightFrame(Airline a){
        //window
        setTitle("Airline Management System: Add Flight");
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
        //flight ID
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JLabel("Flight ID: "), gbc);
        gbc.gridx++;
        FlightIDField = new JTextField(10);
        panel.add(FlightIDField, gbc);

        //I or L
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Local or International flight?"), gbc);
        gbc.gridx++;
        flightTypeField = new JTextField(10);
        panel.add(flightTypeField, gbc);

        //dest
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Country destination."), gbc);
        gbc.gridx++;
        countryDestField = new JTextField(10);
        panel.add(countryDestField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("City destination"), gbc);
        gbc.gridx++;
        cityDestField = new JTextField(10);
        panel.add(cityDestField, gbc);

        //duration
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("duration in hours"),gbc);
        gbc.gridx++;
        durationField = new JTextField(10);
        panel.add(durationField, gbc);

        //date
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("date"), gbc);
        dateButton = new JButton("Select Date");
        //a button for selecting a date
        dateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dateString = JOptionPane.showInputDialog(null, "Please enter the date in yyyy-mm-dd format");
            }

            
        });
        gbc.gridx++;
        panel.add(dateButton, gbc);

        //max # of passenger
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Maximum number of passengers"), gbc);
        gbc.gridx++;
        maxField = new JTextField(10);
        panel.add(maxField, gbc);


       //collect info, and create flight
       createButton = new JButton("Create Flight");

       createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String flightID = FlightIDField.getText();
                char flightType = flightTypeField.getText().charAt(0);
                String countryDest = countryDestField.getText();
                if(flightType == 'L' || flightType == 'l')
                    countryDest = "KSA";
                String cityDest = cityDestField.getText();
                String duration = durationField.getText();
                String maxPassengers = maxField.getText();
                //incase parsing fails
                double durationD = 0; 
                int maxPassengersI = 0; 
                int day = 0; 
                int month = 0;
                int year = 0;
                try{
                
                    durationD = Double.parseDouble(duration);
                    maxPassengersI = Integer.parseInt(maxPassengers);
                    year = Integer.parseInt(dateString.substring(0, 4));
                    //if the month/day starts with 0, I only want the second digit to avoid treating the integer as an octal number
                    if(dateString.charAt(5) == '0')
                        month = Integer.parseInt(dateString.substring(6,7));
                    else
                        month = Integer.parseInt(dateString.substring(5,7));
                    if(dateString.charAt(8) == '0')
                        day = Integer.parseInt(dateString.substring(9));
                    else
                        day = Integer.parseInt(dateString.substring(8));
                }
                catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "You have entered a letter/word in a number place (duration, max passengers, or date). Please enter a valid number.");
                        return; //if invalid input, return to main window
                    }
                  
                try{
                    if(flightType == 'I' || flightType == 'i') //initialize f based on flight type
                        fAdded = new InternationalFlight(flightID, cityDest, countryDest, durationD, day, month, year, maxPassengersI); //International flight
                    else
                        fAdded = new LocalFlight(flightID, cityDest, countryDest, durationD, day, month, year, maxPassengersI); //local flight
                }
                catch( MnthsException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    return; //if invalid input, return to main window
                  }
                if(a.addFlight(fAdded)) //addFlight(Flight f) method checks if there is space in fList and returns true if there is, otherwise it returns false
                  JOptionPane.showMessageDialog(null, "Flight added successfully");
               else
                  JOptionPane.showMessageDialog(null, "Failed to add flight, airline has reached maximum capacity");
                  //wont return to current window because logically speaking, 
                  //there's no point in trying again if the airline has reached max capacity
                
                //return to menu window
                FlightMenuFrame nextWindow = new FlightMenuFrame(a);
                nextWindow.setVisible(true);

                //close current window
                setVisible(false);
                dispose();

            }
       });

        gbc.gridx++;
        gbc.gridy++;
        panel.add(createButton, gbc);


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