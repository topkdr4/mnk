package core.servlets;
import core.HttpHandler;
import core.result.SimpleAnswer;
import exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class CountryServletRemove implements HttpHandler {

    private static final Logger log = Logger.getLogger(CountryServletList.class);

    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        return SimpleAnswer.EMPTY;
    }
}
