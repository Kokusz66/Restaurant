package Guest;

public class Guest {

    private static int guestCounter;

    public static void addGuest(){
        guestCounter++;
    }

    public static int returnNumberOfGuest(){
        return guestCounter;
    }
}
