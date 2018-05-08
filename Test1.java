package ohtello;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Test1 obj = new Test1();
		
		if(obj.accountCreate("takuyta", "password"))
			System.out.println("ok");
		else 
			System.out.println("no");
		
	}
	//アカウント作成
	public boolean accountCreate(String user_name, String password) {

		try {     
			
			//FileInputStreamオブジェクトの生成
            FileInputStream inFile = new FileInputStream("players.obj");
            //ObjectInputStreamオブジェクトの生成
            ObjectInputStream inObject = new ObjectInputStream(inFile);
            //オブジェクトの読み込み
            Player player;

            try{
            while(true){
            	player = (Player)inObject.readObject();
            	if(player.getName().equals(user_name)){
            		return false;
            	}
            }
            }catch(EOFException e){	
    		}
            finally{
            	inObject.close();
            }

            //FileOutputStreamオブジェクトの生成
            FileOutputStream outFile = new FileOutputStream("players.obj");
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

	