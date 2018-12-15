package com.lov.thread.thread_4;

import java.util.concurrent.Executor;
//¿´APIÎÄµµExecutorService,Callable,Executors
public class ExecutorTest  implements Executor{

	public static void main(String[] args) {
		new ExecutorTest().execute(()->System.out.println("Hello Executor"));
	}
	
	@Override
	public void execute(Runnable command) {
		command.run();
		
	}
	
}
