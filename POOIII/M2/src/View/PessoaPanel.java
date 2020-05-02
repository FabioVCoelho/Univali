package src.View;

import src.MembrosPessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class PessoaPanel extends JPanel {

    Map<MembrosPessoa, Shape> desenhos = new HashMap<>();

    public PessoaPanel() {
        desenhos.put(MembrosPessoa.CABECA, new Ellipse2D.Float(162F, 150F, 76F, 76F));
        desenhos.put(MembrosPessoa.CORPO, new Line2D.Float(200F, 226F, 200F, 325F));
        desenhos.put(MembrosPessoa.BRACO_DIREITO, new Line2D.Float(200F, 226F, 100F, 275F));
        desenhos.put(MembrosPessoa.BRACO_ESQUERDO, new Line2D.Float(200F, 226F, 300F, 275F));
        desenhos.put(MembrosPessoa.PERNA_DIREITA, new Line2D.Float(200F, 325F, 100F, 400F));
        desenhos.put(MembrosPessoa.PERNA_ESQUERDA, new Line2D.Float(200F, 325F, 300F, 400F));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D comp2D = (Graphics2D) g;
        comp2D.setColor(Color.white);
        comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle2D.Float background = new Rectangle2D.Float(0F, 0F, 600, 1200/*(float)getSize().width,(float)getSize().height*/);
        comp2D.fill(background);

        comp2D.setColor(Color.black);
        BasicStroke pen = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
        comp2D.setStroke(pen);
    }

    public JPanel getPanel() {
        return this;
    }

    public void setPessoaDraw(MembrosPessoa[] pessoaMembros, Graphics g) {
        Graphics2D comp2D = (Graphics2D) g;
        for (MembrosPessoa pessoaMembro : pessoaMembros) {
            if (pessoaMembro != null) {
                comp2D.draw(desenhos.get(pessoaMembro));
            }
        }
    }
}





