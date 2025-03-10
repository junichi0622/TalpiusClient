package net.typicartist.talpius.gui.tgui.fill;

import net.typicartist.talpius.gui.tgui.shape.AbstractShape;

import java.awt.*;

public interface Fill {

    Color colorAt(AbstractShape abstractShape, float x, float y);
    
}
