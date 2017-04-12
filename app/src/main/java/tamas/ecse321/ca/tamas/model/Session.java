/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;
import java.sql.Time;
import java.util.*;

// line 26 "../../../../../../../../ump/tmp69916/model.ump"
public class Session
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Session Attributes
  private Time startTime;
  private Time endTime;
  private int sectionNumber;
  private String location;

  //Session State Machines
  public enum Weekday { Monday, Tuesday, Wednesday, Thursday, Friday }
  private Weekday weekday;
  public enum SessionType { LabSession, TutorialSession }
  private SessionType sessionType;

  //Session Associations
  private List<Job> sessionJob;
  private Course course;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Session(Time aStartTime, Time aEndTime, int aSectionNumber, String aLocation, Course aCourse)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    sectionNumber = aSectionNumber;
    location = aLocation;
    sessionJob = new ArrayList<Job>();
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create specificSession due to course");
    }
    setWeekday(Weekday.Monday);
    setSessionType(SessionType.LabSession);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setSectionNumber(int aSectionNumber)
  {
    boolean wasSet = false;
    sectionNumber = aSectionNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public int getSectionNumber()
  {
    return sectionNumber;
  }

  public String getLocation()
  {
    return location;
  }

  public String getWeekdayFullName()
  {
    String answer = weekday.toString();
    return answer;
  }

  public String getSessionTypeFullName()
  {
    String answer = sessionType.toString();
    return answer;
  }

  public Weekday getWeekday()
  {
    return weekday;
  }

  public SessionType getSessionType()
  {
    return sessionType;
  }

  public boolean setWeekday(Weekday aWeekday)
  {
    weekday = aWeekday;
    return true;
  }

  public boolean setSessionType(SessionType aSessionType)
  {
    sessionType = aSessionType;
    return true;
  }

  public Job getSessionJob(int index)
  {
    Job aSessionJob = sessionJob.get(index);
    return aSessionJob;
  }

  public List<Job> getSessionJob()
  {
    List<Job> newSessionJob = Collections.unmodifiableList(sessionJob);
    return newSessionJob;
  }

  public int numberOfSessionJob()
  {
    int number = sessionJob.size();
    return number;
  }

  public boolean hasSessionJob()
  {
    boolean has = sessionJob.size() > 0;
    return has;
  }

  public int indexOfSessionJob(Job aSessionJob)
  {
    int index = sessionJob.indexOf(aSessionJob);
    return index;
  }

  public Course getCourse()
  {
    return course;
  }

  public static int minimumNumberOfSessionJob()
  {
    return 0;
  }

  public boolean addSessionJob(Job aSessionJob)
  {
    boolean wasAdded = false;
    if (sessionJob.contains(aSessionJob)) { return false; }
    sessionJob.add(aSessionJob);
    if (aSessionJob.indexOfJobSession(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSessionJob.addJobSession(this);
      if (!wasAdded)
      {
        sessionJob.remove(aSessionJob);
      }
    }
    return wasAdded;
  }

  public boolean removeSessionJob(Job aSessionJob)
  {
    boolean wasRemoved = false;
    if (!sessionJob.contains(aSessionJob))
    {
      return wasRemoved;
    }

    int oldIndex = sessionJob.indexOf(aSessionJob);
    sessionJob.remove(oldIndex);
    if (aSessionJob.indexOfJobSession(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSessionJob.removeJobSession(this);
      if (!wasRemoved)
      {
        sessionJob.add(oldIndex,aSessionJob);
      }
    }
    return wasRemoved;
  }

  public boolean addSessionJobAt(Job aSessionJob, int index)
  {  
    boolean wasAdded = false;
    if(addSessionJob(aSessionJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSessionJob()) { index = numberOfSessionJob() - 1; }
      sessionJob.remove(aSessionJob);
      sessionJob.add(index, aSessionJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSessionJobAt(Job aSessionJob, int index)
  {
    boolean wasAdded = false;
    if(sessionJob.contains(aSessionJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSessionJob()) { index = numberOfSessionJob() - 1; }
      sessionJob.remove(aSessionJob);
      sessionJob.add(index, aSessionJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSessionJobAt(aSessionJob, index);
    }
    return wasAdded;
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
      existingCourse.removeSpecificSession(this);
    }
    course.addSpecificSession(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Job> copyOfSessionJob = new ArrayList<Job>(sessionJob);
    sessionJob.clear();
    for(Job aSessionJob : copyOfSessionJob)
    {
      aSessionJob.removeJobSession(this);
    }
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeSpecificSession(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "sectionNumber" + ":" + getSectionNumber()+ "," +
            "location" + ":" + getLocation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null");
  }
}