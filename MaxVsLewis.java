import java.util.ArrayList;
import java.math.BigDecimal;

public class MaxVsLewis {
	public static void main(String args[]) {
		
		//current points following the Mexican GP with 4 races remaining.
		double maxPoints = 312.5;
		double lewisPoints = 293.5;
		
		//points, sprint points & fastestLap points(if applicable) rewarded to drivers from finishing 1-20
		int pointsAwarded [] = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int sprintPointsAwarded [] = {3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
		//stores every possible scenario between Max and Lewis in a race
		ArrayList<Integer> maxScenarios = new ArrayList<Integer>();
        ArrayList<Integer> lewisScenarios = new ArrayList<Integer>();
        ArrayList<Integer> fastestLapScenarios = new ArrayList<Integer>();

		//stores the number of championship scenarios either won by Max/Lewis or drawn
		double ttlMaxWins = 0;
        double ttlLewisWins = 0;
        double ttlDraws = 0;		

		int racenum = 1;

        int ttlRaceScenarios = 0;
		double ttlChampionshipScenarios = 0;
		
		//total race scenarios for a single race
		for(int i = 0; i<20; i++) {
            for(int j = 0; j<20; j++) {
                if(i != j) {
                    for(int k = 0; k<3; k++){
                        maxScenarios.add(ttlRaceScenarios, i);
                        lewisScenarios.add(ttlRaceScenarios, j);
                        fastestLapScenarios.add(ttlRaceScenarios, k);
                        ttlRaceScenarios++;
                    }
                }
            }
		}

		//runs through every single championship scenario and records max's and lewis' possible results (could've made this more efficient but it was 3am)
		for(int i = 0; i<ttlRaceScenarios; i++) {
            for(int j = 0; j<ttlRaceScenarios; j++) {
                for(int k = 0; k<ttlRaceScenarios; k++) {
                    for(int l = 0; l<ttlRaceScenarios; l++) {
                        
                        int max = 0;
                        int lewis = 0;

						//checks possible race scenarios for Sprint race
                        if(racenum == 1) {
                            max = sprintPointsAwarded[maxScenarios.get(i)];
                            lewis = sprintPointsAwarded[lewisScenarios.get(i)];

                            racenum++;
                        }

                        max = pointsAwarded[maxScenarios.get(i)]+pointsAwarded[maxScenarios.get(j)];
                        lewis = pointsAwarded[lewisScenarios.get(i)]+pointsAwarded[lewisScenarios.get(j)];

						//fastest lap scenarios
                        if (fastestLapScenarios.get(i)==0 && maxScenarios.get(i)>10) {
                            max++;
                        }
                        else if (fastestLapScenarios.get(i)==1 && lewisScenarios.get(i)>10) {
                            lewis++;
                        }

                        if (fastestLapScenarios.get(j)==0 && maxScenarios.get(j)>10) {	
                            max++;
                        }
                        else if (fastestLapScenarios.get(j)==1  && lewisScenarios.get(j)>10) {
                            lewis++;
                        }

                        if (fastestLapScenarios.get(k)==0 && maxScenarios.get(k)>10) {
                            max++;
                        }
                        else if (fastestLapScenarios.get(k)==1  && lewisScenarios.get(k)>10) {
                            lewis++;
                        }

                        if (fastestLapScenarios.get(l)==0 && maxScenarios.get(l)>10) {
                            max++;
                        }
                        else if (fastestLapScenarios.get(l)==1 && lewisScenarios.get(l)>10) {
                            lewis++;
                        }

                        max+=maxPoints;
                        lewis+=lewisPoints;
						
                        if (max>lewis) {
                            ttlMaxWins++;
                        }
                        else if (lewis>max) {
                            ttlLewisWins++;
                        }
                        else if (max==lewis) {
                            ttlDraws++;
                        }


                        if(ttlChampionshipScenarios%1000000000 == 0) {
                            System.out.println(ttlChampionshipScenarios);
                        }


                        ttlChampionshipScenarios++;
                    }
                }
            }
        }

		//prints out results
        System.out.println("max wins: "+BigDecimal.valueOf(ttlMaxWins).toPlainString()+" ("+((ttlMaxWins/ttlChampionshipScenarios)*100)+"%)");
        System.out.println("lewis wins: "+BigDecimal.valueOf(ttlLewisWins).toPlainString()+" ("+((ttlLewisWins/ttlChampionshipScenarios)*100)+"%)");
        System.out.println("draws: "+BigDecimal.valueOf(ttlDraws).toPlainString()+" ("+((ttlDraws/ttlChampionshipScenarios)*100)+"%)");
        System.out.println("total championship scenarios: "+BigDecimal.valueOf(ttlChampionshipScenarios).toPlainString());
        
	}
}