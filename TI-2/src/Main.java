import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Logic logic = new Logic();
        System.out.println("Введите сообщение:");
        String msg= sc.nextLine();
        Output key =  Logic.encrypt(15,msg);

        System.out.println("Введите секретный ключ:");

        int number = sc.nextInt();

        logic.decrypt(key,number);


    }




}
