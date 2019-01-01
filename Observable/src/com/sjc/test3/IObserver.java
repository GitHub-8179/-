package com.sjc.test3;

public interface IObserver {
    //当主题状态改变时,会将一个String类型字符传入该方法的参数,每个观察者都需要实现该方法
    public void update(ISubject subject,BeanInof info);
}


//public interface Observer
//{
//    public void update(double temperature, double humidity, String condition);
//}
