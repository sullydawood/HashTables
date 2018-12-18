import java.util.ArrayList;

public class MusicStore 
{
	
	private MyHashTable<String, Song> musicLibraryTitleKey;
	private MyHashTable<String, ArrayList<Song>> musicLibraryArtistKey;
	private MyHashTable<Integer, ArrayList<Song>> musicLibraryYearKey;
	
    // Constructor
    public MusicStore(ArrayList<Song> songs) 
    {
    	musicLibraryTitleKey = new MyHashTable<>(songs.size());
    	musicLibraryArtistKey = new MyHashTable<>(songs.size());
    	musicLibraryYearKey = new MyHashTable<>(songs.size());
    	
    	// All the heavy duty work is being done in my addSong method
      for (Song mySong: songs)
      {
    	  this.addSong(mySong);
      }
    
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) 
    {
    	Song mySong = s;
    	
    	//Adding to the first HashTable - the Title One
    	musicLibraryTitleKey.put(mySong.getTitle(), mySong);
  	  
    	// Checking if we already have the artist
  	  if(musicLibraryArtistKey.get(mySong.getArtist()) != null)
  	  {
  		  //Basically saying that get the key (get Artist) and add mySong to it (i.e. the arraylist)
  		  musicLibraryArtistKey.get(mySong.getArtist()).add(mySong);
  	  }
  	  
  	  // If artist is not present, we add the artist and the song
  	  else
  	  {
  		  ArrayList<Song> arrayListOfSongs = new ArrayList<>();
  		  arrayListOfSongs.add(mySong);
  		  
  		  musicLibraryArtistKey.put(mySong.getArtist(), arrayListOfSongs);
  	  }
  	  
  	// Checking if we already have the year
  	  if (musicLibraryYearKey.get(mySong.getYear()) != null)
  	  {
  	 	  musicLibraryYearKey.get(mySong.getYear()).add(mySong);
  	  }
  	  
  	// If year is not present, we add the year and the song
  	  else
  	  {
  		  ArrayList<Song> arrayListOfSongs = new ArrayList<>();
  		  arrayListOfSongs.add(mySong);
  		  
  		  musicLibraryYearKey.put(mySong.getYear(), arrayListOfSongs);
  	  }
	
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) 
    {
       return musicLibraryTitleKey.get(title);
    	
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) 
    {
    	return musicLibraryArtistKey.get(artist);
    	
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) 
    {
        return musicLibraryYearKey.get(year);
        
    }
}
