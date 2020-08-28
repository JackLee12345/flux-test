package flux.service;

import flux.controller.StudentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by You.Li
 */
@Configuration
public class StudentRouter {

//         路由器类
//　　　　相当于DispatherServlet，主要就是将不同的请求url和对应的处理器类进行匹配
//　　　　技巧01：路由器类相当于一个Java配置类，所以必须添加 @Configuration 注解，并且路由器类中的路由方法返回的对象必须被容器管理，所以必须添加@Bean注解
//　　　　技巧02：RouterFunctions的route方法接收两个参数，并返回 RouterFunctions 类型
//　　　　　　》参数一 ：请求断言
//　　　　　　》参数二 ：处理函数接口【可用lambda表达式代替】

    @Autowired
    StudentHandler studentHandler;

    @Bean
    public RouterFunction<ServerResponse> routerHelloStudent(StudentHandler studentHandler) {
        return RouterFunctions
                .route( // 路由匹配：将请求url路由到对应的处理器
                        RequestPredicates
                                .GET("/student/hello") // 请求url
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), // 接收的请求数据类型
                        studentHandler::helloStudent // 处理请求的处理方法
                );
        /*
        代码解释：如果请求是/student/hello时就会调用StudentHandler处理器类中的helloStudent方法进行业务逻辑处理
         */
    }
}
