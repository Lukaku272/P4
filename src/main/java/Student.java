public class Student {

  private String Name;
  private String Surname;
  private int Age;

  public Student(String name, String surname, int age) {
    Name = name;
    Surname = surname;
    Age = age;
  }

  public String GetName() { return Name; }
  public String GetSurname() { return Surname; }
  public int GetAge() { return Age; }

  public String ToString() {
    return Name + " " + Surname + " " + Age;
  }

  public static Student Parse(String str) {
    String[] data = str.split(" ");
    if (data.length != 3)
      return new Student("Parse", "Error", -1);
    return new Student(data[0], data[1], Integer.parseInt(data[2]));
  }
}
