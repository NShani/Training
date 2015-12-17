
public class Default_method_example {
    public static void main(String[] args) {
        
        ExampleClass greetingObject = new ExampleClass();
        greetingObject.greetSomeone("Nadeeshani");
        
        //greetingObject.greetAnyone();
        }
    
}
interface ExampleInterface {
    public void greetSomeone(String name);
   
    /*
    public void greetAnyone() {
        System.out.println("Hello there!");
    }
     */
    
    
    default void greetAnyone() {
        System.out.println("Hello there!");
    }
     
}
 
class ExampleClass implements ExampleInterface {
    @Override
    public void greetSomeone(String name) {
        System.out.println("Hello " + name);
    }
    
}
 