import java.util.ArrayList;

public class Method_References {
   public static void main(String args[]){
      ArrayList<String> names = new ArrayList<String>();
		
      names.add("Nadeeshani");
      names.add("Kalani");
      names.add("Heshani");
      names.add("Riyafa");
      names.add("Maryam");
		
      System.out.println("Without using method reference :");
      names.forEach(name -> System.out.println(name+"lllll"));
      System.out.println();
      
      System.out.println("Using method reference :");
      names.forEach(System.out::println);
      
      System.out.println();
      Pow pow1 = (a,b) -> Math.pow(a, b);
      Pow pow2 = Math::pow;
      
      System.out.println("Without using method reference :"+pow1.powTest(3, 2));
      
      System.out.println("Using method reference         :"+pow2.powTest(5, 3));
   }
   interface Pow{
       double powTest(double a, double b);
   }
}
