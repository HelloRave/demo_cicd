package com.sddevops.demo_cicd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongCollectionTest {
	private SongCollection sc;
	private Song s1;
	private Song s2;
	private Song s3;
	private Song s4;
	private final int SONG_COLLECTION_SIZE = 4;

	@BeforeEach
	void setUp() throws Exception {
		sc = new SongCollection();
		s1 = new Song("001", "good 4 u", "Olivia Rodrigo", 3.59);
		s2 = new Song("002", "Peaches", "Justin Bieber", 3.18);
		s3 = new Song("003", "MONTERO", "Lil Nas", 2.3);
		s4 = new Song("004", "bad guy", "billie eilish", 3.14);
		sc.addSong(s1);
		sc.addSong(s2);
		sc.addSong(s3);
		sc.addSong(s4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetSongs() {
		List<Song> testSc = sc.getSongs();
		// Assert that Song Collection is equals to Song Collection Size : 4
		assertEquals(testSc.size(), SONG_COLLECTION_SIZE);
		// Act
		sc.addSong(s1);
		// Assert that Song Collection is equals to Song Collection Size + 1 : 5
		assertEquals(testSc.size(), SONG_COLLECTION_SIZE + 1);
	}

	@Test
	void testAddSong() {
		SongCollection collectionTwo = new SongCollection(1);
		collectionTwo.addSong(s1);
		assertEquals(collectionTwo.getSongs().size(), 1);
		collectionTwo.addSong(s2);
		assertEquals(collectionTwo.getSongs().size(), 1);
	}

	@Test
	void testSortSongsByTitle() {
		List<Song> sortedCollection = sc.sortSongsByTitle();
		assertEquals(sortedCollection.get(0).getId(), "003");
		assertEquals(sortedCollection.get(1).getId(), "002");
		assertEquals(sortedCollection.get(2).getId(), "004");
		assertEquals(sortedCollection.get(3).getId(), "001");
	}

	@Test
	void testSortSongsBySongLength() {
		sc.addSong(s1);
		List<Song> sortedCollectionByLength = sc.sortSongsBySongLength();
		assertEquals(sortedCollectionByLength.get(0).getId(), "001");
		assertEquals(sortedCollectionByLength.get(1).getId(), "001");
		assertEquals(sortedCollectionByLength.get(2).getId(), "002");
		assertEquals(sortedCollectionByLength.get(3).getId(), "004");
		assertEquals(sortedCollectionByLength.get(4).getId(), "003");
	}

	@Test
	void testFindSongsById() {
		Song findSongFirst = sc.findSongsById("001");
		assertEquals(findSongFirst.getId(), "001");
		
		Song findSongSecond = sc.findSongsById("005");
		assertEquals(findSongSecond, null);
	}

	@Test
	void testFindSongByTitle() {
		Song findSongFirst = sc.findSongByTitle("good 4 u");
		assertEquals(findSongFirst.getId(), "001");
		
		Song findSongSecond = sc.findSongByTitle("005");
		assertEquals(findSongSecond, null);
	}

}
