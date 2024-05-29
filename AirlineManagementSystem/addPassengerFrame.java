import java.awt.*;
import java.awt.event.*;
import javax.swing.*;;

public class addPassengerFrame extends JFrame{
   
    JTextField firstNameField, lastNameField, nationalIDField, ageField, professionField;
    JButton addButton, returnButton;
    

    //will pass airline even though i wont use it
    //so i can go back to search flight frame, 
    //or to main menu frame
    public addPassengerFrame(Flight f, Airline a){ 
        setTitle("Airline Management System: Add Passenger");
        setSize(500,400);
        setLocation(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //title
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        JLabel titleAP = new JLabel("Please fill the following");
        titleAP.setFont(new Font("Times new Roman", Font.BOLD, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,0,0); //adjust margins 
   
        panel.add(titleAP, gbc);

        //filling
        //first name
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JLabel("First Name"), gbc);
        gbc.gridx++;
        firstNameField = new JTextField(10);
        panel.add(firstNameField, gbc);

        //last name
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Last Name"), gbc);
        gbc.gridx++;
        lastNameField = new JTextField(10);
        panel.add(lastNameField, gbc);

        //national ID
        gbc.gridy++; //next line
        gbc.gridx = 0; //start of page
        panel.add(new JLabel("National ID"), gbc);
        gbc.gridx++; //towards the right of the page
        nationalIDField = new JTextField(10);
        panel.add(nationalIDField, gbc);

        //age
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Age"), gbc);
        gbc.gridx++;
        ageField = new JTextField(10);
        panel.add(ageField, gbc);

        //profession
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Profession"), gbc);
        gbc.gridx++;
        professionField = new JTextField(10);
        panel.add(professionField, gbc);

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fName = firstNameField.getText();
                String lName = lastNameField.getText();
                try{
                    String nationalID = nationalIDField.getText();
                    String ageString = ageField.getText();
                    String profession = professionField.getText();
                    int age = 0; //incase parsing fails

                    try{
                        age = Integer.parseInt(ageString);
                    }
                    catch (NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "You have entered an invalid age, please enter a valid one");
                        return; //return to main window so they can re-enter
                    }
                    //create passenger
                    Passenger pAdded = new Passenger(fName, lName, nationalID, age, profession);
                    // add passenger
                    if(f.addPassenger(pAdded)) {
                        String seccussMessage = "Passenger added to flight with following ID: " + f.getId();
                        JOptionPane.showMessageDialog(null, seccussMessage); 
                        if (pAdded.getAge() <= 2) //check if passenger is a child 2 years old or younger, ticket is free if they are
                            JOptionPane.showMessageDialog(null, "Ticket is free for kids aged 2 and younger");
                        else{
                            String priceMessage = "Ticket price " + f.getTicketPrice(nationalID) + "SAR";
                            JOptionPane.showMessageDialog(null, priceMessage);
                        } //end inner else
                    } //end if

                    else{
                        JOptionPane.showMessageDialog(null, "The passenger already exist in the flight, please ask the passenger to check their reservations");
                        //return to searching flight window
                        searchFlightFrame nextWindow = new searchFlightFrame(a);
                        nextWindow.setVisible(true);
                        //close current window
                        setVisible(false);
                        dispose();
                    }
                 
                
                }
                catch(IdException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    return; //return to main window so they can re-enter
                }

                catch(NegativeAge ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return; //return to main window so they can re-enter
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
        gbc.gridx++;
        panel.add(addButton, gbc);

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