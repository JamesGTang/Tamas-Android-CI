/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;
import java.util.*;

// line 66 "../../../../../../../../ump/tmp69916/model.ump"
public class Job
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int MIN_HOURS = 45;
  public static final int MAX_HOURS = 180;
  private static int nextJobId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Job Attributes
  private int numberOfHours;
  private int salary;
  private boolean isAssignedToApplicant;
  private boolean isAllocatedToApplicant;
  private String description;
  private String deadline;
  private String schedule;

  //Autounique Attributes
  private int jobId;

  //Job State Machines
  public enum JobState { IsPosted, AppliedTo, AssignedApplicantTo, AssignmentFinalized }
  private JobState jobState;
  public enum JobType { TaJob, GraderJob }
  private JobType jobType;

  //Job Associations
  private Course course;
  private Tamas tamas;
  private Evaluation evaluation;
  private List<Session> jobSession;
  private List<Applicant> applicant;
  private List<JobApplication> jobApplications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Job(int aNumberOfHours, int aSalary, boolean aIsAssignedToApplicant, boolean aIsAllocatedToApplicant, String aDescription, String aDeadline, String aSchedule, Course aCourse, Tamas aTamas)
  {
    numberOfHours = aNumberOfHours;
    salary = aSalary;
    isAssignedToApplicant = aIsAssignedToApplicant;
    isAllocatedToApplicant = aIsAllocatedToApplicant;
    description = aDescription;
    deadline = aDeadline;
    schedule = aSchedule;
    jobId = nextJobId++;
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create job due to course");
    }
    boolean didAddTamas = setTamas(aTamas);
    if (!didAddTamas)
    {
      throw new RuntimeException("Unable to create job due to tamas");
    }
    jobSession = new ArrayList<Session>();
    applicant = new ArrayList<Applicant>();
    jobApplications = new ArrayList<JobApplication>();
    setJobState(JobState.IsPosted);
    setJobType(JobType.TaJob);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfHours(int aNumberOfHours)
  {
    boolean wasSet = false;
    numberOfHours = aNumberOfHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setSalary(int aSalary)
  {
    boolean wasSet = false;
    salary = aSalary;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsAssignedToApplicant(boolean aIsAssignedToApplicant)
  {
    boolean wasSet = false;
    isAssignedToApplicant = aIsAssignedToApplicant;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsAllocatedToApplicant(boolean aIsAllocatedToApplicant)
  {
    boolean wasSet = false;
    isAllocatedToApplicant = aIsAllocatedToApplicant;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeadline(String aDeadline)
  {
    boolean wasSet = false;
    deadline = aDeadline;
    wasSet = true;
    return wasSet;
  }

  public boolean setSchedule(String aSchedule)
  {
    boolean wasSet = false;
    schedule = aSchedule;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfHours()
  {
    return numberOfHours;
  }

  public int getSalary()
  {
    return salary;
  }

  public boolean getIsAssignedToApplicant()
  {
    return isAssignedToApplicant;
  }

  /**
   * When finalized
   */
  public boolean getIsAllocatedToApplicant()
  {
    return isAllocatedToApplicant;
  }

  public String getDescription()
  {
    return description;
  }

  public String getDeadline()
  {
    return deadline;
  }

  public String getSchedule()
  {
    return schedule;
  }

  public int getJobId()
  {
    return jobId;
  }

  public String getJobStateFullName()
  {
    String answer = jobState.toString();
    return answer;
  }

  public String getJobTypeFullName()
  {
    String answer = jobType.toString();
    return answer;
  }

  public JobState getJobState()
  {
    return jobState;
  }

  public JobType getJobType()
  {
    return jobType;
  }

  public boolean setJobState(JobState aJobState)
  {
    jobState = aJobState;
    return true;
  }

  public boolean setJobType(JobType aJobType)
  {
    jobType = aJobType;
    return true;
  }

  public Course getCourse()
  {
    return course;
  }

  public Tamas getTamas()
  {
    return tamas;
  }

  public Evaluation getEvaluation()
  {
    return evaluation;
  }

  public boolean hasEvaluation()
  {
    boolean has = evaluation != null;
    return has;
  }

  public Session getJobSession(int index)
  {
    Session aJobSession = jobSession.get(index);
    return aJobSession;
  }

  public List<Session> getJobSession()
  {
    List<Session> newJobSession = Collections.unmodifiableList(jobSession);
    return newJobSession;
  }

  public int numberOfJobSession()
  {
    int number = jobSession.size();
    return number;
  }

  public boolean hasJobSession()
  {
    boolean has = jobSession.size() > 0;
    return has;
  }

  public int indexOfJobSession(Session aJobSession)
  {
    int index = jobSession.indexOf(aJobSession);
    return index;
  }

  public Applicant getApplicant(int index)
  {
    Applicant aApplicant = applicant.get(index);
    return aApplicant;
  }

  public List<Applicant> getApplicant()
  {
    List<Applicant> newApplicant = Collections.unmodifiableList(applicant);
    return newApplicant;
  }

  public int numberOfApplicant()
  {
    int number = applicant.size();
    return number;
  }

  public boolean hasApplicant()
  {
    boolean has = applicant.size() > 0;
    return has;
  }

  public int indexOfApplicant(Applicant aApplicant)
  {
    int index = applicant.indexOf(aApplicant);
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

  public boolean setCourse(Course aCourse)
  {
    boolean wasSet = false;
    if (aCourse == null)
    {
      return wasSet;
    }

    Course existingCourse = course;
    course = aCourse;
    if (existingCourse != null && !existingCourse.equals(aCourse))
    {
      existingCourse.removeJob(this);
    }
    course.addJob(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setTamas(Tamas aTamas)
  {
    boolean wasSet = false;
    if (aTamas == null)
    {
      return wasSet;
    }

    Tamas existingTamas = tamas;
    tamas = aTamas;
    if (existingTamas != null && !existingTamas.equals(aTamas))
    {
      existingTamas.removeJob(this);
    }
    tamas.addJob(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setEvaluation(Evaluation aNewEvaluation)
  {
    boolean wasSet = false;
    if (evaluation != null && !evaluation.equals(aNewEvaluation) && equals(evaluation.getJob()))
    {
      //Unable to setEvaluation, as existing evaluation would become an orphan
      return wasSet;
    }

    evaluation = aNewEvaluation;
    Job anOldJob = aNewEvaluation != null ? aNewEvaluation.getJob() : null;

    if (!this.equals(anOldJob))
    {
      if (anOldJob != null)
      {
        anOldJob.evaluation = null;
      }
      if (evaluation != null)
      {
        evaluation.setJob(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfJobSession()
  {
    return 0;
  }

  public boolean addJobSession(Session aJobSession)
  {
    boolean wasAdded = false;
    if (jobSession.contains(aJobSession)) { return false; }
    jobSession.add(aJobSession);
    if (aJobSession.indexOfSessionJob(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aJobSession.addSessionJob(this);
      if (!wasAdded)
      {
        jobSession.remove(aJobSession);
      }
    }
    return wasAdded;
  }

  public boolean removeJobSession(Session aJobSession)
  {
    boolean wasRemoved = false;
    if (!jobSession.contains(aJobSession))
    {
      return wasRemoved;
    }

    int oldIndex = jobSession.indexOf(aJobSession);
    jobSession.remove(oldIndex);
    if (aJobSession.indexOfSessionJob(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aJobSession.removeSessionJob(this);
      if (!wasRemoved)
      {
        jobSession.add(oldIndex,aJobSession);
      }
    }
    return wasRemoved;
  }

  public boolean addJobSessionAt(Session aJobSession, int index)
  {  
    boolean wasAdded = false;
    if(addJobSession(aJobSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobSession()) { index = numberOfJobSession() - 1; }
      jobSession.remove(aJobSession);
      jobSession.add(index, aJobSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobSessionAt(Session aJobSession, int index)
  {
    boolean wasAdded = false;
    if(jobSession.contains(aJobSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobSession()) { index = numberOfJobSession() - 1; }
      jobSession.remove(aJobSession);
      jobSession.add(index, aJobSession);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobSessionAt(aJobSession, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfApplicant()
  {
    return 0;
  }

  public boolean addApplicant(Applicant aApplicant)
  {
    boolean wasAdded = false;
    if (applicant.contains(aApplicant)) { return false; }
    applicant.add(aApplicant);
    if (aApplicant.indexOfOfferedJob(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aApplicant.addOfferedJob(this);
      if (!wasAdded)
      {
        applicant.remove(aApplicant);
      }
    }
    return wasAdded;
  }

  public boolean removeApplicant(Applicant aApplicant)
  {
    boolean wasRemoved = false;
    if (!applicant.contains(aApplicant))
    {
      return wasRemoved;
    }

    int oldIndex = applicant.indexOf(aApplicant);
    applicant.remove(oldIndex);
    if (aApplicant.indexOfOfferedJob(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aApplicant.removeOfferedJob(this);
      if (!wasRemoved)
      {
        applicant.add(oldIndex,aApplicant);
      }
    }
    return wasRemoved;
  }

  public boolean addApplicantAt(Applicant aApplicant, int index)
  {  
    boolean wasAdded = false;
    if(addApplicant(aApplicant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplicant()) { index = numberOfApplicant() - 1; }
      applicant.remove(aApplicant);
      applicant.add(index, aApplicant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveApplicantAt(Applicant aApplicant, int index)
  {
    boolean wasAdded = false;
    if(applicant.contains(aApplicant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplicant()) { index = numberOfApplicant() - 1; }
      applicant.remove(aApplicant);
      applicant.add(index, aApplicant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addApplicantAt(aApplicant, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfJobApplications()
  {
    return 0;
  }

  public JobApplication addJobApplication(String aExperience, Applicant aApplicant)
  {
    return new JobApplication(aExperience, aApplicant, this);
  }

  public boolean addJobApplication(JobApplication aJobApplication)
  {
    boolean wasAdded = false;
    if (jobApplications.contains(aJobApplication)) { return false; }
    Job existingAppliedJob = aJobApplication.getAppliedJob();
    boolean isNewAppliedJob = existingAppliedJob != null && !this.equals(existingAppliedJob);
    if (isNewAppliedJob)
    {
      aJobApplication.setAppliedJob(this);
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
    //Unable to remove aJobApplication, as it must always have a appliedJob
    if (!this.equals(aJobApplication.getAppliedJob()))
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
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeJob(this);
    Tamas placeholderTamas = tamas;
    this.tamas = null;
    placeholderTamas.removeJob(this);
    Evaluation existingEvaluation = evaluation;
    evaluation = null;
    if (existingEvaluation != null)
    {
      existingEvaluation.delete();
    }
    ArrayList<Session> copyOfJobSession = new ArrayList<Session>(jobSession);
    jobSession.clear();
    for(Session aJobSession : copyOfJobSession)
    {
      aJobSession.removeSessionJob(this);
    }
    ArrayList<Applicant> copyOfApplicant = new ArrayList<Applicant>(applicant);
    applicant.clear();
    for(Applicant aApplicant : copyOfApplicant)
    {
      aApplicant.removeOfferedJob(this);
    }
    for(int i=jobApplications.size(); i > 0; i--)
    {
      JobApplication aJobApplication = jobApplications.get(i - 1);
      aJobApplication.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "jobId" + ":" + getJobId()+ "," +
            "numberOfHours" + ":" + getNumberOfHours()+ "," +
            "salary" + ":" + getSalary()+ "," +
            "isAssignedToApplicant" + ":" + getIsAssignedToApplicant()+ "," +
            "isAllocatedToApplicant" + ":" + getIsAllocatedToApplicant()+ "," +
            "description" + ":" + getDescription()+ "," +
            "deadline" + ":" + getDeadline()+ "," +
            "schedule" + ":" + getSchedule()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tamas = "+(getTamas()!=null?Integer.toHexString(System.identityHashCode(getTamas())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "evaluation = "+(getEvaluation()!=null?Integer.toHexString(System.identityHashCode(getEvaluation())):"null");
  }
}