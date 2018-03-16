package main;

public class Timer {
	private long startTime;
	private long elapsedTime;
	private long stopWatchTime;
	public Timer(){
		startTime = System.currentTimeMillis(); //starts the timer
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public long getElapsedTime(){
		elapsedTime = System.currentTimeMillis() - startTime;
		return elapsedTime;
	}

}
