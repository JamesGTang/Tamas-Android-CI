package tamas.ecse321.ca.tamas.controller;

import java.util.LinkedList;

public class JobController {

    PersistenceController persistenceController=new PersistenceController();
    LinkedList<String> jobList;

    public LinkedList getAllJobPosting() {
        jobList = persistenceController.getAllJobPostingfromDB();
        if(jobList.isEmpty()){
            return null;
        }
        return jobList;
    }
}
