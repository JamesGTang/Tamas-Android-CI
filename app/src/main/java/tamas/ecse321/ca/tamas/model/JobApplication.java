/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;

// line 83 "../../../../../../../../ump/tmp69916/model.ump"
public class JobApplication
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobApplication Attributes
  private String experience;

  //JobApplication Associations
  private Applicant applicant;
  private Job appliedJob;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public JobApplication(String aExperience, Applicant aApplicant, Job aAppliedJob)
  {
    experience = aExperience;
    boolean didAddApplicant = setApplicant(aApplicant);
    if (!didAddApplicant)
    {
      throw new RuntimeException("Unable to create jobApplication due to applicant");
    }
    boolean didAddAppliedJob = setAppliedJob(aAppliedJob);
    if (!didAddAppliedJob)
    {
      throw new RuntimeException("Unable to create jobApplication due to appliedJob");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setExperience(String aExperience)
  {
    boolean wasSet = false;
    experience = aExperience;
    wasSet = true;
    return wasSet;
  }

  public String getExperience()
  {
    return experience;
  }

  public Applicant getApplicant()
  {
    return applicant;
  }

  public Job getAppliedJob()
  {
    return appliedJob;
  }

  public boolean setApplicant(Applicant aApplicant)
  {
    boolean wasSet = false;
    //Must provide applicant to jobApplication
    if (aApplicant == null)
    {
      return wasSet;
    }

    //applicant already at maximum (3)
    if (aApplicant.numberOfJobApplications() >= Applicant.maximumNumberOfJobApplications())
    {
      return wasSet;
    }
    
    Applicant existingApplicant = applicant;
    applicant = aApplicant;
    if (existingApplicant != null && !existingApplicant.equals(aApplicant))
    {
      boolean didRemove = existingApplicant.removeJobApplication(this);
      if (!didRemove)
      {
        applicant = existingApplicant;
        return wasSet;
      }
    }
    applicant.addJobApplication(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setAppliedJob(Job aAppliedJob)
  {
    boolean wasSet = false;
    if (aAppliedJob == null)
    {
      return wasSet;
    }

    Job existingAppliedJob = appliedJob;
    appliedJob = aAppliedJob;
    if (existingAppliedJob != null && !existingAppliedJob.equals(aAppliedJob))
    {
      existingAppliedJob.removeJobApplication(this);
    }
    appliedJob.addJobApplication(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Applicant placeholderApplicant = applicant;
    this.applicant = null;
    placeholderApplicant.removeJobApplication(this);
    Job placeholderAppliedJob = appliedJob;
    this.appliedJob = null;
    placeholderAppliedJob.removeJobApplication(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "experience" + ":" + getExperience()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "applicant = "+(getApplicant()!=null?Integer.toHexString(System.identityHashCode(getApplicant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appliedJob = "+(getAppliedJob()!=null?Integer.toHexString(System.identityHashCode(getAppliedJob())):"null");
  }
}