package view;

public class DataSegmentObject {
    protected String address;
    protected String value;

    public DataSegmentObject() {
        address = "";
        value = "";
    }

    public String getTitle() {
        return address;
    }

    public void setTitle(String title) {
        this.address = title;
    }

    public String getArtist() {
        return value;
    }

    public void setArtist(String artist) {
        this.value = artist;
    }
}