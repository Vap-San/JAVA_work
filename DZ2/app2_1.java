package JAVA_work.DZ2;

public class app2_1 {
   

   public static void main(String[] args) {
      String request = "SELECT* FROM students WHERE";
      String str = " {\"name\":\"null\", \"country\":\"Russia\", \"city\":\"null\", \"age\":\"11\"}";
      // String str = " {\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
      System.out.println(str);
      StringBuilder builder = new StringBuilder(str);
      String str_clear = builder.toString().replace("{", "").replaceAll("\"", "").replace("}", "").replaceAll(" ", "");
      // System.out.println(str_clear);
      int count = 0;
      String[] group = str_clear.split(",");
      for (String string : group) {
        String[] itemInfo = string.split(":");
         if (!itemInfo[1].equals("null")) {
            if (count != 0) {
               request += " AND";
            }
            count++;
            request += " "+itemInfo[0] + " = '" + itemInfo[1]+"'";
         }
      }
      request += ";";
     if (count==0) {
        System.out.println("Нет данных для запроса");
     } else {
        System.out.println(request);
     }
      
   }

}