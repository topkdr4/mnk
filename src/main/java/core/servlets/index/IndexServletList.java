package core.servlets.index;
import core.HttpHandler;
import core.result.SimpleAnswer;
import core.servlets.HttpUrl;
import exceptions.ServiceException;
import services.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

@HttpUrl(url = "/index/list")
public class IndexServletList implements HttpHandler {


    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        return new SimpleAnswer(IndexService.getIndexes());
    }
}
