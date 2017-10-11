package observer;

import interfaces.Observable;
import java.awt.Color;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class WordObserver implements Observable {

    private StyledDocument styledDocument;
    private Color attributeColor;
    private String wordName;
    private AttributeSet attributeSet;

    public WordObserver(String newWord, Color newColor, StyledDocument styledDocument){
        setWordName(newWord);
        setAttributeColor(newColor);
        setStyledDocument(styledDocument);
    }


    public void setWordName(String newWord){
        wordName = newWord;
    }

    public void setAttributeColor(Color color){
        attributeColor = color;
        attributeSet = StyleContext.getDefaultStyleContext().addAttribute(StyleContext.getDefaultStyleContext().getEmptySet(), StyleConstants.Foreground, attributeColor);
    }

    public void setStyledDocument(StyledDocument newStyledDocument) {
        styledDocument = newStyledDocument;
    }


    @Override
    public void update(String word, int offset, int length) {
        if(word.trim().toLowerCase().equals(wordName.trim().toLowerCase())){
            styledDocument.setCharacterAttributes(offset, length, attributeSet, false);
        }
    }
}
