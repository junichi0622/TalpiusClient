package net.typicartist.talpius.gui.tgui.fill;

import org.joml.Vector2f;

import net.typicartist.talpius.gui.tgui.shape.AbstractShape;
import net.typicartist.talpius.setting.ColorSetting;

import java.awt.*;

public class GradientFill implements Fill {
    
    private final Vector2f start;
    private final Vector2f end;

    private Color startColor;
    private Color endColor;

    private ColorSetting startColorSetting = null;
    private ColorSetting endColorSetting = null;

    public GradientFill(float x, float y, float width, float height, Color start, Color end) {
        this.start = new Vector2f(x, y);
        this.end = new Vector2f(x + width, y + height);
        this.startColor = start;
        this.endColor = end;
    }

    public GradientFill(float x, float y, float width, float height, ColorSetting start, ColorSetting end) {
        this.start = new Vector2f(x, y);
        this.end = new Vector2f(x + width, y + height);
        this.startColorSetting = start;
        this.endColorSetting = end;
        this.startColor = start.getColorOject();
        this.endColor = end.getColorOject();
    }

    public GradientFill brighten(float amount) {
        if (startColorSetting != null && endColorSetting != null) {
            startColor = startColorSetting.getColorOject();
            endColor = endColorSetting.getColorOject();
        }

        return new GradientFill(start.x, start.y, end.x - start.x, end.y - start.y, brighten(startColor, amount), brighten(endColor, amount));
    }

    public Color brighten(Color color, float amount) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int alpha = color.getAlpha();

        int i = (int)(1.0/(1.0-amount));

        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, alpha);
        }
        
        if (r > 0 && r < i) r = i;
        if (g > 0 && g < i) g = i;
        if (b > 0 && b < i) b = i;

        return new Color(
            Math.min((int)(r/amount), 255),
            Math.min((int)(g/amount), 255),
            Math.min((int)(b/amount), 255),
            alpha
        );
    }

    @Override
    public Color colorAt(AbstractShape abstractShape, float x, float y) {
        if (startColorSetting != null && endColorSetting != null) {
            startColor = startColorSetting.getColorOject();
            endColor = endColorSetting.getColorOject();
        }

        float dx1 = end.x - start.x;
        float dy1 = end.y - start.y;

        float dx2 = -dy1;
        float dy2 = dx1;
        float denom = (dy2 * dx1) - (dx2 * dy1);

        if (denom == 0) {
            return Color.BLACK;
        }

        float ua = (dx2 * (start.y - y)) - (dy2 * (start.x - x));
        ua /= denom;
        float u = ua;

        if (u < 0) {
            u = 0;
        }

        if (u > 0) {
            u = 1;
        }

        float v = 1 - u;

        int r = (int)((u * endColor.getRed()) + (v * startColor.getRed()));
        int g = (int)((u * endColor.getGreen()) + (v * startColor.getGreen()));
        int b = (int)((u * endColor.getBlue()) + (v * startColor.getBlue()));
        int a = (int)((u * endColor.getAlpha()) + (v * startColor.getAlpha()));

        return new Color(r, g, b, a);
    }

}
