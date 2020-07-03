class Test{

  class Person{
    String name;
    int id;
  }

  public static void main(String[] args) {
    HashSet<Person> p = new HashSet<Person>();
    p.add({"momo",123});
    System.out.println(p);
  }
}
