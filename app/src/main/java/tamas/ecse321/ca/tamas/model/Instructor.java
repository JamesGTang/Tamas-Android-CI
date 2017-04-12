/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;
import java.util.*;

// line 54 "../../../../../../../../ump/tmp69916/model.ump"
public class Instructor extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Associations
  private List<Course> courseTaught;
  private List<Evaluation> evaluations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Instructor(String aName, String aUsername, String aPassword, Tamas aTamas)
  {
    super(aName, aUsername, aPassword, aTamas);
    courseTaught = new ArrayList<Course>();
    evaluations = new ArrayList<Evaluation>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Course getCourseTaught(int index)
  {
    Course aCourseTaught = courseTaught.get(index);
    return aCourseTaught;
  }

  public List<Course> getCourseTaught()
  {
    List<Course> newCourseTaught = Collections.unmodifiableList(courseTaught);
    return newCourseTaught;
  }

  public int numberOfCourseTaught()
  {
    int number = courseTaught.size();
    return number;
  }

  public boolean hasCourseTaught()
  {
    boolean has = courseTaught.size() > 0;
    return has;
  }

  public int indexOfCourseTaught(Course aCourseTaught)
  {
    int index = courseTaught.indexOf(aCourseTaught);
    return index;
  }

  public Evaluation getEvaluation(int index)
  {
    Evaluation aEvaluation = evaluations.get(index);
    return aEvaluation;
  }

  public List<Evaluation> getEvaluations()
  {
    List<Evaluation> newEvaluations = Collections.unmodifiableList(evaluations);
    return newEvaluations;
  }

  public int numberOfEvaluations()
  {
    int number = evaluations.size();
    return number;
  }

  public boolean hasEvaluations()
  {
    boolean has = evaluations.size() > 0;
    return has;
  }

  public int indexOfEvaluation(Evaluation aEvaluation)
  {
    int index = evaluations.indexOf(aEvaluation);
    return index;
  }

  public static int minimumNumberOfCourseTaught()
  {
    return 0;
  }

  public boolean addCourseTaught(Course aCourseTaught)
  {
    boolean wasAdded = false;
    if (courseTaught.contains(aCourseTaught)) { return false; }
    courseTaught.add(aCourseTaught);
    if (aCourseTaught.indexOfInstructor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCourseTaught.addInstructor(this);
      if (!wasAdded)
      {
        courseTaught.remove(aCourseTaught);
      }
    }
    return wasAdded;
  }

  public boolean removeCourseTaught(Course aCourseTaught)
  {
    boolean wasRemoved = false;
    if (!courseTaught.contains(aCourseTaught))
    {
      return wasRemoved;
    }

    int oldIndex = courseTaught.indexOf(aCourseTaught);
    courseTaught.remove(oldIndex);
    if (aCourseTaught.indexOfInstructor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCourseTaught.removeInstructor(this);
      if (!wasRemoved)
      {
        courseTaught.add(oldIndex,aCourseTaught);
      }
    }
    return wasRemoved;
  }

  public boolean addCourseTaughtAt(Course aCourseTaught, int index)
  {  
    boolean wasAdded = false;
    if(addCourseTaught(aCourseTaught))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseTaught()) { index = numberOfCourseTaught() - 1; }
      courseTaught.remove(aCourseTaught);
      courseTaught.add(index, aCourseTaught);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseTaughtAt(Course aCourseTaught, int index)
  {
    boolean wasAdded = false;
    if(courseTaught.contains(aCourseTaught))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseTaught()) { index = numberOfCourseTaught() - 1; }
      courseTaught.remove(aCourseTaught);
      courseTaught.add(index, aCourseTaught);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseTaughtAt(aCourseTaught, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEvaluations()
  {
    return 0;
  }

  public Evaluation addEvaluation(Job aJob)
  {
    return new Evaluation(this, aJob);
  }

  public boolean addEvaluation(Evaluation aEvaluation)
  {
    boolean wasAdded = false;
    if (evaluations.contains(aEvaluation)) { return false; }
    Instructor existingInstructor = aEvaluation.getInstructor();
    boolean isNewInstructor = existingInstructor != null && !this.equals(existingInstructor);
    if (isNewInstructor)
    {
      aEvaluation.setInstructor(this);
    }
    else
    {
      evaluations.add(aEvaluation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEvaluation(Evaluation aEvaluation)
  {
    boolean wasRemoved = false;
    //Unable to remove aEvaluation, as it must always have a instructor
    if (!this.equals(aEvaluation.getInstructor()))
    {
      evaluations.remove(aEvaluation);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEvaluationAt(Evaluation aEvaluation, int index)
  {  
    boolean wasAdded = false;
    if(addEvaluation(aEvaluation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvaluations()) { index = numberOfEvaluations() - 1; }
      evaluations.remove(aEvaluation);
      evaluations.add(index, aEvaluation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEvaluationAt(Evaluation aEvaluation, int index)
  {
    boolean wasAdded = false;
    if(evaluations.contains(aEvaluation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvaluations()) { index = numberOfEvaluations() - 1; }
      evaluations.remove(aEvaluation);
      evaluations.add(index, aEvaluation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEvaluationAt(aEvaluation, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Course> copyOfCourseTaught = new ArrayList<Course>(courseTaught);
    courseTaught.clear();
    for(Course aCourseTaught : copyOfCourseTaught)
    {
      aCourseTaught.removeInstructor(this);
    }
    for(int i=evaluations.size(); i > 0; i--)
    {
      Evaluation aEvaluation = evaluations.get(i - 1);
      aEvaluation.delete();
    }
    super.delete();
  }

}