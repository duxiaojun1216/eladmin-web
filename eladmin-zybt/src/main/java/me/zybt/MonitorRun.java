package me.zybt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author: dengjie
 * @date: 2020/3/21
 */
@EnableAsync
@SpringBootApplication
public class MonitorRun {

    public static void main(String[] args) {
        SpringApplication.run(MonitorRun.class, args);
    }

}
