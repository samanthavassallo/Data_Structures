import java.sql.SQLOutput;
import java.util.Scanner;

public class SolitaireEncryption {
    public static class Node<E> {
        public E item;
        public Node<E> next;
        public Node<E> prev;
        public Node<E> head;

        public Node(E item) {
            this.item = item;
        }
    }

    private Node<Integer> getNode(int index, Node<Integer> head) {
        Node<Integer> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;

        }

        return current;
    }


    public static char encryptChar(char letter, int key) {
        int value = letter - 'a';
        int encryptedValue = (value + key) % 26;
        char encryptedChar = (char) (encryptedValue + 'a');


        return encryptedChar;
    }


    public static char decryptChar(char letter, int key) {
        int value = letter - 'a';
        int decryptedValue = (value + (26 - key)) % 26;
        char decryptedChar = (char) (decryptedValue + 'a');

        return decryptedChar;
    }

    public static int getKey(CircularLinkedList<Integer> deck) {
        step1(deck);
        step2(deck);
        step3(deck);
        step4(deck);
        step5(deck);

        // calls the steps methods
        return -1;
    }

    private static void step1(CircularLinkedList<Integer> deck) {
        CircularLinkedList.Node<Integer> current = deck.head;
        int jokerOne = 0;
        int num = 0;

        while (current != null) {
            if (current.item == 27) {
                jokerOne = num;
                break;
            }
            current = current.next;
            num++;

        }
        if (jokerOne != 0 && jokerOne < deck.size - 1) {
            current = deck.head;
            num = 0;

            while (current != null && num != jokerOne) {
                current = current.next;
                num++;
            }

        }
        if (current != null && current.next != null) {
            int swap = current.next.item;
            current.next.item = 27;
            current.item = swap;
            //System.out.println(deck);
        }

    }

    private static void step2(CircularLinkedList<Integer> deck) {
        CircularLinkedList.Node<Integer> current = deck.head;
        CircularLinkedList.Node<Integer> prev = null;
        int num = 0;

        while (current != null && current.item != 28) {
            prev = current;
            current = current.next;
            num++;
        }
        if (current != null && current.next != null) {
            int swap1 = current.next.item;
            current.next.item = 28;
            current.item = swap1;
            current = current.next;

            if (current.next != null) {
                int swap2 = current.next.item;
                current.next.item = 28;
                current.item = swap2;
            }
//                swap1 = current.item;
            //System.out.println(deck);
        }
    }

    private static void step3(CircularLinkedList<Integer> deck) {

        CircularLinkedList<Integer> top = new CircularLinkedList<>();
        CircularLinkedList<Integer> middle = new CircularLinkedList<>();
        CircularLinkedList<Integer> bottom = new CircularLinkedList<>();

        while (deck.get(0) != 27 && deck.get(0) != 28) {
            top.add(deck.remove(0)); //top needs to be bottom//
        }
        middle.add(deck.remove(0));
        while (deck.get(0) != 27 && deck.get(0) != 28) {
            middle.add(deck.remove(0));//becomes top//

        }
        middle.add(deck.remove(0));
        while (deck.size > 0) {
            bottom.add(deck.remove(0));//becomes middle//
        }
//        System.out.print(bottom);
//        System.out.print(middle);
//        System.out.println(top);

        while (bottom.size > 0) {
            deck.add(bottom.remove(0));//becomes middle//
        }
        while (middle.size > 0) {
            deck.add(middle.remove(0));//becomes middle//
        }
        while (top.size > 0) {
            deck.add(top.remove(0));//becomes middle//
        }


    }

    private static void step4(CircularLinkedList<Integer> deck) {
        int bottomCard = deck.remove(deck.size-1);
        /*for (int i = 0; i < bottomCard; i++) {
            deck.head = deck.head.next;
            deck.tail = deck.tail.next;
        }*/


        for (int i = 0; i < bottomCard; i++) {
            deck.add(deck.remove(0));
        }
        deck.add(bottomCard);
        //System.out.println(deck);

    }

    private static int step5(CircularLinkedList<Integer> deck) {
        int topCard = deck.head.item;
        int keystream = 0;
        deck.remove(topCard);
        while (deck.get(0) != 27 && deck.get(0) != 28) {
            for (int i = topCard; i < topCard + 1; i++) {
                keystream = deck.get(i);

            }
            deck.add(topCard);

            return -1;
        }
        return keystream;
    }

    public static void main(String[] args) {
//        Scanner mess = new Scanner(System.in);
//        System.out.print("What is your message: ");
//        Scanner kbd = new Scanner(System.in);
//        String message = kbd.next();
        CircularLinkedList<Integer> deck = new CircularLinkedList<>();
        int[] cards = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 3, 6, 9, 12, 15, 18, 21, 24, 27, 2, 5, 8, 11, 14, 17, 20, 23, 26};
        // int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
        deck.read(deck, cards);
        SolitaireEncryption solitaire = new SolitaireEncryption();
        //int run = solitaire.getKey(deck);

        String message = "snisy";
       // String messLower = message.toLowerCase();


        String decryptMess = "".toLowerCase();
        for (char c : message.toCharArray()) {
            int key = getKey(deck);
            char decrypted = encryptChar(c, key);
            decryptMess += (decrypted);

        }
        //System.out.println(decryptMess);


        String encrypt = "".toLowerCase();

        for (char c : message.toCharArray()) {
            int key = getKey(deck);
            char encrypted = decryptChar(c, key);
            encrypt += encrypted;
        }

     System.out.println(encrypt.replaceAll("\\p{Punct}", "")+ " encrypt");
        System.out.println(decryptMess + " decrypt");
    }


    }


