package Calendrier;

import java.util.GregorianCalendar;

interface PanelData {

    /**
     * @brief s'occupe de l'affichage en utilisant le calendrier fourni
     * */
    public void processData(GregorianCalendar gregorianCalendar);

    /**
     * @brief permet de parametrer le comportement du bouton gauche son action
     *        prévue est de retourner d'une unité de temps en arrière
     * */
    public void setPreviousAction();

    /**
     * @brief permet de parametrer le comportement du bouton droite son action
     *        prévue est d'aller d'une unité de temps en avant
     * */
    public void setNextAction();

    /**
     * @brief permet de forcer un mois
     * */
    public void setMonthTo(int number);

    /**
     * @brief permet de forcer une année
     * */
    public void setYearTo(int number);

    /**
     * @brief s'occupe de l'affichage utilise un calendrier interne
     * */
    public void processData();

    /**
     * @brief permet de forcer un jour
     * */
    public void setDayTo(int number);

}
