import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortWithLambdaExpression {
    public static void main(String[] args) {
        //String[] arrayOfNumbers = {"2", "9", "7", "1", "5"};
        String[] arrayOfNumbers = {"a", "aaaaa", "aaa", "aa", "aaa"};
        
        List<String> listOfNumbers = new ArrayList<>();

        for (String str : arrayOfNumbers){
            listOfNumbers.add(str);
        }

        System.out.println("Sort Using Lambda Expression " );
        
        //Before sorting
        System.out.println("Before sorting " + listOfNumbers);

        //Sort by integer value using lambda expressions //sort by length  
        Collections.sort(listOfNumbers, (String first, String second) -> Integer.compare(first.length(), second.length()));
        //Collections.sort(listOfNumbers, (String first, String second) -> Integer.parseInt(first) - Integer.parseInt(second));
        
        //After sorting
        System.out.println("After sorting " + listOfNumbers);
    }
    
}
