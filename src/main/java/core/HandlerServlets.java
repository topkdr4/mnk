package core;
import core.result.SimpleAnswer;
import core.servlets.country.CountryServletAdd;
import core.servlets.country.CountryServletList;
import core.servlets.country.CountryServletRemove;
import exceptions.ServiceException;
import org.apache.log4j.Logger;
import util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
@WebServlet("/")
public final class HandlerServlets extends HttpServlet {

    private static final Map<String, HttpHandler> SERVLETS = new HashMap<>();
    private static final Logger log = Logger.getLogger(HandlerServlets.class);

    static {
        SERVLETS.put("/country/list", new CountryServletList());
        SERVLETS.put("/country/add",  new CountryServletAdd());
        SERVLETS.put("/country/remove", new CountryServletRemove());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.info("request to " + req.getRequestURI());
        HttpHandler handler = SERVLETS.get(req.getRequestURI());
        if (handler == null) {
            resp.sendRedirect("404.jsp");
            return;
        }

        Object answer;

        try {
            answer = handler.execute(req, resp);
        } catch (ServiceException e) {
            SimpleAnswer error = new SimpleAnswer();
            error.setErrorCode(e.getErrorCode());
            error.setErrorText(e.getErrorText());
            answer = error;
        }

        resp.setContentType("application/json;charset=utf-8");
        Writer writer = resp.getWriter();
        writer.write(Util.toJson(answer));
        writer.flush();
    }

}
