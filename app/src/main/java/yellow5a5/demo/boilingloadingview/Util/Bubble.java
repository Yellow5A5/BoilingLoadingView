package yellow5a5.demo.boilingloadingview.Util;

/**
 * Created by Weiwu on 16/1/3.
 */
public class Bubble {

    private static final float MAX_WIDTH = 25;

    private float centerX;
    private float centerY;


    private float radius;

    private float riseSpeed;
    private float largenSpeed;

    public Bubble(float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    //TODO 这部分晚点抽离到动画控制中。
    public void beenLargen() {
        if (radius < MAX_WIDTH) {
            radius += largenSpeed;
        }
    }

    public void beenRise() {
        centerY -= riseSpeed;
    }

    public void setRiseSpeed(float riseSpeed) {
        this.riseSpeed = riseSpeed;
    }

    public void setLargenSpeed(float largenSpeed) {
        this.largenSpeed = largenSpeed;
    }


    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
