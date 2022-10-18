import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

public class DFSM {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("prueba1.txt");
        Scanner scanner = new Scanner(file);

        HashSet<Character> alphabet = getAlphabet(scanner);

        int numberOfStates = scanner.nextInt();
        scanner.nextLine();
        
        HashSet<Character> astates = getAceptingStates(scanner);

        String sstate = "";
        String inputchar = "";
        String estate = "";

        while (scanner.hasNextLine()) {
            try {
                String transition = scanner.nextLine();
                String finaltransition = "";
                for (int i = 0; i < transition.length(); i++) {
                    if (Character.isLetterOrDigit(transition.charAt(i))) {
                        finaltransition += transition.charAt(i);
                    }
                }

                for (int i = 0; i < finaltransition.length(); i++) {
                    if (i % 3 == 0) {
                        sstate += finaltransition.charAt(i);
                    } else if (i % 3 == 1) {
                        inputchar += finaltransition.charAt(i);
                    } else {
                        estate += finaltransition.charAt(i);
                    }
                }

            } catch (Exception e) {
                break;
            }
        }

        char[] startState = new char[alphabet.size() * numberOfStates];
        char[] input = new char[alphabet.size() * numberOfStates];
        char[] endState = new char[alphabet.size() * numberOfStates];

        for (int i = 0; i < sstate.length(); i++) {
            startState[i] = sstate.charAt(i);
        }
        for (int i = 0; i < inputchar.length(); i++) {
            input[i] = inputchar.charAt(i);
        }
        for (int i = 0; i < estate.length(); i++) {
            endState[i] = estate.charAt(i);
        }


        scanner.close();


        System.out.println("Pau Aran: paranmig@pnw.edu");
        System.out.println("Automata Project 1");
        System.out.println("DFSM Simulator");
        System.out.println();
        System.out.println("Alphabet: " + alphabet);
        System.out.println("Number of States: " + numberOfStates);
        System.out.println("Accepting States: " + astates);
        System.out.println("Please enter a string to test: ");
        
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        System.out.println();

        do {

            char[] lang = new char[in.length()];
    
            int state = 0;
    
            for (int i = 0; i < in.length(); i++) {
                lang[i] = in.charAt(i);
            }
    
            for (int i = 0; i < lang.length; i++) {
                if (alphabet.contains(lang[i])) {
                    for (int j = 0; j < startState.length; j++) {
                        if (startState[j] == state && input[j] == lang[i]) {
                            state = endState[j];
                            break;
                        }
                    }
                }else{
                    System.out.println("The language does not belong to the alphabet");
                    System.out.println();
                    break;
                }
            }
    
            if (astates.contains(state)) {
                System.out.println("The String is Accepted");
            } else {
                System.out.println("The String is Rejected");
            }
            System.out.println();            
            

            Scanner choice = new Scanner(System.in);
            System.out.println("Do you want to test another string? (y/n)");
            String ans = choice.nextLine();
            if (ans.equals("y")) {
                System.out.println("Please enter a string to test: ");
                in = sc.nextLine();
            } else {
                break;
            }
            
        }
        while(true);

    }

    public static HashSet<Character> getAlphabet(Scanner scanner) {
        HashSet<Character> alphabetSet = new HashSet<Character>();
        String line = scanner.nextLine();
        for (int i = 0; i < line.length(); i += 3) {
            alphabetSet.add(line.charAt(i));
        }
        return alphabetSet;
    }

    public static HashSet<Character> getAceptingStates(Scanner scanner) {
        HashSet<Character> astates = new HashSet<Character>();
        String aceptingstates = scanner.nextLine();
        for (int i = 0; i < aceptingstates.length(); i += 3) {
            astates.add(aceptingstates.charAt(i));
        }
        return astates;
    }
}