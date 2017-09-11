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
@HttpUrl(url = "/value/remove")
public class ValueServletRemove implements HttpHandler {
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        int uid = Integer.valueOf(req.getParameter("uid"));
        ValueService.remove(new Value(uid));
        return SimpleAnswer.EMPTY;
    }
}
