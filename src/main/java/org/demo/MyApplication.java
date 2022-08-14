package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author xiaohuichao
 * @createdDate 2022/8/14 10:39
 */
@SpringBootApplication
@EnableRetry(proxyTargetClass = true)
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
