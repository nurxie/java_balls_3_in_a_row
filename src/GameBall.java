import java.awt.*;

public class GameBall {
    private Color color;
    private int xCenter;
    private int yCenter;
    private int radius;
    boolean isSelected = false;

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius);
        if (isSelected){
            g.setColor(Color.white);
            g.drawOval(xCenter - 3 , yCenter - 3, 6, 6);
        }
    }

    public boolean isMouseUnderMe(int xMouse, int yMouse){
        return (xMouse-xCenter)*(xMouse-xCenter)+(yMouse-yCenter)*(yMouse-yCenter) <= radius*radius;
    }

    public void setColor(int color) {
        switch (color){
            case 1:
                this.color = Color.red;
                break;
            case 2:
                this.color = Color.gray;
                break;
            case 3:
                this.color = Color.green;
                break;
            case 4:
                this.color = Color.black;
                break;
            case 5:
                this.color = Color.blue;
                break;
            case 6:
                this.color = Color.yellow;
                break;
            case 7:
                this.color = Color.cyan;
                break;
            case 8:
                this.color = Color.orange;
                break;
            case 9:
                this.color = Color.magenta;
                break;
        }
    }

    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}