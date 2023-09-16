import java.util.List;


public interface SongPlayerFrontendInterface {
//public SongPlayerFrontendXX(Scanner userInput, SongPlayerBackendInterface backend);
	public void runCommandLoop();
	public char mainMenuPrompt();
	public void loadDataCommand();
	public List<String> chooseSearchWordsPrompt();
	public void searchSongCommand(List<String> songs);
	public void searchArtistCommand(List<String> artists);
	public void searchAlbumCommand(List<String> albums);
}

