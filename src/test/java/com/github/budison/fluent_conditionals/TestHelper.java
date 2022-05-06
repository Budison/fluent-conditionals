class TestHelper {
  static boolean somethingIsTrue() {return true; }
  static int getHighNumber() { return 1000; }
  static int getLowNumber() { return 1; }
  static String getAString() { return "a string"; }
  static void printFoo() { System.out.println("Foo");}
  static void printBar() { System.out.println("Bar");}
  static RuntimeException createException() { return new RuntimeException(); }
  static void printFirstChar(String s) { System.out.println(s.charAt(0)); }
  static void printLastChar(String s) { System.out.println(s.charAt(s.length()-1)); }
}