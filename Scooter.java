public class Scooter {
    private int id;
    private boolean etat;//True louer, False non louer
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

    public void louer (){
        if (!this.etat){
            this.etat=true;
            System.out.println("votre action c'est bien dérouler");
        }else{
            System.out.println("Ce scouteur est déjà louer");
        }
    }

    public void retour(){
        if(this.etat){
            this.etat=false;
        }else{
            System.out.println("error");
        }
    }
}
