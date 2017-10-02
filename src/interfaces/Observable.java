package interfaces;

import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;

public interface Observable {
    public void update(String word, int offset, int length);
}
