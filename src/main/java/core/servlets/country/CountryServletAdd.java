package core.servlets.country;
import beans.Country;
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
@HttpUrl(url = "/country/add")
public class CountryServletAdd implements core.HttpHandler {

    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String name = req.getParameter("name");
        List<Country> countries = CountryService.getCountries();
        for (Country country : countries) {
            if (country.getName().equalsIgnoreCase(name))
                throw new ServiceException(name + " already exists");
        }

        Country country = new Country();
        country.setName(name);
        country.setUrl(req.getParameter("url"));

        CountryService.register(country);
        return SimpleAnswer.EMPTY;
    }
}
