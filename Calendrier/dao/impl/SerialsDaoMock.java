package Calendrier.dao.impl;

import Calendrier.dao.SerialsDao;
import Calendrier.dto.Serial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SerialsDaoMock implements SerialsDao {

    private static final List<Serial> series;

    static {
	series = new ArrayList<Serial>();
    }

    public SerialsDaoMock() throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	series.add(new Serial("D.B. Weiss", "Game of Thrones", "Fantasy,Action", sdf.parse("2013/12/20"), 5, 2)
	        .setSynopsis("Alors que l'armée de Renly rejoint celle de Stannis, Tyrion découvre les intentions de Cersei pour défendre Port-Réal : le feu grégeois. Pendant ce temps, Daenerys reçoit une proposition de mariage liée à une somme d'argent considérable. Arya, désormais au service de Tywin, se fait un nouvel ami, tout comme sa mère sur le chemin du retour vers le campement de Robb. Snow a l'occasion de participer à une expédition avec le célèbre Qhorin Mimain. Théon espère obtenir le respect de son équipage et de son père en attaquant le Nord, tandis que Bran doit prendre les décisions stratégiques pour protéger le royaume du Nord."));
	series.add(new Serial("D.B. Weiss", "Game of Thrones", "Fantasy,Action", sdf.parse("2013/12/20"), 6, 2)
	        .setSynopsis("Theon Greyjoy s'empare de Winterfell sans grand combat, et assoit son autorité en décapitant lui-même Ser Rodrik Cassel. La nouvelle arrive vite au camp de Robb Stark, qui doit y faire face alors que Tywin Lannister organise sa prochaine attaque. Tyrion doit faire front à son neveu et roi Joffrey lors d'une révolte populaire où eux deux, mais aussi Sansa et Cersei manquent de mourir sous les coups des badauds. Jon croise la route d'une sauvageonne, Ygritte, qui va mettre à l'épreuve ses valeurs. À Qarth, les dragons sont volés"));
	series.add(new Serial("D.B. Weiss", "Game of Thrones", "Fantasy,Action", sdf.parse("2013/12/20"), 7, 2)
	        .setSynopsis("Quand il apprend que Bran et Rickon ont fui Winterfell, Theon Greyjoy les traque tout en martyrisant les villageois alentour. Dans les montagnes du Nord, Jon Snow est mis à mal dans sa fierté quand Ygritte découvre que les Gardes de nuit ont fait vœu d'abstinence. À Port-Réal, Sansa a ses premières règles, et devient donc une possible mère pour les enfants de Joffrey. Dans le camp de Robb Stark, celui-ci s'absente et Jaime en profite pour tenter de s'enfuir. À Qarth, le Conseil des Treize est décimé par l'alliance entre la guilde des Conjurateurs et le négociant qui s'était porté garant pour la Khaleesi."));

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
