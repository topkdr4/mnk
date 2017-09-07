package core.servlets.country;
import beans.Country;
import core.result.SimpleAnswer;
import core.servlets.HttpUrl;
import exceptions.ServiceException;
import services.CountryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
@HttpUrl(url = "/country/add")
public class CountryServletAdd implements core.HttpHandler {

    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        Country country = new Country();
        CountryService.register(country);
        return SimpleAnswer.EMPTY;
    }
}
