
public class Pair {
	private String key;
	private String info;
	
	public Pair(String key, String info) {
		this.key = key;
		this.info = info;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public String getKey() {
		return this.key;

	}
	
	@Override
	public String toString() {
		return this.info;
	}
}
