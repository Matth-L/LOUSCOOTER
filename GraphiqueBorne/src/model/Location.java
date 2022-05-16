package GraphiqueBorne.src.model;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Location {
    static int numDeRetour = 0;
    private Date dateDebut;
    private Date dateFin;
    int scootId;

    /*
     * getter
     */
    Date getDate(boolean x) {
        if (x) {
            return dateDebut;
        }
        return dateFin;
    }

    // setter
    void setDatedeb(Date x) {
        dateDebut = x;
    }

    void setDatefin(Date x) {
        dateFin = x;
    }

    Location() {

    }

    Location(Date dateDeb, Date dateEnd, int s) {
        dateDebut = dateDeb;
        dateFin = dateEnd;
        scootId = s;
    }

    // prend une date de format jour/mois/année
    public static Date stringToDate(String date) {
        if (date.trim().equals("")) {
            // date = ""-> null
            return null;
        } else {
            SimpleDateFormat dateFrt = new SimpleDateFormat("dd/MM/yyyy");
            // pas de clémence
            dateFrt.setLenient(false);
            try {
                Date d = dateFrt.parse(date);
                return d;
            } catch (ParseException erreur) {
                // mauvaise date -> null
                return null;
            }
        }
    }

    static String dateToString(Date d) {
        SimpleDateFormat dateFrt = new SimpleDateFormat("dd/MM/yyyy");
        return dateFrt.format(d);
    }

    /*
     * teste si une date est dans un autre intervalle de date.
     * Si elle y est retourne faux sinon vrai.
     */
    boolean dateInter(Date dateDebInput, Date dateFinInput) {
        // date actuelle
        Calendar calendar = Calendar.getInstance();
        Date dateAct = Location
                .stringToDate(calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
                        + calendar.get(Calendar.YEAR));
        // si la date de fin est avant la date d'aujourd'hui
        if (dateDebInput.before(dateAct) || dateFinInput.before(dateAct)) {

            return true;
        }

        /*
         * On vérifie si la date est soit la date de début ou de fin de location est
         * dans un intervalle de location du scoote, ou si elle englobe une location
         * déjà existante
         */
        else if (dateTest(dateDebInput) || dateTest(dateFinInput) || dateDebInput.after(dateFinInput)
                || (dateDebInput.before(dateDebut) && dateFinInput.after(dateDebut))) {
            return true;
        } else {
            return false;
        }
    }

    boolean dateTest(Date dat) {
        return ((dat.after(dateDebut) && dat.before(dateFin)) || (dateFin.equals(dat) || dateDebut.equals(dat)));
    }

}
