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
        int uid  = Integer.valueOf(req.getParameter("uid"));
        double x = Double.valueOf(req.getParameter("x"));
        double y = Double.valueOf(req.getParameter("y"));
    
        Value value = new Value(x, y);
        value.setUid(uid);
        
        ValueService.updateValue(value);
        return SimpleAnswer.EMPTY;
    }
}
