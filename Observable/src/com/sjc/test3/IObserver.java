package com.sjc.test3;

public interface IObserver {
    //������״̬�ı�ʱ,�Ὣһ��String�����ַ�����÷����Ĳ���,ÿ���۲��߶���Ҫʵ�ָ÷���
    public void update(ISubject subject,BeanInof info);
}


//public interface Observer
//{
//    public void update(double temperature, double humidity, String condition);
//}
