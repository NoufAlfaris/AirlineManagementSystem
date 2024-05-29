import java.awt.*;
import java.awt.event.*;
import javax.swing.*;;




public class FlightFrame extends JFrame{
  //attirbutes
  Button button;
  JTextField AirlineNameField;
  JTextField AirlineMaxFlightsField;
  

  public FlightFrame(){
    
    setTitle("Airline Management System");
    setSize(500,400);
    setLocation(500, 250);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //creating airline
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    JLabel titleCA = new JLabel("Create Airline");
    titleCA.setFont(new Font("Times new Roman", Font.BOLD, 40));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx=0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.NORTH; //coordinates
    gbc.insets = new Insets(0,0,10,0); //adjust margins 
  
    panel.add(titleCA, gbc);
    

    gbc.gridy++; //to make the next label lower than the title
    gbc.gridx = 0; //starting at the same horizontal coordinates
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(0, 0, 0, 0);
    panel.add(new JLabel("Airline name: "), gbc);
    gbc.gridx++;
    AirlineNameField = new JTextField(10);
    panel.add(AirlineNameField, gbc);

    gbc.gridy++; //to make the next label lower than the prevoius one
    gbc.gridx = 0; //starting at the same horizontal coordinates
    panel.add(new JLabel("Maximum number of flights: "), gbc);
    gbc.gridx++;
    AirlineMaxFlightsField = new JTextField(10);
    panel.add(AirlineMaxFlightsField, gbc);


    JButton AirlineButton = new JButton("Create");
    AirlineButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String airlineName = AirlineNameField.getText();
        String maxFlightText = AirlineMaxFlightsField.getText();
        int maxFlight = 0; //incase parsing fails
        try{
          maxFlight = Integer.parseInt(maxFlightText);
        }
        catch(NumberFormatException nfe){
          JOptionPane.showMessageDialog(null, "Maximum flights should be an integer. Please enter a valid number.");
          return; //if invalid input, return to main window
        }
        try{
        Airline a = new Airline(airlineName, maxFlight);
      
        
        //new window after creating an airline
        FlightMenuFrame nextWindow = new FlightMenuFrame(a);
        nextWindow.setVisible(true);
      }
      catch(AirLineException ex){
        JOptionPane.showMessageDialog(null, ex.getMessage());
        return; 
      }
        //close the current window
        setVisible(false);
        dispose();
      }
    });
    gbc.gridx++;
    gbc.gridy++;
    panel.add(AirlineButton, gbc);

    add(panel);
  }






}

  