import enums.Gender;
import enums.Role;
import model.*;
import service.category.CategoryService;
import service.category.CategoryServiceImp;
import service.payment.PaymentService;
import service.payment.PaymentServiceImp;
import service.product.ProductService;
import service.product.ProductServiceImp;
import service.subcategory.SubcategoryService;
import service.subcategory.SubcategoryServiceImp;
import service.user.UserService;
import service.user.UserServiceImp;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;




class Main implements Constant{
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserServiceImp();
    static CategoryService categoryService = new CategoryServiceImp();
    static PaymentService paymentService = new PaymentServiceImp();
    static ProductService productService = new ProductServiceImp();
    static SubcategoryService subcategoryService = new SubcategoryServiceImp();



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

    private static void category() throws SQLException {
        List<Category> categories = categoryService.getALl();
        for (Category category : categories) {
            System.out.println(category.getName());
        }
        System.out.print("Enter category name : ");
        String name = scannerStr.nextLine();
        Category category = categoryService.getCategoryByName(name);
        if(category != null){
            List<SubCategory> subcategories = subcategoryService.getSubcategories(category.getId());
            for (SubCategory subcategory : subcategories) {
                System.out.println(subcategory.getName());
            }
            System.out.print("Enter category name : ");
            String name2 = scannerStr.nextLine();
            SubCategory subCategory = subcategoryService.getByName(name2);
            if(subCategory != null) {
                List<Product> subcategoryProducts = productService.getSubcategoryProducts(subCategory.getId());
                for (Product product : subcategoryProducts) {
                    System.out.println(product.getName() + " -> " + product.getPrice() + " -> " + product.getDescription());
                }
            }else{
                System.out.println(NOT_FOUND);
            }
        }else{
            System.out.println(NOT_FOUND);
        }
    }

    private static void signIn() throws SQLException {
        System.out.println("Enter phone number : ");
        String phone = scannerStr.nextLine();
        User user = userService.getByPhone(phone);
        if(user != null){
            User currentUser = user;
            if(user.getRole().equals(Role.OWNER)){
                ownerPage(currentUser);
            }else if(user.getRole().equals(Role.ADMIN)){
                adminPage(currentUser);
            }else{
                userPage(currentUser);
            }
        }else{
            System.out.println(NOT_FOUND);
        }
    }

    private static void userPage(User currentUser) {
        System.out.println("usersan");
    }

    private static void adminPage(User currentUser) {
        System.out.println("adminsan");
    }

    private static void ownerPage(User currentUser) {

    }

    private static void signUp() throws SQLException {
        System.out.print("Enter number : ");
        String phone = scannerStr.nextLine();
        System.out.print("Enter name : ");
        String last = scannerStr.nextLine();
        System.out.print("Choose gender [1.Male 2.Female]: ");
        int option = scannerInt.nextInt();
        Gender gender;
        if (option== 1) gender = Gender.MALE;
        else if(option == 2) gender = Gender.FEMALE;
        else {System.err.println(WRONG_OPTION+"\n");
            return;
        }
        User user = User.builder()
                .name(last)
                .phoneNumber(phone)
                .gender(gender)
                .role(Role.USER)
                .build();
        int i = userService.create(user);
        if (i>0) System.out.println("User created successfully");
        else System.err.println("User not created\n");
    }
}