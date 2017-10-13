package components;

import interfaces.Observable;
import observer.Subject;
import observer.WordManager;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.awt.*;

public class StyledDocumentCustom extends DefaultStyledDocument {

    final AttributeSet attrWhite = StyleContext.getDefaultStyleContext().addAttribute(StyleContext.getDefaultStyleContext().getEmptySet(), StyleConstants.Foreground, Color.white);
    private Subject subject = new Subject();

    public StyledDocumentCustom(){
        for(Observable observer: WordManager.getObservableList(this)){
            subject.register(observer);
        }
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);

        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offs);
        if (before < 0) before = 0;
        int after = findFirstNonWordChar(text, offs);

        setCharacterAttributes(before, after - before, attrWhite, false);


        String word = text.substring(before, after);
        subject.notify(word, before, after - before);
    }

    @Override
    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offset, str, a);

        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offset);
        if (before < 0) before = 0;
        int after = findFirstNonWordChar(text, offset + str.length());
        int wordL = before;
        int wordR = before;

        while (wordR <= after) {
            if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                setCharacterAttributes(wordL, wordR - wordL, attrWhite, false);
                String word = text.substring(wordL, wordR);
                subject.notify(word, wordL, wordR - wordL);

                wordL = wordR;
            }
            wordR++;
        }
    }

    private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
}
