package net.codejava.upload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
/*We need add dependency commons-fileupload in pom.xml file, after that we writing cod
and only click import*/
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /*These constants will be used to configure upload settings.
    * Name of the directory on the server where upload file will be stored.*/
    private static final String UPLOAD_DIRECTORY = "upload";

    /*file that has size less than this threshold value will be saved into memory.
    If the size is greater than this value, it will be stored on disk, temporarily.
    The value is measured in bytes.*/
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB

    /*Specifies the maximum size of an upload file.*/
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB

    /*Specifies the maximum size of a HTTP request which contains the upload file
    and other form’s data, so this constant should be greater than the MAX_FILE_SIZE.*/
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    /*Write code for the UploadServlet class to read upload data and save it as a file on disk.
    We implement the code in the servlet’s doPost() method.*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        /*That checks if the request contains upload data
        (indicated by the attribute enctype="multipart/form-data" of the upload form).
        If not, we print a message to the user and exit the method.*/
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Request does not contain upload data");
            writer.flush();
            return;
        }

        /*Manages file content either in memory or on disk.*/
        DiskFileItemFactory factory = new DiskFileItemFactory();
        /*Specify the threshold above which files are stored on disk*/
        factory.setSizeThreshold(THRESHOLD_SIZE);
        /*Copy the files from the temporary directory to a desired location.*/
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        /*Main class that handles file upload by parsing the request and returning a list of file items.*/
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        /*Reading upload data and save to file*/
        try {
            /*Returns a list of form items which will be iterated to identify the item*/
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // processes only fields that are not form fields
                //Checks if an item is a form field or not.
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);

                    // saves the file on disk
                    item.write(storeFile);
                }
            }
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex){
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);



    }
}
