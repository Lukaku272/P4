import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;

public class Service {

  public void dodajStudenta(Student student) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", true))) {
      writer.append(student.toString());
      writer.newLine();
    }
  }

  public Collection<Student> pobierzStudentów() throws IOException {
    Collection<Student> studenci = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("db.txt"))) {
      String linia;
      while ((linia = reader.readLine()) != null) {
        studenci.add(Student.parse(linia));
      }
    }
    return studenci;
  }

  public Student znajdźStudentaPoImieniu(String imię) throws IOException {
    Collection<Student> studenci = pobierzStudentów();

    for (Student student : studenci) {
      if (student.getImie().equalsIgnoreCase(imię)) {
        return student;
      }
    }

    return null;
  }

  public boolean usunStudenta(String imie, String nazwisko) throws IOException {
    Collection<Student> studenci = pobierzStudentów();
    Collection<Student> studenciPozostali = new ArrayList<>();

    boolean usunieto = false;
    for (Student student : studenci) {
      if (student.getImie().equalsIgnoreCase(imie) && student.getNazwisko().equalsIgnoreCase(nazwisko)) {
        usunieto = true;
      } else {
        studenciPozostali.add(student);
      }
    }

    if (usunieto) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt"))) {
        for (Student student : studenciPozostali) {
          writer.append(student.toString());
          writer.newLine();
        }
      }
    }

    return usunieto;
  }
}
