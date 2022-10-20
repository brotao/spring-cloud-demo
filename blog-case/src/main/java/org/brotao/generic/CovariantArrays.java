package org.brotao.generic;

/**
 * @Author luotao
 * @Date 2022-06-20 13:07
 * @Description 泛型通配符
 */

class Fruit {}
class Apple extends Fruit {}
class Orange extends Fruit {}

class Jonathan extends Apple {}

public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple(); // OK
        fruit[1] = new Jonathan(); // OK

        try {
// Compiler allows you to add Fruit:
            fruit[0] = new Fruit(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
// Compiler allows you to add Oranges:
            fruit[0] = new Orange(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
