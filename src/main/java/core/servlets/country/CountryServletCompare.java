package core.servlets.country;
import beans.Country;
import beans.Index;
import beans.ResultValue;
import core.HttpHandler;
import core.result.SimpleAnswer;
import core.servlets.HttpUrl;
import exceptions.ServiceException;
import services.ValueService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
@HttpUrl(url = "/country/compare")
public class CountryServletCompare implements HttpHandler {



    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        try {
            int first  = Integer.parseInt(req.getParameter("first"));
            int second = Integer.parseInt(req.getParameter("second"));
            int index  = Integer.parseInt(req.getParameter("index"));

            Map<Integer, List<ResultValue>> result = new HashMap<>();
            result.put(first, ValueService.getResultValues(new Country(first), new Index(index)));
            result.put(second, ValueService.getResultValues(new Country(second), new Index(index)));

            return new SimpleAnswer(result);
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        }
    }
}
