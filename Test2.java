package ohtello;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Test2 obj = new Test2();
		//obj.create();
		if(obj.accountCreate("sample1", "password"))
			System.out.println("ok");
		else 
			System.out.println("no");
		
	}
	
	public void create(){
        //FileOutputStreamオブジェクトの生成
        FileOutputStream outFile;
		try {
			outFile = new FileOutputStream("players.obj");  
			//ObjectOutputStreamオブジェクトの生成
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        //クラスHelloのオブジェクトの書き込み
        outObject.writeObject(new Player("nnn","password"));
        outObject.close();
        

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
	//アカウント作成
	public boolean accountCreate(String user_name, String password) {
		

		try {     
		//	ArrayList<Player> array = new ArrayList<Player>();
			//FileInputStreamオブジェクトの生成
            FileInputStream inFile = new FileInputStream("players.obj");
            //ObjectInputStreamオブジェクトの生成
            //オブジェクトの読み込み
            Player player;

            try{
            while(true){ 
            	ObjectInputStream inObject = new ObjectInputStream(inFile);
            	player = (Player)inObject.readObject();
            	System.out.println(player.getName());
            	if(player.getName().equals(user_name)){
            		inObject.close();
            		return false;
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
           return true;
	}
}

	