import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(System.in);
      Service s = new Service();

      while (true) {
        System.out.print("Podaj imię studenta (lub naciśnij Enter, aby zakończyć): ");
        String imie = scanner.nextLine();
        if (imie.isBlank()) {
          break;
        }

        System.out.print("Podaj nazwisko studenta: ");
        String nazwisko = scanner.nextLine();
        if (nazwisko.isBlank()) {
          System.out.println("Nazwisko nie może być puste.");
          continue;
        }

        System.out.print("Podaj wiek studenta: ");
        int wiek;
        try {
          wiek = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Nieprawidłowy wiek, spróbuj ponownie.");
          continue;
        }

        System.out.print("Podaj datę urodzenia studenta (YYYY-MM-DD): ");
        String dataUrodzenia = null;
        while (true) {
          dataUrodzenia = scanner.nextLine().trim();
          if (isValidDateFormat(dataUrodzenia)) {
            break;
          } else {
            System.out.println("Nieprawidłowy format daty. Spróbuj ponownie (format: YYYY-MM-DD).");
          }
        }

        s.dodajStudenta(new Student(imie, nazwisko, wiek, dataUrodzenia));
      }

      System.out.print("\nCzy chcesz wyszukać studenta po imieniu? (tak/nie): ");
      String odpowiedz = scanner.nextLine().trim().toLowerCase();

      if (odpowiedz.equals("tak")) {
        System.out.print("Podaj imię studenta, którego chcesz znaleźć: ");
        String searchName = scanner.nextLine().trim();
        Student foundStudent = s.znajdźStudentaPoImieniu(searchName);

        if (foundStudent != null) {
          System.out.println("Znaleziono studenta: " + foundStudent.toString());
        } else {
          System.out.println("Student o imieniu " + searchName + " nie został znaleziony.");
        }
      } else {
        System.out.println("Zakończono bez wyszukiwania studenta.");
      }

    } catch (IOException e) {
      System.out.println("Wystąpił błąd podczas operacji na pliku: " + e.getMessage());
    }
  }

  private static boolean isValidDateFormat(String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      formatter.parse(date);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }
}
