package components;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRendererCustom extends DefaultTreeCellRenderer {
    @Override
    public Color getBackgroundNonSelectionColor() {
        return (null);
    }

    @Override
    public Color getBackgroundSelectionColor() {
        return new Color(49, 52, 64);
    }

    @Override
    public Color getBackground() {
        return (null);
    }


    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        final Component ret = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        final DefaultMutableTreeNode node = ((DefaultMutableTreeNode) (value));
        this.setText(value.toString());
        super.setForeground(Color.white);
        return ret;
    }
}
