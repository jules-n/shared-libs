package shared.libs.nonFunctionalLib;

import java.util.Random;

class Greetings {
    public static String helloThere() {
        var rndIndex = new Random().nextInt();
        return "General Kenobi? - " + rndIndex;
    }
}
