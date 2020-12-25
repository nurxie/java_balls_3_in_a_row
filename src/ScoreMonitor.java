import java.awt.*;
import java.applet.*;

public class ScoreMonitor {
    private int score = 0;
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Score: " + String.valueOf(score), 20, 50);
    }

    public void setScore(int score) {
        this.score = score;
    }
}
