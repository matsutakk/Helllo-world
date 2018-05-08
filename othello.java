package ohtello;

public class othello{
  private int row = 8; //�I�Z���̏c���̃}�X�̐�
  private int[] grids = new int[row*row]; //�ǖʏ��--�����u����Ă��Ȃ�:0 ��:1 ��:2
  private String turn; //���(black,white)

  public othello() { //�R���X�g���N�^
    turn = "black";  //���͍�
    for(int i=0; i < row*row; i++ ) {
      grids[i] = 0;
    }
    int center = row*row/2;  //�܂�32
    grids[center - row/2 - 1] = 1;  //��
    grids[center + row/2    ] = 1;  //��
    grids[center - row/2    ] = 2;  //��
    grids[center + row/2 - 1] = 2;  //��
  }

  /*******************************���\�b�h**************************************/


  public String checkWinner() {  //���s���ʂ𔻒f
    int black_num = 0;
    for(int i=0; i<row*row; i++) {
      if(grids[i] == 1) {  //�ǖʂ�����������
        black_num++;       //�J�E���g���Ă���
      }
    }

    if(black_num < 32) {         //�������Ȃ��Ƃ�
      return "white";
    } else if(black_num > 32) {  //���������Ƃ�
      return "black";
    } else {                     //���Ɣ��������̂Ƃ�
      return "draw";             //"draw"��Ԃ�
    }
  }

  public String getTurn() {  //��Ԃ��擾--"black","white"��Ԃ�
    return turn;
  }

  public int[] getGrids() {  //�ǖʏ����擾
    return grids;
  }

  public void chengeTurn() {  //��Ԃ�ύX
    if(turn.equals("black") == true) {  //����������
      turn = "white";                   //���ɕύX
    } else {
      turn = "black";                   //�����łȂ���΍��ɕύX
    }
  }

  public boolean isGameover(int[] grids) {  //�΋ǏI���𔻒f
    int isContinue = 0;  //�΂��u����Ƃ� =1
    for(int mass_i = 0; mass_i < 64; mass_i++) {
      if(grids[mass_i] != 0) {  //�ǖʂɐ΂���������
        System.out.println("���łɐ΂��u����Ă���");
        continue;      //���̃��[�v�ł́A�ȍ~�̏������Ƃ΂�
      }

      int colorInt = 0;  //�΂̐F��Integer�^�ɒ���
      if(color.equals("black") ==true) {
        colorInt = 1;  //���Ȃ�1
      } else {
        colorInt = 2;  //���Ȃ�2
      }
      int swiStone = 0;  //�Ԃ�������̂����̐�

      int line = i/8;  //�s��
      int row = i%8;  //��

      int sFlag = 0;  //�e�����ɂ��āA�����̐΂Ƃ͂��߂邩�ǂ���--�͂��߂���sFlag=1
      int ssFlag = 0;  //sFlag=1�ƂȂ�ꍇ�AssFlag=1

      int cl = 0;  //�ړ���̈ʒu
      int cr = 0;
      int dl = 0;  //�͂��ޖ����̐΂̈ʒu
      int dr = 0;
      int nStone = 0;
      if(colorInt == 1) {  //����������������
  /********�������***************************************************************/
        cl = line - 1;
        cr = row - 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cl--;
            cr--;
            while(cl>=0 && cr>=0) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl--;
              cr--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line - 1;
              cr = row -1;
              while(dl<cl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl--;
                cr--;
              }
            }
          }
        }

