package by.bulaukin.search_engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class IndexConfig {

    @Bean
    public ForkJoinPool forkJoinPool() {
        return new ForkJoinPool();
    }

    @Bean
    public Lock lock(){
        return new ReentrantLock();
    }

}
