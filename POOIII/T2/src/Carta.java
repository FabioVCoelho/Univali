package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Carta {

    protected boolean virada = true;
    protected Image image;
    protected Integer id;
    protected Image imagemViradaParaBaixo;

    {
        try {
            imagemViradaParaBaixo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Carta(Integer id) {
        id = id + 1;
        if (id > 10) id = id - 10;
        this.image = inserirImagemNaCarta(id);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Image getImagem() {
        if (!virada) {
            return imagemViradaParaBaixo;
        }
        return this.image;
    }

    public void virar() {
        this.virada = true;
    }

    public void desvirar() {
        this.virada = false;
    }

    private Image inserirImagemNaCarta(int i) {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(i + ".png"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return image;
    }

    public boolean estaVirada() {
        return virada;
    }

    public Image obterImagemDaCarta() {
        return image;
    }
}
