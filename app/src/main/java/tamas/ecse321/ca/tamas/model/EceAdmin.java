/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;
import java.util.*;

// line 60 "../../../../../../../../ump/tmp69916/model.ump"
public class EceAdmin extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EceAdmin Associations
  private List<Course> courseList;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EceAdmin(String aName, String aUsername, String aPassword, Tamas aTamas)
  {
    super(aName, aUsername, aPassword, aTamas);
    courseList = new ArrayList<Course>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Course getCourseList(int index)
  {
    Course aCourseList = courseList.get(index);
    return aCourseList;
  }

  public List<Course> getCourseList()
  {
    List<Course> newCourseList = Collections.unmodifiableList(courseList);
    return newCourseList;
  }

  public int numberOfCourseList()
  {
    int number = courseList.size();
    return number;
  }

  public boolean hasCourseList()
  {
    boolean has = courseList.size() > 0;
    return has;
  }

  public int indexOfCourseList(Course aCourseList)
  {
    int index = courseList.indexOf(aCourseList);
    return index;
  }

  public static int minimumNumberOfCourseList()
  {
    return 0;
  }

  public Course addCourseList(String aCourseCode, int aCourseCredit, int aNumberOfHours, int aCourseBudget, int aStudentEnrolment, Tamas aTamas)
  {
    return new Course(aCourseCode, aCourseCredit, aNumberOfHours, aCourseBudget, aStudentEnrolment, aTamas, this);
  }

  public boolean addCourseList(Course aCourseList)
  {
    boolean wasAdded = false;
    if (courseList.contains(aCourseList)) { return false; }
    EceAdmin existingEceAdmin = aCourseList.getEceAdmin();
    boolean isNewEceAdmin = existingEceAdmin != null && !this.equals(existingEceAdmin);
    if (isNewEceAdmin)
    {
      aCourseList.setEceAdmin(this);
    }
    else
    {
      courseList.add(aCourseList);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCourseList(Course aCourseList)
  {
    boolean wasRemoved = false;
    //Unable to remove aCourseList, as it must always have a eceAdmin
    if (!this.equals(aCourseList.getEceAdmin()))
    {
      courseList.remove(aCourseList);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCourseListAt(Course aCourseList, int index)
  {  
    boolean wasAdded = false;
    if(addCourseList(aCourseList))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseList()) { index = numberOfCourseList() - 1; }
      courseList.remove(aCourseList);
      courseList.add(index, aCourseList);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseListAt(Course aCourseList, int index)
  {
    boolean wasAdded = false;
    if(courseList.contains(aCourseList))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseList()) { index = numberOfCourseList() - 1; }
      courseList.remove(aCourseList);
      courseList.add(index, aCourseList);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseListAt(aCourseList, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=courseList.size(); i > 0; i--)
    {
      Course aCourseList = courseList.get(i - 1);
      aCourseList.delete();
    }
    super.delete();
  }

}