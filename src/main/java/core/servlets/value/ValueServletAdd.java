package core.servlets.value;
import beans.Country;
import beans.Index;
import beans.Value;
import core.HttpHandler;
import core.result.SimpleAnswer;
import core.servlets.HttpUrl;
import exceptions.ServiceException;
import services.ValueService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */
@HttpUrl(url = "/value/add")
public class ValueServletAdd implements HttpHandler {
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        int countryUid = Integer.valueOf(req.getParameter("countryUid"));
        int indexUid   = Integer.valueOf(req.getParameter("indexUid"));
        double valueX  = Double.valueOf(req.getParameter("valueX"));
        double valueY  = Double.valueOf(req.getParameter("valueY"));
        
        Country country = new Country();
        country.setId(countryUid);
    
        Index index =  new Index(indexUid, "");
        
        Value value = new Value(valueX, valueY);
        
        ValueService.add(country, index, value);
        return SimpleAnswer.EMPTY;
    }
}
