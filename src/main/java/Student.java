public class Student {

  private String imie;
  private String nazwisko;
  private int wiek;
  private String dataUrodzenia;

  public Student(String imie, String nazwisko, int wiek, String dataUrodzenia) {
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.wiek = wiek;
    this.dataUrodzenia = dataUrodzenia;
  }

  public String getImie() {
    return imie;
  }

  public String getNazwisko() {
    return nazwisko;
  }

  public int getWiek() {
    return wiek;
  }

  public String getDataUrodzenia() {
    return dataUrodzenia;
  }

  public String toString() {
    return imie + " " + nazwisko + " " + wiek + " " + dataUrodzenia;
  }

  public static Student parse(String str) {
    String[] dane = str.split(" ");
    if (dane.length != 4)
      return new Student("Parse", "Error", -1, "Error");

    return new Student(dane[0], dane[1], Integer.parseInt(dane[2]), dane[3]);
  }
}
