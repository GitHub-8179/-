package com.sjc.test1;

/**
 * 测试类
 * @author hu
 *
 */
public class TestRxjava {

    public static void main(String[] args) {

        //创建观察者

        CreaterWatcher watcher1 = new CreaterWatcher();
        CreaterWatcher watcher2 = new CreaterWatcher();
        CreaterWatcher watcher3 = new CreaterWatcher();
        CreaterWatcher watcher4 = new CreaterWatcher();
        //创建被观察者

        CreaterWatchered watchered = new CreaterWatchered();
        watchered.addWatcher(watcher1);
        watchered.addWatcher(watcher2);
        watchered.addWatcher(watcher3);
        watchered.addWatcher(watcher4);

        //被观察者开始 发消息了  也就像 小偷开始偷东西了，警察（观察者）一直在监视者  这个时候警察 就是接收到消息了
        watchered.notifyWatcher("我开始偷东西了");


    }

}