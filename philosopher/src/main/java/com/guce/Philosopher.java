package com.guce;

/**
 * Created by Administrator on 2016/5/4.
 */
public class Philosopher extends Thread {
    private Chopstick leftchopstick;
    private Chopstick rightchopstick;
    private String name;

    public Philosopher(String name,Chopstick leftchopstick, Chopstick rightchopstick){
        this.name = name;
        this.leftchopstick = leftchopstick;
        this.rightchopstick = rightchopstick;
    }

    public void pickupLeftChopstick(){
        System.out.println(name + " ;准备拿起左边的筷子 ：" + leftchopstick.getName() );
        leftchopstick.pickupChopstick();
        System.out.println(name + " ;拿起左边的筷子 ：" + leftchopstick.getName() );
    }

    public void pickupRightChopstick(){

        System.out.println(name + " ;准备拿起右边的筷子 ：" + rightchopstick.getName() );
        rightchopstick.pickupChopstick();
        System.out.println(name + " ;拿起右边的筷子 ：" + rightchopstick.getName() );
    }

    public void eat(){
        System.out.println(name + " 开始吃饭！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putdownLeftChopstick(){
        System.out.println(name + " ;准备拿起放下的筷子 ：" + leftchopstick.getName() );
        leftchopstick.putdownChopstick();
        System.out.println(name + " ;放下左边的筷子 ：" + leftchopstick.getName() );
    }

    public void putdownRightChopstick(){
        System.out.println(name + " ;准备拿起放下的筷子 ：" + rightchopstick.getName() );
        rightchopstick.putdownChopstick();
        System.out.println(name + " ;放下右边的筷子 ：" + rightchopstick.getName() );
    }


    public void run() {

        pickupLeftChopstick();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pickupRightChopstick();
        eat();
        putdownLeftChopstick();
        putdownRightChopstick();
    }

    public static void main(String[] args) {
        Chopstick ch1 = new Chopstick("ch1");
        Chopstick ch2 = new Chopstick("ch2");
        Chopstick ch3 = new Chopstick("ch3");
        Chopstick ch4 = new Chopstick("ch4");
        Chopstick ch5 = new Chopstick("ch5");

        Philosopher ph1 = new Philosopher("A",ch1,ch2);
        Philosopher ph2 = new Philosopher("B",ch2,ch3);
        Philosopher ph3 = new Philosopher("C",ch3,ch4);
        Philosopher ph4 = new Philosopher("D",ch4,ch5);
        Philosopher ph5 = new Philosopher("E",ch5,ch1);

        ph1.start();
        ph2.start();
        ph3.start();
        ph4.start();
//        ph5.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ph5.start();
    }
}
