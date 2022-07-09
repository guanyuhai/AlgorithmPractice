package TEST;

import java.util.Scanner;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/23 20:32
 */
public class Main3 {
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int seatNum = sc.nextInt();
        sc.nextLine();
        String seatOrLeaveLine = sc.nextLine();
        String[] c = seatOrLeaveLine.substring(1,seatOrLeaveLine.length()-1).split(", ");
        int[] seatOrLeave = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            seatOrLeave[i] = Integer.parseInt(c[i]);
        }

        Main socialDistance = new Main();
        int ans = conferenceSeatDistance(seatNum, seatOrLeave);
        System.out.println(ans);
    }*/


    public static void main(String[] args) {
        int seatNum = 10;
        int[] seatOrLeave = new int[]{1,1,1,1,-4,1};
        System.out.println(conferenceSeatDistance(seatNum, seatOrLeave));
    }
    /**
     * 计算最后进来的人，坐的位置
     * @param seatNum 会议室座位总数
     * @param seatOrLeave 员工的进出顺序
     * @return 最后进来的人，坐的位置
     */
    public static int conferenceSeatDistance(int seatNum, int[] seatOrLeave) {
        int res = -1;
        int[] dp = new int[seatOrLeave.length];
        int length = dp.length;

        // 初始化: 假设全部进场
        dp[0] = 0;
        dp[1] = seatNum-1;
        dp[2] = (dp[1]-dp[0])/2;
        int count=0;
        for (int i = 3; i < length; i++) {
            if (seatOrLeave[i]==1) {
                int min = Math.min(dp[i-1-count],dp[i-2-count]);
                min = Math.min(min,dp[i-3-count]);
                dp[i] = Math.min(Math.abs(dp[i-1-count]-dp[i-2-count])/2, Math.abs(dp[i-1-count] - dp[i-3-count])/2)+min;
                count=0;
            }
            if (seatOrLeave[i] < 0) {
                for (int j = 0; j < dp.length; j++) {
                    if (dp[j] == Math.abs(seatOrLeave[i])){
                        int index = j-1>0?j-1:0;
                        dp[j] = dp[index];
                        count++;
                        break;
                    }
                }
            }
        }

        return dp[dp.length-1];
    }
}
