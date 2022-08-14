package org.demo.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author xiaohuichao
 * @createdDate 2022/8/14 10:42
 */
@Service
@Slf4j
public class HelloWorldService {

    @Retryable
    @SneakyThrows
    public String hello(Integer second) {
        log.info("entry hello method, second = {}", second);
        Thread.sleep(Duration.ofSeconds(second).toMillis());
        return "World => " + second;
    }

}
