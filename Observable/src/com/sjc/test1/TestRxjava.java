package com.sjc.test1;

/**
 * ������
 * @author hu
 *
 */
public class TestRxjava {

    public static void main(String[] args) {

        //�����۲���

        CreaterWatcher watcher1 = new CreaterWatcher();
        CreaterWatcher watcher2 = new CreaterWatcher();
        CreaterWatcher watcher3 = new CreaterWatcher();
        CreaterWatcher watcher4 = new CreaterWatcher();
        //�������۲���

        CreaterWatchered watchered = new CreaterWatchered();
        watchered.addWatcher(watcher1);
        watchered.addWatcher(watcher2);
        watchered.addWatcher(watcher3);
        watchered.addWatcher(watcher4);

        //���۲��߿�ʼ ����Ϣ��  Ҳ���� С͵��ʼ͵�����ˣ����죨�۲��ߣ�һֱ�ڼ�����  ���ʱ�򾯲� ���ǽ��յ���Ϣ��
        watchered.notifyWatcher("�ҿ�ʼ͵������");


    }

}