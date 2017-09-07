package core;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
@WebListener
public class ServletListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ServletListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
