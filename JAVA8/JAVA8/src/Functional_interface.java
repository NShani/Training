import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Functional_interface {
   
   public static void main(String args[]){
       
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
   // StringNumberComparator s= new StringNumberComparator();
	System.out.println("Print all the numbers ");
	numberPrint (list, n -> true);

	System.out.println("Print all even numbers  ");
	numberPrint (list, n -> n%2 == 0);

	System.out.println("Print all odd numbers  ");
	numberPrint (list, n -> n%2 == 1);

	System.out.println("Print all numbers greater than 3 ");
	numberPrint (list, n -> n>3);
	Predicate<StringNumberComparator> predicate=  s -> s.name.equals("nn");
	   System.out.println(predicate.test(new StringNumberComparator()));
   }
	
   public static void numberPrint(List<Integer> list, Predicate<Integer> predicate) {
      for(Integer n: list) {		
         if(predicate.test(n)) {             
         //   System.out.println(n + " ");
         }
      }
      System.out.println();
   }
   
}
class Person{
    
}