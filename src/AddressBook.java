import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook
{
    public static File contactsDir = new File("contacts");
    private Map<String, String> addressItem;
    private final String contactsFile = "contacts.txt";

    public AddressBook()
    {
        addressItem = new HashMap<>();
        VerifyDir();
    }

    private void VerifyDir()
    {
        if (!contactsDir.exists()) {
            contactsDir.mkdir();
        }
    }

    public void Load()
    {
        File file = new File(contactsDir, contactsFile);
        if (file.exists())
        {
            try (Scanner sc = new Scanner(file))
            {
                while (sc.hasNextLine())
                {
                    String line = sc.nextLine();
                    if (!line.trim().isEmpty())
                    {
                        String[] parts = line.split(" : ", 2);
                        if (parts.length == 2)
                        {
                            String phone = parts[0].trim();
                            String name = parts[1].trim();
                            addressItem.put(phone, name);
                        }
                    }
                }
            }
            catch (Exception e)
            {
                System.err.println("Error loading contacts: " + e.getMessage());
            }
        }
    }

    public void Save()
    {
        File file = new File(contactsDir, contactsFile);
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Map.Entry<String, String> entry : addressItem.entrySet()) {
                pw.println(entry.getKey() + " : " + entry.getValue());
            }
        } catch (Exception e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }

    public void ListContacts()
    {
        if (addressItem.isEmpty())
        {
            System.out.println("You don't have any contacts.");
        }
        else
        {
            System.out.println("Contacts:");
            for (Map.Entry<String, String> entry : addressItem.entrySet())
            {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    public void CreateContact(Scanner scanner)
    {
        System.out.print("Enter your contacts name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter your contacts phone number: ");
        String phone = scanner.nextLine().trim();

        if (addressItem.containsKey(phone))
        {
            System.out.println("A contact with that phone number already exists.");
        }
        else
        {
            addressItem.put(phone, name);
            System.out.println("Contact added successfully.");
            Save();
        }
    }
    public void DeleteContact(Scanner scanner)
    {
        System.out.print("Enter the phone number of your contact: ");
        String phone = scanner.nextLine().trim();

        if (addressItem.containsKey(phone))
        {
            addressItem.remove(phone);
            System.out.println("Contact deleted successfully.");
            Save();
        }
        else
        {
            System.out.println("Couldn't find a contact with that number.");
        }
    }
}
