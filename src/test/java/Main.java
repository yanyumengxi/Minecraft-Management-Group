import com.tucker.admingroup.utils.BinaryUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static String get(String urlStr) {
        StringBuilder res = new StringBuilder();
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String str = "";
                while ((str = reader.readLine()) != null) {
                    res.append(str);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(res).replace(" ", "");
    }

    public static void main(String[] args) {
//        System.out.println("请输入需要转换的字符串(必须位纯英文):");
//        Scanner s = new Scanner(System.in);
//        StringBuilder res = new StringBuilder();
//        for (char c : s.next().toCharArray()) {
//            res.append(getpy(String.valueOf(c)));
//        }
//        System.out.println(res.substring(0, 7));
//        String data = "68747470733a2f2f79616e79756d656e6778692e6769746875622e696f2f436c69656e74757064617465536572766963652f7570646174652e6a736f6e==";
//        String newestVersion = get(BinaryUtils.hexStrStr(data.substring(0, data.length() - 2)));
//        System.out.println(newestVersion);
        StringBuilder sss = new StringBuilder();
        sss.append("111,");
        sss.append("222");
        System.out.println(String.valueOf(sss));
    }

    public static int getpy(String s){
        int res = -1;
        switch (s){
            case "a":
                res = 0;
                break;
            case "b":
                res = 1;
                break;
            case "c":
                res = 2;
                break;
            case "d":
                res = 3;
                break;
            case "e":
                res = 4;
                break;
            case "f":
                res = 5;
                break;
            case "g":
                res = 6;
                break;
            case "h":
                res = 7;
                break;
            case "i":
                res = 8;
                break;
            case "j":
                res = 9;
                break;
            case "k":
                res = 10;
                break;
            case "l":
                res = 11;
                break;
            case "m":
                res = 12;
                break;
            case "n":
                res = 13;
                break;
            case "o":
                res = 14;
                break;
            case "p":
                res = 15;
                break;
            case "q":
                res = 16;
                break;
            case "r":
                res = 17;
                break;
            case "s":
                res = 18;
                break;
            case "t":
                res = 19;
                break;
            case "u":
                res = 20;
                break;
            case "v":
                res = 21;
                break;
            case "w":
                res = 22;
                break;
            case "x":
                res = 23;
                break;
            case "y":
                res = 24;
                break;
            case "z":
                res = 25;
                break;
        }
        return res;
    }


    /**
     * 字符串转换成为16进制(无需Unicode编码)
     * @param str
     * @return
     */
    public  static  String strToStrOneSix(String str) {
        char [] chars =  "0123456789abcdefghigklmnopqrstuvwxyz" .toCharArray();
        StringBuilder sb =  new  StringBuilder( "" );
        byte [] bs = str.getBytes();
        int  bit;
        for  ( int  i =  0 ; i < bs.length; i++) {
            bit = (bs[i] &  0x0f0 ) >>  4 ;
            sb.append(chars[bit]);
            bit = bs[i] &  0x0f ;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return  sb.toString().trim();
    }

    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public  static  String strOneSixToStr(String hexStr) {
//        String str =  "0123456789ABCDEF" ;
        String str =  "0123456789abcdefghigklmnopqrstuvwxyz" ;
        char [] hexs = hexStr.toCharArray();
        byte [] bytes =  new  byte [hexStr.length() /  2 ];
        int  n;
        for  ( int  i =  0 ; i < bytes.length; i++) {
            n = str.indexOf(hexs[ 2  * i]) *  16 ;
            n += str.indexOf(hexs[ 2  * i +  1 ]);
            bytes[i] = ( byte ) (n &  0xff );
        }
        return  new  String(bytes);
    }
}
