package Week1;
public class Exercise01_11 {
    public void main() {
        int worldPopulation = 312032486;
        System.out.println(worldPopulation);
        System.out.println("Population after 1 year: " + math(worldPopulation, 1));
        System.out.println("Population after 2 years: " + math(worldPopulation, 2));
        System.out.println("Population after 3 years: " + math(worldPopulation, 3));
        System.out.println("Population after 4 years: " + math(worldPopulation, 4));
        System.out.println("Population after 5 years: " + math(worldPopulation, 5));
        int x = 2;
        int y = 1;
        x *= y + 1;
        System.out.println(x);
    }


    public float math(int currentPop, int years) {
        int secondsInYear = 31536000;
        float births = (secondsInYear / 7) * years;
        float deaths = (secondsInYear / 13) * years;
        float immi = (secondsInYear / 45) * years;
        float newPop = currentPop + births - deaths + immi;
        //System.out.println("b: "+ births + " d: " + deaths + " i: " + immi);
        return newPop;
    }
}
