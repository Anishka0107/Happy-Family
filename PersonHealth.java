package family;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.scene.image.Image;

public class PersonHealth implements Serializable{
    private static long serialVersionUID = 42L;
    private String name;
    private int age;
    private char gender;
    private LocalDate dob;
    private String quote;
    
    public PersonHealth(){
        name = "Name";
        age = 0;
        gender = 'M';
        dob = LocalDate.MIN;
        quote= "Health is wealth";
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
        LocalDate currentdate=LocalDate.now();
        this.age = currentdate.getYear()-dob.getYear();
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
    
}
    class Eye implements Serializable{
        double leftsp;
        double leftcy;
        double leftax;
        String leftvis;
        double rightsp;
        double rightcy;
        double rightax;
        String rightvis;
        boolean colorblind;
        String anyother;

        public Eye() {
            leftsp = 0.0;
            leftcy = 0.0;
            leftax = 0.0;
            leftvis = "";
            rightsp = 0.0;
            rightcy = 0.0;
            rightax = 0.0;
            rightvis = "";
            colorblind = false;
            anyother = null;
        }
        
    }
    
    class Body implements Serializable{
        double haemo;  //haemoglobin
        String group;  //bloodgroup
        double weight;
        double height;
        double bmi;
        String docname;
        String docnumber;

        public Body() {
            haemo = 0.0;
            group = "";
            weight = 0.0;
            height = 0.0;
            bmi = 0.0;
            docname = null;
            docnumber = null;
        }
        
    }
    
    class Mouth implements Serializable{
        int numMilkTeeth;
        int numPermTeeth;
        int totalTeeth;
        int numArtificial;
        int numRCT;
        int numFilled;
        String gums;
        String tongue;
        String teeth;
        String treatmenthistory;

        public Mouth() {
            numMilkTeeth=0;
            numPermTeeth=0;
            totalTeeth=0;
            numArtificial=0;
            numRCT=0;
            numFilled=0;
            gums=null;
            tongue=null;
            teeth=null;
            treatmenthistory=null;
        }
        
    }
    
    class AI implements Serializable{
        String illnesses;
        String accidents;
    }//for accidents and illnesses
    
    class Report implements Serializable{
        Image arr[];
        String medicationstaken;
        Report(){
            arr=new Image[100];
            medicationstaken=null;
        }
        
    }// upload reports and store links here
    
    class AR implements Serializable{
        String allergies;
        String reactions;
        String deficiencies;
        AR(){
            allergies=null;
            reactions=null;
            deficiencies=null;
        }
    }//allergies and reactions of drugs