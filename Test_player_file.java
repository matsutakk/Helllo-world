package othello;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test_player_file {

	public static void main(String[] args) {
      Test_player_file obj = new Test_player_file();
      if(obj.accountCreate("katddki", "takuya")) {
    	  System.out.println("success");
      }
      else System.out.println("false");
	}

	public boolean accountCreate(String user_name, String password) {
		Player_v2 player_obj;
       try {
   	   //FileInputStreamオブジェクトの生成
    	   FileInputStream inFile = new FileInputStream("Player.txt");
    	   //ObjectInputStreamオブジェクトの生成
    	   ObjectInputStream inObject = new ObjectInputStream(inFile);

 //   	   if(inObject.readObject() != null ) System.out.println("hello");

    	   while(null!=(player_obj=(Player_v2)inObject.readObject())) {
    		   if(player_obj.getName().equals(user_name)) {
    			   System.out.println(player_obj.getName());
    	      	   inObject.close();  //オブジェクト入力ストリームのクローズ
    	    	   inFile.close();  //ファイル入力ストリームのクローズ
    			   return false;
    		   }
    	   } 
       	   inObject.close();  //オブジェクト入力ストリームのクローズ
    	   inFile.close();  //ファイル入力ストリームのクローズ

   	   //FileOutputStreamオブジェクトの生成
    	   FileOutputStream outFile = new FileOutputStream("Player.txt");
           //(ObjectOutputStreamオブジェクトの生成
    	   ObjectOutputStream outObject = new ObjectOutputStream(outFile);
 
    	   outObject.writeObject(new Player_v2(user_name, password));
           
    	   outObject.close();  //オブジェクト出力ストリームのクローズ
    	   outFile.close();  //ファイル出力ストリームのクローズ
//      	   return true;

       }
       catch(Exception e) {
    	   e.printStackTrace();
       }
       System.out.println("wwwww");
       return true;
	}
	
}
