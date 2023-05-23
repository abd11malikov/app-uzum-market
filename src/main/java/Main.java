import enums.CardType;
import enums.Gender;
import enums.Role;
import model.*;
import service.card.CardService;
import service.card.CardServiceImp;
import service.category.CategoryService;
import service.category.CategoryServiceImp;
import service.order.OrderService;
import service.order.OrderServiceImp;
import service.payment.PaymentService;
import service.payment.PaymentServiceImp;
import service.product.ProductService;
import service.product.ProductServiceImp;
import service.subcategory.SubcategoryService;
import service.subcategory.SubcategoryServiceImp;
import service.user.UserService;
import service.user.UserServiceImp;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


class Main implements Constant {
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserServiceImp();
    static CategoryService categoryService = new CategoryServiceImp();
    static PaymentService paymentService = new PaymentServiceImp();
    static ProductService productService = new ProductServiceImp();
    static SubcategoryService subcategoryService = new SubcategoryServiceImp();
    static CardService cardService = new CardServiceImp();
    static OrderService orderService = new OrderServiceImp();


    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println(ENTRANCE);
            int option = scannerInt.nextInt();
            if (option == 1) signUp();
            else if (option == 2) signIn();
            else if (option == 3) category();
            else if (option == 0) break;
            else System.err.println(WRONG_OPTION + "\n");
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
        if (category != null) {
            List<SubCategory> subcategories = subcategoryService.getSubcategories(category.getId());
            for (SubCategory subcategory : subcategories) {
                System.out.println(subcategory.getName());
            }
            System.out.print("Enter category name : ");
            String name2 = scannerStr.nextLine();
            SubCategory subCategory = subcategoryService.getByName(name2);
            if (subCategory != null) {
                List<Product> subcategoryProducts = productService.getSubcategoryProducts(subCategory.getId());
                for (Product product : subcategoryProducts) {
                    System.out.println(product.getName() + " -> " + product.getPrice() + " -> " + product.getDescription());
                }
            } else {
                System.out.println(NOT_FOUND);
            }
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    private static void signIn() throws SQLException {
        System.out.println("Enter phone number : ");
        String phone = scannerStr.nextLine();
        User user = userService.getByPhone(phone);
        if (user != null) {
            User currentUser = user;
            if (user.getRole().equals(Role.OWNER)) {
                ownerPage(currentUser);
            } else if (user.getRole().equals(Role.ADMIN)) {
                adminPage(currentUser);
            } else if(user.getRole().equals(Role.PRODUCT_OWNER)) {
                productOwnerService();
            }else{
                userPage(currentUser);
            }
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    private static void productOwnerService() {
        System.out.println(PRODUCT_OWNER_PAGE);
        System.out.println("Tabrikleman siz product ownersiz \n\n Afsuski qo'lizdan hich nima kemedi \t\t Hozircha☻");
        while (true){

        }
    }

    private static void userPage(User currentUser) throws SQLException {
        while (true) {
            System.out.println(USER_PAGE);
            int option = scannerInt.nextInt();
            if (option == 1) {
                categories(currentUser);
            } else if (option == 2) {
                allCategories(currentUser);
            } else if (option == 3) {
                basket(currentUser);
            } else if (option == 4) {
                sozlamalar(currentUser);
            } else if (option == 0) {
                break;
            } else {
                System.out.println(WRONG_OPTION);
            }
        }
    }

    private static void sozlamalar(User currentUser) {
    }

    private static void basket(User currentUser) {
    }

    private static void allCategories(User currentUser) throws SQLException {
        List<Category> categoryList = categoryService.getALl();
        if(categoryList != null){
            int cnt = 1;
            for (Category category : categoryList) {
                System.out.println(cnt++ + ". " +category.getName());
            }
            System.out.println("Enter category number : ");
            int number = scannerInt.nextInt();
            if( number > 0 && number <= categoryList.size()){
                Category category = categoryList.get(categoryList.size() - number);
                List<SubCategory> subCategoryList = subcategoryService.getSubcategories(category.getId());
                if(subCategoryList != null){
                    int cnt2 = 1;
                    for (SubCategory subCategory : subCategoryList) {
                        System.out.println(cnt2++ + ". " + subCategory.getName());
                    }
                    System.out.println("Enter subcategory number : ");
                    int number2 = scannerInt.nextInt();
                    if(number2 > 0 && number2 <= subCategoryList.size()){
                        SubCategory subCategory = subCategoryList.get(subCategoryList.size() - number2);
                        List<Product> subcategoryProducts = productService.getSubcategoryProducts(subCategory.getId());
                        if(subcategoryProducts != null){
                            for (Product subcategoryProduct : subcategoryProducts) {
                                System.out.println(subcategoryProduct.getName() +  " -> " + subcategoryProduct.getPrice() + " -> " + subcategoryProduct.getDescription());
                            }
                        }else{
                            System.out.println("There is no product yet!");
                        }
                    }
                }else{
                    System.out.println("SubcategoryList is null!");
                }
            }
        }else{
            System.out.println("Nothing was found!");
        }

    }

    private static void categories(User currentUser) throws SQLException {
        List<Category> categories = categoryService.getALl();
        for (Category category : categories) {
            System.out.println(category.getName());
        }
        System.out.print("Enter category name : ");
        String name = scannerStr.nextLine();
        Category category = categoryService.getCategoryByName(name);
        if (category != null) {
            List<SubCategory> subcategories = subcategoryService.getSubcategories(category.getId());
            for (SubCategory subcategory : subcategories) {
                System.out.println(subcategory.getName());
            }
            System.out.print("Enter category name : ");
            String name2 = scannerStr.nextLine();
            SubCategory subCategory = subcategoryService.getByName(name2);
            if (subCategory != null) {
                List<Product> subcategoryProducts = productService.getSubcategoryProducts(subCategory.getId());
                int cnt = 1;
                for (Product product : subcategoryProducts) {
                    System.out.println(cnt++ + ". " + product.getName() + " -> " + product.getPrice() + " -> " + product.getDescription());
                }
                System.out.println("Enter pruduct number : ");
                int number = scannerInt.nextInt();
                if (number >= 1 && number <= subcategoryProducts.size()) {
                    Product product = subcategoryProducts.get(number - 1);
                    while (true) {
                        System.out.println(" Enter option number ->  1.Buy   2.Add to Basket   0.Cancel");
                        int option = scannerInt.nextInt();
                        if (option == 1) {
                            buyProduct(product.getId(), currentUser.getId());
                        } else if (option == 2) {
                            boolean result = productService.checkProduct(product.getId(), currentUser.getId());
                            if (result) {
                                System.out.println(ADDED_TO_BASKET);
                            } else {
                                boolean result2 = userService.addToBasket(currentUser.getId(), product.getId());
                                if (result2) {
                                    System.out.println(ADDED_TO_BASKET);
                                } else {
                                    System.out.println(PROCESS_FAILED);
                                }
                            }
                        } else if (option == 0) {
                            break;
                        } else {
                            System.out.println(WRONG_OPTION);
                        }
                    }
                } else {
                    System.out.println("Wrong number !");
                }
            } else {
                System.out.println(NOT_FOUND);
            }
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    private static void adminPage(User currentUser) {
        System.out.println("Sizi badeshka qib owner qib qo'ydik!");
    }

    private static void ownerPage(User currentUser) {
        System.out.println("Tabrikleman siz ownersiz !");
    }

    private static void signUp() throws SQLException {
        System.out.print("Enter phone number : ");
        String phone = scannerStr.nextLine();
        User CheckedUser = userService.getByPhone(phone);
        if(CheckedUser != null){
            System.out.println(USER_EXIST);
        }else{
            System.out.print("Enter name : ");
            String last = scannerStr.nextLine();
            System.out.print("Choose gender [1.Male 2.Female]: ");
            int option = scannerInt.nextInt();
            Gender gender;
            if (option == 1) gender = Gender.MALE;
            else if (option == 2) gender = Gender.FEMALE;
            else {
                System.err.println(WRONG_OPTION + "\n");
                return;
            }
            boolean result = true;
            while (result) {
                System.out.println("If you want to sell some product on here choose the option : 1.Yes \t\t 0.No");
                int options = scannerInt.nextInt();
                if (options == 1) { // ADD PRODUCT TO SELL

                        System.out.println("Enter your card number : ");
                        String number = scannerStr.nextLine();
                        System.out.println("Enter password");
                        String password = scannerStr.nextLine();
                        while (true) {
                            System.out.println("Comfirm your password : ");
                            String password2 = scannerStr.nextLine();
                            if (password.equals(password2)) {
                                System.out.println("Enter card type : (HUMO / UZCARD / VISA)");
                                String cardType = scannerStr.nextLine();
                                User user = User.builder()
                                        .name(last)
                                        .phoneNumber(phone)
                                        .gender(gender)
                                        .role(Role.PRODUCT_OWNER)
                                        .build();
                                int userId = userService.create(user);
                                if(userId > 0){
                                    Card card = Card.builder()
                                            .ownerId(userId)
                                            .number(number)
                                            .password(password)
                                            .type(CardType.valueOf(cardType))
                                            .build();
                                    int cardId = cardService.create(card);
                                    if(cardId > 0){
                                        System.out.println(SUCCESSFULLY_COMPLETED);
                                        result = false;
                                        break;
                                    }else{
                                        System.out.println(PROCESS_FAILED);
                                    }
                                }else{
                                    System.out.println(USER_NOT_CREATED);
                                }

                            } else {
                                System.out.println(WRONG_PASSWORD);
                            }
                        }

                } else if (options == 0) {
                    User user = User.builder()
                            .name(last)
                            .phoneNumber(phone)
                            .gender(gender)
                            .role(Role.USER)
                            .build();
                    int i = userService.create(user);
                    if (i > 0) System.out.println("User created successfully");
                    else System.err.println("User not created\n");
                    break;
                } else {
                    System.out.println(WRONG_OPTION);
                }
            }
        }


    }

    private static void buyProduct(int productId, int currentUserId) throws SQLException {
        while (true) {
            System.out.println(BUY_PRODUCT);
            int option = scannerInt.nextInt();
            if (option == 1) { // Cash
                System.out.println("Excuse us! Now it isn't enable.");
            } else if (option == 2) { // Credit card
                getByCard(productId, currentUserId);
            } else if (option == 0) {
                break;
            } else {
                System.out.println(WRONG_OPTION);
            }
        }
    }

    private static void getByCard(int productId, int currentUserId) throws SQLException {
        int cardId = cardService.checkCard(currentUserId);
        boolean resultCheck = cardService.cardBalance(cardId, productId);
        if(resultCheck){
            if (cardId > 0) {
                Order order = Order.builder()
                        .userId(currentUserId)
                        .build();
                int orderId = orderService.create(order);
                if (orderId > 0) {
                    boolean check2 = productService.orderProducts(orderId, productId);
                    if (check2) {
                        Product product = productService.get(productId);
                        if (product != null) {

                            Payment payment = Payment.builder()
                                    .cardId(cardId)
                                    .price(product.getPrice())
                                    .orderId(orderId)
                                    .build();
                            int paymentId = paymentService.create(payment);
                            if (paymentId > 0) {
                                boolean check4 = paymentService.addToUserHistory(currentUserId, paymentId, productId);
                                if (check4) {
                                    int ownerId = product.getOwnerId();
                                    User owner = userService.get(ownerId);
                                    if(owner.getRole().equals(Role.OWNER)){
                                        int ownerCardId = cardService.getOwnerCard(ownerId);
                                        int userCardId = cardService.getOwnerCard(currentUserId);
                                        if(ownerCardId > 0 && userCardId > 0){
                                            boolean result = cardService.addSum(product.getPrice(), ownerCardId, userCardId);
                                            if(result){
                                                System.out.println(SUCCESSFULLY_COMPLETED);
                                            }else{
                                                System.out.println("Failed!");
                                            }
                                        }else{
                                            System.out.println("Owner haven't got card!");
                                        }
                                    }else if(owner.getRole().equals(Role.PRODUCT_OWNER)){
                                        int userCardId = cardService.getOwnerCard(currentUserId);
                                        int productCardId = cardService.getOwnerCard(ownerId);
                                        boolean result = cardService.calculationOwnerProduct(product.getPrice(), userCardId, productCardId);
                                        if(result){
                                            System.out.println(SUCCESSFULLY_COMPLETED);
                                        }else {
                                            System.out.println("Calculation failed !");
                                        }
                                    }
                                } else {
                                    System.out.println("User history failed! ");
                                }
                            } else {
                                System.out.println("Payment wasn't created!");
                            }
                        } else {
                            System.out.println("Product not found!");
                        }
                    } else {
                        System.out.println("Order product was failed!");
                    }
                } else {
                    System.out.println("Order wasn't created!");
                }
            } else {
                System.out.println("Enter your card number : ");
                String number = scannerStr.nextLine();
                System.out.println("Enter password");
                String password = scannerStr.nextLine();
                while (true) {
                    System.out.println("Comfirm your password : ");
                    String password2 = scannerStr.nextLine();
                    if (password.equals(password2)) {
                        System.out.println("Enter card type : (HUMO / UZCARD / VISA)");
                        String cardType = scannerStr.nextLine();
                        Card card = Card.builder()
                                .ownerId(currentUserId)
                                .number(number)
                                .password(password)
                                .type(CardType.valueOf(cardType))
                                .build();
                        int cardId1 = cardService.create(card);

                        break;
                    } else {
                        System.out.println(WRONG_PASSWORD);
                    }
                }
            }
        }else{
            System.out.println("Mablag' yetarli emas!");
        }

    }
}