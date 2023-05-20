import enums.Gender;
import model.User;
import service.user.UserService;
import service.user.UserServiceImp;

import java.sql.SQLException;
import java.util.Scanner;




class Main implements Constant{
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserServiceImp();

    public static void main(String[] args) throws SQLException {
        while (true){
            System.out.println(ENTRANCE);
            int option = scannerInt.nextInt();
            if (option==1) signUp();
            else if (option==2) signIn();
            else if (option == 3) category();
            else if (option==0) break;
            else System.err.println(WRONG_OPTION+"\n");
        }
    }

    private static void category() {
    }

    private static void signIn() {
    }

    private static void signUp() throws SQLException {
        System.out.print("Enter number : ");
        String phone = scannerStr.nextLine();
        System.out.print("Enter name : ");
        String last = scannerStr.nextLine();
        System.out.print("Choose gender [1.Male 2.Female]: ");
        String gender;
        int option = scannerInt.nextInt();
        if (option== 1) gender = "MALE";
        else if(option == 2) gender = "FEMALE";
        else {System.err.println(WRONG_OPTION+"\n");
            return;
        }
        User user = User.builder()
                .name(last)
                .phoneNumber(phone)
                .gender(Gender.valueOf(gender))
                .build();
        int i = userService.create(user);
        if (i>0) System.out.println("User created successfully");
        else System.err.println("User not created\n");
    }
}