package com.clfsys.note;

import java.lang.reflect.Array;

/**
 * @author cdy
 * @date 2021/7/26 9:34
 */
public class main {

    int t = 0,n = 5;
    int [] a = new int[5];



    public  void TongPaiXu(){
        for ( int i = 0 ;i<=n;i++)
        {
//            if ()
        }
    }
   public void  maoPaiXu(){


       for(int i = 0;i<=n-1;i++) //n个数，对n-1个数进行排序
       {
            for (int j = 1;j<=n-i;j++)//n-i指排除排完序的数
            {
                if (a[j]<a[j+1])
                {
                    t=a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                }
            }
       }
   }
}
