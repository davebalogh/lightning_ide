package components;

import helpers.DocumentManager;
import interfaces.Documentable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class JPanelWithTree extends JPanel {
    public JTree getTreeDocument() {
        return treeDocument;
    }

    JTree treeDocument;

    public JScrollPaneCustom getjScrollPaneCustom() {
        return jScrollPaneCustom;
    }

    JScrollPaneCustom jScrollPaneCustom;

    public JPanelWithTree(DocumentManager documentManager, Documentable openFile){
        JScrollPaneCustom lastJSCrollPaneAdded = new JScrollPaneCustom(openFile);
        lastJSCrollPaneAdded.setDocumentManager(documentManager);
        lastJSCrollPaneAdded.initialize(openFile.getText());
        this.jScrollPaneCustom = lastJSCrollPaneAdded;
        this.setLayout(new BorderLayout());

        if(!openFile.getIsNewDocument()){

            ArrayList<File> fileList = documentManager.getSiblingDocuments(openFile.getFile());

            this.setBackground(new Color(38,40,49));
            //create the root node
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");


            for(File singleFile: fileList){
                if(singleFile.isDirectory()){
                    DefaultMutableTreeNode folderNode = new DefaultMutableTreeNode(singleFile.getName());
                    for(File secondLineFile: singleFile.listFiles()){
                        if(!secondLineFile.isHidden()){
                            folderNode.add(new DefaultMutableTreeNode(secondLineFile.getName()));
                        }

                    }
                    if(folderNode.getChildCount() > 0){
                        root.add(folderNode);
                    }
                }
                else{
                    if(!singleFile.isHidden()) {
                        root.add(new DefaultMutableTreeNode(singleFile.getName()));
                    }
                }
            }

            //create the tree by passing in the root node
            treeDocument = new JTree(root);
            treeDocument.expandRow(0);

            treeDocument.setRootVisible(false);
            JScrollPane jScrollPane = new JScrollPane(treeDocument);
            jScrollPane.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(38,40,49)));
            jScrollPane.setSize(300, 500);
            treeDocument.setBackground(new Color(38,40,49));
            treeDocument.setCellRenderer(new TreeCellRendererCustom());
            add(jScrollPane, BorderLayout.WEST);
        }

        this.setBorder(BorderFactory.createEmptyBorder());
        add(jScrollPaneCustom, BorderLayout.CENTER);
    }
}
