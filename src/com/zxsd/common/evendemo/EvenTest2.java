package com.zxsd.common.evendemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * even
 * 2019/10/31
 */
public class EvenTest2 extends EvenTest1 {

    public void listRemove(List list){


        Iterator it = list.iterator();
        while (it.hasNext()){
            list.remove(it.next());
        }
    }
    
    public static void main(String[] args){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        new EvenTest2().listRemove(list);
        System.out.println(list.toString());
    }
}
