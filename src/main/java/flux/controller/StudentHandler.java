package flux.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by You.Li
 *  相当于springMVC的controller类
 */
@Controller
public class StudentHandler {

//    　处理器类
//　　　相当于SpringMVC中的service类，主要就是根据不同的请求url执行不同的业务逻辑
//　　　技巧01：编写的处理器类必须被容器所管理

    public Mono<ServerResponse> helloStudent(ServerRequest serverRequest) {
        return ServerResponse // 响应对象封装
                .ok() // 响应码
                .contentType(MediaType.TEXT_PLAIN) // 响应类型
                .body(BodyInserters.fromObject("这是学生控制类")); // 响应体
    }
}
