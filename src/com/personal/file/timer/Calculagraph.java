package com.personal.file.timer;

/**
 * 计时器
 */
public class Calculagraph extends Thread {

    private boolean startTimer = true;
    private boolean stopTimer = false;
    private long now = 11;
    private long start = System.currentTimeMillis();
    private long time;

    public boolean isStopTimer() {
        return stopTimer;
    }

    public void setStopTimer(boolean stopTimer) {
        this.stopTimer = stopTimer;
    }

    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void run(){
        while (startTimer){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            now = System.currentTimeMillis();// 获取一秒之后的毫秒值
            time = now - start;// 两个时间相减的到毫秒差
            setTime(time);
            System.out.format("%02d:%02d:%02d\n",
                    time/(1000 * 60 * 60/* 时 */),
                    time/(1000 * 60)%60/* 分 */,
                    time/1000%60 /* 秒 */);//格式化字符串输出（定时的时间单位秒）
        }

    }

    public static boolean startTimeFactory(){
        Calculagraph calculagraph = new Calculagraph();
        calculagraph.start();
        boolean star = true;
        while (star){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 此处5*1000代表需要监控的时间长度，单位为ms
            if(calculagraph.getTime() >= 5*1000){
                calculagraph.setStopTimer(calculagraph.getTime() >= 5*1000);
                System.out.println("停止计时！");
                calculagraph.setStartTimer(false);
                star = false;
            }
        }
        return calculagraph.isStopTimer();
    }



}
