package raf.dsw.components;

public abstract class ClassContent {
    private String vidljivost;
    private String tip;
    private String naziv;
    public ClassContent(String vidljivost, String tip, String naziv){
        this.vidljivost = vidljivost;
        this.naziv = naziv;
        this.tip = tip;
    }
    public String getVidljivost(){return vidljivost;}
    public String getTip(){return tip;}
    public String getNaziv(){return naziv;}
}
