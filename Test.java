import java.util.*;

class Test{
  public int foo(int[] nums){
    int sum = 0;
      switch(nums.length){
      case 2:
        sum = nums[0]+nums[1];
        System.out.println("the sum of 2 num is: " + sum);
        break;
      case 3:
        sum = nums[0]+nums[1]+nums[2];
        System.out.println("the sum of 3 num is: " + sum);
        break;
    }
    return 0;
  }

  public static void main(String[] args) {
      ArrayList<String> cars = new ArrayList();
      cars.add("Volvo");
      cars.add("BMW");
      cars.add("Ford");
      cars.add("Mazda");
      boolean bool  = (1!=1) && cars.remove("Volvo");
      System.out.println(bool);
      System.out.println(cars);
  }
  }
