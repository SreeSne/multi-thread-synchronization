import java.util.LinkedList;

public class SynchronizedBuffer implements Buffer {
	private boolean m_producerDone;
	private int m_maxsize;
	private LinkedList<String> m_buffer; 
	
	public SynchronizedBuffer (int size) {
		m_maxsize = size;
		m_producerDone = false;
		m_buffer = new LinkedList<String>();
	}
	public synchronized void put(String s) throws InterruptedException{
		m_buffer.add(s);
		if (m_buffer.size() == m_maxsize) {
			wait();
		}
	}
	public synchronized String get () throws InterruptedException {
		String removed_s = m_buffer.poll();
		notifyAll();
		return removed_s;
	}
	//alert the consumer to when the producer is done 
	public synchronized boolean isProducerDone () throws InterruptedException {
		return m_producerDone;
	}
	public synchronized void setProducerDone (boolean b) throws InterruptedException {
		m_producerDone = b;
	}
}
