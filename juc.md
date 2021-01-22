# 创建多线程的方式

## Thread

### run()和start()

Thread.run()是单线程，也就是手动调用了run中的代码

Thread.start()则是多线程，它会开辟新的栈空间，将run方法压栈并执行。此时有main和run两个方法并发执行。

### getName()

获取线程名称，

## Runnable接口

implements runnable之后，需要重写run()方法。另一个类想要执行run方法时，需要new Thread(new runnable实现类()).start();

好处：1、避免单继承局限性，继承了Thread就不能继承别的类

​			2、解耦

# 同步

## Synchronized

### 同步代码块

```java
Object obj=new Object;
synchronized(obj){
  //同步代码块内容
}
```

