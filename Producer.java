import java.util.UUID;

public class Producer implements Runnable {
	private static final int STRING_COUNT = 2000000;
	private Buffer m_buffer;
	public Producer(Buffer buffer) {
		m_buffer = buffer;
	}
	
	public void run() {
		int i;
		for(i=1; i<STRING_COUNT+1; i++) {
			try {
				m_buffer.put(UUID.randomUUID().toString());
				//printing for every 1000 strings produced
				if (i % 1000 == 0 && i != 0) {
					System.out.println("produced: " + i);
				}
			}
			catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
			
		}
		System.out.println("done producing! " + (i-1) + " produced");
		try {
			m_buffer.setProducerDone(true);
		}
		catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}
}
