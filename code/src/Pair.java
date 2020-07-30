/**
 * A simple class to store a key, value pair. Will mainly hold ids of movies and actors along with their name
 */
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
