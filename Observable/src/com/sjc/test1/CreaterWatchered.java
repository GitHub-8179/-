package com.sjc.test1;

import java.util.ArrayList;
import java.util.List;


/**
 * ���۲��ߵ���
 * @author hu
 *
 */
public class CreaterWatchered implements Watchered {

    //����һ�����ϴ洢 �۲���  ����ķ�����������ʹ�ýӿ� ������ָ������
    List<Watcher> list = new ArrayList<Watcher>();


    public void addWatcher(Watcher watcher) {

        list.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    public void notifyWatcher(String str) {

        //ͨ���������֪ͨ�۲���  ��Ҫ͵������
            //ͨ��ѭ��֪ͨÿλ�۲���  forѭ��  �����Ե�

        /**
         * ����1�� ������ѭ��
         */
        /*Iterator<Watcher> it = list.iterator();
        while(it.hasNext()){

            CreaterWatcher watcher = (CreaterWatcher) it.next();
            watcher.updata(str);
        }*/


        /**
         * ����2����ǿfor
         */

        /*for (Watcher watcher : list) {

            watcher.updata(str);
        }*/

        /**
         * ����3����ͨforѭ��
         */
        for (int i = 0; i < list.size(); i++) {

            list.get(i).updata(str);
        }
    }

}