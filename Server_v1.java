package ohtello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server_v1 {

	private int port; //ポート番号
	private boolean [] online; //クライアント接続状態
	private PrintWriter [] out; //データ送信用オブジェクト
	private Receiver [] receiver; //データ受信用オブジェクト
	private String password; //パスワード
	private String user_name; //ユーザ名
	//private ArrayList list; //リストオブジェクト

	//コンストラクタ
	public Server_v1(int port) { //待ち受けポートを引数とする
		this.port = port; //待ち受けポートを渡す
		out = new PrintWriter [2]; //データ送信用オブジェクトを2クライアント分用意
		receiver = new Receiver [2]; //データ受信用オブジェクトを2クライアント分用意
		online = new boolean[2]; //オンライン状態管理用配列を用意
	}

	// データ受信用スレッド(内部クラス)
	class Receiver extends Thread {
		private InputStreamReader sisr; //受信データ用文字ストリーム
		private BufferedReader br; //文字ストリーム用のバッファ
		private PrintWriter out;
		private int playerNo; //プレイヤを識別するための番号

		// 内部クラスReceiverのコンストラクタ
		Receiver (Socket socket, int playerNo){
			try{
				this.playerNo = playerNo; //プレイヤ番号を渡す
				sisr = new InputStreamReader(socket.getInputStream());
				br = new BufferedReader(sisr);
				out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				System.out.println(playerNo+"がせつぞくしました");
			} catch (IOException e) {
				System.err.println("データ受信時にエラーが発生しました: " + e);
			}
		}
		// 内部クラス Receiverのメソッド
		public void run(){
			try{
				while(true) {// データを受信し続ける
					String inputLine = br.readLine();//データを一行分読み込む
					if (inputLine != null){ //データを受信したら
						if(inputLine.equals("loginRequest")) {
							String user_name = br.readLine();
							String password = br.readLine();
							String msg = loginCheck(user_name,password);
							out.println(msg);
							out.flush();
							System.out.println("server message sent");
						}
						else if(inputLine.equals("accountRequest")) {
								String user_name = br.readLine();
								String password = br.readLine();
								String msg = accountCreate(user_name, password);
								out.println(msg);
								out.flush();
								System.out.println("server message sent");
						}
						else if(inputLine.equals("myPlayerRequest")) {
								playerInfo();
						}
						/*else if() {
							forwardMessage(inputLine, playerNo); //もう一方に転送する
						}*/
					}
				}
			} catch (IOException e){ // 接続が切れたとき
				System.err.println("プレイヤ " + playerNo + "との接続が切れました．");
				online[playerNo] = false; //プレイヤの接続状態を更新する
				printStatus(); //接続状態を出力する
			}
		}
	}


	/*********************メソッド***********************/

	//クライアントの接続(サーバの起動)
	public void acceptClient(){
		int i=0;
		try {
			System.out.println("サーバが起動しました．");
			ServerSocket ss = new ServerSocket(port); //サーバソケットを用意
			while (true) {
				Socket socket = ss.accept(); //新規接続を受け付ける
				new Receiver(socket,i).start(); //データ受信スレッドのスタート
				i++;
			}
		} catch (Exception e) {
			System.err.println("ソケット作成時にエラーが発生しました: " + e);
		}
	}


	//ログイン認証
	public String loginCheck(String user_name, String password) {
		try{
			//FileInputStreamオブジェクトの生成
			FileInputStream inFile = new FileInputStream("players.obj");
	           //オブジェクトの読み込み
            Player player;

            try{
            while(true){   
            	//ObjectInputStreamオブジェクトの生成
            	ObjectInputStream inObject = new ObjectInputStream(inFile);
            	player = (Player)inObject.readObject();
            	if(player.getName().equals(user_name) && player.getPassword().equals(password)){
            		inObject.close();
            		System.out.println("login permit");
            		return "permit";
            	}        
           		inObject.close();
             }
            }catch(EOFException e){	
    		}
            finally{
            }
            

		}catch(Exception e){
			
		}
		return "notPermit";
	}

	//アカウント作成
	public String accountCreate(String user_name, String password) {
		

		try {     
		//	ArrayList<Player> array = new ArrayList<Player>();
			//FileInputStreamオブジェクトの生成
            FileInputStream inFile = new FileInputStream("players.obj");
 
            //オブジェクトの読み込み
            Player player;

            try{
            while(true){   
            	//ObjectInputStreamオブジェクトの生成
            	ObjectInputStream inObject = new ObjectInputStream(inFile);
            	player = (Player)inObject.readObject();
            	System.out.println(player.getName());
            	if(player.getName().equals(user_name)){
            		inObject.close();
            		System.out.println("false");
            		return "notPermit";
            	}       
             }
            }catch(EOFException e){	
    		}
            finally{
             }
            
            //FileOutputStreamオブジェクトの生成
            FileOutputStream outFile = new FileOutputStream("players.obj",true);
            //ObjectOutputStreamオブジェクトの生成
            ObjectOutputStream outObject = new ObjectOutputStream(outFile);
            //クラスHelloのオブジェクトの書き込み
            outObject.writeObject(new Player(user_name,password));
   
            outObject.close();
       }
       catch(Exception e) {
    	   e.printStackTrace();
       }
		System.out.println("true");
           return "permit";
	}

	//Playerの対戦成績を送信
	public void playerInfo() {

	}
	//クライアント接続状態の確認
	public void printStatus(){

	}

	//データ更新
	public void dataUpdate() {

	}

	//対局者情報の転送
	public void sendPlayerInfo() {

	}

	//対局待ち状態受付 and 対戦者リスト転送
	public void sendList(){

;	}

	//対局申し込み転送
	public void requestGame(){

	}

	/*//先手後手情報の送信
	public void sendColor(int playerNo) {

	}*/

	//操作情報を転送
	public void forwardMessage(String msg, int playerNo) {

	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Server_v1 server = new Server_v1(10005); //待ち受けポート10000番でサーバオブジェクトを準備
		server.acceptClient(); //クライアント受け入れを開始
	}

}
