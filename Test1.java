package ohtello;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test1 {

	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Test1 obj = new Test1();
		
		if(obj.accountCreate("takuyta", "password"))
			System.out.println("ok");
		else 
			System.out.println("no");
		
	}
	//�A�J�E���g�쐬
	public boolean accountCreate(String user_name, String password) {

		try {     
			
			//FileInputStream�I�u�W�F�N�g�̐���
            FileInputStream inFile = new FileInputStream("players.obj");
            //ObjectInputStream�I�u�W�F�N�g�̐���
            ObjectInputStream inObject = new ObjectInputStream(inFile);
            //�I�u�W�F�N�g�̓ǂݍ���
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

            //FileOutputStream�I�u�W�F�N�g�̐���
            FileOutputStream outFile = new FileOutputStream("players.obj");
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

	