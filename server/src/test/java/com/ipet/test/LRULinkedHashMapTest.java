/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipet.test;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class LRULinkedHashMapTest {
    //Test
     public static void main(String[] args) throws Exception {

        //指定缓存最大容量为4
        Map<Integer, Integer> map = new LRULinkedHashMap<Integer, Integer>(4);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        //总共put了5个元素，超过了指定的缓存最大容量
        //遍历结果
        for (Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            System.out.println(it.next().getKey());
        }
    }
}
