package core.servlets;
import core.ServletListener;
import core.result.SimpleAnswer;
import util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;





/**
 * Ветошкин А.В. РИС-16бзу
 * */

@WebServlet("/image/list")
public class ImagePrinter extends HttpServlet {

    private static final Path context = Paths.get(ServletListener.applicationPath);
    private static final Path applicationPath = Paths.get(ServletListener.applicationPath, "icons", "countries_icons");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> lists = Files.find(applicationPath, Integer.MAX_VALUE, (path, attr) -> {
            return path.toString().endsWith(".png") && attr.isRegularFile();
        }).collect(Collectors.toList()).stream().map((path -> {
            return path.toString().substring(context.toString().length());
        })).collect(Collectors.toList());

        String result = Util.toJson(new SimpleAnswer(lists));
        resp.setContentType("application/json");
        resp.getWriter().write(result);
        resp.getWriter().flush();
    }
}
