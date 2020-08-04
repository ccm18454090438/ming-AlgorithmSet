import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
//SM3 password hashing algorithm Java implementation
public class SM3 {

  private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
      '9', 'A', 'B', 'C', 'D', 'E', 'F'};
  private static final  String IV ="7380166f 4914b2b9 172442d7 da8a0600 a96f30bc 163138aa e38dee4d b0fb0e4e";
  private static final BigInteger V0 = new BigInteger(IV.replaceAll(" ",
      ""), 16);

  private static final int[] Tj={0x79cc4519,0x7a879d8a};


  static int k,l;

  //Left shift method
  public static int leftByte(int x,int l) {
    //X is the term, and the left shift is l
    return Integer.rotateLeft(x, l);
  }


  private static int getTj(int j){
    if (j>=0 && j<=15){
      return Tj[0];
    }
    if (j>=16 && j<=63){
      return Tj[1];
    }

    return -1;//error
  }

  //Boolean function
  private static int FFj(int X,int Y,int Z,int j){
    if (j>=0 && j<=15){
      return Integer.valueOf(X^Y^Z);
    }
    if (j>=16 && j<=63){
      return Integer.valueOf((X & Y)|(X & Z)|(Y & Z));
    }
    return -1;
  }

  //Boolean function
  private static int GGj(int X,int Y,int Z,int j){
    if (j>=0 && j<=15){
      return Integer.valueOf(X^Y^Z);
    }
    if (j>=16 && j<=63){
      return Integer.valueOf((X & Y)|(~X & Z));
    }

    return -1;
  }


  private static int  P0(int X){
    return X^ leftByte(X,9)^ leftByte(X,17);
  }


  private static int  P1(int X){
    return X^ leftByte(X,15)^ leftByte(X,23);
  }


  //fill
  private static String fill(String init){
    int  l=init.length()*8;//The bit size of the original string
    SM3.l=l;
    int k=0;
    for (;;){
      if ((l+k+1-448)%512==0){
        break;
      }
      k++;

    }//For k
    SM3.k=k;
    String to= stringToByte(init);//Get bit representation
    //It's represented as bits
    to+="1";
    for (int i=0;i<k;i++){
      to+="0";
    }//fill
    to+= intToBinary((char)l,64);
    return to;//Returns the populated string
  }

  //String becomes binary
  private static String stringToByte(String be){
    char[] a=be.toCharArray();
    String to="";
    for (int i=0;i<a.length;i++){
      to=to+ intToBinary(a[i],8);
    }
    return to;
  }

  //The integer becomes binary and l is the length of the binary
  private static String intToBinary(int a,int l){
    int b=a;
    String to="";
    for (int i=0;i<l;i++){
      if ((b & 1)==1){
        to="1"+to;
      }else {
        to="0"+to;
      }
      b>>=1;
    }
    return to;
  }


  private static byte[] compress(String be) throws IOException {
    //Compression method
    //grouping
    int n=(l+k+65)/512;
    char[][] B=new char[n][512];
    for (int i=0;i<n;i++){
      B[i]=be.substring(i*512,i*512+512).toCharArray();
    }
    byte[] V=V0.toByteArray();
    //Packet to complete
    byte[] vv=null;
    for ( int i=0;i<n;i++){
      vv=CF(V,String.valueOf(B[i]));//Iterative compression
      V=vv;
    }
    return  vv;
  }


  private static int stringBinaryToInt(String a){
    return (int) Long.parseLong(a, 2);
  }


  private static int toInteger(byte[] source, int index) {
    StringBuilder valueStr = new StringBuilder("");
    for (int i = 0; i < 4; i++) {
      valueStr.append(hexDigits[(byte) ((source[index * 4 + i] & 0xF0) >> 4)]);
      valueStr.append(hexDigits[(byte) (source[index * 4 + i] & 0x0F)]);
    }
    return Long.valueOf(valueStr.toString(), 16).intValue();

  }

  private static byte[] CF(byte[] V,String B) throws IOException {
    //Message to expand
    String [] w=new String[68];
    String [] W=new String[64];
    for (int i=0;i<16;i++){
      w[i]=B.substring(i*32,i*32+32);
    }
    for (int i=16;i<68;i++){
      int as= leftByte(stringBinaryToInt(w[i-3]),15);
      int a=P1(
          stringBinaryToInt(w[i-16]) ^ stringBinaryToInt(w[i-9])^ leftByte(stringBinaryToInt(w[i-3]),15)) ^ leftByte(
          stringBinaryToInt(w[i-13]),7) ^ stringBinaryToInt(w[i-6]);
      w[i]= intToBinary(a,32);
    }
    for (int j=0;j<64;j++){
      int  a= stringBinaryToInt(w[j])^ stringBinaryToInt(w[j+4]);
      W[j]= intToBinary(a,32);
    }
    //Message expansion completed
    int [] ABC=new int[8];
    for (int i=0;i<8;i++){
      ABC[i]=toInteger(V,i);
    }
    int   SS1,SS2,TT1,TT2;
    for (int j=0;j<64;j++){
      SS1 = Integer
          .rotateLeft(
              Integer.rotateLeft(ABC[0], 12) + ABC[4]
                  + Integer.rotateLeft(getTj(j), j), 7);
      SS2=SS1 ^ leftByte(ABC[0],12);
      TT1=FFj(ABC[0],ABC[1],ABC[2],j)+ABC[3]+SS2+ stringBinaryToInt(W[j]);
      TT2=GGj(ABC[4],ABC[5],ABC[6],j)+ABC[7]+SS1+ stringBinaryToInt(w[j]);
      ABC[3]=ABC[2];
      ABC[2]= leftByte(ABC[1],9);
      ABC[1]=ABC[0];
      ABC[0]=TT1;
      ABC[7]=ABC[6];
      ABC[6]= leftByte(ABC[5],19);
      ABC[5]=ABC[4];
      ABC[4]=P0(TT2);
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
    for (int i=0;i<ABC.length;i++){
      baos.write(intToByteArray(ABC[i]));
    }
    byte[] vo = baos.toByteArray();
    for (int i = 0; i <vo.length; i++) {
      vo[i] = (byte) (vo[i] ^ V[i]);
    }
    return vo;
  }

  private static byte[] intToByteArray(int i) {
    byte[] byteArray = new byte[4];
    byteArray[0] = (byte) (i >>> 24);
    byteArray[1] = (byte) ((i & 0xFFFFFF) >>> 16);
    byteArray[2] = (byte) ((i & 0xFFFF) >>> 8);
    byteArray[3] = (byte) (i & 0xFF);
    return byteArray;
  }



  public static String   getSM3(String  X) throws IOException {
    String to= fill(X);
    //fill
    byte[]  V= compress(to);
    //compression

    return  SM3.byteArrayToHexString(V);
  }

  private static String byteArrayToHexString(byte[] b) {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      resultSb.append(byteToHexString(b[i]));
    }
    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0)
      n = 256 + n;
    int d1 = n / 16;
    int d2 = n % 16;
    return ""+hexDigits[d1] + hexDigits[d2];
  }

}

