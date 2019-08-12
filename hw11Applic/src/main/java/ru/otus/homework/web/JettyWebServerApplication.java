package ru.otus.homework.web;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import ru.otus.homework.services.UserServiceImpl;
import ru.otus.homework.servlet.PrivateInfoServlet;
import ru.otus.homework.servlet.UserInfoServlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;

public class JettyWebServerApplication {
    private final static int PORT = 8080;


    public void start() throws Exception{
        Server server = createServer(PORT);
        server.start();
        server.join();
    }

    public Server createServer(int port) throws IOException {

        UserServiceImpl userService = new UserServiceImpl();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new PrivateInfoServlet()), "/privateInfo");
        contextHandler.addServlet(new ServletHolder(new UserInfoServlet(userService)), "/userInfo");

        Server server = new Server(port);
        server.setHandler(new HandlerList(contextHandler));

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{createResourceHandler(), createSecurityHandler(contextHandler)});
        server.setHandler(handlerList);
        return server;
    }

    private ResourceHandler createResourceHandler() throws MalformedURLException {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setWelcomeFiles(new String[]{"info.html"});

        URL fileDir = JettyWebServerApplication.class.getClassLoader().getResource("static");
        if (fileDir == null) {
            throw new RuntimeException("File Directory not found");
        }
        //resourceHandler.setResourceBase(fileDir.getPath());
        resourceHandler.setResourceBase(URLDecoder.decode(fileDir.getPath()));
        return resourceHandler;
    }

    private SecurityHandler createSecurityHandler(ServletContextHandler context) throws MalformedURLException {
        Constraint constraint = new Constraint();
        constraint.setName("auth");
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[]{"user", "admin"});

        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/privateInfo/*");
        mapping.setConstraint(constraint);

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        security.setAuthenticator(new BasicAuthenticator());

        URL propFile = null;
        File realmFile = new File("./realm.properties");
        if (realmFile.exists()) {
            propFile = realmFile.toURI().toURL();
        }
        if (propFile == null) {
            System.out.println("local realm config not found, looking into Resources");
            propFile = JettyWebServerApplication.class.getClassLoader().getResource("realm.properties");
        }

        if (propFile == null) {
            throw new RuntimeException("Realm property file not found");
        }

        security.setLoginService(new HashLoginService("MyRealm", URLDecoder.decode(propFile.getPath())));
        security.setHandler(new HandlerList(context));
        security.setConstraintMappings(Collections.singletonList(mapping));

        return security;
    }
}
