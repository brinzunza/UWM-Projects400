public class SongAE implements Comparable<SongAE> {

    private String title;
    public SongAE(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public int compareTo(SongAE o) {
        String str1 = this.title;
        String str2 = o.title;

        int result = str1.compareTo(str2);

        return result;
    }
}
