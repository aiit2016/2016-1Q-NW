package jp.ac.aiit.nw.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/*
 * Simple http server
 */
public class PhttpServer {
    public static final int PORT = 80; 
    public static void main (String args[]) {
    	int port = PORT;
    	try {
    		port = Integer.parseInt(args[0]);
    	} catch (Throwable e) {
    		System.err.println("Parameter error!");
    	}
        try {
            // ソケットを生成
            ServerSocket serverSocket = new ServerSocket(port);
            // 入出力ストリームを用意し，クライアントからの要求を待つ
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream writer = new PrintStream(socket.getOutputStream());

            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
            writer.println("Hello!");

            writer.close();
            reader.close();
            socket.close();
            serverSocket.close();
        } catch (SocketException e) {
            System.err.println("Socket error");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("IO error");
            System.exit(-1);
        }
    }
}
