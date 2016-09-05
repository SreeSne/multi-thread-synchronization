public interface Buffer {
	public void put (String s) throws InterruptedException;
	public String get () throws InterruptedException;
	public boolean isProducerDone () throws InterruptedException;
	public void setProducerDone (boolean b) throws InterruptedException;
}
