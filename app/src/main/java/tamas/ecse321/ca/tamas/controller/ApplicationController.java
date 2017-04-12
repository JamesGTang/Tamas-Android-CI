package tamas.ecse321.ca.tamas.controller;
import tamas.ecse321.ca.tamas.model.Applicant;
import tamas.ecse321.ca.tamas.model.JobApplication;
import tamas.ecse321.ca.tamas.persistence.ApplicantPersistence;
import tamas.ecse321.ca.tamas.util.InputValidator;
import tamas.ecse321.ca.tamas.Exception.InvalidInputException;

public class ApplicationController {
    String applicant_cv;
    String job_posting_id;
    String applicant_fname;
    String applicant_lname;
    String applicant_email;
    String applicant_status;

    InputValidator inputValidator=new InputValidator();
    PersistenceController persistenceController;

    JobApplication ja;

    public ApplicationController(){

    }

    public ApplicationController(String cv,String id,String fname,String lname,String email,String status){
        this.applicant_cv=cv;
        this.job_posting_id=id;
        this.applicant_fname=fname;
        this.applicant_lname=lname;
        this.applicant_email=email;
        this.applicant_status=status;
    }

    public String verifyApplicationInformation(){
        // only cv will be verifiec because all other information is fetched
        // and checked from sever
        String error="";
        if(applicant_cv.trim().length()==0){
            error="Applicant cv cannot be empty! ";
        }
        if(applicant_cv.trim().length()>=2000){
            error="Applicant cv must be less than 2000 characters! ";
        }
        if (error.trim().length()==0){
            return "Verified!";
        }else{
            return  error;
        }

    }


    public String authenticateStudent(String username,String password){
        if(username.isEmpty()||password.isEmpty()){
            return "Username or password cannot be empty!";
        }
        if(!inputValidator.isNumeric(username)){
            return "Username must be numeric!";
        }
        if(username.length()!=9){
            return "Username must be 9 digits";
        }

        ApplicantPersistence applicantPersistence=new ApplicantPersistence();
        return applicantPersistence.authenticateStudentInforinDB(username,password);
    }

    public String submitApplication(){
        persistenceController=new PersistenceController();
        String feedback=persistenceController.submitApplication(applicant_lname,applicant_fname,applicant_email,applicant_cv,job_posting_id,applicant_status);
        return feedback;
    }

    public void addApplicant(Applicant Applicant) throws InvalidInputException{
        String error="";

        String aApplicantId;
        String aName;
        String aPassword;
        String gradStatus;
        Boolean aIsGrad;
        String aUsername;

        aApplicantId=Applicant.getApplicantId();
        aName=Applicant.getName();
        aPassword=Applicant.getPassword();
        aUsername=Applicant.getUsername();

        gradStatus=Applicant.getGradStatusFullName();
        if(gradStatus.equalsIgnoreCase("Grad")){
            aIsGrad=true;
        }else{
            aIsGrad=false;
        }

        if(aApplicantId==null||aApplicantId.trim().length()==0){
            error=error+"Applicant ID cannot be empty! ";
        }
        if(aName==null||aName.trim().length()==0){
            error=error+"Applicant name cannot be empty! ";
        }
        if(aPassword==null||aPassword.trim().length()==0){
            error=error+"Applicant password cannot be empty! ";
        }
        if(aUsername==null||aUsername.trim().length()==0){
            error=error+"Applicant email cannot be empty! ";
        }
        if(aIsGrad!=true||aIsGrad!=false){
            error=error+"Applicant status cannot be empty! ";
        }

        ja.setApplicant(Applicant);
    }
    public void addExperience(String experience) throws InvalidInputException {
        String error="";
        if(experience==null||experience.length()==0){
            error=error+"Experience cannot be empty! ";
        }
        if(experience.length()<=20){
            error=error+"Experience cannot be less than 20 characters! ";
        }
        if(experience.length()>=2000){
            error=error+"Experience cannot be more than 2000 characters!";
        }
        error=error.trim();
        if(error.length()>0){
            throw new InvalidInputException(error);
        }
        ja.setExperience(experience);
    }

}
