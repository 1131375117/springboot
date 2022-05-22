package com.zhouyu.springboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Map;

public class ZhouyuSpringApplication {

    public static void run(Class clazz){
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(clazz);
        applicationContext.refresh();

        WebServer webServer = getWebServer(applicationContext);
        webServer.start();

    }

    public static WebServer getWebServer(ApplicationContext applicationContext){
        // key为beanName, value为Bean对象
        Map<String, WebServer> webServers = applicationContext.getBeansOfType(WebServer.class);
        if (webServers.isEmpty()) {
            throw new NullPointerException();
        }
        if (webServers.size() > 1) {
            throw new IllegalStateException();
        }
        return webServers.values().stream().findFirst().get();
    }

//    public static void startTomcat(WebApplicationContext applicationContext){
//
//        Tomcat tomcat = new Tomcat();
//
//        Server server = tomcat.getServer();
//        Service service = server.findService("Tomcat");
//
//        Connector connector = new Connector();
//        connector.setPort(8081);
//
//        Engine engine = new StandardEngine();
//        engine.setDefaultHost("localhost");
//
//        Host host = new StandardHost();
//        host.setName("localhost");
//
//        String contextPath = "";
//        Context context = new StandardContext();
//        context.setPath(contextPath);
//        context.addLifecycleListener(new Tomcat.FixContextListener());
//
//        host.addChild(context);
//        engine.addChild(host);
//
//        service.setContainer(engine);
//        service.addConnector(connector);
//
//        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet(applicationContext));
//        context.addServletMappingDecoded("/*", "dispatcher");
//
//        try {
//            tomcat.start();
//        } catch (LifecycleException e) {
//            e.printStackTrace();
//        }
//
//    }
}
