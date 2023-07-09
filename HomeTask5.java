import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class HomeTask5{
    // Реализуйте структуру телефонной книги с помощью HashMap.
    // Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо считать, как одного человека с разными телефонами. 
    // Вывод должен быть отсортирован по убыванию числа телефонов.

    public static void main(String[] args) {
        Map<String, LinkedList<Integer>> phonebook = new HashMap<>();
        interface1(phonebook);
    }

    static void interface1(Map<String, LinkedList<Integer>> phonebook){
        System.out.print("\033[H\033[J");
        drowing();
        // Scanner input = new Scanner(System.in);

        // System.out.print("Choose an action: ");
        // int user_choice = input.nextInt();

        int user_choice = checkNum();
        
        
        choice(user_choice, phonebook);
    }

    static void drowing(){
        System.out.println("1 - Show contatcs");
        System.out.println("2 - Add contatc");
        System.out.println("3 - Change contatc");
        System.out.println("4 - Delete contatc");
        System.out.println("5 - Search contatc");
        System.out.println("6 - Exit");
    }

    static void choice(int user_choice, Map<String, LinkedList<Integer>> phonebook ){
        
        switch (user_choice) {
            case 1:
                showContacts(phonebook);
                interface1(phonebook);
            case 2:
                addContact(phonebook);
                interface1(phonebook);
            case 3:
                changeContact(phonebook);
                interface1(phonebook);
            case 4:
                removeContact(phonebook);
                interface1(phonebook);
            case 5:
                searchContact(phonebook);
                interface1(phonebook);
            case 6:
                System.exit(0);
            default:
                System.out.println("No such command!");
                pause();
                interface1(phonebook);
        }
    }

    static void showContacts(Map<String, LinkedList<Integer>> phonebook){
        System.out.print("\033[H\033[J");
        if (phonebook.isEmpty()) System.out.println("No contacts yet!");
        // else System.out.println(phonebook);
        else {
            for (Map.Entry<String, LinkedList<Integer>> entry : phonebook.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        pause();
    }

    static void addContact(Map<String, LinkedList<Integer>> phonebook){
        System.out.print("\033[H\033[J");
        
        String surname = checkSurname("Input Surname of contatc: ");

        System.out.println();

        int number = checkPhoneNumber("Input phone number of contatc: ");

        System.out.println();

        if (phonebook.containsKey(surname)) phonebook.get(surname).add(number);
        else {
            phonebook.put(surname, new LinkedList<>());
            phonebook.get(surname).add(number);
        }
        System.out.println("Contact added successfully!");
        showContacts(phonebook);
    }

    static void changeContact(Map<String, LinkedList<Integer>> phonebook){
        if(phonebook.isEmpty()){
            System.out.println("No contacts yet!");
            pause();
            return;
        };

        showContacts(phonebook);

        String surname = checkSurname("Input Surname of contact to change: ");

        if (phonebook.containsKey(surname)){
            for (int i = 0; i < phonebook.get(surname).size(); i++) {
                System.out.println(i+1 + " : " + phonebook.get(surname).get(i));
            }

            int index = checkIndex(phonebook, surname, "Input number to change: ");

            phonebook.get(surname).remove(index);
            System.out.println();
    
            int number = checkPhoneNumber("Input new phone number: ");
        
            phonebook.get(surname).add(number);

            System.out.println("Contact changed successfully!");
            showContacts(phonebook);
        } else {
            System.out.println("No such contact!");
            pause();
            changeContact(phonebook);
        }

    }

    static void removeContact(Map<String, LinkedList<Integer>> phonebook){
        if(phonebook.isEmpty()){
            System.out.println("No contacts yet!");
            pause();
            return;
        };

        showContacts(phonebook);

        String surname = checkSurname("Input Surname of contact to remove: ");

        if (phonebook.containsKey(surname)){
            for (int i = 0; i < phonebook.get(surname).size(); i++) {
                System.out.println(i+1 + " : " + phonebook.get(surname).get(i));
            }
        
            int index = checkIndex(phonebook, surname, "Input number to remove: ");

            phonebook.get(surname).remove(index);
            if (phonebook.get(surname).isEmpty()) phonebook.remove(surname);
            System.out.println("Contact removed successfully!");
            showContacts(phonebook);
        } else {
            System.out.println("No such contacts!");
            pause();
            removeContact(phonebook);
        }
    }

    static void searchContact(Map<String, LinkedList<Integer>> phonebook){
        System.out.print("\033[H\033[J");

        String surname = checkSurname("Input Surname to search contact: ");
        
        if (phonebook.containsKey(surname)) {
            for (int i = 0; i < phonebook.get(surname).size(); i++) {
                System.out.println(surname + " : " + phonebook.get(surname).get(i));
            }
        }
        else if (phonebook.isEmpty()) System.out.println("No contacts yet!");
        else System.out.println("No such contact!");
        pause();
    }

    static Integer checkNum(){
        Scanner input = new Scanner(System.in);
        System.out.print("Choose an action: ");
        int user_choice;

        if (input.hasNextInt()){
            user_choice = input.nextInt();
            return user_choice;
        } else {
            System.out.println("Incorrect num!");
            input.next();
            return user_choice = checkNum();
        }
        
    }

    static String checkSurname(String message){
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        String surname = input.nextLine();
        try{
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        return surname.replaceAll("\\d", "");
        } catch (Exception e) {
            System.out.println("String cannot be empty!");
            return checkSurname(message);
        }
        
    }

    static Integer checkPhoneNumber(String message){
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        int number;

        if (input.hasNextInt()) return number = input.nextInt();
        else {
            System.out.println("Incorrect phone number!");
            return number = checkPhoneNumber(message);
        }
    }

    static void pause(){
        Scanner input = new Scanner(System.in);
        System.out.println("Press any key for continue: ");
        input.nextLine();
    }

    static Integer checkIndex(Map<String, LinkedList<Integer>> phonebook, String surname, String message){
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        try {
            int num = input.nextInt();
            if (num-1 < 0 || num > phonebook.get(surname).size()){
                System.out.println("Incorrect num!");
                return checkIndex(phonebook, surname, message);
            } else return num-1;
            
        } catch (Exception e) {
            System.out.println("Incorrect num!");
            return checkIndex(phonebook, surname, message);
        }
    }
}