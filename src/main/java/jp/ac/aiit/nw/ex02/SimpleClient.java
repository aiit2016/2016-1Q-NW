package jp.ac.aiit.nw.ex02;
/*
* SimpleClient.java: 送ったメッセージをそのまま受信するクライアント
*/
import java.io.*; import java.net.*;
public class SimpleClient {

    public static final int PORT = 5555;
        public static void main (String args[]) {
        	int port = PORT;
        	try {
        		port = Integer.parseInt(args[1]);
        	} catch (Throwable e) {
        		System.err.println("Parameter error!");
        	}
            try {
                // ソケットを作成
                Socket socket = new Socket(args[0], port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
	    
                // サーバにメッセージを送る
                //writer.writeBytes("Hello\nWorld!\n");
	    
                //サーバからメッセージを受け取る
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("TimeServer: " + line);
                }
                writer.close();
                reader.close();
                socket.close();
            } catch (UnknownHostException e) {
                System.err.println("Host not found");
                System.exit(-1);
            } catch (SocketException e) {
                System.err.println("Socket error");
                System.exit(-1);
            } catch (IOException e) {
                System.err.println("IO error");
                System.exit(-1);
            }
        }
}
