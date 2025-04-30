import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(System.in);  // Tworzenie obiektu do wczytywania danych od użytkownika
      Service s = new Service();  // Tworzenie obiektu Service do zarządzania studentami

      while (true) {
        // Wyświetlanie menu opcji dla użytkownika
        System.out.println("Wybierz opcję:");
        System.out.println("1. Dodaj studenta");
        System.out.println("2. Wyszukaj studenta");
        System.out.println("3. Usuń studenta");
        System.out.println("4. Wyświetl wszystkich studentów");
        System.out.println("5. Zakończ");

        String opcja = scanner.nextLine().trim();  // Odczytanie wybranej opcji

        if (opcja.equals("1")) {
          // Dodawanie studenta
          System.out.print("Podaj imię studenta (lub naciśnij Enter, aby zakończyć): ");
          String imie = scanner.nextLine();
          if (imie.isBlank()) {  // Zakończenie pętli, jeśli imię jest puste
            break;
          }

          System.out.print("Podaj nazwisko studenta: ");
          String nazwisko = scanner.nextLine();
          if (nazwisko.isBlank()) {  // Walidacja nazwiska
            System.out.println("Nazwisko nie może być puste.");
            continue;
          }

          System.out.print("Podaj wiek studenta: ");
          int wiek;
          try {
            wiek = Integer.parseInt(scanner.nextLine());  // Konwersja wieku na liczbę
          } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy wiek, spróbuj ponownie.");
            continue;
          }

          // Walidacja formatu daty
          System.out.print("Podaj datę urodzenia studenta (YYYY-MM-DD): ");
          String dataUrodzenia = null;
          while (true) {
            dataUrodzenia = scanner.nextLine().trim();
            if (isValidDateFormat(dataUrodzenia)) {  // Sprawdzenie poprawności daty
              break;
            } else {
              System.out.println("Nieprawidłowy format daty. Spróbuj ponownie (format: YYYY-MM-DD).");
            }
          }

          s.dodajStudenta(new Student(imie, nazwisko, wiek, dataUrodzenia));  // Dodanie studenta do bazy danych

        } else if (opcja.equals("2")) {
          // Wyszukiwanie studenta po imieniu
          System.out.print("\nCzy chcesz wyszukać studenta po imieniu? (tak/nie): ");
          String odpowiedz = scanner.nextLine().trim().toLowerCase();

          if (odpowiedz.equals("tak")) {
            System.out.print("Podaj imię studenta, którego chcesz znaleźć: ");
            String searchName = scanner.nextLine().trim();
            Student foundStudent = s.znajdźStudentaPoImieniu(searchName);  // Wyszukiwanie studenta

            if (foundStudent != null) {
              System.out.println("Znaleziono studenta: " + foundStudent.toString());
            } else {
              System.out.println("Student o imieniu " + searchName + " nie został znaleziony.");
            }
          } else {
            System.out.println("Zakończono bez wyszukiwania studenta.");
          }

        } else if (opcja.equals("3")) {
          // Usuwanie studenta po imieniu i nazwisku
          System.out.print("Podaj imię studenta, którego chcesz usunąć: ");
          String imieDoUsuniecia = scanner.nextLine().trim();

          System.out.print("Podaj nazwisko studenta, którego chcesz usunąć: ");
          String nazwiskoDoUsuniecia = scanner.nextLine().trim();

          boolean usunieto = s.usunStudenta(imieDoUsuniecia, nazwiskoDoUsuniecia);  // Usuwanie studenta

          if (usunieto) {
            System.out.println("Student został usunięty.");
          } else {
            System.out.println("Nie znaleziono studenta do usunięcia.");
          }

        } else if (opcja.equals("4")) {
          // Wyświetlanie wszystkich studentów
          Collection<Student> studenci = s.pobierzStudentów();  // Pobranie listy studentów
          if (studenci.isEmpty()) {  // Sprawdzanie, czy lista jest pusta
            System.out.println("Brak studentów w bazie.");
          } else {
            System.out.println("Lista wszystkich studentów:");
            for (Student student : studenci) {
              System.out.println(student.toString());  // Wyświetlanie każdego studenta
            }
          }

        } else if (opcja.equals("5")) {
          break;  // Zakończenie programu
        } else {
          System.out.println("Nieznana opcja. Spróbuj ponownie.");
        }
      }

    } catch (IOException e) {
      // Obsługa błędów związanych z operacjami na plikach
      System.out.println("Wystąpił błąd podczas operacji na pliku: " + e.getMessage());
    }
  }

  // Metoda do sprawdzania poprawności formatu daty
  private static boolean isValidDateFormat(String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      formatter.parse(date);  // Próba sparsowania daty
      return true;
    } catch (DateTimeParseException e) {
      return false;  // Zwraca fałsz, jeśli data jest niepoprawna
    }
  }
}
