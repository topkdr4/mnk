package core.servlets.value;
import beans.Country;
import beans.Index;
import beans.Value;
import core.HttpHandler;
import core.result.SimpleAnswer;
import core.servlets.HttpUrl;
import exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import services.CountryService;
import services.IndexService;
import services.ValueService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

@HttpUrl(url = "/value/list")
public class ValueServletList implements HttpHandler {
    
    private static final Logger log = LogManager.getLogger(ValueServletList.class);
    
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String countryUid = req.getParameter("countryUid");
        String indexUid = req.getParameter("indexUid");
        
        if (countryUid == null || countryUid.isEmpty())
            throw new ServiceException(1, "country uid is incorrect");
        
        if (indexUid == null || indexUid.isEmpty())
            throw new ServiceException(1, "index uid is incorrect");
        
        int countryID;
        int indexID;
        try {
            countryID = Integer.valueOf(countryUid);
            indexID = Integer.valueOf(indexUid);
        } catch (NumberFormatException e) {
            throw new ServiceException(1, e.getMessage());
        }
        
        List<Country> countries = CountryService.getCountries();
        Country findCountry = null;
        for (Country country : countries) {
            if (country.getId() == countryID) {
                findCountry = country;
                break;
            }
        }
        
        if (findCountry == null)
            throw new ServiceException("Country not found");
        
        List<Index> indices = IndexService.getIndexes();
        Index findIndex = null;
        for (Index index : indices) {
            if (index.getUid() == indexID) {
                findIndex = index;
                break;
            }
        }
        
        if (findIndex == null)
            throw new ServiceException("Index not found");

        List<Value> valueList = ValueService.getValues(findCountry, findIndex);
        return new SimpleAnswer(valueList);
    }
}
