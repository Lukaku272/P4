  import java.io.IOException;
  import java.util.Scanner;

  class Main {
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

          System.out.print("Podaj wiek studenta: ");
          int wiek;
          try {
            wiek = Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy wiek, spróbuj ponownie.");
            continue;
          }

          s.addStudent(new Student(imie, wiek));
        }

        var students = s.getStudents();
        System.out.println("\nLista studentów:");
        for (Student current : students) {
          System.out.println(current.ToString());
        }

      } catch (IOException e) {
        System.out.println("Wystąpił błąd podczas operacji na pliku: " + e.getMessage());
      }
    }
  }
