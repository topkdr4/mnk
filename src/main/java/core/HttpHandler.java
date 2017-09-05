package core;

import core.result.SimpleAnswer;
import exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public interface HttpHandler {

    SimpleAnswer execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;

}
