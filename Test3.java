package ohtello;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Test3 {
	public static void main(String[] args) {
		Player player;
		try{
			FileInputStream inFile = new FileInputStream("players.obj");
			while(true){
	          	ObjectInputStream inObject = new ObjectInputStream(inFile);
            	player = (Player)inObject.readObject();
            	System.out.println(player.getName()+" "+player.getPassword());
			}
		}catch(Exception e){
			
		}
	}
}
