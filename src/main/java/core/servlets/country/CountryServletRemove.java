package core.servlets.country;
import beans.Country;
import core.HttpHandler;
import core.result.SimpleAnswer;
import exceptions.ServiceException;
import org.apache.log4j.Logger;
import services.CountryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class CountryServletRemove implements HttpHandler {
    
    private static final Logger log = Logger.getLogger(CountryServletList.class);
    
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String uid = req.getParameter("uid");
        int id;
        try {
            id = Integer.valueOf(uid);
        } catch (NumberFormatException e) {
            throw new ServiceException("incorrect format UID");
        }
        
        List<Country> countries = CountryService.getCountries();
        if (countries.isEmpty())
            throw new ServiceException("Country by uid = `" + uid + "` not found");
        
        Country findCountry = null;
        for (Country country : countries) {
            if (country.getId() == id) {
                findCountry = country;
                break;
            }
        }
        
        if (findCountry == null)
            throw new ServiceException("Country by uid = `" + uid + "` not found");
        
        CountryService.remove(findCountry);
        log.info("removed country: " + findCountry.getName());
        return SimpleAnswer.EMPTY;
    }
}
