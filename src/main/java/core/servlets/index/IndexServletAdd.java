package core.servlets.index;
import beans.Index;
import core.HttpHandler;
import core.result.SimpleAnswer;
import core.servlets.HttpUrl;
import exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import services.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

@HttpUrl(url = "/index/add")
public class IndexServletAdd implements HttpHandler {
    
    private static final Logger logger = LogManager.getLogger(IndexServletAdd.class);
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String futureName = req.getParameter("name");
        Index index = IndexService.register(futureName);
        return SimpleAnswer.EMPTY;
    }
}
