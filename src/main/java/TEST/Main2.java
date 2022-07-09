package TEST;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //String target = sc.next();
        //String source = sc.next();
        String target = "abc";
        String source = "abcaybec";
        int res = -1;

        int startIndex = source.indexOf(target.charAt(0));
        int length = source.length();
        int count = 1;
        int out = 0;
        for (int i = 1; i < target.length(); i++) {
            for (int j = startIndex+1; j < length; j++) {
                if (source.charAt(j) == target.charAt(i)) {
                    count++;
                    startIndex=j;
                    if (startIndex==length) startIndex--;
                    if (count==target.length()) {
                        res = j-i-out;
                        count=0;
                        i=-1;
                        out=0;
                    }
                    break;
                } else out++;
            }
        }

        System.out.println(res);
    }

}
