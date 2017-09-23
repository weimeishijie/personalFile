package com.personal.file.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mj on 2017/9/9.
 */
public class TimerDemo {

    //单利模式
    private static TimerDemo timerDemo = null;
    private TimerDemo(){}
    public static TimerDemo getInstance(){
        if(timerDemo == null){
            timerDemo = new TimerDemo();
        }
        return timerDemo;
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(getInstance().new Task(),1000,1000);
    }

    class Task extends TimerTask{

        @Override
        public void run() {
            System.out.println("Hello World !");
        }
    }
}
