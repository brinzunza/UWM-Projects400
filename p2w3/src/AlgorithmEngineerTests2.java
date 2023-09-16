import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmEngineerTests2 {

        @Test
        void test1(){
            RedBlackTreeAE<SongAE> test = new RedBlackTreeAE<SongAE>();
            SongAE one = new SongAE("song1");
            SongAE two = new SongAE("song2");
            SongAE three = new SongAE("song3");
            SongAE four = new SongAE("song4");
            test.insert(one);


            assertEquals(one, test.searchSpecific(one));
        }
        @Test
        void test2(){
            RedBlackTreeAE<SongAE> test = new RedBlackTreeAE<SongAE>();
            SongAE one = new SongAE("song1");
            SongAE two = new SongAE("song2");
            SongAE three = new SongAE("song3");
            SongAE four = new SongAE("song4");
            test.insert(one);
            test.insert(two);
            test.insert(three);
            test.insert(four);

            assertEquals(three, test.searchSpecific(three));
        }
        @Test
        void test3(){
            RedBlackTreeAE<SongAE> test = new RedBlackTreeAE<SongAE>();
            SongAE one = new SongAE("song1");
            SongAE two = new SongAE("song2");
            SongAE three = new SongAE("song3");
            SongAE four = new SongAE("song4");
            SongAE five = new SongAE("song5");
            test.insert(one);
            test.insert(two);
            test.insert(three);
            test.insert(four);

            assertEquals(true, test.hasNext(two));
            try{
                test.hasNext(five);
                fail();
            }
            catch(Exception e){

            }

        }
        @Test
        void test4(){
            RedBlackTreeAE<SongAE> test = new RedBlackTreeAE<SongAE>();
            SongAE one = new SongAE("song1");
            SongAE two = new SongAE("song2");
            SongAE three = new SongAE("song3");
            SongAE four = new SongAE("song4");
            test.insert(one);
            test.insert(two);
            test.insert(three);
            test.insert(four);

            assertEquals(three, test.next(two));
        }
        @Test
        void test5(){
            RedBlackTreeAE<SongAE> test = new RedBlackTreeAE<SongAE>();
            SongAE one = new SongAE("song1");
            SongAE two = new SongAE("song2");
            SongAE three = new SongAE("song3");
            SongAE four = new SongAE("song4");
            test.insert(one);
            test.insert(two);
            test.insert(three);
            test.insert(four);
            List<SongAE> iterated = new ArrayList<SongAE>();
            iterated = test.iterator();
            String iteratedString = "[";
            for(int i = 0; i<iterated.size(); i++) {
                iteratedString += (iterated.get(i).getTitle()) + ", ";
            }
            iteratedString = iteratedString.substring(0, iteratedString.length()-2);
            iteratedString += "]";


            assertEquals("[song1, song2, song3, song4]",iteratedString );
        }

        @Test
        void codeReviewOfBackendDeveloper1(){
            SongReaderDW reader = new SongReaderDW();
            RedBlackTreeAEBD<SongTitleInterface> redBlackTreeTitle = new RedBlackTreeAEBD<SongTitleInterface>();
            RedBlackTreeAEBD<SongArtistInterface> redBlackTreeArtist = new RedBlackTreeAEBD<SongArtistInterface>();
            RedBlackTreeAEBD<SongAlbumInterface> redBlackTreeAlbum = new RedBlackTreeAEBD<SongAlbumInterface>();

            List<SongInterface> songs = null;
            try {
                songs = reader.readSongsFromFile("C:\\Users\\Bruno\\IdeaProjects\\p2w3\\src\\songData.csv\\");
                SongCollectionBD expectedSongs = new SongCollectionBD(reader, redBlackTreeTitle, redBlackTreeArtist, redBlackTreeAlbum, "DW");
                SongDW song1 = new SongDW("Stitches", "Shawn Mendes", "Handwritten");
                SongDW song2 = new SongDW("Counting Stars", "OneRepublic", "Native");
                SongDW song3 = new SongDW("Natural", "Imagine Dragons", "Origins");
                SongDW song4 = new SongDW("Too Many Nights", "Metro Boomin", "Heroes and Villains");
                SongDW song5 = new SongDW("Ew", "Joji", "Nectar");
                expectedSongs.insert(song1);
                expectedSongs.insert(song2);
                expectedSongs.insert(song3);
                expectedSongs.insert(song4);
                expectedSongs.insert(song5);
                SongDW song6 = new SongDW("Null", "null", "null");

                assertTrue(expectedSongs.contains(song1));
//            assertTrue(expectedSongs.contains(song2));
//            assertTrue(expectedSongs.contains(song3));
//            assertTrue(expectedSongs.contains(song4));
//            assertTrue(expectedSongs.contains(song5));
                assertTrue(!expectedSongs.contains(song6));

            } catch (FileNotFoundException e) {
                fail();
            }
        }

        @Test
        void codeReviewOfBackendDeveloper2() {
            SongReaderDW reader = new SongReaderDW();
            RedBlackTreeAEBD<SongTitleInterface> redBlackTreeTitle = new RedBlackTreeAEBD<SongTitleInterface>();
            RedBlackTreeAEBD<SongArtistInterface> redBlackTreeArtist = new RedBlackTreeAEBD<SongArtistInterface>();
            RedBlackTreeAEBD<SongAlbumInterface> redBlackTreeAlbum = new RedBlackTreeAEBD<SongAlbumInterface>();

            List<SongInterface> songs = null;
            try {
                songs = reader.readSongsFromFile("C:\\Users\\Bruno\\IdeaProjects\\p2w3\\src\\songData.csv\\");
                SongCollectionBD expectedSongs = new SongCollectionBD(reader, redBlackTreeTitle, redBlackTreeArtist, redBlackTreeAlbum, "DW");
                SongDW song1 = new SongDW("Stitches", "Shawn Mendes", "Handwritten");
                SongDW song2 = new SongDW("Counting Stars", "OneRepublic", "Native");
                SongDW song3 = new SongDW("Natural", "Imagine Dragons", "Origins");
                SongDW song4 = new SongDW("Too Many Nights", "Metro Boomin", "Heroes and Villains");
                SongDW song5 = new SongDW("Ew", "Joji", "Nectar");
                expectedSongs.insert(song1);
                expectedSongs.insert(song2);
                expectedSongs.insert(song3);
                expectedSongs.insert(song4);
                expectedSongs.insert(song5);
                SongDW song6 = new SongDW("Null", "null", "null");


                assertTrue(!expectedSongs.isEmpty());
                assertEquals("Stitches by Shawn Mendes in the album: Handwritten", expectedSongs.searchSpecificSong("Stitches", "Shawn Mendes", "Handwritten"));
//            assertEquals("Counting Stars by OneRepublic in the album: Native", expectedSongs.searchSpecificSong("Ew", "Joji", "Nectar"));

            } catch (FileNotFoundException e) {
                fail();
            }
        }

        @Test
        void testIntegration1(){
            SongReaderDW reader = new SongReaderDW();
            RedBlackTreeAEBD<SongTitleInterface> redBlackTreeTitle = new RedBlackTreeAEBD<SongTitleInterface>();
            RedBlackTreeAEBD<SongArtistInterface> redBlackTreeArtist = new RedBlackTreeAEBD<SongArtistInterface>();
            RedBlackTreeAEBD<SongAlbumInterface> redBlackTreeAlbum = new RedBlackTreeAEBD<SongAlbumInterface>();

            List<SongInterface> songs = null;
            try {
                //data wrangler
                songs = reader.readSongsFromFile("C:\\Users\\Bruno\\IdeaProjects\\p2w3\\src\\songData.csv\\");
                SongCollectionBD expectedSongs = new SongCollectionBD(reader, redBlackTreeTitle, redBlackTreeArtist, redBlackTreeAlbum, "DW");
                SongDW song1 = new SongDW("Stitches", "Shawn Mendes", "Handwritten");
                SongDW song2 = new SongDW("Counting Stars", "OneRepublic", "Native");
                SongDW song3 = new SongDW("Natural", "Imagine Dragons", "Origins");
                SongDW song4 = new SongDW("Too Many Nights", "Metro Boomin", "Heroes and Villains");
                SongDW song5 = new SongDW("Ew", "Joji", "Nectar");

                //backend developer
                expectedSongs.insert(song1);
                expectedSongs.insert(song2);
                expectedSongs.insert(song3);
                expectedSongs.insert(song4);
                expectedSongs.insert(song5);
                SongDW song6 = new SongDW("Null", "null", "null");

                //algorithm engineer
                assertTrue(!expectedSongs.isEmpty());
                assertEquals("Stitches by Shawn Mendes in the album: Handwritten", expectedSongs.searchSpecificSong("Stitches", "Shawn Mendes", "Handwritten"));

            } catch (FileNotFoundException e) {
                fail();
            }
        }

        @Test
        void testIntegration2(){
            SongReaderDW reader = new SongReaderDW();
            RedBlackTreeAEBD<SongTitleInterface> redBlackTreeTitle = new RedBlackTreeAEBD<SongTitleInterface>();
            RedBlackTreeAEBD<SongArtistInterface> redBlackTreeArtist = new RedBlackTreeAEBD<SongArtistInterface>();
            RedBlackTreeAEBD<SongAlbumInterface> redBlackTreeAlbum = new RedBlackTreeAEBD<SongAlbumInterface>();

            List<SongInterface> songs = null;
            try {
                //data wrangler
                songs = reader.readSongsFromFile("C:\\Users\\Bruno\\IdeaProjects\\p2w3\\src\\songData.csv\\");
                SongCollectionBD expectedSongs = new SongCollectionBD(reader, redBlackTreeTitle, redBlackTreeArtist, redBlackTreeAlbum, "DW");
                SongDW song1 = new SongDW("Stitches", "Shawn Mendes", "Handwritten");
                SongDW song2 = new SongDW("Counting Stars", "OneRepublic", "Native");
                SongDW song3 = new SongDW("Natural", "Imagine Dragons", "Origins");
                SongDW song4 = new SongDW("Too Many Nights", "Metro Boomin", "Heroes and Villains");
                SongDW song5 = new SongDW("Ew", "Joji", "Nectar");
                SongDW song6 = new SongDW("Null", "null", "null");




                //backend developer
                expectedSongs.insert(song1);
                expectedSongs.insert(song2);
                expectedSongs.insert(song3);
                expectedSongs.insert(song4);
                expectedSongs.insert(song5);

                //algorithm engineer
                assertEquals(false, expectedSongs.contains(song6));

            } catch (FileNotFoundException e) {
                fail();
            }
        }

        public static void main(String[] args) {
//        RedBlackTreeAE<SongAE> test = new RedBlackTreeAE<SongAE>();
//        SongAE one = new SongAE("song1");
//        SongAE two = new SongAE("song2");
//        SongAE three = new SongAE("song3");
//        SongAE four = new SongAE("song4");
//        test.insert(one);
//        test.insert(two);
//        test.insert(three);
//        test.insert(four);
//        System.out.println(test.toInOrderString());
        }

    }


