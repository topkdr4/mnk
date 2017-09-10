package core.servlets.country;
import beans.Country;
import core.HttpHandler;
import core.result.SimpleAnswer;
import core.servlets.HttpUrl;
import exceptions.ServiceException;
import services.CountryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
@HttpUrl(url = "/country/list")
public class CountryServletList implements HttpHandler {
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        List<Country> list = CountryService.getCountries();
        SimpleAnswer result = new SimpleAnswer();
        result.setResult(list);
        return result;
    }
    
}
