package com.sjc.test1;

import java.util.ArrayList;
import java.util.List;


/**
 * 被观察者的类
 * @author hu
 *
 */
public class CreaterWatchered implements Watchered {

    //定义一个集合存储 观察者  这里的泛型类型我们使用接口 父引用指向子类
    List<Watcher> list = new ArrayList<Watcher>();


    public void addWatcher(Watcher watcher) {

        list.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    public void notifyWatcher(String str) {

        //通过这个方法通知观察者  我要偷东西了
            //通过循环通知每位观察者  for循环  都可以的

        /**
         * 方法1： 迭代器循环
         */
        /*Iterator<Watcher> it = list.iterator();
        while(it.hasNext()){

            CreaterWatcher watcher = (CreaterWatcher) it.next();
            watcher.updata(str);
        }*/


        /**
         * 方法2：增强for
         */

        /*for (Watcher watcher : list) {

            watcher.updata(str);
        }*/

        /**
         * 方法3：普通for循环
         */
        for (int i = 0; i < list.size(); i++) {

            list.get(i).updata(str);
        }
    }

}