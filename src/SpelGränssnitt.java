import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class SpelGränssnitt extends JFrame implements ActionListener {

    JPanel jp = new JPanel();
    JPanel knappGrid = new JPanel();
    JPanel tomPanel = new JPanel();
    JPanel nyttSpelPlats = new JPanel();
    JButton knapp1 = new JButton("1");
    JButton knapp2 = new JButton("2");
    JButton knapp3 = new JButton("3");
    JButton knapp4 = new JButton("4");
    JButton knapp5 = new JButton("5");
    JButton knapp6 = new JButton("6");
    JButton knapp7 = new JButton("7");
    JButton knapp8 = new JButton("8");
    JButton knapp9 = new JButton("9");
    JButton knapp10 = new JButton("10");
    JButton knapp11 = new JButton("11");
    JButton knapp12 = new JButton("12");
    JButton knapp13 = new JButton("13");
    JButton knapp14 = new JButton("14");
    JButton knapp15 = new JButton("15");
    JButton tomKnapp = new JButton("osynlig");
    JButton nyttSpelKnapp = new JButton("Nytt spel");
    JButton vinstKnapp = new JButton("Vinn");


    SpelGränssnitt() {
        this.add(jp);
        jp.setLayout(new BorderLayout());
        jp.add(knappGrid, BorderLayout.SOUTH);
        jp.add(nyttSpelPlats, BorderLayout.NORTH);
        jp.add(tomPanel, BorderLayout.CENTER);
        knappGrid.setLayout(new GridLayout(4, 4));
        nyttSpelPlats.setLayout(new FlowLayout());
        tomPanel.add(new JLabel("\n"));
        knappGrid.add(knapp1);
        knapp1.setName("0");
        knapp1.setPreferredSize(new Dimension(70, 70));
        knappGrid.add(knapp2);
        knapp2.setName("1");
        knappGrid.add(knapp3);
        knapp3.setName("2");
        knappGrid.add(knapp4);
        knapp4.setName("3");
        knappGrid.add(knapp5);
        knapp5.setName("4");
        knappGrid.add(knapp6);
        knapp6.setName("5");
        knappGrid.add(knapp7);
        knapp7.setName("6");
        knappGrid.add(knapp8);
        knapp8.setName("7");
        knappGrid.add(knapp9);
        knapp9.setName("8");
        knappGrid.add(knapp10);
        knapp10.setName("9");
        knappGrid.add(knapp11);
        knapp11.setName("10");
        knappGrid.add(knapp12);
        knapp12.setName("11");
        knappGrid.add(knapp13);
        knapp13.setName("12");
        knappGrid.add(knapp14);
        knapp14.setName("13");
        knappGrid.add(knapp15);
        knapp15.setName("14");
        knappGrid.add(tomKnapp);
        tomKnapp.setName("15");
        tomKnapp.setVisible(false);
        knapp1.addActionListener(this);
        knapp2.addActionListener(this);
        knapp3.addActionListener(this);
        knapp4.addActionListener(this);
        knapp5.addActionListener(this);
        knapp6.addActionListener(this);
        knapp7.addActionListener(this);
        knapp8.addActionListener(this);
        knapp9.addActionListener(this);
        knapp10.addActionListener(this);
        knapp11.addActionListener(this);
        knapp12.addActionListener(this);
        knapp13.addActionListener(this);
        knapp14.addActionListener(this);
        knapp15.addActionListener(this);
        vinstKnapp.addActionListener(this);
        nyttSpelPlats.add(vinstKnapp);
        vinstKnapp.setBackground(Color.green);
        vinstKnapp.setVisible(false);
        nyttSpelPlats.add(nyttSpelKnapp);
        nyttSpelKnapp.addActionListener(this);
        setVisible(true);
        knappGrid.setVisible(false);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nyttSpelKnapp) {
            nyttSpelTryck();
        } else if (e.getSource() == vinstKnapp){
                System.out.println("EEEEEE");
        } else {
            brickatryck((JButton) e.getSource());
        }
    }
    public void nyttSpelTryck(){
        knappGrid.setVisible(true);
        vinstKnapp.setVisible(true);
        Random slumpare = new Random();
        int i = -1;
        int i2 = 0;
        Component[] components = knappGrid.getComponents();
            knappGrid.removeAll();
        for(Component comp :components) {
            i++;
            int randomIndexToSwap = slumpare.nextInt(components.length);
            Component temp = components[randomIndexToSwap];
            components[randomIndexToSwap] = components[i];
            components[i] = temp;
        }
        for(Component comp :components) {
            comp.setName(String.valueOf(i2));
            i2++;
            knappGrid.add(comp);
        }
        pack();
        setLocationRelativeTo(null);
        knappGrid.validate();
        knappGrid.repaint();
}

    public void brickatryck(JButton source) {
        int tomRutaPlats = 0;
        String temporärIndex;
        Component[] components = knappGrid.getComponents();

        Component[] rad1 = skapaRad(components, 0);
        Component[] rad2 = skapaRad(components, 4);
        Component[] rad3 = skapaRad(components, 8);
        Component[] rad4 = skapaRad(components, 12);

        Component[][] fullGrid = new Component[4][4];
        fullGrid[0] = rad1;
        fullGrid[1] = rad2;
        fullGrid[2] = rad3;
        fullGrid[3] = rad4;

        int trycktRad = 0;
        int trycktKolumn = 0;
        Component tomRuta = null;
        int tomRad = 0;
        int tomKolumn = 0;

        knappGrid.removeAll();
        Component temp = components[parseInt(source.getName())];
        for (Component comp : components) {
            JButton rutaSomKollas = (JButton) comp;
            if (rutaSomKollas.getText().equals("osynlig")) {
                tomRuta = comp;
                tomRutaPlats = parseInt(comp.getName());
                break;
            }
        }
        for (int r = 0; r < fullGrid.length; r++) {
            for (int k = 0; k < fullGrid[0].length; k++) {
                if (source == fullGrid[r][k]) {
                    trycktRad = r;
                    trycktKolumn = k;
                }
                if (tomRuta == fullGrid[r][k]) {
                    tomRad = r;
                    tomKolumn = k;
                }
            }
        }
        Component trycktRuta = fullGrid[trycktRad][trycktKolumn];
        Component tryckbarRuta1;
        Component tryckbarRuta2;
        Component tryckbarRuta3;
        Component tryckbarRuta4;
        try{
        tryckbarRuta1 = fullGrid[tomRad - 1][tomKolumn];
        }
        catch (ArrayIndexOutOfBoundsException e){
            tryckbarRuta1 = null;
        }
        try{
         tryckbarRuta2 = fullGrid[tomRad + 1][tomKolumn];
        }
        catch (ArrayIndexOutOfBoundsException e){
            tryckbarRuta2 = null;
        }
        try{
         tryckbarRuta3 = fullGrid[tomRad ][tomKolumn-1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            tryckbarRuta3 = null;
        }
        try{
         tryckbarRuta4 = fullGrid[tomRad][tomKolumn+1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            tryckbarRuta4 = null;
        }

            if (trycktRuta == tryckbarRuta1 || trycktRuta == tryckbarRuta2 ||
                    trycktRuta == tryckbarRuta3 || trycktRuta == tryckbarRuta4) {

                components[parseInt(source.getName())] = components[tomRutaPlats];
                components[tomRutaPlats] = temp;

                for (Component comp : components) {
                    knappGrid.add(comp);
                }
                temporärIndex = source.getName();
                source.setName(tomKnapp.getName());
                tomKnapp.setName(temporärIndex);
                knappGrid.validate();
                knappGrid.repaint();

                if (knappGrid.getComponent(0) == knapp1 && knappGrid.getComponent(1) == knapp2 && knappGrid.getComponent(2) == knapp3 &&
                        knappGrid.getComponent(3) == knapp4 && knappGrid.getComponent(4) == knapp5 && knappGrid.getComponent(5) == knapp6 &&
                        knappGrid.getComponent(6) == knapp7 && knappGrid.getComponent(7) == knapp8 && knappGrid.getComponent(8) == knapp9 &&
                        knappGrid.getComponent(9) == knapp10 && knappGrid.getComponent(10) == knapp11 && knappGrid.getComponent(11) == knapp12 &&
                        knappGrid.getComponent(12) == knapp13 && knappGrid.getComponent(13) == knapp14 && knappGrid.getComponent(14) == knapp15) {
                    JOptionPane.showMessageDialog(null, "Grattis, du vann!");
                }
            } else {
                for (Component comp : components) {
                    knappGrid.add(comp);
                }
                knappGrid.validate();
                knappGrid.repaint();
            }

            knappGrid.validate();
            knappGrid.repaint();
    }


        public Component[] skapaRad (Component[]components,int startpunkt){
            Component[] rad = new Component[4];
            for (int i = 0; i < rad.length; i++) {
                rad[i] = components[startpunkt];
                startpunkt++;
            }
            return rad;
        }
    }


