package core.servlets;
import beans.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import core.HttpHandler;
import core.result.SimpleAnswer;
import exceptions.ServiceException;
import org.apache.log4j.Logger;
import services.CountryService;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class CountryServletList implements HttpHandler {

    private static final Logger log = Logger.getLogger(CountryServletList.class);

    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        List<Country> list = CountryService.getCountries();
        SimpleAnswer result =  new SimpleAnswer();
        result.setResult(list);
        return result;
    }

}
