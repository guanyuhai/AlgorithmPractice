package NiuKe;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 购物单（动态规划）
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 16:21
 */
public class HW16 {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int money = Integer.parseInt(str.split(" ")[0]);
        // 物品个数
        int nums = Integer.parseInt(str.split(" ")[1]);

        while (sc.hasNext()) {
            String str2 = sc.nextLine();
            int price = Integer.parseInt(str.split(" ")[0]);
            // 物品的重要程度（1~5），值越大就越重要
            int heavy = Integer.parseInt(str.split(" ")[1]);
            // 主件还是附件，值为0，则为主件，否则为附件
            int isMain = Integer.parseInt(str.split(" ")[1]);

            if (isMain == 0) {
                // 计算满意度

            }
        }

        // 输出最大满意度
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        int n = sc.nextInt();
        if(n<=0||money<=0) System.out.println(0);

        good[] Gs = new good[n+1];
        for (int i = 1; i <= n; i++) {
            int v = sc.nextInt(); // 表示该物品的价格（ v<10000 ）
            int p = sc.nextInt(); // p 表示该物品的重要度（ 1 ~ 5 ）
            int q = sc.nextInt(); // q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
            Gs[i] = new good(v,p,q);

            // 添加附件 到对应主件 的类属性中
            if(q>0){
                if(Gs[q].a1==0){
                    Gs[q].setA1(i);
                }else {
                    Gs[q].setA2(i);
                }
            }
        }

        int[][] dp = new int[n+1][money+1];
        for (int i = 1; i <= n; i++) {
            int v=0,v1=0,v2=0,v3=0,tempdp=0,tempdp1=0,tempdp2=0,tempdp3=0;

            v = Gs[i].v;

            tempdp = Gs[i].p*v; //只有主件

            if(Gs[i].a1!=0){//主件加附件1
                v1 = Gs[Gs[i].a1].v+v;
                tempdp1 = tempdp + Gs[Gs[i].a1].v*Gs[Gs[i].a1].p;
            }

            if(Gs[i].a2!=0){//主件加附件2
                v2 = Gs[Gs[i].a2].v+v;
                tempdp2 = tempdp + Gs[Gs[i].a2].v*Gs[Gs[i].a2].p;
            }

            if(Gs[i].a1!=0&&Gs[i].a2!=0){//主件加附件1和附件2
                v3 = Gs[Gs[i].a1].v+Gs[Gs[i].a2].v+v;
                tempdp3 = tempdp + Gs[Gs[i].a1].v*Gs[Gs[i].a1].p + Gs[Gs[i].a2].v*Gs[Gs[i].a2].p;
            }

            for(int j=1; j<=money; j++){
                if(Gs[i].q > 0) {   //当物品i是附件时,相当于跳过
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                    if(j>=v&&v!=0) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-v]+tempdp);
                    if(j>=v1&&v1!=0) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-v1]+tempdp1);
                    if(j>=v2&&v2!=0) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-v2]+tempdp2);
                    if(j>=v3&&v3!=0) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-v3]+tempdp3);
                }
            }
        }
        System.out.println(dp[n][money]);
    }


    /**
     * 定义物品类
     */
    private static class good{
        public int v;  //物品的价格
        public int p;  //物品的重要度
        public int q;  //物品的主附件ID

        public int a1=0;   //附件1ID
        public int a2=0;   //附件2ID

        public good(int v, int p, int q) {
            this.v = v;
            this.p = p;
            this.q = q;
        }

        public void setA1(int a1) {
            this.a1 = a1;
        }

        public void setA2(int a2) {
            this.a2 = a2;
        }
    }

}
