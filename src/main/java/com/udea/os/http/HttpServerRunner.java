package com.udea.os.http;

import com.udea.os.config.Properties;
import com.udea.os.config.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerRunner {
    private static final Properties properties = PropertiesManager.getPropertiesManager().getProperties();
    private static final Logger LOGGER = LogManager.getLogger(HttpServerRunner.class);

    private ServerSocket serverSocket;



    public void start()  {
        try {
            serverSocket = new ServerSocket(properties.getPort());
            LOGGER.info("Server started.\nListening for connections on port : " + properties.getPort() + " ...\n");
            //ExecutorService concurrentRequestLauncher = Executors.newFixedThreadPool(properties.getMaxThreadPool());
            LOGGER.info("Max thread pool: "+properties.getMaxThreadPool());

            do{
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new HttpProcessor(clientSocket));
                thread.start();
                //concurrentRequestLauncher.execute(new HttpProcessor(clientSocket));

            } while (!serverSocket.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
