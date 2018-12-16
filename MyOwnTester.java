package assignment4;

public class MyOwnTester {

	public static void main(String[] args) 
	{
		MyHashTable<String, String> myHash = new MyHashTable<String, String>(6);

		myHash.put("Lil Yachty", "SONG 1");
		myHash.put("xws", "SONG 2");
		myHash.put("aaa", "SONG 2");
		myHash.put("ddd", "SONG 3");
		myHash.put("xxxtentacion", "SONG 3");
		myHash.put("dad", "SONG 4");
		myHash.put("xxxtentacion", "SONG 4");
		myHash.put("xxxtentacion1", "SONG 4");
		myHash.put("xxxtentacion2", "SONG 5");
		myHash.put("xxxtentacion3", "SONG 6");
		
		//System.out.println(myHash.get("Lil Yachty"));
		
		//System.out.println(myHash.get("Lil Yachty"));
		
		//REMOVE CHECKER // myHash.remove("Ali Sharif");
		//System.out.println(myHash.get("Lil Yachty"));
		//System.out.println(myHash.get("Ali Sharif"));
		
		System.out.println("Unique Values: " + myHash.values());
		
		
		

		
		
		
		
	}

}
