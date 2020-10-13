import java.util.*;
public class Playfair{
  private static char[][] decodekey = new char[5][5];
  private static ArrayList<String> letterPair = new ArrayList<String>();
  private static String preparedtoencode = "";
  private static String decodedmessage = "";
  //encode setup
  public static void putX(int index, String input,String originalinput){
    if(input.length() == 1) preparedtoencode += input;
    else if(index >= originalinput.length()-1);
    else{
      boolean uhoh = false;
      int count = 0;
      for(int i = 0; i < input.length(); i+=2){
        uhoh = false;
        if (input.length() - (count * 2) == -1) preparedtoencode += input.substring(input.length());
        if(uhoh) break;
        if(Character.valueOf(input.charAt(i)).equals(input.charAt(i+1))){
          preparedtoencode += input.charAt(i) + "X";
          index = i + 1;
          uhoh = true;
        }
        else{
          preparedtoencode += input.substring(i,i+2);
          index += 2;
          count += 1;
        }

        if (index == originalinput.length()-1){
          preparedtoencode += originalinput.charAt(index) + "";
          break;
        }
        else if (index > originalinput.length()-1) break;
        else if (uhoh){
          putX(index, input.substring(index),input);
          break;
        }
      }
    }
  }

  public static void putZ(){
    if(preparedtoencode.length() % 2 == 1) preparedtoencode += "Z";
  }

  public static void decode(){
    int firstrow = 0;
    int firstcolumn = 0;
    int secondrow = 0;
    int secondcolumn = 0;
    for(int i = 0; i < letterPair.size();i++){
      for(int t = 0; t < 5; t++){
        for(int y = 0; y < 5; y++){
          if (letterPair.get(i).charAt(0) == decodekey[t][y]){
            firstrow = t;
            firstcolumn = y;
          }
          if(letterPair.get(i).charAt(1) == decodekey[t][y]){
            secondrow = t;
            secondcolumn = y;
          }
        }
      }
      if (firstrow == secondrow) verticalDecode(letterPair.get(i));
      else if (firstcolumn == secondcolumn) horizontalDecode(letterPair.get(i));
      else regularEncode(letterPair.get(i));
    }
  }

  public static void verticalDecode(String pair){
    int row = 0;
    int columnhey = 0;
    int columnthere = 0;
    char hey = pair.charAt(0);
    char there = pair.charAt(1);
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        if (decodekey[i][j] == hey){
          row = i;
          columnhey = j;
        }
        if (decodekey[i][j] == there) columnthere = j;
      }
    }
    if (row == 0) decodedmessage += decodekey[4][columnhey] + "" + decodekey[4][columnthere];
    else decodedmessage += decodekey[row - 1][columnhey] + "" + decodekey[row - 1][columnthere];
  }
  public static void horizontalDecode(String pair){
    int column = 0;
    int rowhey = 0;
    int rowthere = 0;
    char hey = pair.charAt(0);
    char there = pair.charAt(1);
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        if (decodekey[i][j] == hey){
          column = j;
          rowhey = i;
        }
        if (decodekey[i][j] == there) rowthere = i;
      }
    }
    if(column == 0) decodedmessage += decodekey[rowhey][4] + "" + decodekey[rowthere][4];
    else decodedmessage += decodekey[rowhey][column - 1] + "" + decodekey[rowthere][column - 1];
  }
//vertical, horizontal, or regular
  public static void encode(){
    int firstrow = 0;
    int firstcolumn = 0;
    int secondrow = 0;
    int secondcolumn = 0;
    for(int i = 0; i < letterPair.size();i++){
      for(int t = 0; t < 5; t++){
        for(int y = 0; y < 5; y++){
          if (letterPair.get(i).charAt(0) == decodekey[t][y]){
            firstrow = t;
            firstcolumn = y;
          }
          if(letterPair.get(i).charAt(1) == decodekey[t][y]){
            secondrow = t;
            secondcolumn = y;
          }
        }
      }
      if (firstrow == secondrow) verticalEncode(letterPair.get(i));
      else if (firstcolumn == secondcolumn) horizontalEncode(letterPair.get(i));
      else regularEncode(letterPair.get(i));
    }
  }

  //same row
  public static void verticalEncode(String pair){
    int row = 0;
    int columnhey = 0;
    int columnthere = 0;
    char hey = pair.charAt(0);
    char there = pair.charAt(1);
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        if (decodekey[i][j] == hey){
          row = i;
          columnhey = j;
        }
        if (decodekey[i][j] == there) columnthere = j;
      }
    }
    if (row == 4) decodedmessage += decodekey[0][columnhey] + "" + decodekey[0][columnthere];
    else decodedmessage += decodekey[row + 1][columnhey] + "" + decodekey[row + 1][columnthere];
  }
  //same column
  public static void horizontalEncode(String pair){
    int column = 0;
    int rowhey = 0;
    int rowthere = 0;
    char hey = pair.charAt(0);
    char there = pair.charAt(1);
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        if (decodekey[i][j] == hey){
          column = j;
          rowhey = i;
        }
        if (decodekey[i][j] == there) rowthere = i;
      }
    }
    if(column == 4) decodedmessage += decodekey[rowhey][0] + "" + decodekey[rowthere][0];
    else decodedmessage += decodekey[rowhey][column + 1] + "" + decodekey[rowthere][column + 1];
  }
  //regular
  public static void regularEncode(String pair){
    int rowhey = 0;
    int columnhey = 0;
    int rowthere = 0;
    int columnthere = 0;
    char hey = pair.charAt(0);
    char there = pair.charAt(1);
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        if(decodekey[i][j] == hey){
          rowhey = i;
          columnhey = j;
        }
        if(decodekey[i][j] == there){
          rowthere = i;
          columnthere = j;
        }
      }
    }
    decodedmessage += decodekey[rowhey][columnthere] + "" + decodekey[rowthere][columnhey];
  }
  public static void getPairs(String input){
    for(int i = 0 ; i < input.length()-1;i+=2){
      letterPair.add(input.substring(i,i+2));
    }
  }
  public static void main(String [] args){
    String method = args[0];
    String prepremessage = args[1];
    String prekeyinput = args[2];
    String premessage = prepremessage.toUpperCase();
    String keyinput = prekeyinput.toUpperCase();
    String key1 = keyinput.substring(0,5);
    String key2 = keyinput.substring(5,10);
    String key3 = keyinput.substring(10,15);
    String key4 = keyinput.substring(15,20);
    String key5 = keyinput.substring(20);
    //set up the key
    for(int i = 0; i < 5; i++){
      decodekey[0][i] = key1.charAt(i);
    }
    for(int i = 0; i < 5; i++){
      decodekey[1][i] = key2.charAt(i);
    }
    for(int i = 0; i < 5; i++){
      decodekey[2][i] = key3.charAt(i);
    }
    for(int i = 0; i < 5; i++){
      decodekey[3][i] = key4.charAt(i);
    }
    for(int i = 0; i < 5; i++){
      decodekey[4][i] = key5.charAt(i);
    }
    String message = "";
    for(int i = 0; i < premessage.length(); i++){
      if (premessage.charAt(i) == 'J') message += "I";
      else message += premessage.charAt(i);
    }
    putX(0, message,message);
    putZ();
    getPairs(preparedtoencode);
    if(method.charAt(0) == 'e'){
      encode();
    }
    else{
      decode();
    }
    System.out.println(decodedmessage);
  }
}
