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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Test2 obj = new Test2();
		//obj.create();
		if(obj.accountCreate("sample1", "password"))
			System.out.println("ok");
		else 
			System.out.println("no");
		
	}
	
	public void create(){
        //FileOutputStream�I�u�W�F�N�g�̐���
        FileOutputStream outFile;
		try {
			outFile = new FileOutputStream("players.obj");  
			//ObjectOutputStream�I�u�W�F�N�g�̐���
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        //�N���XHello�̃I�u�W�F�N�g�̏�������
        outObject.writeObject(new Player("nnn","password"));
        outObject.close();
        

		} catch (Exception e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}

	}
	//�A�J�E���g�쐬
	public boolean accountCreate(String user_name, String password) {
		

		try {     
		//	ArrayList<Player> array = new ArrayList<Player>();
			//FileInputStream�I�u�W�F�N�g�̐���
            FileInputStream inFile = new FileInputStream("players.obj");
            //ObjectInputStream�I�u�W�F�N�g�̐���
            //�I�u�W�F�N�g�̓ǂݍ���
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
            
            //FileOutputStream�I�u�W�F�N�g�̐���
            FileOutputStream outFile = new FileOutputStream("players.obj",true);
            //ObjectOutputStream�I�u�W�F�N�g�̐���
            ObjectOutputStream outObject = new ObjectOutputStream(outFile);
            //�N���XHello�̃I�u�W�F�N�g�̏�������
            outObject.writeObject(new Player(user_name,password));
   
            outObject.close();
       }
       catch(Exception e) {
    	   e.printStackTrace();
       }
           return true;
	}
}

	