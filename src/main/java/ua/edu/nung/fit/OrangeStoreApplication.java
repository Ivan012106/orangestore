package ua.edu.nung.fit;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class OrangeStoreApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new OrangeStoreApplication().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        // Реєстрація нашого REST ресурсу
        final UserResource resource = new UserResource();
        environment.jersey().register(resource);
    }
}