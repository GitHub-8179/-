package com.sjc.test3;

import java.util.Observer;

//����ӿ� ���۲���
interface ISubject {
   //��ӹ۲���
   void addObserver(IObserver obj);
   //�Ƴ��۲���
   void deleteObserver(IObserver obj);
   //�����ⷽ���ı�ʱ,�������������,֪ͨ���еĹ۲���
   void notifyObserver(BeanInof beanInof);
}
