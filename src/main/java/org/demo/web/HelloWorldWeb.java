package org.demo.web;

import org.demo.conf.MyCircuitBreakerConfig;
import org.demo.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaohuichao
 * @createdDate 2022/8/14 10:41
 */
@RestController
public class HelloWorldWeb {

    private Logger LOG = LoggerFactory.getLogger(HelloWorldWeb.class);

    @Autowired
    private HelloWorldService helloWorldService;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("/hello")
    public String helloWorld(@RequestParam(value = "s") Integer second){

        /**
         * 这里使用了 {@link MyCircuitBreakerConfig#slowCustomizerRetry()} 这个slow配置，应该会出现超时，从而重试6次，但是实际并未重试操作。
         */
        return circuitBreakerFactory.create("slow").run( () -> helloWorldService.hello(second), t -> {
            LOG.warn("delay call failed error", t);
            return "fallback:" + "world";
        });

    }


    @GetMapping("/hello2")
    public String helloWorld2(@RequestParam(value = "s") Integer second){

        /**
         * 这里使用了 {@link MyCircuitBreakerConfig#slowCustomizerRetry()} 这个slow配置，应该会出现超时，从而重试6次，但是实际并未重试操作。
         */
        return helloWorldService.hello(second);

    }




}
