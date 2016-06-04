package jp.ac.aiit.nw.ex03;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/*
 * Simple http server
 */
public class PhttpServer {
    public static final int PORT = 80; 

	public static void main(String[] args) throws Exception {
    	int port = PORT;
    	try {
    		port = Integer.parseInt(args[0]);
    	} catch (Throwable e) {
    		System.err.println("Parameter error!");
    	}
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/", new MyHandler());
		server.setExecutor(null);
		server.start();
	}

	static class MyHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			String response = "Hello!";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}