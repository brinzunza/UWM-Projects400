/*** JUnit imports ***/
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class FrontendDeveloperTests {


	/*
	 * Test the loadDataCommand()
	 * both when a file is not found and when a file is found
	 *
	 */
	@Test
	public void individualCodeTest1() {

		TextUITester uiTester = new TextUITester("L\nmarvin's_room.mpg\nQ\n");
		
		SongCollectionInterface backend = new SongCollectionFD();
		Scanner scan = new Scanner(System.in);
		SongPlayerFrontendFD frontend = new SongPlayerFrontendFD(scan, backend);
		frontend.runCommandLoop();
		String systemOutput = uiTester.checkOutput();
		if (!systemOutput.contains("\nI'm sorry, this file was not found!\n")) {

			fail("loadDataCommand() does not work correctly when the file cannot be found.");

		}
		
		TextUITester uiTester1 = new TextUITester("L\nsong.mpg\nQ\n");

		Scanner scan1 = new Scanner(System.in);
	
		SongPlayerFrontendFD frontend1 = new SongPlayerFrontendFD(scan1, backend);	
		frontend1.runCommandLoop();
		systemOutput = uiTester1.checkOutput();
		if (!systemOutput.contains("\nData loaded!\n")) {

			fail("loadDataCommand() does not work correctly when there is a matching file that should be loaded.");

		}

	}

	/*
         * Test the mainMenuPrompt() when an unknown command is typed
         *
         */
	@Test
	public void individualCodeTest2() {


		TextUITester uiTester = new TextUITester("P\nQ\n");
		
		SongCollectionInterface backend = new SongCollectionFD();
		Scanner scan = new Scanner(System.in);
		SongPlayerFrontendFD frontend = new SongPlayerFrontendFD(scan, backend);	
		frontend.runCommandLoop();
		String systemOutput = uiTester.checkOutput();
		if (!systemOutput.contains("\nError: this command was not recognized. Try again!\n")) {

			fail("runCommandLoop() does not work correctly when an unfamiliar command is used.");

		}
	}

	/*
         * Test the chooseSearchWordsPrompt() and the searchSongCommand() method
	 * both when results are not found and when they are found
         *
         */
	@Test
	public void individualCodeTest3() {


		TextUITester uiTester = new TextUITester("T\nMarvin's Room\n\nQ\n");
	
		SongCollectionInterface backend = new SongCollectionFD();
		Scanner scan = new Scanner(System.in);
		SongPlayerFrontendFD frontend = new SongPlayerFrontendFD(scan, backend);
		frontend.runCommandLoop();
		String systemOutput = uiTester.checkOutput();
		if (!systemOutput.contains("\nResult not found!\n")) {

			fail("searchSongCommand() does not work correctly when no matching song titles are found.");

		}
	
		TextUITester uiTester1 = new TextUITester("T\nIvy\n\nQ\n");
		Scanner scan1 = new Scanner(System.in);
		SongPlayerFrontendFD frontend1 = new SongPlayerFrontendFD(scan1,backend);
		frontend1.runCommandLoop();
		systemOutput = uiTester1.checkOutput();
		if (!systemOutput.contains("\nResult found!\n")) {

			fail("searchSongCommand() does not work correctly when there should be matching song titles found.");

		}

	}


	/*
         * Test the chooseSearchWordsPrompt() and the searchArtistCommand() method
         * both when results are not found and when they are found
         *
         */
	@Test
	public void individualCodeTest4() {


		TextUITester uiTester = new TextUITester("A\nTaylor Swift\n\nQ\n");
		
		SongCollectionInterface backend = new SongCollectionFD();
		Scanner scan = new Scanner(System.in);
		SongPlayerFrontendFD frontend = new SongPlayerFrontendFD(scan, backend);	
		frontend.runCommandLoop();
		String systemOutput = uiTester.checkOutput();
		if (!systemOutput.contains("\nResult not found!\n")) {
			
			fail("searchArtistCommand() does not work correctly when no matching artists are found.");

		}
		
		TextUITester uiTester1 = new TextUITester("A\nDrake\n\nQ\n");
		Scanner scan1 = new Scanner(System.in);
		SongPlayerFrontendFD frontend1 = new SongPlayerFrontendFD(scan1, backend);
		frontend1.runCommandLoop();
		systemOutput = uiTester1.checkOutput();
		if (!systemOutput.contains("\nResult found!\n")) {

			fail("searchArtistCommand() does not work correctly when there should be matching artists found.");

		}


	}


	/*
         * Test the chooseSearchWordsPrompt() and the searchAlbumCommand() method
         * both when results are not found and when they are found
         *
         */
	@Test
	public void individualCodeTest5() {



		TextUITester uiTester = new TextUITester("B\nScorpion\n\nQ\n");
		
		SongCollectionFD backend = new SongCollectionFD();
		Scanner scan = new Scanner(System.in);
		SongPlayerFrontendFD frontend = new SongPlayerFrontendFD(scan, backend);	
		frontend.runCommandLoop();
		String systemOutput = uiTester.checkOutput();
		if (!systemOutput.contains("\nResult not found!\n")) {

			fail("searchAlbumCommand() does not work correctly when no matching albums are found.");

		}
		
		TextUITester uiTester1 = new TextUITester("B\nHer Loss\n\nQ\n");
		Scanner scan1 = new Scanner(System.in);
		SongPlayerFrontendFD frontend1 = new SongPlayerFrontendFD(scan1,backend);
		frontend1.runCommandLoop();
		systemOutput = uiTester1.checkOutput();
		if (!systemOutput.contains("\nResult found!\n")) {

			fail("searchAlbumCommand() does not work correctly when there should be matching albums found.");

		}
	}


	/*
         * Test the chooseSearchWordsPrompt() and the searchAlbumCommand() method
         * both when results are not found and when they are found
         *
         */
	@Test
	public void integrationTest1() {

		TextUITester uiTester = new TextUITester("B\nHer Loss\n\nQ\n");
	
		SongReaderInterface songReader = new SongReaderBD();
		RedBlackTreeInterfaceAE<SongTitleInterface> redBlackTreeTitle = new RedBlackTreeAEBD<SongTitleInterface>();
		RedBlackTreeInterfaceAE<SongArtistInterface> redBlackTreeArtist = new RedBlackTreeAEBD<SongArtistInterface>();
		RedBlackTreeInterfaceAE<SongAlbumInterface> redBlackTreeAlbum = new RedBlackTreeAEBD<SongAlbumInterface>();	
		SongCollectionInterface backend = new SongCollectionBD(songReader, redBlackTreeTitle, redBlackTreeArtist, redBlackTreeAlbum, "BD");	
		Scanner scan = new Scanner(System.in);
		SongBD song0 = new SongBD("Calling My Name", "Drake", "Honestly, Nevermind");
		backend.insert(song0);
		SongPlayerFrontendFD frontend = new SongPlayerFrontendFD(scan, backend);	
		frontend.runCommandLoop();
		String systemOutput = uiTester.checkOutput();
		if (!systemOutput.contains("\nResult not found!\n")) {

			fail("searchAlbumCommand() does not work correctly when no matching albums are found.");

		}
		
		TextUITester uiTester1 = new TextUITester("B\nScorpion\n\nQ\n");
		Scanner scan1 = new Scanner(System.in);
		SongBD song = new SongBD("In My Feelings", "Drake", "Scorpion");
                backend.insert(song);
		SongBD song1 = new SongBD("Calling My Name", "Drake", "Honestly, Nevermind");
		backend.insert(song);
		SongBD song2 = new SongBD("Ratchet Happy Birthday", "Drake", "Scorpion");
		SongPlayerFrontendFD frontend1 = new SongPlayerFrontendFD(scan1,backend);
		frontend1.runCommandLoop();
		systemOutput = uiTester1.checkOutput();
		if (!systemOutput.contains("\nResult found!\n")) {

			fail("searchAlbumCommand() does not work correctly when there should be matching albums found.");

		}
		if (!systemOutput.contains("\n[In My Feelings] by [Drake] in the album [Scorpion]\n")) {

			fail("searchAlbumCommand() and searchByAlbum() command do not work correctly when the frontend and backend developer code is merged");
		}
		if (systemOutput.contains("\n[Ratchet Happy Birthday] by [Drake] in the album [Scorpion]\n")) {
			fail("searchAlbumCommand() and searchByAlbum() command do not work correctly when the frontend and backend developer code is merged");
		}
	}

	/*
         * Test the chooseSearchWordsPrompt() and the searchAlbumCommand() method
         * both when results are not found and when they are found
         *
         */
	@Test
	public void integrationTest2() {



		TextUITester uiTester = new TextUITester("L\ninvalidFile\nQ\n");
	
		SongReaderInterface songReader = new SongReaderBD();
		RedBlackTreeInterfaceAE<SongTitleInterface> redBlackTreeTitle = new RedBlackTreeAEBD<SongTitleInterface>();
		RedBlackTreeInterfaceAE<SongArtistInterface> redBlackTreeArtist = new RedBlackTreeAEBD<SongArtistInterface>();
		RedBlackTreeInterfaceAE<SongAlbumInterface> redBlackTreeAlbum = new RedBlackTreeAEBD<SongAlbumInterface>();   
		SongCollectionInterface backend = new SongCollectionBD(songReader, redBlackTreeTitle, redBlackTreeArtist, redBlackTreeAlbum, "BD");	
		Scanner scan = new Scanner(System.in);
		SongPlayerFrontendFD frontend = new SongPlayerFrontendFD(scan, backend);	
		frontend.runCommandLoop();
		String systemOutput = uiTester.checkOutput();
		if (!systemOutput.contains("\nI'm sorry, this file was not found!\n")) {

			fail("searchAlbumCommand() does not work correctly when no matching files are found.");

		}
		
		TextUITester uiTester1 = new TextUITester("L\nsongData.csv\nQ\n");
		Scanner scan1 = new Scanner(System.in);
		SongPlayerFrontendFD frontend1 = new SongPlayerFrontendFD(scan1,backend);
		frontend1.runCommandLoop();
		systemOutput = uiTester1.checkOutput();
		if (!systemOutput.contains("\nData loaded!\n")) {

			fail("searchAlbumCommand() does not work correctly when there should be matching files found.");

		}
	}

	/*
         * Test the Data Wrangler's SongDW class
         *
         */
	@Test
	public void codeReviewOfDataWrangler1() {

		// test get methods 

		SongArtistDW song = new SongArtistDW("Sticky","Drake","Honestly, Nevermind");
		String expected = "Sticky";
		assertEquals(song.getTitle(), expected);
		expected = "Drake";
		assertEquals(song.getArtist(), expected);
		expected = "Honestly, Nevermind";
		assertEquals(song.getAlbum(), expected);
		SongArtistDW song1 = new SongArtistDW("Calling My Name", "Drake", "Honestly, Nevermind");
		SongArtistDW song2 = new SongArtistDW("Sticky", "Drake", "Honestly, Nevermind");
		SongArtistDW song3 = new SongArtistDW("X", "Y", "Z");

	    //Test compareTo() method 
            assertNotEquals(song.compareTo(song1), 0);
            assertNotEquals(song.compareTo(song3), 0);
            assertEquals(song.compareTo(song2), 0);


	}


	/*
         * Test the Data Wrangler's SongReaderDW class
         *
         */
	@Test
	public void codeReviewOfDataWrangler2() {
		
		SongReaderDW reader = new SongReaderDW();
            try {
                List<SongInterface> songsList = reader.readSongsFromFile("songData.csv");
                int expected = 14;
                assertEquals(songsList.size(), expected);
		String check = "Stitches";
		assertEquals(songsList.get(0).getTitle(), check);
            } catch (FileNotFoundException e) {
                // Fail if file not found
                fail();
	    }

	    try {
		    reader.readSongsFromFile("fakeFile.csv");
		    fail("the Data Wrangler's readSongsFromFile() does not throw an exception when the file cannot be found.");
	    } catch (FileNotFoundException e) {
		    // exception should be thrown 
	    }

	}
}

