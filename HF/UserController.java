
package family;

import static family.FXMLDocumentController.primary;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserController implements Initializable {

    @FXML
    private Label _name;
    
    @FXML
    private Label _age;
    
    @FXML
    private Label _gender;
    
    @FXML
    private Label _dob;
    
    @FXML
    private Label _quote;
   
    @FXML
    private ImageView _pic;

    @FXML
    private DatePicker dp;
        
    @FXML
    private TextField myname;
    
    @FXML
    private TextField myquote;
    
    @FXML
    private Button bi1;
    
    @FXML
    private Button bi2;

    @FXML
    private Button bi3;
    
    @FXML
    private Group g;

    @FXML
    private RadioButton rm;
    
    @FXML
    private RadioButton rf;
    
    @FXML
    private RadioButton ro;
    
    @FXML
    private ImageView iv1;
    
    @FXML
    private Label tbmine;
    
    @FXML
    private ImageView iveye;

    @FXML
    private Label tbeye;
    
    @FXML
    private ImageView ivbody;

    @FXML
    private Label tbbody;
        
    @FXML
    private ImageView ivmouth;
    
    @FXML
    private Label tbmouth;
    
    @FXML
    private Label tbhelp;
    
    @FXML
    private ImageView ivhelp;
    
    @FXML
    private Label tbmany;
    
    @FXML
    private ImageView ivmany;
    
    @FXML
    private Label tbmisc;
    
    @FXML
    private ImageView ivmisc;
    
    @FXML
    private ImageView ivold;
    
    @FXML
    private Label tbold;

    @FXML
    private ImageView ivlogout;
    
    @FXML
    private Label tblogout;
    
    @FXML
    private Pane eyelink;
 
    @FXML
    private Pane mouthlink;
    
    @FXML
    private Pane bodylink;
    
    @FXML
    private Pane profilelink;
    
    @FXML
    private Pane misclink;
    
    @FXML
    private Pane repolink;
    
    @FXML
    private Pane otherlink;
    
    @FXML
    private Pane helplink;
    
    @FXML
    private Pane logoutlink;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openUserProfile();
    }   
    
    @FXML
    public void openUserProfile(){
        String name = primary;
        File f = new File(name+".txt");
     if(!f.exists()){
        try{
            f.createNewFile();
            FileOutputStream ff = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(ff);
            PersonHealth dummy = new PersonHealth();
            dummy.setName(name);
            oos.writeObject(dummy);
            ff.close();
           // System.out.println("Yeaayyy... created the file");
        }
        catch(IOException io){
            io.printStackTrace();
            System.out.println(io.getMessage());
        }
    }
        try(FileInputStream fis = new FileInputStream(f)){
           // Image img=new Image("./people/pics/"+name+".jpg");
           // _pic.setImage(img);
            ObjectInputStream ois = new ObjectInputStream(fis);
            PersonHealth ph = (PersonHealth) ois.readObject();
            _name.setText(ph.getName());
            _dob.setText(ph.getDob().toString());
            _age.setText(ph.getAge() + " years");
            _quote.setText(ph.getQuote());
            char g = ph.getGender();
            if(g == 'M')
                _gender.setText("Male");
            else if(g == 'F')
                _gender.setText("Female");
            else if(g == 'O')
                _gender.setText("Other");
        }
        catch(ClassNotFoundException|IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            //code to show up a dialog box saying that user has deleted his/her data and needs to restart..
                }
    }

    @FXML
    public void changeDetsdobage(MouseEvent ae){
        PersonHealth ph = null;
        String name = primary;
        try(FileInputStream fis = new FileInputStream(name+".txt")){
            ObjectInputStream ois = new ObjectInputStream(fis);
            ph = (PersonHealth) ois.readObject();
        }
        catch(ClassNotFoundException|IOException e){
            System.out.println(e.getMessage());
            //code to show up a dialog box saying that user has deleted his/her data and needs to restart.
        }  
        if(ae.getSource()==_dob){
            dp.setVisible(true);
            bi1.setVisible(true);
            _dob.setVisible(false);
        }
       else if(ae.getSource()==bi1){
           bi1.setVisible(false);
           LocalDate d = dp.getValue();
           ph.setDob(d);
           dp.setVisible(false);
           _dob.setText(ph.getDob().toString());
           _age.setText(ph.getAge()+" years");
           _dob.setVisible(true);
           try(FileOutputStream fos = new FileOutputStream(name+".txt",false)){
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(ph);
           }
           catch(IOException e){
               System.out.println(e.getMessage());
           }
       } 
    }

    @FXML
    public void changeDetsgend(MouseEvent ae){
        PersonHealth ph=null;
        String name = primary;
        try(FileInputStream fis=new FileInputStream(name+".txt")){
            ObjectInputStream ois=new ObjectInputStream(fis);
            ph=(PersonHealth)ois.readObject();
        }
        catch(ClassNotFoundException|IOException e){
            System.out.println(e.getMessage());
            //code to show up a dialog box saying that user has deleted his/her data and needs to restart.
        }  
        if(ae.getSource()==_gender){
            g.setVisible(true);
            bi2.setVisible(true);
            _gender.setVisible(false);
        }
        else if(ae.getSource()==bi2){
           bi2.setVisible(false);
           if(rf.isSelected()==true){
               ph.setGender('F');
               _gender.setText("Female");
           }
           else if(rm.isSelected()==true){
               ph.setGender('M');
               _gender.setText("Male");
           }
           else if(ro.isSelected()==true){
               ph.setGender('O');
               _gender.setText("Other");
           }
           g.setVisible(false);
           _gender.setVisible(true);
           try(FileOutputStream fos = new FileOutputStream(name+".txt",false)){
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(ph);
           }
           catch(IOException e){
               //write some code
           }
       }
    }
    
    @FXML
    public void shiftLeft(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->iv1.setTranslateX(-60)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmine.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPos(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->iv1.setTranslateX(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmine.setVisible(false)));
        t1.play();
        t2.play();
    }

    @FXML
    public void shiftUp1(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivbody.setTranslateY(-30)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbbody.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPos1(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivbody.setTranslateY(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbbody.setVisible(false)));
        t1.play();
        t2.play();
    }    

    @FXML
    public void shiftUp2(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->iveye.setTranslateY(-15)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbeye.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPos2(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->iveye.setTranslateY(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbeye.setVisible(false)));
        t1.play();
        t2.play();
    }    
    
    @FXML
    public void shiftUp3(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivmouth.setTranslateY(-15)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmouth.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPos3(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivmouth.setTranslateY(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmouth.setVisible(false)));
        t1.play();
        t2.play();
    }

    @FXML
    public void shiftLefta(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivmany.setTranslateX(-60)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmany.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPosa(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivmany.setTranslateX(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmany.setVisible(false)));
        t1.play();
        t2.play();
    }    
        
    @FXML
    public void shiftLeftb(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivmisc.setTranslateX(-60)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmisc.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPosb(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivmisc.setTranslateX(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbmisc.setVisible(false)));
        t1.play();
        t2.play();
    }    

    @FXML
    public void shiftLeftc(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivold.setTranslateX(-60)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbold.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPosc(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivold.setTranslateX(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbold.setVisible(false)));
        t1.play();
        t2.play();
    }    

    @FXML
    public void shiftUpx(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivhelp.setTranslateY(-25)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbhelp.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPosx(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivhelp.setTranslateY(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tbhelp.setVisible(false)));
        t1.play();
        t2.play();
    }    

    @FXML
    public void shiftUpy(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivlogout.setTranslateY(-20)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tblogout.setVisible(true)));
        t1.play();
        t2.play();
    }
    
    @FXML
    public void backToPosy(MouseEvent me){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(200),ab->ivlogout.setTranslateY(0)));
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(200),ab->tblogout.setVisible(false)));
        t1.play();
        t2.play();
    }    
    
    @FXML
    public void clickeye(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)eyelink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("eye.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }

    @FXML
    public void clickmouth(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)mouthlink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("mouth.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }
    
    @FXML
    public void clickbody(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)bodylink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("body.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }
    
    @FXML
    public void clickprofile(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)profilelink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("user.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
            //openUserProfile(primary);
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }
    
    @FXML
    public void clickmisc(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)misclink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("misc.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }
    
    @FXML
    public void clickrepo(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)repolink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("report.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }
    
    @FXML
    public void clickother(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)otherlink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("other.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }
    
    @FXML
    public void clicklogout(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)logoutlink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("loginsignup.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }
    
    @FXML
    public void clickhelp(MouseEvent ae){
        try{
            Stage stagex; 
            Parent rootx;
            stagex = (Stage)helplink.getScene().getWindow();
            rootx=FXMLLoader.load(getClass().getResource("help.fxml"));
            Scene scene = new Scene(rootx);
            stagex.setScene(scene);
            stagex.show();
        }
        catch(IOException ie){
            System.out.println(ie.getMessage());
        }
    }    
}