package Guest;

public class Guest {

    private static int guestCounter;

    public Guest(){
        guestCounter++;
    }

    public static int returnNumberOfGuest(){
        return guestCounter;
    }
}
