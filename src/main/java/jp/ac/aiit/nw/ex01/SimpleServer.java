package jp.ac.aiit.nw.ex01;
/*
* SimpleServer.java: 受け取ったメッセージをそのまま返すサーバ
*/
import java.io.*; 
import java.net.*;
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

                // データを読み込み，そのまま返す
                while (true) {
                    String line = reader.readLine();
                    if (line == null)
                        break;
                    System.out.println(line);
                    writer.println(line);
                }
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
