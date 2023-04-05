package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car = new Car("model1", 11);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("model2", 22)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("model3", 33)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("model4", 44)));
      userService.add(new User("User5", "Lastname5", "user5@gmail.com"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString() + "\n");
      }

      System.out.println(userService.getUser(car).toString());
      System.out.println(userService.getUser(new Car("model4", 44)).toString());

      context.close();
   }
}
