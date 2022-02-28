package org.brotao.multithread;

/**
 * @author luotao
 * @className PrintA_To_Z
 * @description 三个线程顺序打印a-z
 * @date 2022-02-25 17:04
 */
public class PrintA_To_Z {

	public static  int i = 0;

	public static Object obj = new Object();

	public static boolean flag_A = true;
	public static boolean flag_B = false;
	public static boolean flag_C = false;

	static class PrintThread_A implements Runnable{
		@Override
		public void run(){
			while(i < 26){
				if(flag_A){
					synchronized (obj){
						System.out.println(Thread.currentThread().getName() + (char)('A' + i));
						++i;
						flag_A = false;
						flag_B = true;
					}
				}
			}
		}
	}

	static class PrintThread_B implements Runnable{
		@Override
		public void run(){
			while(i < 26){
				if(flag_B){
					synchronized (obj){
						System.out.println(Thread.currentThread().getName() + (char)('A' + i));
						++i;
						flag_B = false;
						flag_C = true;
					}
				}
			}
		}
	}

	static class PrintThread_C implements Runnable{
		@Override
		public void run(){
			while(i < 26){
				if(flag_C){
					synchronized (obj){
						System.out.println(Thread.currentThread().getName() + (char)('A' + i));
						++i;
						flag_C = false;
						flag_A = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception{
		Thread t1 = new Thread(new PrintThread_A(), "1");
		Thread t2 = new Thread(new PrintThread_B(), "2");
		Thread t3 = new Thread(new PrintThread_C(), "3");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		System.out.println("OK");
	}
}
