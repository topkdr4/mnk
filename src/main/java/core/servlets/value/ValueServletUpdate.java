package core.servlets.value;
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
@HttpUrl(url = "/value/update")
public class ValueServletUpdate implements HttpHandler {
    
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        try {
            int uid  = Integer.parseInt(req.getParameter("uid"));
            double x = Double.parseDouble(req.getParameter("x"));
            double y = Double.parseDouble(req.getParameter("y"));
    
            Value value = new Value(x, y);
            value.setUid(uid);
    
            ValueService.updateValue(value);
            return SimpleAnswer.EMPTY;
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        }
    }
}
