import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        AddressBook agenda = new AddressBook();

        agenda.Load();

        int option = 0;
        do
        {
            System.out.println("\n--- Address Book ---");
            System.out.println("1. List Contacts");
            System.out.println("2. Create Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Exit");
            System.out.print("Enter an option:\t");
            try
            {
                option = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Opción inválida.");
                continue;
            }

            switch (option)
            {
                case 1:
                    agenda.ListContacts();
                    break;
                case 2:
                    agenda.CreateContact(scanner);
                    break;
                case 3:
                    agenda.DeleteContact(scanner);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
        while (option != 4);
        scanner.close();
    }
}
