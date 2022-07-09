package TEST;

import java.util.Scanner;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/23 19:23
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //String t = sc.next().toUpperCase();
        //String p = sc.next().toUpperCase();
        String t = "AVERDXIVYERDXIAN";
        String p = "RDXII";
        int res = -1;

        int length_t = t.length();
        int length_p = p.length();
        if (length_t < length_p) System.out.println("No");
        res = t.indexOf(p);
        if (res!=-1) System.out.println(res+1);
        else System.out.println("No");
    }
}
