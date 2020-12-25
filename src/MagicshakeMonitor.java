import java.awt.*;

public class MagicshakeMonitor {
    boolean visible = false;
    public void draw(Graphics g) {
        if(visible) {
            g.setColor(Color.black);
            g.drawString("ITS MAGICSHAKE!!!", 550, 45);
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
