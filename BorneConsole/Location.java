package BorneConsole;

import java.sql.Date;

public class Location {
    Date dateDebut;
    Date dateFin;

    Scooter scoot;

    Location(Date d, Date f, Scooter s) {
        dateDebut = d;
        dateFin = f;
        scoot = s;
    }

}
