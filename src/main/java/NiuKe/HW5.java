package NiuKe;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 十六进制转十进制
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 8:56
 */
public class HW5 {
    //16进制数的第0位的权值为16的0次方，第1位的权值为16的1次方，第2位的权值为16的2次方……
    /*
    例：2AF5换算成10进制:
    用竖式计算：
　　第0位： 5 * 16^0 = 5
　　第1位： F * 16^1 = 240
　　第2位： A * 16^2= 2560
　　第3位： 2 * 16^3 = 8192 +
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //String str = in.nextLine();
        String str = "0x2C";
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('A',10);
        map.put('B',11);
        map.put('C',12);
        map.put('D',13);
        map.put('E',14);
        map.put('F',15);

        // 0x开头，需要去除
        str = str.replaceFirst("0x","");

        int length = str.length();
        int result = 0;
        int n = 0; // 次方
        for (int i = length-1; i >= 0; i--,n++) {
            // Math.pow() 返回第一个参数的第二个参数的次方值
            char c = str.charAt(i);
            if (map.containsKey(c))
                result+=(map.get(c)*Math.pow(16,n));
            else {
                // 转为数字
                int integer = Integer.valueOf(String.valueOf(c));
                result+=(integer*Math.pow(16,n));
            }
        }


        System.out.println(result);


        /** 其他解法 */
        //Scanner sc = new Scanner(System.in);
        //while(sc.hasNextLine()){
            //String str = sc.nextLine();
            String num = str.substring(2,str.length());
            System.out.println(Integer.parseInt(num,16));
        //}
    }
}