  /********�E�����***************************************************************/
        cl = line - 1;
        cr = row + 1;
        nStone = cl*8 +cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cl--;
            cr++;
            while(cl>=0 && cr<=7) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl--;
              cr++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line - 1;
              cr = row + 1;
              while(dl<cl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl--;
                cr--;
              }
            }
          }
        }
    /********��������***************************************************************/

        cl = line + 1;
        cr = row - 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cl++;
            cr--;
            while(cl<=7 && cr>=0) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl++;
              cr--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line + 1;
              cr = row -1;
              while(cl<dl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl++;
                cr--;
              }
            }
          }
        }
  /********�E������***************************************************************/
        cl = line + 1;
        cr = row + 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cl++;
            cr++;
            while(cl<=7 && cr<=7) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl++;
              cr++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line + 1;
              cr = row + 1;
              while(cl<dl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl++;
                cr++;
              }
            }
          }
        }

  /********�����***************************************************************/
        cl = line - 1;
        cr = row;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cl--;
            while(cl>=0) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line - 1;
              cr = row;
              while(dl<cl) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl--;
              }
            }
          }
        }

  /********������***************************************************************/
        cl = line + 1;
        cr = row;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cl++;
            while(cl<=7) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line + 1;
              cr = row;
              while(cl<dl) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl++;
              }
            }
          }
        }
  /********������***************************************************************/
        cl = line;
        cr = row - 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cr--;
            while(cr>=0) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cr--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line;
              cr = row -1;
              while(dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cr--;
              }
            }
          }
        }
  /********�E����***************************************************************/
        cl = line;
        cr = row + 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
            cr++;
            while(cr<=7) {
              if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cr++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line;
              cr = row + 1;
              while(cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cr++;
              }
            }
          }
        }
      } else if(colorInt == 2) {             //����������������
  /********�������***************************************************************/
        cl = line - 1;
        cr = row - 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cl--;
            cr--;
            while(cl>=0 && cr>=0) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl--;
              cr--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line - 1;
              cr = row -1;
              while(dl<cl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl--;
                cr--;
              }
            }
          }
        }
    /********�E�����***************************************************************/
        cl = line - 1;
        cr = row + 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cl--;
            cr++;
            while(cl>=0 && cr<=7) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl--;
              cr++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line - 1;
              cr = row + 1;
              while(dl<cl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl--;
                cr++;
              }
            }
          }
        }

  /********��������***************************************************************/

        cl = line + 1;
        cr = row - 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cl++;
            cr--;
            while(cl<=7 && cr>=0) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl++;
              cr--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line + 1;
              cr = row -1;
              while(cl<dl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl++;
                cr--;
              }
            }
          }
        }

  /********�E������***************************************************************/
        cl = line + 1;
        cr = row + 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<= 63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cl++;
            cr++;
            while(cl<=7 && cr<=7) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl++;
              cr++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line + 1;
              cr = row + 1;
              while(cl<dl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl++;
                cr++;
              }
            }
          }
        }

  /********�����***************************************************************/
        cl = line - 1;
        cr = row;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cl--;
            while(cl>=0) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line - 1;
              cr = row;
              while(dl<cl) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl--;
              }
            }
          }
        }
  /********������***************************************************************/
        cl = line + 1;
        cr = row;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cl++;
            while(cl<=7) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cl++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line + 1;
              cr = row;
              while(cl<dl) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cl++;
              }
            }
          }
        }
  /********������***************************************************************/
        cl = line;
        cr = row - 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone <=63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cr--;
            while(cr>=0) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cr--;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line;
              cr = row -1;
              while(dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cr--;
              }
            }
          }
        }
  /********�E����***************************************************************/
        cl = line;
        cr = row + 1;
        nStone = cl*8 + cr;
        if(0<=nStone && nStone<=63) {
          if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
            cr++;
            while(cr<=7) {
              if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
                dl = cl;
                dr = cr;
                sFlag = 1;
                break;  //�����Ƃ��āA���[�v�𔲂���
              }
              if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
                break;  //���s�Ƃ��āA���[�v�𔲂���
              }
              cr++;
            }
            if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
              sFlag = 0;
              ssFlag = 1;
              cl = line;
              cr = row + 1;
              while(cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
                //grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
                swiStone++;  //�Ԃ����΂��J�E���g
                cr++;
              }
            }
          }
        }

      }

      if(swiStone == 0) {  //�Ԃ����΂̐����O�̂Ƃ��Afalse��Ԃ� = �΂��u���Ȃ�
        System.out.println("�Ԃ���΂�����܂���");
        return false;
      }
      /*
      if(colorInt == 1) {  //����������
        grids[i] = 1;
      } else if(colorInt == 2) {  //����������
        grids[i] = 2;
      }
      */
      return true;  //�u����΂�����ꍇ
    }
  }


  public boolean putStone(int i, String color) {  //�ǖʂɑ���𔽉f
    if(i<0 || 63<i) {  //�ǖʊO�Ȃ�
      System.out.println("�ǖʊO");
      return false;          //false��Ԃ�
    }
    if(grids[i] != 0) {  //�ǖʂɐ΂���������
      System.out.println("���łɐ΂��u����Ă���");
      return false;      //false��Ԃ�
    }

    int colorInt = 0;  //�΂̐F��Integer�^�ɒ���
    if(color.equals("black") ==true) {
      colorInt = 1;  //���Ȃ�1
    } else {
      colorInt = 2;  //���Ȃ�2
    }
    int swiStone = 0;  //�Ԃ�������̂����̐�

    int line = i/8;  //�s��
    int row = i%8;  //��

    int sFlag = 0;  //�e�����ɂ��āA�����̐΂Ƃ͂��߂邩�ǂ���--�͂��߂���sFlag=1
    int ssFlag = 0;  //sFlag=1�ƂȂ�ꍇ�AssFlag=1

    int cl = 0;  //�ړ���̈ʒu
    int cr = 0;
    int dl = 0;  //�͂��ޖ����̐΂̈ʒu
    int dr = 0;
    int nStone = 0;
    if(colorInt == 1) {  //����������������
/********�������***************************************************************/
      cl = line - 1;
      cr = row - 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cl--;
          cr--;
          while(cl>=0 && cr>=0) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl--;
            cr--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line - 1;
            cr = row -1;
            while(dl<cl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl--;
              cr--;
            }
          }
        }
      }

/********�E�����***************************************************************/
      cl = line - 1;
      cr = row + 1;
      nStone = cl*8 +cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cl--;
          cr++;
          while(cl>=0 && cr<=7) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl--;
            cr++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line - 1;
            cr = row + 1;
            while(dl<cl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl--;
              cr--;
            }
          }
        }
      }
  /********��������***************************************************************/

      cl = line + 1;
      cr = row - 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cl++;
          cr--;
          while(cl<=7 && cr>=0) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl++;
            cr--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line + 1;
            cr = row -1;
            while(cl<dl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl++;
              cr--;
            }
          }
        }
      }
