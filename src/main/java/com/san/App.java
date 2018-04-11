package com.san;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.san.config.AppConfig;
import com.san.config.JPA2Config;
import com.san.config.JPAConfig;

/**
 * Hello world!
 *
 */
public class App {
	public static AnnotationConfigApplicationContext appContext;

	public static void main(String[] args) {
		System.out.println("Initializing Application");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JPA2Config.class, JPAConfig.class, AppConfig.class);
		appContext = ctx;
		System.out.println("Application Initialized");
		initializeH2WebConsoleServer();
		Main.main(ctx);
	}

	private static void initializeH2WebConsoleServer() {
		int port = 8082;
		System.out.println("Launching H2 web console at address : http://127.0.0.1:" + port);
		// return org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "" + port);
		BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(org.h2.tools.Server.class, "createWebServer");
		AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
		beanDefinition.setInitMethodName("start");
		beanDefinition.setDestroyMethodName("stop");
		ConstructorArgumentValues args = new ConstructorArgumentValues();
		args.addGenericArgumentValue(new String[] { "-web", "-webAllowOthers", "-webDaemon", "-webPort", "" + port });
		beanDefinition.setConstructorArgumentValues(args);
		BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) appContext.getAutowireCapableBeanFactory();
		beanFactory.registerBeanDefinition("h2WebConsoleServer", beanDefinition);
		System.out.println("h2WebConsoleServer instance : " + appContext.getBean("h2WebConsoleServer"));
	}

}
