package com.zhouyu.springboot;

public class TomcatWebServer implements WebServer{

    @Override
    public void start() {
        System.out.println("启动Tomcat");
    }
}