/********�E������***************************************************************/
      cl = line + 1;
      cr = row + 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cl++;
          cr++;
          while(cl<=7 && cr<=7) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl++;
            cr++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line + 1;
            cr = row + 1;
            while(cl<dl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl++;
              cr++;
            }
          }
        }
      }

/********�����***************************************************************/
      cl = line - 1;
      cr = row;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cl--;
          while(cl>=0) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line - 1;
            cr = row;
            while(dl<cl) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl--;
            }
          }
        }
      }

/********������***************************************************************/
      cl = line + 1;
      cr = row;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cl++;
          while(cl<=7) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line + 1;
            cr = row;
            while(cl<dl) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl++;
            }
          }
        }
      }
/********������***************************************************************/
      cl = line;
      cr = row - 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cr--;
          while(cr>=0) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cr--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line;
            cr = row -1;
            while(dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cr--;
            }
          }
        }
      }
/********�E����***************************************************************/
      cl = line;
      cr = row + 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 2) {  //�ׂ�����̐΂������ꍇ�̂�
          cr++;
          while(cr<=7) {
            if(grids[cl*8 + cr] == 1) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cr++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line;
            cr = row + 1;
            while(cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 1;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cr++;
            }
          }
        }
      }
    } else if(colorInt == 2) {             //����������������
/********�������***************************************************************/
      cl = line - 1;
      cr = row - 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cl--;
          cr--;
          while(cl>=0 && cr>=0) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl--;
            cr--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line - 1;
            cr = row -1;
            while(dl<cl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl--;
              cr--;
            }
          }
        }
      }
  /********�E�����***************************************************************/
      cl = line - 1;
      cr = row + 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cl--;
          cr++;
          while(cl>=0 && cr<=7) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl--;
            cr++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line - 1;
            cr = row + 1;
            while(dl<cl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl--;
              cr++;
            }
          }
        }
      }

/********��������***************************************************************/

      cl = line + 1;
      cr = row - 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cl++;
          cr--;
          while(cl<=7 && cr>=0) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl++;
            cr--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line + 1;
            cr = row -1;
            while(cl<dl && dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl++;
              cr--;
            }
          }
        }
      }

/********�E������***************************************************************/
      cl = line + 1;
      cr = row + 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<= 63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cl++;
          cr++;
          while(cl<=7 && cr<=7) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl++;
            cr++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line + 1;
            cr = row + 1;
            while(cl<dl && cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl++;
              cr++;
            }
          }
        }
      }

/********�����***************************************************************/
      cl = line - 1;
      cr = row;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cl--;
          while(cl>=0) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line - 1;
            cr = row;
            while(dl<cl) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl--;
            }
          }
        }
      }
/********������***************************************************************/
      cl = line + 1;
      cr = row;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cl++;
          while(cl<=7) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cl++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line + 1;
            cr = row;
            while(cl<dl) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cl++;
            }
          }
        }
      }
/********������***************************************************************/
      cl = line;
      cr = row - 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone <=63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cr--;
          while(cr>=0) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cr--;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line;
            cr = row -1;
            while(dr<cr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cr--;
            }
          }
        }
      }
/********�E����***************************************************************/
      cl = line;
      cr = row + 1;
      nStone = cl*8 + cr;
      if(0<=nStone && nStone<=63) {
        if(grids[cl*8 + cr] == 1) {  //�ׂ�����̐΂������ꍇ�̂�
          cr++;
          while(cr<=7) {
            if(grids[cl*8 + cr] == 2) {  //�����̐΂ŋ��߂��Ƃ�
              dl = cl;
              dr = cr;
              sFlag = 1;
              break;  //�����Ƃ��āA���[�v�𔲂���
            }
            if(grids[cl*8 + cr] == 0) {  //�}�X�ɐ΂��Ȃ�������
              break;  //���s�Ƃ��āA���[�v�𔲂���
            }
            cr++;
          }
          if(sFlag == 1) { //����:���߂��Ƃ�sFlag=1    ���s:�������Ȃ����� or �}�X�ɐ΂��Ȃ�����
            sFlag = 0;
            ssFlag = 1;
            cl = line;
            cr = row + 1;
            while(cr<dr) {  //�͂��񂾐΂�Ԃ����[�v
              grids[cl*8 + cr] = 2;  //�����̐F�̐΂ɂ���
              swiStone++;  //�Ԃ����΂��J�E���g
              cr++;
            }
          }
        }
      }

    }

    if(swiStone == 0) {  //�Ԃ����΂̐����O�̂Ƃ��Afalse��Ԃ�
      System.out.println("�Ԃ���΂�����܂���");
      return false;
    }
    if(colorInt == 1) {  //����������
      grids[i] = 1;
    } else if(colorInt == 2) {  //����������
      grids[i] = 2;
    }
    return true;
  }
}
