package Calendrier.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Calendrier.dao.SerialsDao;
import Calendrier.dto.Serial;

public class SerialsDaoMock implements SerialsDao {

    static List<Serial> series;

    static {
	series = new ArrayList<Serial>();
    }

    public SerialsDaoMock() throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	series.add(new Serial("D.B. Weiss", "Game of Thrones", "Fantasy,Action", sdf.parse("2013/12/20"), 4, 2)
	        .setSynopsis("Les victoires de Robb Stark énervent le roi Joffrey, qui se venge sur Sansa, qui n'a que Tyrion et Bronn comme soutien dans la cour. Tyrion continue de constituer son réseau d'information au sein de Port-Réal. Catelyn essaie de réconcilier les deux frères Stannis et Renly pour s'allier contre les Lannister, or les deux veulent le Trône de Fer. Au loin, Daenerys parvient à rejoindre les portes de Qarth mais se heurte à la curiosité des dirigeants qui veulent voir les dragons."));
	series.add(new Serial("Vince Gilligan", "Breaking Bad", "Drame, Thriller, Comedie noire", sdf.parse("2014/09/20"), 12, 5)
	        .setSynopsis("Jesse, qui était sur le point de mettre le feu à la demeure de la famille White, s'est ravisé pour une raison inconnue. Walt, arrivant sur les lieux, décide de cacher la vérité à ses proches et invente une histoire de pompe à essence défectueuse. Walt décide alors d'emmener sa famille à l'hôtel, pour la protéger de Jesse."));
	series.add(new Serial("Vince Gilligan", "Breaking Bad", "Drame, Thriller, Comedie noire", sdf.parse("2014/09/21"), 13, 5)
	        .setSynopsis("Lydia vient analyser la production de Todd et son oncle dans le hangar de fabrication. Même si la pureté du produit atteint désormais 76 %, Lydia n'est pas satisfaite de l'absence de la couleur bleue, qu'elle considère comme leur marque de fabrique auprès de ses clients tchèques. Todd lui promet alors d'améliorer rapidement la qualité du produit. Lydia partie, Todd reçoit un appel de Walt proposant un contrat à son oncle : assassiner Jesse, devenu ingérable."));
	series.add(new Serial("Tim Kring", "Heroes", "Fantasy,Action", sdf.parse("2010/10/20"), 12, 1)
	        .setSynopsis("Deux semaines ont passé et l'état de Peter reste critique. Inconscient, le jeune homme est en proie à une forte fièvre, que les médecins ne parviennent pas à expliquer. Exaspéré d'attendre entre quatre murs, Nathan demande à Simone de lui présenter le fameux peintre visionnaire. Claire quant à elle fait croire à son père que sa mémoire a bien été effacée, et qu'elle n'a plus aucun souvenir des événements récents. Matt et l'agent Hanson eux dirigent une perquisition dans la fabrique de papier dirigée par Mr Bennett, sans succès. Se sachant incontrôlable, Niki se rend aux autorités afin de préserver de bien être de sa famille et pour se protéger d'elle-même."));
    }

    @Override
    public List<Serial> getSerialListForDay(Date date) {
	List<Serial> data = new ArrayList<Serial>();
	Calendar calendarDate = Calendar.getInstance();
	Calendar serieDate = Calendar.getInstance();
	calendarDate.setTime(date);

	for (Serial serie : series) {
	    serieDate.setTime(serie.getDate());
	    if (calendarDate.get(Calendar.YEAR) == serieDate.get(Calendar.YEAR)
		    && calendarDate.get(Calendar.DAY_OF_YEAR) == serieDate.get(Calendar.DAY_OF_YEAR)) {
		data.add(serie);
	    }
	}
	return data;
    }

    @Override
    public List<Serial> getSerialListForMonth(Date date) {
	List<Serial> data = new ArrayList<Serial>();
	Calendar calendarDate = Calendar.getInstance();
	Calendar serieDate = Calendar.getInstance();
	calendarDate.setTime(date);

	for (Serial serie : series) {
	    serieDate.setTime(serie.getDate());
	    if (calendarDate.get(Calendar.YEAR) == serieDate.get(Calendar.YEAR) && calendarDate.get(Calendar.MONTH) == serieDate.get(Calendar.MONTH)) {
		data.add(serie);
	    }
	}
	return data;
    }

    @Override
    public List<Serial> getSerialListForYear(Date date) {
	List<Serial> data = new ArrayList<Serial>();
	Calendar calendarDate = Calendar.getInstance();
	Calendar serieDate = Calendar.getInstance();
	calendarDate.setTime(date);

	for (Serial serie : series) {
	    serieDate.setTime(serie.getDate());
	    if (calendarDate.get(Calendar.YEAR) == serieDate.get(Calendar.YEAR)) {
		data.add(serie);
	    }
	}
	return data;
    }
}
