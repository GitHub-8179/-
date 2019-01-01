package com.sjc.test1;


public class CreaterWatcher implements Watcher {

    //这个方法其实是供 被 被观察者 形参 CreateWather调用  到后来 还会回调当前这个方法
    public void updata(String str) { //接收被监听者发过来的消息
        System.out.println(str);
    }

}
