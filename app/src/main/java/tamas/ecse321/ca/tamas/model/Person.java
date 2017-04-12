/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-cdf9661 modeling language!*/

package tamas.ecse321.ca.tamas.model;

// line 12 "../../../../../../../../ump/tmp69916/model.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;
  private String username;
  private String password;

  //Person Associations
  private Tamas tamas;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, String aUsername, String aPassword, Tamas aTamas)
  {
    name = aName;
    username = aUsername;
    password = aPassword;
    boolean didAddTamas = setTamas(aTamas);
    if (!didAddTamas)
    {
      throw new RuntimeException("Unable to create person due to tamas");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public Tamas getTamas()
  {
    return tamas;
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
      existingTamas.removePerson(this);
    }
    tamas.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Tamas placeholderTamas = tamas;
    this.tamas = null;
    placeholderTamas.removePerson(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tamas = "+(getTamas()!=null?Integer.toHexString(System.identityHashCode(getTamas())):"null");
  }
}