import java.util.Scanner;

/**
 * main entry point for starting and running the SongCollection app
 */
public class SongCollectionApp {
    public static void main(String[] args) {
        SongReaderInterface songLoader = new SongReaderDW();

        RedBlackTreeInterfaceAE songTree = new RedBlackTreeAE<>();
        RedBlackTreeInterfaceAE albumTree = new RedBlackTreeAE<>();
        RedBlackTreeInterfaceAE artistTree = new RedBlackTreeAE<>();

        SongCollectionInterface backend = new SongCollectionBD(songLoader, songTree, artistTree, albumTree, "DW");

        Scanner scanner = new Scanner(System.in);
        SongPlayerFrontendInterface frontend = new SongPlayerFrontendFD(scanner, backend);
        frontend.runCommandLoop();
    }
}