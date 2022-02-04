package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.Month;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }


    public  void makePlainUsers(){
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);

        Member m1 = new Member("Karen89", "karen@hotmail.dk", "test123", "Karen");
        m1.addRole(Role.USER);
        memberRepository.save(m1);

        Member m2 = new Member("Karsten89", "karsten@hotmail.dk", "test123",
                "Karsten", "Karstensen", "Karstenvej", "Karstenville", 1234,
                true, "gold");
        m2.addRole(Role.USER);
        memberRepository.save(m2);

        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users");

    }

    public void makeCars() {
        Car c1 = new Car("Volvo", "Bedst", "300");
        carRepository.save(c1);

        Car c2 = new Car("Kia", "Lille", "200");
        carRepository.save(c2);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();
        carRepository.deleteAll();

        makePlainUsers();

        makeCars();


    }
}
