package Client.printCustom;

public class Print {
    
    public static void color(String color,String nickname, String message){
        System.out.println(color+nickname+": "+Colors.RESET+message);
    }

}
