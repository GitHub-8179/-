package com.sjc.test3;

import java.util.Observer;

//主题接口 被观察者
interface ISubject {
   //添加观察者
   void addObserver(IObserver obj);
   //移除观察者
   void deleteObserver(IObserver obj);
   //当主题方法改变时,这个方法被调用,通知所有的观察者
   void notifyObserver(BeanInof beanInof);
}
