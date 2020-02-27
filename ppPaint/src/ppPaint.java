import Graphics.ppPaintGraphics;

public class ppPaint {
    static final String FRM_TITLE = "ppPaint";
    static final int FRM_WIDTH = 700;
    static final int FRM_HEIGHT = 500;
    static final int FRM_MIN_WIDTH = 600;
    static final int FRM_MIN_HEIGHT = 400;

    public static void main(String[] args) {
        new ppPaintGraphics(FRM_TITLE, FRM_WIDTH, FRM_HEIGHT, FRM_MIN_WIDTH, FRM_MIN_HEIGHT);
    }
}
