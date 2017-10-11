package observer;

import interfaces.Observable;

import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WordManager {

    public static List<Observable> getObservableList(StyledDocument styledDocument){
        List<Observable> wordObserverList = new ArrayList();

        Color blueColor = new Color(128,187,252);
        Color pinkColor = new Color(219,158,245);
        Color redColor = new Color(249,134,123);
        Color greenColor = new Color(163,238,160);
        Color yellowColor = new Color(247,197,101);

        //Modifiers
        wordObserverList.add(new WordObserver("public", blueColor, styledDocument));
        wordObserverList.add(new WordObserver("private", blueColor, styledDocument));
        wordObserverList.add(new WordObserver("protected", blueColor, styledDocument));

        //logic
        wordObserverList.add(new WordObserver("if", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("else", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("switch", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("case", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("while", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("for", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("return", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("this", pinkColor, styledDocument));
        wordObserverList.add(new WordObserver("super", pinkColor, styledDocument));

        //others
        wordObserverList.add(new WordObserver("function", redColor, styledDocument));
        wordObserverList.add(new WordObserver("class", redColor, styledDocument));
        wordObserverList.add(new WordObserver("include", redColor, styledDocument));
        wordObserverList.add(new WordObserver("import", redColor, styledDocument));
        wordObserverList.add(new WordObserver("extends", redColor, styledDocument));
        wordObserverList.add(new WordObserver("implements", redColor, styledDocument));
        wordObserverList.add(new WordObserver("interface", redColor, styledDocument));
        wordObserverList.add(new WordObserver("void", redColor, styledDocument));
        wordObserverList.add(new WordObserver("static", blueColor, styledDocument));
        wordObserverList.add(new WordObserver("main", blueColor, styledDocument));
        wordObserverList.add(new WordObserver("system", blueColor, styledDocument));
        wordObserverList.add(new WordObserver("package", blueColor, styledDocument));

        wordObserverList.add(new WordObserver("@override", yellowColor, styledDocument));
        wordObserverList.add(new WordObserver("final", yellowColor, styledDocument));
        wordObserverList.add(new WordObserver("/", yellowColor, styledDocument));
        wordObserverList.add(new WordObserver("*", yellowColor, styledDocument));


        //data types
        wordObserverList.add(new WordObserver("int", greenColor, styledDocument));
        wordObserverList.add(new WordObserver("string", greenColor, styledDocument));
        wordObserverList.add(new WordObserver("double", greenColor, styledDocument));
        wordObserverList.add(new WordObserver("float", greenColor, styledDocument));
        wordObserverList.add(new WordObserver("date", greenColor, styledDocument));
        wordObserverList.add(new WordObserver("boolean", greenColor, styledDocument));
        wordObserverList.add(new WordObserver("color", greenColor, styledDocument));

        return wordObserverList;
    }
}
