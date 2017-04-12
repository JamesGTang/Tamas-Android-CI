package tamas.ecse321.ca.tamas.controller;

import java.util.HashMap;
import java.util.Map;

import tamas.ecse321.ca.tamas.model.Applicant;
import tamas.ecse321.ca.tamas.model.Tamas;
import tamas.ecse321.ca.tamas.util.InputValidator;

public class ApplicantController {

    private Tamas tamas;
    Applicant aApplicant;

    private String applicant_name;
    private String applicant_email;
    private String applicant_id;
    private String applicant_password;
    private String applicant_status;
    private String error;
    private String feedback;

    InputValidator inputValidator=new InputValidator();
    PersistenceController persistenceController=new PersistenceController();

    Map<String,String> applicantInfo=new HashMap<>();

    public ApplicantController(){
    }

    public ApplicantController(Tamas tamas){
        setTamas(tamas);
    }

    public ApplicantController(String fNAME,String lName, String aUsername,
                               String aPassword,String aApplicantId,String aStatus){
        applicant_name=fNAME+" "+lName;
        applicant_email=aUsername;
        applicant_id=aApplicantId;
        applicant_status=aStatus;
        applicant_password=aPassword;
    }

    private void setTamas(Tamas tamas){
        this.tamas = tamas;
    }

    public String vertifyApplicantInformation(){

        if(applicant_id==null| applicant_id.trim().length()==0){
            error="Applicant ID cannot be empty! ";
        }
        if(!inputValidator.isNumeric(applicant_id)){
            error=error+"Applicant ID must be numeric!";
        }
        if(applicant_id.length()!=9){
            error=error+"Applicant ID must be 9 digit";
        }
        if(applicant_name==null||applicant_name.trim().length()==0){
            error=error+"Applicant name cannot be empty! ";
        }
        if(applicant_password==null||applicant_password.trim().length()<=6){
            error=error+"Applicant password cannot be empty or less than 6 letters ";
        }
        if(applicant_email==null||applicant_email.trim().length()==0){
            error=error+"Applicant email cannot be empty! ";
        }

        if(error.trim().length()==0){
            // validation is sucessful
            feedback="Verified";
        }else{
            feedback=error;
        }
        return feedback;
    }

    public void addApplicant(){
        aApplicant=new Applicant(applicant_name,applicant_email,applicant_password,tamas,applicant_id);
    }

    public String registerApplicant(){
        return persistenceController.registerApplicant(aApplicant,applicant_status);
    }

    public Map getApplicantRecordById(String password, String id){
        applicantInfo=applicantInfo=persistenceController.getApplicantRecordfromDB(password,id);
        if(applicantInfo.isEmpty()){
            return null;
        }else{
            return applicantInfo;
        }
    }


}
