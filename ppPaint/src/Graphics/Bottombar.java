package Graphics;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class Bottombar extends JPanel {
    public Bottombar(BorderLayout layout) {
        super(layout);
        setPreferredSize(new Dimension(0, 100));

        // left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        JPanel curColorPanel = new JPanel();
        JPanel alterColorPanel = new JPanel();
        leftPanel.add(curColorPanel, BorderLayout.WEST);
        leftPanel.add(alterColorPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.WEST);

        // center panel
        JPanel centerPanel = new JPanel();
        JColorChooser swatches = new JColorChooser();
        swatches.getSelectionModel().addChangeListener((ChangeEvent e) -> {
            ppPaintGraphics.board.setColor(swatches.getColor());
        });
        swatches.setPreviewPanel(new JPanel());
        AbstractColorChooserPanel[] panels = swatches.getChooserPanels();
        for(AbstractColorChooserPanel p : panels) {
            String displayName = p.getDisplayName();
            switch (displayName) {
                case "HSV":
                    swatches.removeChooserPanel(p);
                    break;
                case "HSL":
                    swatches.removeChooserPanel(p);
                    break;
                case "CMYK":
                    swatches.removeChooserPanel(p);
                    break;
                case "RGB":
                    swatches.removeChooserPanel(p);
                    break;
            }
        }
        centerPanel.add(swatches);
        add(centerPanel);

        // right panel
        JPanel rightPanel = new JPanel();
        JRadioButton fillRadio = new JRadioButton("Fill");
        JRadioButton nofillRadio = new JRadioButton("No fill");
        ButtonGroup bg = new ButtonGroup();
        bg.add(fillRadio);
        bg.add(nofillRadio);
        add(rightPanel, BorderLayout.EAST);
    }
}
