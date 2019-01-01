package com.sjc.test1;

/**
 * 被观察者接口
 * @author hu
 *
 */
public interface Watchered {

    public void addWatcher(Watcher watcher);
    public void removeWatcher(Watcher watcher);
    public void notifyWatcher(String str);
}
