package JAVA_work.DZ2;

public class app2_1 {
   private static int count = 0; //по умолчанию запрос пустой (все null)
   private static String request = "SELECT* FROM students WHERE";
   private static String str = "{\"name\":\"Павел\", \"country\":\"Россия\", \"city\":\"null\", \"age\":\"null\"}";
   public static void main(String[] args) {
         
      System.out.println("Строка аргументов запроса:\n" + str);
      String str_clear = clearStr(str);
      System.out.println("SQL запрос: \n" + print(makeRequest(str_clear)));
   }

   public static String clearStr(String str) {
      StringBuilder builder = new StringBuilder(str);
      String str_clear = builder.toString().replace("{", "").replaceAll("\"", "").replace("}", "").replaceAll(" ", "");
      return str_clear;
   }
   
   public static String makeRequest(String str) {
      String[] group = str.split(",");
      for (String string : group) {
         String[] itemInfo = string.split(":");
         if (!itemInfo[1].equals("null")) {
            if (count != 0) {                          //Если не нулевых параметров >1, то добавляем AND
               request += " AND";
            }
            count++;
            request += " " + itemInfo[0] + " = '" + itemInfo[1] + "'";
         }
      }
      request += ";";
      return request;
   }
  
   public static String print(String request) {
      String output = "";
      if (count == 0) {
         output = "Нет данных для запроса";
      } else {
         output = request;
      }
      return output;
   }
}