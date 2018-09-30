# Synchronized

## 前言
**synchronized是Java的关键字，是一种同步锁**

## 目录
* 修饰一个代码块，被修饰的代码块称为同步语句块，作用范围是大括号{}包括的代码，作用的对象是调用这个代码块的对象
* 修饰一个方法，被修饰的方法称为同步方法，作用的范围是整个方法，作用的对象是调用这个方法的对象
* 修饰一个静态方法，作用的范围式整个静态方法，作用的对象是这个类的所有对象
* 修饰一个类，作用的范围synchronized后面括号的部分，作用的对象是这个类的所有对象

## 修饰一个代码块
```java
/*
 * 同步线程
 */
public class SyncThread implements Runnable {

	private static int count;

	public SyncThread() {
		// TODO Auto-generated constructor stub
		count = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

	}
	
	public int getCOunt() {
		return count;
	}

}
```
### 1.一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞
```java
public static void main(String[] args) {
		// TODO Auto-generated method stub
		SyncThread syncThread = new SyncThread();
		Thread thread1 = new Thread(syncThread, "SyncThread1");
		Thread thread2 = new Thread(syncThread, "SyncThread2");
		thread1.start();
		thread2.start();
	}
```

**结果显示**
```java
SyncThread2:0
SyncThread2:1
SyncThread2:2
SyncThread1:3
SyncThread1:4
SyncThread1:5
```

当两个并发线程(Thread1和Thread2)访问同一对象(syncThread)中的synchronized代码块，同一个时刻只能有一个线程得到执行，另一个线程受阻塞，必须等待当前线程执行完这个代码块以后才能执行该代码块。Thread1和Thread2是互斥的，在执行synchronized代码块是会锁定当前的对象，只有执行完代码块才能释放对象锁，下一个线程才能执行并锁定该对象

**synchronized只锁定对象，每个对象只有一个锁与之关联**,修改一下上面的代码
```java
public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
		Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
		thread1.start();
		thread2.start();
	}
```

**结果显示**
```java
SyncThread2:0
SyncThread1:0
SyncThread2:1
SyncThread1:2
SyncThread1:3
SyncThread2:4

```

由于synchronized锁定的是对象，上述中含有两个SynchThread对象，两个线程执行的是不同的代码，所以两个线程是互不干扰的，所有如果两个线程碰巧的话会出现SyncThread2:0 SyncThread1:0的情况

### 2.当一个线程访问对象的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该对象的非synchronized(this)同步代码块
```java
public class Counter implements Runnable {

	private int count;

	public Counter() {
		// TODO Auto-generated constructor stub
		count = 0;
	}

	public void countAdd() {
		synchronized (this) {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void printCount() {
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " count:" + count);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		String threadName = Thread.currentThread().getName();
		if (threadName.equals("A")) {
			//A线程执行 锁定的函数
			countAdd();
		} else if (threadName.equals("B")) {
			//B线程执行 未锁定的函数
			printCount();
		}
	}

}
```

**结果显示**
```text
A:0
B count:1
A:1
B count:2
A:2
B count:2
```

从结果我们可以看到由于countAdd方法是synchronized的，但是printCount不是的，可以知道一个线程访问一个对象的synchronized代码块时，别的线程可以访问该对象的非synchronized代码块而不受阻塞


### 3.给指定某个对象加锁
```java
public class Account {
	private String name;
	private float amount;

	public Account(String name, float amount) {
		this.name = name;
		this.amount = amount;
	}

	// 存钱
	public void putMoney(float money) {
		amount += money;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 取钱
	public void getMoney(float money) {
		amount -= money;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 获取账户金额
	public float getNumber() {
		return amount;
	}
}

// 账户操作类
class AccountOperator implements Runnable {
	private Account account;

	public AccountOperator(Account account) {
		// TODO Auto-generated constructor stub
		this.account = account;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (account) {
			account.putMoney(500);
//			account.getMoney(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getNumber());
		}

	}

}
```

**测试类**
```java
public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account("nullWolf", 10000.0f);
		AccountOperator accountOperator = new AccountOperator(account);

		final int THREAD_NUM = 5;
		Thread threads[] = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i++) {
			threads[i] = new Thread(accountOperator, "Thread" + i);
			threads[i].start();
		}
	}
```

**结果显示（随机）**
```java
Thread1:10500.0
Thread4:11000.0
Thread3:11500.0
Thread2:12000.0
Thread0:12500.0
```

**在AccountOperator 类中的run方法里，我们用synchronized给account对象加了锁。这时，当一个线程访问account对象时，其他试图访问account对象的线程将会阻塞，直到该线程访问account对象结束。也就是说谁拿到那个锁谁就可以运行它所控制的那段代码。**

## 修饰一个方法
**Synchronized修饰方法，就是public synchronized void method(){},类似于修饰整个代码块。效果一样，只是作用的范围不一样**
### 注意点
* **synchronized关键字**不能继承，子类重写synchronized方法必须显式地在子类的方法上加上synchronized关键字，或者使用super调用父类的方法
* 定义接口中不能使用synchronized关键字
* 构造方法中不能使用synchronized关键字，但是可以使用synchronized代码块来进行同步

## 修饰一个静态方法
```java
public synchronized static void method{
	//todo
}
```

**静态方法属于类不属于对象，所以锁定的是类(类的所有对象)而不是单个对象**

```java
**
 * 同步线程
 */
class SyncThread implements Runnable {
   private static int count;

   public SyncThread() {
      count = 0;
   }

   public synchronized static void method() {
      for (int i = 0; i < 5; i ++) {
         try {
            System.out.println(Thread.currentThread().getName() + ":" + (count++));
            Thread.sleep(100);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   public synchronized void run() {
      method();
   }
}
```

**调用代码**
```java
SyncThread syncThread1 = new SyncThread();
SyncThread syncThread2 = new SyncThread();
Thread thread1 = new Thread(syncThread1, "SyncThread1");
Thread thread2 = new Thread(syncThread2, "SyncThread2");
thread1.start();
thread2.start();
```

**结果显示**
```java
SyncThread1:0 
SyncThread1:1 
SyncThread1:2 
SyncThread1:3 
SyncThread1:4 
SyncThread2:5 
SyncThread2:6 
SyncThread2:7 
SyncThread2:8 
SyncThread2:9
```

**虽然有两个SynchThread的对象，但是thread1和thread2并发执行的时候保持线程同步，因为run中调用了静态方法，属于类，所以相当于互斥了**

## 修饰一个类
```java
class ClassName {
   public void method() {
      synchronized(ClassName.class) {
         // todo
      }
   }
}
```

**因为作用对象和修饰一个静态方法一样，所以效果也是一样的**

## 参考文章
* [Java中Synchronized的用法](http://blog.csdn.net/luoweifu/article/details/46613015)
