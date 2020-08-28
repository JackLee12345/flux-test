package webfluxquickstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by You.Li
 *
 * @author user
 */
@RestController
@RequestMapping("/api")
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/mono")
    public Mono<String> mono() {
        logger.info("start");
        return Mono.just("hello webflux");
    }

    @GetMapping("/mono1")
    public Mono<Object> mono1() {
        logger.info("mono1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mono.create(monoSink -> {
            logger.info("创建 Mono");
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> {
            //当订阅者去订阅发布者的时候，该方法会调用
            logger.info("1:{}", subscription);
        }).doOnNext(o -> {
            //当订阅者收到数据时，改方法会调用
            logger.info("2:{}", o);
        });
    }

    @GetMapping("flux")
    public Flux<String> flux() {
        return Flux.just("hello", "webflux", "spring", "boot");
    }

    @GetMapping("/test01")
    public String test01() {
        logger.info("mvc-start");
        String result = doSomething("MVC");
        logger.info("mvc-end");
        return result;
    }

    @GetMapping("/test02")
    public Mono<String> test02() {
        logger.info("mono-start");
        Mono<String> result = Mono.fromSupplier(() -> doSomething("MONO"));
        logger.info("mono-end");
        return result;
    }


    public String doSomething(String s) {
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s;
    }

}
