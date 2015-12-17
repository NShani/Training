import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortWithoutLambdaExpression {
    public static void main(String[] args) {
       // String[] arrayOfNumbers = {"2", "9", "4", "6", "1"};
        String[] arrayOfNumbers = {"a", "aaaaa", "aaa", "aa", "aaa"};
        
        List<String> listOfNumbers = new ArrayList<>();

        for (String str : arrayOfNumbers){
            listOfNumbers.add(str);
        }

        System.out.println("Sort Without Using Lambda Expression :" );
        //Before sorting
        System.out.println("Before sorting " + listOfNumbers);
        
        //Sort by integer value without lambda expressions
        Collections.sort(listOfNumbers, new StringNumberComparator());

        //After sorting
        System.out.println("After sorting " + listOfNumbers);
    }
}
