package core;
import core.servlets.HttpUrl;
import org.apache.log4j.Logger;
import util.DBSource;
import util.Util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
@WebListener
public class ServletListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ServletListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            String cc = servletContextEvent.getServletContext().getRealPath("/");
            Path appPath = Paths.get(cc, "WEB-INF", "classes");
            Set<Class<?>> classes = Util.getClasses(appPath, clazz -> {
                return clazz.isAnnotationPresent(HttpUrl.class) && HttpHandler.class.isAssignableFrom(clazz);
            });

            for (Class<?> aClass : classes) {
                HttpUrl httpUrl = aClass.getAnnotation(HttpUrl.class);
                String url = httpUrl.url();
                HttpHandler handler = (HttpHandler) aClass.newInstance();
                HandlerServlets.registerServlets(url, handler);
            }

        } catch (Exception e) {
            log.fatal(e.getMessage(), e);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
