package com.zxsd.common.evendemo;

/**
 * even
 * 2019/10/31
 */
public class EvenTest1 {

    protected String str = "11";

      public static int tryCatchFinally(){
          try {
              return 1;
          }catch (Exception e){
              return 2;
          }finally {
              return 3;
          }
      }

      public static void main(String[] args){
          System.out.println(tryCatchFinally());
      }
}
