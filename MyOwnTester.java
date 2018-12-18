public class MyOwnTester {

	public static void main(String[] args) 
	{
		MyHashTable<String, String> myHash = new MyHashTable<String, String>(6);

		myHash.put("Lil Yachty", "SONG 1");
		myHash.put("xws", "SONG 2");
		myHash.put("aaa", "SONG 2");
		myHash.put("ddd", "SONG 3");
		myHash.put("artist1", "SONG 3");
		myHash.put("dad", "SONG 4");
		myHash.put("artist2", "SONG 4");
		myHash.put("artist3", "SONG 4");
		myHash.put("artist4", "SONG 5");
		myHash.put("artist5", "SONG 6");
		
		//System.out.println(myHash.get("Lil Yachty"));
		
		//System.out.println(myHash.get("Lil Yachty"));
		
		//REMOVE CHECKER // myHash.remove("Ali Sharif");
		//System.out.println(myHash.get("Lil Yachty"));
		//System.out.println(myHash.get("Ali Sharif"));
		
		System.out.println("Unique Values: " + myHash.values());
		
		
		

		
		
		
		
	}

}
