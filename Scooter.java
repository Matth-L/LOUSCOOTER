public class Scooter {
    private int id;
    private boolean etat;
    private int kilometrage;
    private String marque;
    private String modele;

    public int getId (){
        return this.id;
    }
    public boolean getEtat(){
        return this.etat;
    }
    public int getKilometrage(){
        return this.kilometrage;
    }
    public String getMarque(){
        return this.marque;
    }
    public String getModele(){
        return this.modele;
    }
    public void setId(int x){
        this.id=x;
    }
    public void setEtat (boolean x){
        this.etat=x;
    }
    public void setKilometrage (int x){
        this.kilometrage=x;
    }
    public void setMarque(String x){
        this.marque=x;
    }
    public void setModele (String x){
        this.modele=x;
    }
}
