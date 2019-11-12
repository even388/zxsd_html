package com.zxsd.common.evendemo;

/**
 * even
 * 2019/11/1
 */
public class VarTest {
    final int i ;
    public VarTest(){
// 在构造方法中初始化了
        i = 3;
    }
    public VarTest(int n){
// 有多个构造方法，必须在每个构造方法中进行初始化
        i = n;
    }
    public void doSomething() {
        int j;
        j = 1;
// 对于临时变量，如果这里不进行初始化，下面使用 ++j 时编译不能通过
        System.out.println(++j + i);
    }
    public static void main(String[] args) {
        VarTest test = new VarTest();
        test.doSomething();
    }
}