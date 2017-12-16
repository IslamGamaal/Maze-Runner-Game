package views.flyweight;

import javafx.scene.image.Image;

public class WallImage {

    public final static String unBreakable = "/images/RegularMode/mazeObjects/mainwall.png";
    public final static String breakable = "/RegularMode/mazeObjects/mainwall.png"; //wall health [76,100]
    public final static String breakingState1 = "/RegularMode/mazeObjects/mainwall.png";//wall health [51,75]
    public final static String breakingState2 = "/RegularMode/mazeObjects/mainwall.png";//wall health [26,50]
    public final static String breakingState3 = "/RegularMode/mazeObjects/mainwall.png";//wall health [1,25]

    private static final Image unBreakableImage;
//    private static final Image breakableImage;
//    private static final Image breakingState1Image;
//    private static final Image breakingState2Image;
//    private static final Image breakingState3Image;
    static {
        unBreakableImage = new Image(PlayerImage.class.getResourceAsStream(unBreakable));
//        breakableImage = new Image(PlayerImage.class.getResourceAsStream(breakable));
//        breakingState1Image = new Image(PlayerImage.class.getResourceAsStream(breakingState1));
//        breakingState2Image = new Image(PlayerImage.class.getResourceAsStream(breakingState2));
//        breakingState3Image = new Image(PlayerImage.class.getResourceAsStream(breakingState3));
    }
    public static Image getImage(String state) {
        switch (state) {
            case unBreakable:
                return unBreakableImage;
//            case breakable:
//                return breakableImage;
//            case breakingState1:
//                return breakingState1Image;
//            case breakingState2:
//                return breakingState2Image;
//            case breakingState3:
//                return breakingState3Image;
            default:
                return unBreakableImage;
        }
    }
}
