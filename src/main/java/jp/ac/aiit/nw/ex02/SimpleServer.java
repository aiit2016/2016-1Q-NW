package jp.ac.aiit.nw.ex02;
/*
* SimpleServer.java: 受け取ったメッセージをそのまま返すサーバ
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class SimpleServer {
    public static final int PORT = 5555; 
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

                writer.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss 'TimeZone:'Z").format(
					Calendar.getInstance().getTime()).toString());
                
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
