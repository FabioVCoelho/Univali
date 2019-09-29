import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import java.io.File;
import java.io.IOException;

public class JSTLServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String directoryRequested = request.getParameter("directory");
        File files = new File(directoryRequested);
        JSONArray filesArray = new JSONArray();
        FileAttributesFormatted fileAttributesFormatted = new FileAttributesFormatted();
        if (files.listFiles() != null) {
            for (int i = 0; i < files.listFiles().length; i++) {
                JSONObject jsonObject = new JSONObject();
                File file = files.listFiles()[i];
                jsonObject.put("name", file.getName());
                jsonObject.put("isDirectory", String.valueOf(file.isDirectory()));
                jsonObject.put("lastModified", fileAttributesFormatted.formatDate(file.lastModified()));
                jsonObject.put("createDate", fileAttributesFormatted.getCreatedTimeFile(file));
                jsonObject.put("size", fileAttributesFormatted.formatSize(file.length()));
                jsonObject.put("absolutePath", file.getAbsolutePath());
                filesArray.put(jsonObject);
            }
        }
        request.setAttribute("directories", filesArray.toList());
        RequestDispatcher rd = request.getRequestDispatcher("directory.jsp");
        rd.forward(request, response);
    }
}
