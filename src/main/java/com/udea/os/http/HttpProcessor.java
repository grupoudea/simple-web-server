package com.udea.os.http;

import com.udea.os.config.Method;
import com.udea.os.config.Properties;
import com.udea.os.config.PropertiesManager;
import com.udea.os.config.StatusCode;
import com.udea.os.dto.Request;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

import static com.udea.os.utils.MimeTypes.getMimeType;

public class HttpProcessor implements Runnable {
    private static final Properties properties = PropertiesManager.getPropertiesManager().getProperties();
    private final Socket connect;

    public HttpProcessor(Socket connect) {
        this.connect = connect;
    }

    @Override
    public void run() {
        BufferedReader input = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;

        try {
            input = getInput();
            out = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            Request request = getRequest(input);
            if (!request.getMethod().equals(Method.GET.name()) && !request.getMethod().equals(Method.HEAD.name())) {
                serverError(out, dataOut);
            } else {
                validatePath(request);
                successResponse(out, dataOut, request);
            }
        } catch (FileNotFoundException fnfe) {
            try {
                badRequest(out, dataOut);
            } catch (IOException ioe) {
                System.err.println("Error with file not found exception : " + ioe.getMessage());
            }
        } catch (IOException ioe) {
            System.err.println("Server error : " + ioe);
        } finally {
            try {
                input.close();
                out.close();
                dataOut.close();
                connect.close();
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }
            System.out.println("\n");
        }
    }

    private void successResponse(PrintWriter out, BufferedOutputStream dataOut, Request request) throws IOException {
        File file = new File(properties.getWebRoot(), request.getPath());
        int fileLength = (int) file.length();
        String content = getContentType(request.getPath());
        if (request.getMethod().equals("GET")) {
            byte[] fileData = readFileData(file, fileLength);
            sendHttpHeaders(out, dataOut, fileLength, content, fileData, StatusCode.HTTP_200.getStatus());
        }
    }

    private void badRequest(PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        File file = new File(properties.getWebRoot(), properties.getFileNotFound());
        int fileLength = (int) file.length();
        String content = getMimeType(".htm");
        byte[] fileData = readFileData(file, fileLength);
        sendHttpHeaders(out, dataOut, fileLength, content, fileData, StatusCode.HTTP_400.getStatus());
    }

    private void serverError(PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        File file = new File(properties.getWebRoot(), properties.getMethodNotSupperted());
        int fileLength = (int) file.length();
        String contentMimeType = getMimeType(".htm");
        byte[] fileData = readFileData(file, fileLength);
        sendHttpHeaders(out, dataOut, fileLength, contentMimeType, fileData, StatusCode.HTTP_500.getStatus());
    }

    private void sendHttpHeaders(PrintWriter out, BufferedOutputStream dataOut, int fileLength, String content, byte[] fileData, String response) throws IOException {
        out.println(response);
        show("Status code", response);
        out.println("Server: JGKServer: 1.0.0");
        out.println("Date: " + new Date());
        show("Time", new Date().toString());
        out.println("Content-type: " + content);
        out.println("Content-length: " + fileLength);
        out.println();
        out.flush();
        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();
    }

    private void validatePath(Request request) {
        if (request.getPath().endsWith("/")) {
            request.setPath(request.getPath().concat(properties.getDefaultFile()));
        } else {
            show("Incorrect path", request.getPath());
        }
    }

    private BufferedReader getInput() throws IOException {
        return new BufferedReader(new InputStreamReader(connect.getInputStream()));
    }

    private Request getRequest(BufferedReader input) throws IOException {
        String requestMethod = input.readLine();
        show("Request Method", requestMethod);
        StringTokenizer parse = new StringTokenizer(requestMethod);
        String method = parse.nextToken().toUpperCase();
        String fileRequested = parse.nextToken().toLowerCase();
        show("Request URL", fileRequested);
        return new Request(method, fileRequested);
    }

    private String getContentType(String fileRequested) {
        show("Request Mime", fileRequested);
        return getMimeType(fileRequested);
    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }
        return fileData;
    }

    private void show(String name, String value) {
        System.out.println(name.concat(": ".concat(value)));
    }
}
