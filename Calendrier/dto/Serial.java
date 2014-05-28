package Calendrier.dto;

import java.util.Date;

/**
 * @brief dto to describe a serie my purpose is to make a mock for calendar not
 *        a real application POJO
 * */
public class Serial {
    private long id;
    private String director;
    private String title;
    private String actors; // FIXME should be a list! List<Actors>
    private String status;
    private String country;
    private String genre;
    private String synopsis;
    private String imgSrc;
    private Date date; // FIXME Attention il ne s'agit pas des dates de la
    // serie, mais d'une seule serie donnée. Dans une
    // vraie application elle serait sous la forme de
    // List<Serie>::date
    private int numSerie; // de même List<Serie>::numero
    private int numSaison; // List<Serie>::saison avec en plus possibilité d'une
	                   // table saison
    private int nbSeries;
    private int nbSeasons;

    public Serial(String director, String title, String actors, String status, String country, String genre, String synopsis, String imgSrc,
	    Date date, int nbSeries, int nbSeasons) {
	super();
	this.director = director;
	this.title = title;
	this.actors = actors;
	this.status = status;
	this.country = country;
	this.genre = genre;
	this.synopsis = synopsis;
	this.imgSrc = imgSrc;
	this.date = date;
	this.nbSeries = nbSeries;
	this.nbSeasons = nbSeasons;
    }

    public Serial(String director, String title, String genre, Date date, int numSerie, int numSaison) {
	super();
	this.director = director;
	this.title = title;
	this.genre = genre;
	this.date = date;
	this.setNumSerie(numSerie);
	this.setNumSaison(numSaison);
    }

    // List<Serie>
    // List<>
    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getDirector() {
	return director;
    }

    public void setDirector(String director) {
	this.director = director;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getActors() {
	return actors;
    }

    public void setActors(String actors) {
	this.actors = actors;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public String getGenre() {
	return genre;
    }

    public void setGenre(String genre) {
	this.genre = genre;
    }

    public String getSynopsis() {
	return synopsis;
    }

    public void setSynopsis(String synopsis) {
	this.synopsis = synopsis;
    }

    public String getImgSrc() {
	return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
	this.imgSrc = imgSrc;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public int getNbSeries() {
	return nbSeries;
    }

    public void setNbSeries(int nbSeries) {
	this.nbSeries = nbSeries;
    }

    public int getNbSeasons() {
	return nbSeasons;
    }

    public void setNbSeasons(int nbSeasons) {
	this.nbSeasons = nbSeasons;
    }

    public int getNumSerie() {
	return numSerie;
    }

    public void setNumSerie(int numSerie) {
	this.numSerie = numSerie;
    }

    public int getNumSaison() {
	return numSaison;
    }

    public void setNumSaison(int numSaison) {
	this.numSaison = numSaison;
    }

}
