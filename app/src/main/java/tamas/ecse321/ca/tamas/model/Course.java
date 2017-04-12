/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;
import java.util.*;
import java.sql.Time;

// line 44 "../../../../../../../../ump/tmp69916/model.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String courseCode;
  private int courseCredit;
  private int numberOfHours;
  private int courseBudget;
  private int studentEnrolment;

  //Course Associations
  private List<Session> specificSession;
  private Tamas tamas;
  private List<Instructor> instructors;
  private EceAdmin eceAdmin;
  private List<Job> jobs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aCourseCode, int aCourseCredit, int aNumberOfHours, int aCourseBudget, int aStudentEnrolment, Tamas aTamas, EceAdmin aEceAdmin)
  {
    courseCode = aCourseCode;
    courseCredit = aCourseCredit;
    numberOfHours = aNumberOfHours;
    courseBudget = aCourseBudget;
    studentEnrolment = aStudentEnrolment;
    specificSession = new ArrayList<Session>();
    boolean didAddTamas = setTamas(aTamas);
    if (!didAddTamas)
    {
      throw new RuntimeException("Unable to create course due to tamas");
    }
    instructors = new ArrayList<Instructor>();
    boolean didAddEceAdmin = setEceAdmin(aEceAdmin);
    if (!didAddEceAdmin)
    {
      throw new RuntimeException("Unable to create courseList due to eceAdmin");
    }
    jobs = new ArrayList<Job>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCourseCode(String aCourseCode)
  {
    boolean wasSet = false;
    courseCode = aCourseCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setCourseCredit(int aCourseCredit)
  {
    boolean wasSet = false;
    courseCredit = aCourseCredit;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfHours(int aNumberOfHours)
  {
    boolean wasSet = false;
    numberOfHours = aNumberOfHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setCourseBudget(int aCourseBudget)
  {
    boolean wasSet = false;
    courseBudget = aCourseBudget;
    wasSet = true;
    return wasSet;
  }

  public boolean setStudentEnrolment(int aStudentEnrolment)
  {
    boolean wasSet = false;
    studentEnrolment = aStudentEnrolment;
    wasSet = true;
    return wasSet;
  }

  public String getCourseCode()
  {
    return courseCode;
  }

  public int getCourseCredit()
  {
    return courseCredit;
  }

  public int getNumberOfHours()
  {
    return numberOfHours;
  }

  public int getCourseBudget()
  {
    return courseBudget;
  }

  public int getStudentEnrolment()
  {
    return studentEnrolment;
  }

  public Session getSpecificSession(int index)
  {
    Session aSpecificSession = specificSession.get(index);
    return aSpecificSession;
  }

  public List<Session> getSpecificSession()
  {
    List<Session> newSpecificSession = Collections.unmodifiableList(specificSession);
    return newSpecificSession;
  }

  public int numberOfSpecificSession()
  {
    int number = specificSession.size();
    return number;
  }

  public boolean hasSpecificSession()
  {
    boolean has = specificSession.size() > 0;
    return has;
  }

  public int indexOfSpecificSession(Session aSpecificSession)
  {
    int index = specificSession.indexOf(aSpecificSession);
    return index;
  }

  public Tamas getTamas()
  {
    return tamas;
  }

  public Instructor getInstructor(int index)
  {
    Instructor aInstructor = instructors.get(index);
    return aInstructor;
  }

  public List<Instructor> getInstructors()
  {
    List<Instructor> newInstructors = Collections.unmodifiableList(instructors);
    return newInstructors;
  }

  public int numberOfInstructors()
  {
    int number = instructors.size();
    return number;
  }

  public boolean hasInstructors()
  {
    boolean has = instructors.size() > 0;
    return has;
  }

  public int indexOfInstructor(Instructor aInstructor)
  {
    int index = instructors.indexOf(aInstructor);
    return index;
  }

  public EceAdmin getEceAdmin()
  {
    return eceAdmin;
  }

  public Job getJob(int index)
  {
    Job aJob = jobs.get(index);
    return aJob;
  }

  public List<Job> getJobs()
  {
    List<Job> newJobs = Collections.unmodifiableList(jobs);
    return newJobs;
  }

  public int numberOfJobs()
  {
    int number = jobs.size();
    return number;
  }

  public boolean hasJobs()
  {
    boolean has = jobs.size() > 0;
    return has;
  }

  public int indexOfJob(Job aJob)
  {
    int index = jobs.indexOf(aJob);
    return index;
  }

  public static int minimumNumberOfSpecificSession()
  {
    return 0;
  }

  public Session addSpecificSession(Time aStartTime, Time aEndTime, int aSectionNumber, String aLocation)
  {
    return new Session(aStartTime, aEndTime, aSectionNumber, aLocation, this);
  }

  public boolean addSpecificSession(Session aSpecificSession)
  {
    boolean wasAdded = false;
    if (specificSession.contains(aSpecificSession)) { return false; }
    Course existingCourse = aSpecificSession.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aSpecificSession.setCourse(this);
    }
    else
    {
      specificSession.add(aSpecificSession);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificSession(Session aSpecificSession)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificSession, as it must always have a course
    if (!this.equals(aSpecificSession.getCourse()))
    {
      specificSession.remove(aSpecificSession);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSpecificSessionAt(Session aSpecificSession, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificSession(aSpecificSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificSession()) { index = numberOfSpecificSession() - 1; }
      specificSession.remove(aSpecificSession);
      specificSession.add(index, aSpecificSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificSessionAt(Session aSpecificSession, int index)
  {
    boolean wasAdded = false;
    if(specificSession.contains(aSpecificSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificSession()) { index = numberOfSpecificSession() - 1; }
      specificSession.remove(aSpecificSession);
      specificSession.add(index, aSpecificSession);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificSessionAt(aSpecificSession, index);
    }
    return wasAdded;
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
      existingTamas.removeCourse(this);
    }
    tamas.addCourse(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfInstructors()
  {
    return 0;
  }

  public boolean addInstructor(Instructor aInstructor)
  {
    boolean wasAdded = false;
    if (instructors.contains(aInstructor)) { return false; }
    instructors.add(aInstructor);
    if (aInstructor.indexOfCourseTaught(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aInstructor.addCourseTaught(this);
      if (!wasAdded)
      {
        instructors.remove(aInstructor);
      }
    }
    return wasAdded;
  }

  public boolean removeInstructor(Instructor aInstructor)
  {
    boolean wasRemoved = false;
    if (!instructors.contains(aInstructor))
    {
      return wasRemoved;
    }

    int oldIndex = instructors.indexOf(aInstructor);
    instructors.remove(oldIndex);
    if (aInstructor.indexOfCourseTaught(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aInstructor.removeCourseTaught(this);
      if (!wasRemoved)
      {
        instructors.add(oldIndex,aInstructor);
      }
    }
    return wasRemoved;
  }

  public boolean addInstructorAt(Instructor aInstructor, int index)
  {  
    boolean wasAdded = false;
    if(addInstructor(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInstructorAt(Instructor aInstructor, int index)
  {
    boolean wasAdded = false;
    if(instructors.contains(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInstructorAt(aInstructor, index);
    }
    return wasAdded;
  }

  public boolean setEceAdmin(EceAdmin aEceAdmin)
  {
    boolean wasSet = false;
    if (aEceAdmin == null)
    {
      return wasSet;
    }

    EceAdmin existingEceAdmin = eceAdmin;
    eceAdmin = aEceAdmin;
    if (existingEceAdmin != null && !existingEceAdmin.equals(aEceAdmin))
    {
      existingEceAdmin.removeCourseList(this);
    }
    eceAdmin.addCourseList(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfJobs()
  {
    return 0;
  }

  public Job addJob(int aNumberOfHours, int aSalary, boolean aIsAssignedToApplicant, boolean aIsAllocatedToApplicant, String aDescription, String aDeadline, String aSchedule, Tamas aTamas)
  {
    return new Job(aNumberOfHours, aSalary, aIsAssignedToApplicant, aIsAllocatedToApplicant, aDescription, aDeadline, aSchedule, this, aTamas);
  }

  public boolean addJob(Job aJob)
  {
    boolean wasAdded = false;
    if (jobs.contains(aJob)) { return false; }
    Course existingCourse = aJob.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aJob.setCourse(this);
    }
    else
    {
      jobs.add(aJob);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJob(Job aJob)
  {
    boolean wasRemoved = false;
    //Unable to remove aJob, as it must always have a course
    if (!this.equals(aJob.getCourse()))
    {
      jobs.remove(aJob);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addJobAt(Job aJob, int index)
  {  
    boolean wasAdded = false;
    if(addJob(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobAt(Job aJob, int index)
  {
    boolean wasAdded = false;
    if(jobs.contains(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobAt(aJob, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (specificSession.size() > 0)
    {
      Session aSpecificSession = specificSession.get(specificSession.size() - 1);
      aSpecificSession.delete();
      specificSession.remove(aSpecificSession);
    }
    
    Tamas placeholderTamas = tamas;
    this.tamas = null;
    placeholderTamas.removeCourse(this);
    ArrayList<Instructor> copyOfInstructors = new ArrayList<Instructor>(instructors);
    instructors.clear();
    for(Instructor aInstructor : copyOfInstructors)
    {
      aInstructor.removeCourseTaught(this);
    }
    EceAdmin placeholderEceAdmin = eceAdmin;
    this.eceAdmin = null;
    placeholderEceAdmin.removeCourseList(this);
    for(int i=jobs.size(); i > 0; i--)
    {
      Job aJob = jobs.get(i - 1);
      aJob.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "courseCode" + ":" + getCourseCode()+ "," +
            "courseCredit" + ":" + getCourseCredit()+ "," +
            "numberOfHours" + ":" + getNumberOfHours()+ "," +
            "courseBudget" + ":" + getCourseBudget()+ "," +
            "studentEnrolment" + ":" + getStudentEnrolment()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tamas = "+(getTamas()!=null?Integer.toHexString(System.identityHashCode(getTamas())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "eceAdmin = "+(getEceAdmin()!=null?Integer.toHexString(System.identityHashCode(getEceAdmin())):"null");
  }
}