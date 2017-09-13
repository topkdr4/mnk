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
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

@HttpUrl(url = "/index/remove")
public class IndexServletRemove implements HttpHandler{
    
    private static final Logger log = LogManager.getLogger(IndexServletRemove.class);
    
    @Override
    public SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String uid = req.getParameter("uid");
        int id;
        try {
            id = Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            throw new ServiceException("incorrect format UID");
        }
    
        List<Index> countries = IndexService.getIndexes();
        if (countries.isEmpty())
            throw new ServiceException("Index by uid = `" + uid + "` not found");
    
        Index findIndex = null;
        for (Index index : countries) {
            if (index.getUid() == id) {
                findIndex = index;
                break;
            }
        }
    
        if (findIndex == null)
            throw new ServiceException("Index by uid = `" + uid + "` not found");
    
        IndexService.remove(findIndex);
        log.info("removed country: " + findIndex.getName());
        return SimpleAnswer.EMPTY;
    }
}
