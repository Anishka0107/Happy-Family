
package family;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button cont;
    
    @FXML
    private Button extras;
    
    @FXML
    private TextField loginname;
    
    @FXML 
    private PasswordField loginpass;
    
    @FXML
    private Button loginbutton;
    
    @FXML
    private Button registerbutton;
         
    @FXML
    private TextField registername;
    
    @FXML
    private PasswordField pass1;
    
    @FXML
    private PasswordField pass2;
    
    @FXML
    private Label unmatchingpassword;
    
    @FXML
    private Label samename;
    
    @FXML
    private Label presence;
    
    @FXML
    private Label lba;
    
    @FXML
    private Label lbb;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
     if(event.getSource()==cont){       
        Stage stage; 
        Parent root;
        stage=(Stage) cont.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("loginsignup.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
     }
    }
    
    static String primary;
    
    @FXML 
    private void handleSignAction(ActionEvent event) throws IOException{
        Connection con=null;
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        ResultSet rs=null;
        try{
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Member","Anishka","2010aarav");
        }
        catch(SQLException se){
            se.printStackTrace();
            //System.out.println(se.getMessage());
        }
        if(event.getSource()==loginbutton){
            String name=loginname.getText();
            String pass=loginpass.getText();
            try{
            ps=con.prepareStatement("Select * from Anishka.familymembers where name=? and password=?");
            ps.setString(1,name);
            ps.setString(2,pass);
            rs=ps.executeQuery();
                if(rs.next()){
                   primary = name;
                   Stage stagex; 
                   Parent rootx;
                   stagex = (Stage) loginbutton.getScene().getWindow();
                   FXMLLoader fl=new FXMLLoader(getClass().getResource("user.fxml"));
                   UserController uc=new UserController();
                   fl.setController(uc);
                   rootx = fl.load();
                   Scene scene = new Scene(rootx);
                   stagex.setScene(scene);
                   stagex.show();
                }
                else{
                    presence.setText("Invalid Member Name/Password");
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                //System.out.println(e.getMessage());
            } 
        }
        else if(event.getSource() == registerbutton){
            String name = registername.getText();
            String p = pass1.getText();
            String cp = pass2.getText();
            if(!p.equals(cp))
                unmatchingpassword.setText("Passwords do not match!!");
            else{
                unmatchingpassword.setText("");
            try{
            ps=con.prepareStatement("Select * from Anishka.familymembers where name=?");
            ps.setString(1,name);
            rs=ps.executeQuery();
                if(rs.next()){
                    samename.setText("Member already exists!!");
                }
                else{
                   ps1 = con.prepareStatement("Insert into Anishka.familymembers values(?,?)");
                   ps1.setString(1,name);
                   ps1.setString(2,p);
                   ps1.execute();
                   lbb.setText("Succesfully signed up!");
                   Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2500),ab->lba.setText("")));
                   timeline.play();
                   Stage stagex; 
                   Parent rootx;
                   stagex = (Stage) registerbutton.getScene().getWindow();
                   rootx = FXMLLoader.load(getClass().getResource("user.fxml"));
                   Scene scene = new Scene(rootx);
                   primary = name;
                   timeline = new Timeline(new KeyFrame(Duration.millis(2000),ab->stagex.setScene(scene)));
                   timeline.play();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                //System.out.println(e.getMessage());
            }            
        }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


}   

        
