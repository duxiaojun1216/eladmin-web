package me.zhengjie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.utils.SpringContextHolder;

/**
 *
 * @author: dengjie
 * @date: 2020/3/21
 */
@EnableAsync
@RestController
@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaRepositories("me.zybt.repository")
////@EntityScan("me.zybt.domain")
//@EntityScan(value={"me.zybt.domain"})
//@ComponentScan(basePackages = "me.zybt.*.*")
public class MonitorRun {

	 public static void main(String[] args) {
	        SpringApplication.run(MonitorRun.class, args);
	    }

	    @Bean
	    public SpringContextHolder springContextHolder() {
	        return new SpringContextHolder();
	    }

	    @Bean
	    public ServletWebServerFactory webServerFactory() {
	        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
	        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
	        return fa;
	    }

	    /**
	     * 访问首页提示
	     * @return /
	     */
	    @GetMapping("/")
	    @AnonymousAccess
	    public String index() {
	        return "Backend service started successfully";
	    }

}
