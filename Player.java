package ohtello;
import java.io.Serializable;

public class Player implements Serializable {               //�N���XPlayer:�v���C���[�̏�������
	private static final long serialVersionUID = 1L;
	  private String myName = "";       //�v���C����
	  private String myColor = "";      //�������
	  private int[] results = {0,0,0,0};
	  private  String password;
	  //������:0
	  //�s�k��:1
	  //����������:2
	  //������:3
	  private double myRate = 0;           //���[�g

	  public Player(String user_name, String password){
	    this.myName = user_name;
	    this.password = password;
	  }

	  public void setName(String name) {    //�v���C�������󂯕t����
	    myName = name;
	  }

	  public String getName() {             //�v���C�������擾����
	    return myName;
	  }
	 
	  public String getPassword() { 
	    return password;
	  }

  public void setColor(String color) {  //���������󂯕t����
    myColor = color;
  }

  public String getColor() {              //���������擾����
    return myColor;
  }

  public void setWin(int win) {           //���������󂯕t����
    results[0] = win;
  }

  public int getWin() {                   //���������擾����
    return results[0];
  }

  public void setDefeat(int defeat) {     //�s�k�����󂯕t����
    results[1] = defeat;
  }

  public int getDefeat() {                //�s�k�����擾����
    return results[1];
  }

  public void setDraw(int draw) {         //�������������󂯕t����
    results[2] = draw;
  }

  public int getDraw() {                  //�������������擾����
    return results[2];
  }

  public void setSurrender(int surrender) {//���������󂯕t����
    results[3] = surrender;
  }

  public int getSurrender() {             //���������擾����
    return results[3];
  }

  public void setRate(double rate) {       //���[�g���󂯕t����
    myRate = rate;
  }

  public double getRate() {                 //���[�g���擾����
    return myRate;
  }
}
