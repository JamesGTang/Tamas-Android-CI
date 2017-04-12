/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;
import java.util.*;

// line 36 "../../../../../../../../ump/tmp69916/model.ump"
public class Applicant extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Applicant Attributes
  private String applicantId;

  //Applicant State Machines
  public enum GradStatus { Undergrad, Grad }
  private GradStatus gradStatus;

  //Applicant Associations
  private List<Job> offeredJob;
  private List<JobApplication> jobApplications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Applicant(String aName, String aUsername, String aPassword, Tamas aTamas, String aApplicantId)
  {
    super(aName, aUsername, aPassword, aTamas);
    applicantId = aApplicantId;
    offeredJob = new ArrayList<Job>();
    jobApplications = new ArrayList<JobApplication>();
    setGradStatus(GradStatus.Undergrad);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setApplicantId(String aApplicantId)
  {
    boolean wasSet = false;
    applicantId = aApplicantId;
    wasSet = true;
    return wasSet;
  }

  public String getApplicantId()
  {
    return applicantId;
  }

  public String getGradStatusFullName()
  {
    String answer = gradStatus.toString();
    return answer;
  }

  public GradStatus getGradStatus()
  {
    return gradStatus;
  }

  public boolean setGradStatus(GradStatus aGradStatus)
  {
    gradStatus = aGradStatus;
    return true;
  }

  public Job getOfferedJob(int index)
  {
    Job aOfferedJob = offeredJob.get(index);
    return aOfferedJob;
  }

  public List<Job> getOfferedJob()
  {
    List<Job> newOfferedJob = Collections.unmodifiableList(offeredJob);
    return newOfferedJob;
  }

  public int numberOfOfferedJob()
  {
    int number = offeredJob.size();
    return number;
  }

  public boolean hasOfferedJob()
  {
    boolean has = offeredJob.size() > 0;
    return has;
  }

  public int indexOfOfferedJob(Job aOfferedJob)
  {
    int index = offeredJob.indexOf(aOfferedJob);
    return index;
  }

  public JobApplication getJobApplication(int index)
  {
    JobApplication aJobApplication = jobApplications.get(index);
    return aJobApplication;
  }

  public List<JobApplication> getJobApplications()
  {
    List<JobApplication> newJobApplications = Collections.unmodifiableList(jobApplications);
    return newJobApplications;
  }

  public int numberOfJobApplications()
  {
    int number = jobApplications.size();
    return number;
  }

  public boolean hasJobApplications()
  {
    boolean has = jobApplications.size() > 0;
    return has;
  }

  public int indexOfJobApplication(JobApplication aJobApplication)
  {
    int index = jobApplications.indexOf(aJobApplication);
    return index;
  }

  public static int minimumNumberOfOfferedJob()
  {
    return 0;
  }

  public static int maximumNumberOfOfferedJob()
  {
    return 3;
  }

  public boolean addOfferedJob(Job aOfferedJob)
  {
    boolean wasAdded = false;
    if (offeredJob.contains(aOfferedJob)) { return false; }
    if (numberOfOfferedJob() >= maximumNumberOfOfferedJob())
    {
      return wasAdded;
    }

    offeredJob.add(aOfferedJob);
    if (aOfferedJob.indexOfApplicant(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOfferedJob.addApplicant(this);
      if (!wasAdded)
      {
        offeredJob.remove(aOfferedJob);
      }
    }
    return wasAdded;
  }

  public boolean removeOfferedJob(Job aOfferedJob)
  {
    boolean wasRemoved = false;
    if (!offeredJob.contains(aOfferedJob))
    {
      return wasRemoved;
    }

    int oldIndex = offeredJob.indexOf(aOfferedJob);
    offeredJob.remove(oldIndex);
    if (aOfferedJob.indexOfApplicant(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOfferedJob.removeApplicant(this);
      if (!wasRemoved)
      {
        offeredJob.add(oldIndex,aOfferedJob);
      }
    }
    return wasRemoved;
  }

  public boolean setOfferedJob(Job... newOfferedJob)
  {
    boolean wasSet = false;
    ArrayList<Job> verifiedOfferedJob = new ArrayList<Job>();
    for (Job aOfferedJob : newOfferedJob)
    {
      if (verifiedOfferedJob.contains(aOfferedJob))
      {
        continue;
      }
      verifiedOfferedJob.add(aOfferedJob);
    }

    if (verifiedOfferedJob.size() != newOfferedJob.length || verifiedOfferedJob.size() > maximumNumberOfOfferedJob())
    {
      return wasSet;
    }

    ArrayList<Job> oldOfferedJob = new ArrayList<Job>(offeredJob);
    offeredJob.clear();
    for (Job aNewOfferedJob : verifiedOfferedJob)
    {
      offeredJob.add(aNewOfferedJob);
      if (oldOfferedJob.contains(aNewOfferedJob))
      {
        oldOfferedJob.remove(aNewOfferedJob);
      }
      else
      {
        aNewOfferedJob.addApplicant(this);
      }
    }

    for (Job anOldOfferedJob : oldOfferedJob)
    {
      anOldOfferedJob.removeApplicant(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addOfferedJobAt(Job aOfferedJob, int index)
  {  
    boolean wasAdded = false;
    if(addOfferedJob(aOfferedJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferedJob()) { index = numberOfOfferedJob() - 1; }
      offeredJob.remove(aOfferedJob);
      offeredJob.add(index, aOfferedJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOfferedJobAt(Job aOfferedJob, int index)
  {
    boolean wasAdded = false;
    if(offeredJob.contains(aOfferedJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferedJob()) { index = numberOfOfferedJob() - 1; }
      offeredJob.remove(aOfferedJob);
      offeredJob.add(index, aOfferedJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOfferedJobAt(aOfferedJob, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfJobApplications()
  {
    return 0;
  }

  public static int maximumNumberOfJobApplications()
  {
    return 3;
  }

  public JobApplication addJobApplication(String aExperience, Job aAppliedJob)
  {
    if (numberOfJobApplications() >= maximumNumberOfJobApplications())
    {
      return null;
    }
    else
    {
      return new JobApplication(aExperience, this, aAppliedJob);
    }
  }

  public boolean addJobApplication(JobApplication aJobApplication)
  {
    boolean wasAdded = false;
    if (jobApplications.contains(aJobApplication)) { return false; }
    if (numberOfJobApplications() >= maximumNumberOfJobApplications())
    {
      return wasAdded;
    }

    Applicant existingApplicant = aJobApplication.getApplicant();
    boolean isNewApplicant = existingApplicant != null && !this.equals(existingApplicant);
    if (isNewApplicant)
    {
      aJobApplication.setApplicant(this);
    }
    else
    {
      jobApplications.add(aJobApplication);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJobApplication(JobApplication aJobApplication)
  {
    boolean wasRemoved = false;
    //Unable to remove aJobApplication, as it must always have a applicant
    if (!this.equals(aJobApplication.getApplicant()))
    {
      jobApplications.remove(aJobApplication);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addJobApplicationAt(JobApplication aJobApplication, int index)
  {  
    boolean wasAdded = false;
    if(addJobApplication(aJobApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobApplications()) { index = numberOfJobApplications() - 1; }
      jobApplications.remove(aJobApplication);
      jobApplications.add(index, aJobApplication);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobApplicationAt(JobApplication aJobApplication, int index)
  {
    boolean wasAdded = false;
    if(jobApplications.contains(aJobApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobApplications()) { index = numberOfJobApplications() - 1; }
      jobApplications.remove(aJobApplication);
      jobApplications.add(index, aJobApplication);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobApplicationAt(aJobApplication, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Job> copyOfOfferedJob = new ArrayList<Job>(offeredJob);
    offeredJob.clear();
    for(Job aOfferedJob : copyOfOfferedJob)
    {
      aOfferedJob.removeApplicant(this);
    }
    for(int i=jobApplications.size(); i > 0; i--)
    {
      JobApplication aJobApplication = jobApplications.get(i - 1);
      aJobApplication.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "applicantId" + ":" + getApplicantId()+ "]";
  }
}