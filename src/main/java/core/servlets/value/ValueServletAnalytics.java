package core.servlets.value;
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
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
@HttpUrl(url = "/value/analytic")
public class ValueServletAnalytics implements HttpHandler {

    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        try {
            int countryUid = Integer.valueOf(req.getParameter("countryUid"));
            int indexUid   = Integer.valueOf(req.getParameter("indexUid"));

            List<ResultValue> result = ValueService.getResultValues(new Country(countryUid), new Index(indexUid));
            return new SimpleAnswer(result);
        } catch (NumberFormatException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
