public class Default_method {
   public static void main(String args[]){
      Car car = new Car();
      car.print();
   }
}

interface Vehicle {
   default void print(){
      System.out.println("I am a vehicle!");
   }
	
   static void blowHorn(){
      System.out.println("Blowing horn!!!");
   }
}

interface FourWheeler {
   default void print(){
      System.out.println("I am a four wheeler!");
   }
}

class Car implements Vehicle, FourWheeler {
   @Override
   public void print(){
    
      //Vehicle.super.print();
      //FourWheeler.super.print();
      //Vehicle.blowHorn();
      //System.out.println("I am a car!");
   }
}
