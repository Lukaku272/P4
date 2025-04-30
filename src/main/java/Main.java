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
        System.out.print("Podaj imię studenta (lub Enter, aby zakończyć): ");
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

        String birthDate = null;
        while (true) {
          System.out.print("Podaj datę urodzenia studenta (YYYY-MM-DD): ");
          birthDate = scanner.nextLine().trim();
          if (isValidDateFormat(birthDate)) {
            break;
          } else {
            System.out.println("Nieprawidłowy format daty. Spróbuj ponownie (format: YYYY-MM-DD).");
          }
        }

        s.addStudent(new Student(imie, nazwisko, wiek, birthDate));
      }

      System.out.print("\nCzy chcesz wyświetlić wszystkich studentów? (tak/nie): ");
      String odpowiedz = scanner.nextLine().trim().toLowerCase();
      if (odpowiedz.equals("tak")) {
        var students = s.getStudents();
        System.out.println("\nLista studentów:");
        for (Student current : students) {
          System.out.println(current.ToString());
        }
      } else {
        System.out.println("Zakończono bez wyświetlania studentów.");
      }

    } catch (IOException e) {
      System.out.println("Wystąpił błąd podczas operacji na pliku: " + e.getMessage());
    }
  }

  // Funkcja do walidacji formatu daty (YYYY-MM-DD)
  private static boolean isValidDateFormat(String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      formatter.parse(date); // Próbujemy sparsować datę
      return true;
    } catch (DateTimeParseException e) {
      return false; // Zwracamy false, jeśli data jest w złym formacie
    }
  }
}
