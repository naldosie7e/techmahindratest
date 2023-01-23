package tecnicaltest.reynaldo.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

    @Autowired
    UserSeeder userSeeder;

    @Override
    public void run(String... args) throws Exception {
        // execute the code you want to or just call an other function that will handle
        // that like the following
        userSeeder.run();
    }
}