package io.renren.utils;

public class TestDemo {

        public static void main(String[] args) {
             //Input: numbers={2, 7, 11, 15}, target=9
            //Output: index1=1, index2=2
             int[]   a =  {2,7,11,9};
             int i = 0, j=a.length-1;
             int trage = 9;
             while (i<j){
                int sum =  a[i] + a [j]  ;
                if(sum ==trage){
                    System.out.println(a[i]  +"_"+ a[j] );
                }else if(sum>trage){
                    j--;
                }else {
                    i++;
                }
                Integer aaa = Integer.valueOf(123);
                String da= "";
             }

       }
}
