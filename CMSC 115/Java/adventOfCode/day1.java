package adventOfCode;
import java.io.*;

public class day1 {
    public static void main(String[] args) throws Exception{
        FileReader file = new FileReader(
            "C:\\Users\\danny\\OneDrive\\Desktop\\Algo\\School-repo\\CMSC 115\\Java\\adventOfCode\\day1.txt");
        int i;
        int line = 0;
        int totalSum = 0;
        String str = "";
        while ((i = file.read()) != -1) {
            //System.out.print((char)i + " " + i + " : ");
            if (i > 47 && i < 58) {
                str += (char)i;
            }
            if (i == 13) {
                line ++;
                if (str.length() > 1)
                    str = "" + str.charAt(0) + str.charAt(str.length() - 1);
                else if (str.length() == 1)
                    str = "" + str.charAt(0) + str.charAt(0);
                System.out.println("row " + line + " number is " + str);
                totalSum += Integer.parseInt(str);
                str = "";
            }
            //System.out.println(str);
        }

        System.out.println("total sum is: " + totalSum);
        file.close();
    }
}
