package tamas.ecse321.ca.tamas.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import tamas.ecse321.ca.tamas.model.Applicant;
import tamas.ecse321.ca.tamas.persistence.ApplicantPersistence;
import tamas.ecse321.ca.tamas.persistence.JobApplicationPersistence;
import tamas.ecse321.ca.tamas.persistence.JobPersistence;

public class PersistenceController {
    private String applicant_lname;
    private String applicant_fname;
    private String applicant_email;
    private String applicant_id;
    private String applicant_username;
    private String applicant_password;
    private String applicant_status;

    ApplicantPersistence applicantPersistence;
    JobPersistence jobPersistence;
    JobApplicationPersistence jobApplicationPersistence;

    private LinkedList<String> joblinkedList;
    Map<String,String> applicantInfo=new HashMap<>();

    public String registerApplicant(Applicant aApplicant,String status){
        String[] nameString;
        nameString=aApplicant.getName().split(" ");
        applicant_fname=nameString[0];
        applicant_lname=nameString[1];
        applicant_email=aApplicant.getUsername();
        applicant_id=aApplicant.getApplicantId();
        applicant_password=aApplicant.getPassword();
        applicant_status=status;

        applicantPersistence=new ApplicantPersistence();
        String feedback=applicantPersistence.registerStudenttoDB(applicant_lname,applicant_fname,applicant_email,applicant_id,applicant_password,applicant_status);
        return feedback;
    }

    public LinkedList getAllJobPostingfromDB(){
        jobPersistence=new JobPersistence();
        joblinkedList=jobPersistence.getAllJobPosting();
        return joblinkedList;
    }

    public Map getApplicantRecordfromDB(String password, String id){
        applicantPersistence=new ApplicantPersistence();
        applicantInfo=applicantPersistence.getStudentRecordByID(password,id);
        return applicantInfo;
    }

    public String submitApplication(String lname,String fname,String email,String cv, String id,String status){
        String feedback;
        jobApplicationPersistence=new JobApplicationPersistence();

        String applicant_cv=cv;
        String job_posting_id=id;
        String applicant_fname=fname;
        String applicant_lname=lname;
        String applicant_email=email;
        String applicant_status=status;

        feedback=jobApplicationPersistence.submitJobApplicationtoDB(job_posting_id,applicant_fname,applicant_lname,applicant_email,applicant_cv,applicant_status);
        return feedback;
    }
}
