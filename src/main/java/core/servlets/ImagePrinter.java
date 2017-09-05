package core.servlets;
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





/**
 * Ветошкин А.В. РИС-16бзу
 * */

@WebServlet("/image/*")
public class ImagePrinter extends HttpServlet {

    private static Path file = null;

    private static byte[] bytes = null;

    static {
        try {
            file = Paths.get("");
            bytes = Files.readAllBytes(file);
        } catch (IOException e) {
            //
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");
        resp.setContentLengthLong(Files.size(file));
        OutputStream stream = resp.getOutputStream();
        stream.write(bytes);
        stream.flush();
    }
}
