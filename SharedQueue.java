import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SharedQueue implements Buffer {
	private boolean m_producerDone;
	private int m_maxsize;
	private LinkedBlockingQueue<String> m_buffer; 
	
	public SharedQueue(int size) {
		m_maxsize = size;
		m_producerDone = false;
		m_buffer = new LinkedBlockingQueue<String>(m_maxsize);
	}
	public void put(String s) throws InterruptedException{
		m_buffer.put(s);
	}
	public String get () throws InterruptedException {
		return m_buffer.poll(500,TimeUnit.MILLISECONDS);	
	}
	// check whether the producer is done
	public boolean isProducerDone () throws InterruptedException {
		return m_producerDone;
	}
	public void setProducerDone (boolean b) throws InterruptedException {
		m_producerDone = b;
	}
}
