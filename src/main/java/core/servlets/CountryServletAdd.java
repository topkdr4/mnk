package core.servlets;
import beans.Country;
import core.result.SimpleAnswer;
import exceptions.ServiceException;
import services.CountryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class CountryServletAdd implements core.HttpHandler {

    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        Country country = new Country();
        CountryService.register(country);
        SimpleAnswer result = new SimpleAnswer();
        return result;
    }
}
