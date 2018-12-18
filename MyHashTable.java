package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
	
    // num of entries to the table
    private int numEntries;
    
    // num of buckets 
    private int numBuckets;
    
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    
    // ArrayList of buckets. 
    // Each bucket is a LinkedList of HashPair!
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    
    
    // constructor
    public MyHashTable(int initialCapacity) 
    {
    	this.numEntries = 0;
        this.numBuckets = initialCapacity;
        
        this.buckets = new ArrayList<LinkedList<HashPair<K, V>>>(this.numBuckets);
        
        //Adding to the Hashtable while the size is smaller till the number of Buckets required
        while (this.buckets.size() < this.numBuckets)
        {
        	this.buckets.add(new LinkedList<HashPair<K,V>>());
        }
        
    }
    
    public int size() 
    {
        return this.numEntries;
    }
    
    public int numBuckets() 
    {
        return this.numBuckets;
    }
    
    
    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    
    
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) 
    {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. 
     * 
     * Expected average run time  O(1)
     */
    public V put(K key, V value) 
    {
    	//Load Factor (with casting)
    	double currentLoadFactor = (double) (this.numEntries+1)/(this.numBuckets);
    	if (MAX_LOAD_FACTOR <= currentLoadFactor)
    	{
    		rehash();
    	}
    	
    	int intPositionOfKey = hashFunction(key);
    	
    	HashPair<K,V> tempHash = new HashPair <K,V> (key, value);                  
    		
    	LinkedList<HashPair<K, V>> myBucket = this.buckets.get(intPositionOfKey);
    	
    	for (HashPair<K,V> myHashPair: myBucket)
    	{
    		if (myHashPair.getKey().equals(key))
    		{
    			V oldHashPairValue = myHashPair.getValue();
    			
    			myHashPair.setValue(value);
    			
    			return oldHashPairValue;
    		}
    		
    	}
    	 	
    	myBucket.add(tempHash);
    	this.numEntries++;
    	

    	
    	return null;
    	
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) 
    {   	
    	int intPositionOfKey = hashFunction(key);
    	
    	LinkedList<HashPair<K, V>> myBucket = this.buckets.get(intPositionOfKey);
    	
    	for (HashPair<K,V> myHashPair: myBucket)
    	{
    		if (myHashPair.getKey().equals(key))
    		{
    			return myHashPair.getValue();
    		}
    		
    	}
    	
    	return null;
    	
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    //I have kept the variable names self-explanatory!!!
    public V remove(K key)
    {
      int intPositionOfKey = hashFunction(key);
      
      LinkedList<HashPair<K, V>> myBucket = this.buckets.get(intPositionOfKey);
      
      for (HashPair<K, V> myHashPair: myBucket)
      {
    	  if (myHashPair.getKey().equals(key))
    	  {
    		  myBucket.remove(myHashPair);
    		  numEntries--;
    		      		  
      		  return myHashPair.getValue();
      	  }
      }
    	
        return null;
        
    }
    
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    
    public void rehash() 
    {
    	int newBucketSize = this.numBuckets*2;
    	
    	MyHashTable<K, V> newHashTable = new MyHashTable<K, V>(newBucketSize);
    	
    	for (LinkedList<HashPair<K,V>> myLinkedList: this.buckets)
    		{
    			for (HashPair<K,V> myHashPair: myLinkedList)
    			{
    				newHashTable.put(myHashPair.getKey(), myHashPair.getValue());
    			}
    		}
    		
    	this.buckets = newHashTable.buckets;
    	
    	this.numBuckets = newHashTable.numBuckets;
    	this.numEntries = newHashTable.numEntries;

	}	
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() 
    {
    	ArrayList<K> keyArrayList = new ArrayList<K>();
    	
        for (LinkedList<HashPair<K,V>> myLinkedList: this.buckets)
        {
        	for (HashPair<K,V> myHashPair: myLinkedList)
        	{
        		keyArrayList.add(myHashPair.getKey());
        	}
        }
    	
    	
        return keyArrayList;
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() 
    {
    	ArrayList<V> uniqueValuesList = new ArrayList<V>();
    	
    	ArrayList<K> keyList = this.keys();
    	
    	MyHashTable<V, Boolean> encounteredValues = new MyHashTable<V, Boolean>(keyList.size());
    	
    	for(K k : keyList)
    	{
    		V myValue = this.get(k);
    		
    		//Checking to add to encounteredValues ONLY if the the list doesn't already have the value
    		if(encounteredValues.get(myValue) == null)
    		{
    			uniqueValuesList.add(myValue);
    			encounteredValues.put(myValue, true);
    		}
    	}
        
    	 return uniqueValuesList;	
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    //PRIVATE CLASS HERE Y'ALLL
    private class MyHashIterator implements Iterator<HashPair<K,V>> 
    {
    	
        private LinkedList<HashPair<K,V>> entries;

        //Private Fields added by me
    	private HashPair<K,V> currentHashPair;
    	private HashPair<K,V> nextHashPair;
    	
    	private int n, k;
        
        // CONSTRUCTORR
        private MyHashIterator() 
        {
        	for (LinkedList<HashPair<K, V>> myLinkedList: buckets)
            {
            	for (HashPair<K, V> myHashPair: myLinkedList)
            	{
            		entries.add(myHashPair);
            	}
            }        
        
        n = entries.size();
        
        // When we call the .next for the first time, it will go to k = 0, as k represents the NEXT one
        k = 0;
        
        currentHashPair = null;        
        nextHashPair = entries.get(0);
        
        }
    	
        @Override
        public boolean hasNext()
        {
        	return nextHashPair != null;
        }
        
        @Override
        public HashPair<K,V> next() 
        {
        	currentHashPair = nextHashPair;
        	
        	if(k < n)
        	{
        		nextHashPair = entries.get(k++);
        	}
        	else
        	{
        		nextHashPair = null;
        	}
        	
           return currentHashPair;
        }
        
    }
}
