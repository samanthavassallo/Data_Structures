import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class ArrayListEx {

//    public static <E> boolean unique (List<E> list){
//
//        return true;
//    }
//    public static List<String> allStringsOfSize(List<String> list, int size){
//
//        return null;
//    }

    public static void main(String[] args) {
        //List<String> test1 = List.of("Moo", "Cow", "Horse");
        //System.out.println(unique(test1));

        //unique
        ArrayList<String> test1 = new ArrayList<>();
        test1.add("Moo");
        test1.add("Cow");
        test1.add("Horse");
        unique(test1);
        System.out.print(unique(test1) + ", ");
        ArrayList<String> test2 = new ArrayList<>();
        //List<String>  = new ArrayList<>();
        test2.add("Moo");
        test2.add("Cow");
        test2.add("Horse");
        test2.add("Moo");
        unique(test2);
        System.out.println(unique(test2));

        //all multiples
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(25);
        nums.add(2);
        nums.add(5);
        nums.add(30);
        nums.add(19);
        nums.add(57);
        nums.add(2);
        nums.add(25);
        ArrayList<Integer> fives = new ArrayList<>();
        int num = 5;
        AllMultiples(nums, fives, num);

        //allStringsOfSize
        ArrayList<String> words = new ArrayList<>();
        words.add("Like");
        words.add("to");
        words.add("eat");
        words.add("sleep");
        words.add("and");
        words.add("cook");
        int charLength = 3;
        ArrayList<String> results = new ArrayList<>();
        allStringsOfSize(words, results);

        //isPermautation
        ArrayList<Integer> pre1 = new ArrayList<>();
        pre1.add(3);
        pre1.add(6);
        pre1.add(8);
        ArrayList<Integer> pre2 = new ArrayList<>();
        pre2.add(6);
        pre2.add(3);
        pre2.add(8);
        isPermutation(pre1, pre2);
        System.out.println(isPermutation(pre1, pre2));

        //stringoListOfwords
        ArrayList<String> low = new ArrayList<>();
        String inputWords = "Hello, world!";
        stringToListOfWord(low, inputWords);

        //removeAllInstances
        ArrayList<Integer> bye = new ArrayList<>();
        bye.add(1);
        bye.add(4);
        bye.add(5);
        bye.add(6);
        bye.add(5);
        bye.add(5);
        bye.add(2);
        Integer toRemove = 5;
        removeAllInstances(bye, toRemove);

        //List<String> wordList = stringToListOfWord(List<String>, final);


        //Integer y = 10;  Integer x = 5; // System.out.println(x.compareTo(y));// compare to works like if x comes before y it prints neg num
        // is y before x prints postiviebye.add(1);


    }

    public static <E> boolean unique(List<E> list) {

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    return false;

                }

            }
        }
        return true;
    }

    public static void AllMultiples(List<Integer> list, List<Integer> fives, int num) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % num  == 0) {
                fives.add(list.get(i));
                //System.out.print(fives);

            }
            //return (AllMultiples(list, fives));
        }
        System.out.println(fives);


    }

    public static void allStringsOfSize(List<String> list, List<String> results) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 3) {
                results.add(list.get(i));

            }

        }
        System.out.println(results);


    }

    public static <E> boolean isPermutation(List<E> listA, List<E> listB) {
        if (listA.size() != listB.size()) {
            return false;
        }

        for (E item : listA) {
            int countA = 0;
            int countB = 0;

            for (int i = 0; i < listA.size(); i++) {
                E other = listB.get(i);
                if (item.equals(other)) {
                    countA++;
                }

            }
            for (int i = 0; i < listB.size(); i++) {
                E other = listA.get(i);
                if (item.equals(other)) {
                    countB++;
                }
            }
            if (countA != countB) {
                return false;
            }
        }

        return true;
    }


    public static void stringToListOfWord(List<String> list, String inputWords) {
        ArrayList<String> low = new ArrayList<>();
       // String[] sep = inputWords.split("\\s+");
       String[] sep = inputWords.replaceAll("[,.;:?!]", " ").split("\"" +
               "s+");


        for (String word : sep) {
            if (!word.isEmpty()) {
                low.add(word);
            }
            System.out.println(low);
        }


    }
    public static <E >void removeAllInstances(List<E> bye, E item){
        for (int i = 0; i < bye.size(); i++) {
            while(bye.get(i).equals(item)){
                bye.remove(i);

            }

        }
        System.out.println(bye);

    }
}




