package com.sjc.test1;

/**
 * ���۲��߽ӿ�
 * @author hu
 *
 */
public interface Watchered {

    public void addWatcher(Watcher watcher);
    public void removeWatcher(Watcher watcher);
    public void notifyWatcher(String str);
}
