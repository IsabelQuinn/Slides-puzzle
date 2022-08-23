import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;
import java.awt.Point;
import java.io.*;

public class Hernandez296Proy2 implements ActionListener {

    private JFrame wind;
    private JLabel lb_U, lb_fac, lb_degree, lb_course, lb_prof, lb_stud, lb_id, lb_gr, lb_date;
    private JLabel lb_timer, lb_move;
    private JTextField tx_timer, tx_move;
    private JButton btn_boton, btn_iniciar, btn_iniciar2, bt_tops;
    private JButton btn_tmp, bt_reiniciar;
    private List<JButton> btns;
    private Random rnd;
    private int l = 0, u = 0, d = 0, r = 0;
    private Point aux = new Point();
    private int[][] CO;

    DefaultListModel<String> listModel;
    JList<String> ls_lista;
    JScrollPane listScroller;

    Timer timer = new Timer(1000, new ActionListener() { // timer para temporizador
        public void actionPerformed(ActionEvent e) {
            int t, cont = 0;
            File tops = new File("C:\\Users\\ambar\\OneDrive\\Escritorio\\tops.txt");
            t = Integer.parseInt(tx_timer.getText());
            tx_timer.setText(String.valueOf(t + 1));

            for (int i = 0; i <= 14; i++) {

                if (btns.get(i).getLocation().x == CO[i][0] && btns.get(i).getLocation().y == CO[i][1]
                        && !tx_timer.getText().equals("0")) {
                    // System.out.println(cont);
                    cont++;
                }
            }
            if (cont == 15) {// GANASTE
                ArrayList<String> gamers = new ArrayList<>();
                timer.stop();
                int tiempo = Integer.parseInt(tx_timer.getText());

                JOptionPane.showMessageDialog(wind, "¡Has ganado!", "Felicidades",
                        JOptionPane.INFORMATION_MESSAGE);

                String name = JOptionPane.showInputDialog(null, "Ingrese su nombre", "Datos",
                        JOptionPane.QUESTION_MESSAGE);

                if (tops.exists()) {// SI EXISTE
                    try {
                        FileReader jugadores = new FileReader("C:\\Users\\ambar\\OneDrive\\Escritorio\\tops.txt");
                        BufferedReader leer = new BufferedReader(jugadores);

                        String resp = " ";
                        do {
                            System.out.println("AQUI ESTAMOS");
                            resp = leer.readLine();
                            listModel.addElement(resp);

                            if (resp != null) {
                                gamers.add(resp);
                            }

                        } while (resp != null);
                        for (int j = 0; j < gamers.size(); j++) {

                        }
                        if (gamers.size() == 0) {// si es igual 0
                            gamers.add(name);
                            gamers.add(String.valueOf(tiempo));
                            System.out.println("==0");
                        } else {
                            if (gamers.size() < 10) {// cuando es dif de 0
                                System.out.println("menor a 10");
                                for (int g = 1; g < gamers.size(); g = g + 2) {
                                    if (Integer.parseInt(gamers.get(g)) > tiempo) {
                                        System.out.println("menor a: " + gamers.get(g));
                                        gamers.add(g - 1, name);
                                        gamers.add(g, String.valueOf(tiempo));
                                        break;
                                    } else {
                                        System.out.println("mayor a: " + gamers.get(g));
                                        gamers.add(g + 1, name);
                                        gamers.add(g + 2, String.valueOf(tiempo));
                                        break;

                                    }

                                }
                            } else {
                                if (tiempo <= Integer.parseInt(gamers.get(9))) {
                                    System.out.println("remplazar ");
                                    for (int g2 = 1; g2 < gamers.size(); g2 = g2 + 2) {

                                        if (Integer.parseInt(gamers.get(g2)) > tiempo) {
                                            System.out.println("menor a2: " + gamers.get(g2));
                                            gamers.add(g2 - 1, name);
                                            gamers.add(g2, String.valueOf(tiempo));
                                            gamers.remove(10);
                                            gamers.remove(11);

                                            break;
                                        } else {
                                            System.out.println("menor a2: " + gamers.get(g2));
                                            gamers.add(g2 + 1, name);
                                            gamers.add(g2 + 2, String.valueOf(tiempo));
                                            gamers.remove(10);
                                            gamers.remove(11);
                                            break;
                                        }

                                    }

                                }
                            }
                        }
                        for (int v = 1; v < (gamers.size() / 2); v = v + 2) {// ordenamiento
                            for (int f = 1; f < (gamers.size() / 2) - 2; f = f + 2) {
                                if (Integer.parseInt(gamers.get(f)) > Integer.parseInt(gamers.get(f + 2))) {
                                    gamers.add(f - 1, gamers.get(f));
                                    gamers.add(f, gamers.get(f));
                                    gamers.remove(f + 1);
                                    gamers.remove(f + 2);

                                }

                            }

                        }
                        FileWriter fw = new FileWriter("C:\\Users\\ambar\\OneDrive\\Escritorio\\tops.txt", false);

                        for (int c = 0; c < gamers.size(); c++) {
                            fw.write(gamers.get(c) + "\n");
                            System.out.println(gamers.get(c) + " hello ");

                        }
                        fw.close();

                        leer.close();
                    } catch (Exception r) {

                    }
                    for (int j = 0; j < gamers.size(); j++) {
                        System.out.println(gamers.get(j));

                    }

                } else {// NO EXISTE

                    try {
                        tops = new File("C:\\Users\\ambar\\OneDrive\\Escritorio\\tops.txt");
                        if (tops.createNewFile()) {
                            System.out.println("se creo");
                        } else {
                            System.out.println("NO se creo");
                        }
                        FileReader jugadores = new FileReader(
                                "C:\\Users\\ambar\\OneDrive\\Escritorio\\tops.txt");
                        BufferedReader leer = new BufferedReader(jugadores);
                        String resp = " ";
                        do {
                            resp = leer.readLine();
                            listModel.addElement(resp);

                        } while (resp != null);
                        leer.close();

                    } catch (Exception p) {
                        System.out.println("Error de entrada2 " + r);

                    }

                }

            }
        }
    });

    Timer timer2 = new Timer(40, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int x, y;
            x = btn_tmp.getLocation().x;
            y = btn_tmp.getLocation().y;
            // btn_tmp.setLocation(x, y);
            // System.out.println(btns.get(15).getLocation());

            // Izquierda
            if (btns.get(15).getY() == y && btns.get(15).getX() == x + (55 - l)) {
                btn_tmp.setLocation(btn_tmp.getLocation().x + 5, y);// x+5 en
                                                                    // pixeles
                                                                    // que se
                                                                    // van a
                                                                    // correr
                if (x + 5 == btns.get(15).getX()) {// si la x del btn tem + 5 es igual a la posicion de mi boton en la
                                                   // posicion 15
                    timer2.stop();
                    btns.get(15).setLocation(aux);// boton en posiscion 15 toma la ubicacion de mi boton
                                                  // aux=btn_temp.getLocation
                    // System.out.println("temporal " + btn_tmp.getLocation() + " " +
                    // btns.get(15).getLocation());
                }
            }
            if (l < 60) {
                l = l + 5;
            }
            if (l == 55) {
                l = 0;
            }
            // Derecha
            if (btns.get(15).getY() == y && btns.get(15).getX() == x - (55 - r)) {
                btn_tmp.setLocation(btn_tmp.getLocation().x - 5, y);
                if (x - 5 == btns.get(15).getX()) {
                    timer2.stop();
                    btns.get(15).setLocation(aux);
                }
            }
            if (r < 60) {
                r = r + 5;
            }
            if (r == 55) {
                r = 0;
            }
            // Arriba
            if (btns.get(15).getY() == y - (55 - u) && btns.get(15).getX() == x) {
                btn_tmp.setLocation(btn_tmp.getLocation().x, y - 5);
                // System.out.println("vacio " + btns.get(15).getLocation() + " " + "temp " +
                // btn_tmp.getLocation());
                if (y - 5 == btns.get(15).getY()) {
                    // System.out.println("temporal " + btn_tmp.getLocation());
                    timer2.stop();
                    btns.get(15).setLocation(aux);
                }
            }
            if (u < 60) {
                u = u + 5;
            }
            if (u == 55) {
                u = 0;
            }
            // Abajo
            if (btns.get(15).getY() == y + (55 - d) && btns.get(15).getX() == x) {
                btn_tmp.setLocation(btn_tmp.getLocation().x, y + 5);
                // System.out.println("vacio " + btns.get(15).getLocation() + " " + "temp " +
                // btn_tmp.getLocation());
                if (y + 5 == btns.get(15).getY()) {
                    // System.out.println("temporal " + btn_tmp.getLocation());
                    timer2.stop();
                    btns.get(15).setLocation(aux);
                }
            }
            if (d < 60) {
                d = d + 5;
            }
            if (d == 55) {
                d = 0;
            }

        }
    });

    public static void main(String[] args) {
        new Hernandez296Proy2();
    }

    Hernandez296Proy2() {
        rnd = new Random();

        CO = new int[16][2];
        /**************************
         * FRAME,TEXT,LABELS,BUTTONS
         ******************************************/
        wind = new JFrame("Rompecabezas");
        wind.setBounds(200, 200, 1000, 400);
        wind.setLayout(null);
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lb_U = new JLabel("UNIVERSIDAD TECNOLOGICA DE PANAMA");
        lb_U.setBounds(50, 50, 320, 40);
        wind.add(lb_U);

        lb_fac = new JLabel("FACULTAD I.SISTEMAS C.");
        lb_fac.setBounds(50, 65, 200, 40);
        wind.add(lb_fac);

        lb_degree = new JLabel("LIC.DESARROLLO DE SOFTWARE");
        lb_degree.setBounds(50, 90, 260, 40);
        wind.add(lb_degree);

        lb_course = new JLabel("DESARROLLO DE SOFTWARE III");
        lb_course.setBounds(50, 110, 250, 40);
        wind.add(lb_course);

        lb_prof = new JLabel("PROF.RICARDO CHAN");
        lb_prof.setBounds(50, 130, 170, 40);
        wind.add(lb_prof);

        lb_stud = new JLabel("AMBAR HERNANDEZ");
        lb_stud.setBounds(50, 150, 150, 40);
        wind.add(lb_stud);

        lb_id = new JLabel("8-977-1296");
        lb_id.setBounds(50, 170, 100, 40);
        wind.add(lb_id);

        lb_gr = new JLabel("1LS221");
        lb_gr.setBounds(50, 190, 60, 40);
        wind.add(lb_gr);

        lb_date = new JLabel("08/06/2022");
        lb_date.setBounds(50, 210, 100, 40);
        wind.add(lb_date);

        lb_timer = new JLabel("Timer"); // timer
        lb_timer.setBounds(700, 20, 50, 20);
        wind.add(lb_timer);

        tx_timer = new JTextField("0"); // texF timer
        tx_timer.setBounds(750, 20, 30, 20);
        tx_timer.setEditable(false);
        wind.add(tx_timer);

        lb_move = new JLabel("Movimientos");
        lb_move.setBounds(800, 20, 110, 20);
        wind.add(lb_move);

        tx_move = new JTextField("0");
        tx_move.setBounds(910, 20, 30, 20);
        tx_move.setEditable(false);
        wind.add(tx_move);

        btn_iniciar = new JButton("Iniciar");
        btn_iniciar.setBounds(700, 60, 80, 20);
        btn_iniciar.addActionListener(this);
        wind.add(btn_iniciar);

        btn_iniciar2 = new JButton("Iniciar2");
        btn_iniciar2.setActionCommand("iniciar2");
        btn_iniciar2.setBounds(700, 85, 100, 20);
        btn_iniciar2.addActionListener(this);
        wind.add(btn_iniciar2);

        bt_tops = new JButton("Consultar");
        bt_tops.setBounds(700, 110, 100, 20);
        bt_tops.addActionListener(this);
        wind.add(bt_tops);

        bt_reiniciar = new JButton("Reiniciar juego");
        bt_reiniciar.setBounds(805, 110, 160, 20);
        bt_reiniciar.addActionListener(this);
        wind.add(bt_reiniciar);

        /*****************************
         * LISTA DE JUGADORES
         **********************************/

        listModel = new DefaultListModel<String>();
        ls_lista = new JList<String>(listModel);
        ls_lista.setVisible(false);

        listScroller = new JScrollPane(ls_lista);
        listScroller.setBounds(700, 135, 200, 160);
        listScroller.setVisible(false);
        wind.add(listScroller);

        /*****************************
         * CREACION DE BOTONES
         **********************************/

        btns = new ArrayList<>();
        for (int j = 0; j < 16; j++) {

            btn_boton = new JButton(String.valueOf(j + 1));
            btn_boton.setBounds(400 + 55 * (j % 4), 100 + 55 * (j / 4), 50, 50);
            btn_boton.setActionCommand(String.valueOf(j));
            btn_boton.addActionListener(this);
            // GUARDO LA POSICION PARA LUEGO DETERMINAR SI GANO
            CO[j][0] = 400 + 55 * (j % 4);
            CO[j][1] = 100 + 55 * (j / 4);

            btns.add(btn_boton);

            wind.add(btns.get(j));
            // System.out.println(btn_boton.getLocation() + " " + j);

        }
        btns.get(15).setVisible(false);
        wind.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        // JButton btn_tmp = new JButton();
        // System.out.println(JButton) (e.getSource())).getLocation().x + "/ " +
        // ((JButton) (e.getSource())).getLocation().y);
        // System.out.println(((JButton) (e.getSource())).getActionCommand());
        int x, y, z;
        if (e.getSource().equals(btn_iniciar)) {
            for (int j = 0; j < 15; j++) {
                z = rnd.nextInt(15);
                x = btns.get(j).getLocation().x;
                y = btns.get(j).getLocation().y;

                btns.get(j).setLocation(btns.get(z).getLocation().x, btns.get(z).getLocation().y);
                btns.get(z).setLocation(x, y);
            }
            timer.start();

        } else {
            if (!timer2.isRunning() && !((JButton) (e.getSource())).getActionCommand().equals("16")) {
                btn_tmp = (JButton) e.getSource();
                aux = new Point(btn_tmp.getLocation());
                tx_move.setText(String.valueOf(Integer.parseInt(tx_move.getText()) + 1));// CONTADOR DE MOVIMIENTOIS
                timer2.start();

            }

        }

        if (((JButton) (e.getSource())).getActionCommand().equals("iniciar2")) {// GANAR RAPIDO
            timer2.stop();
            timer.stop();
            timer.start();
            for (int index = 0; index < 16; index++) {

                btns.get(index).setLocation(CO[index][0], CO[index][1]);

            }
            btns.get(14).setLocation(CO[15][0], CO[15][1]);
            btns.get(15).setLocation(CO[14][0], CO[14][1]);

        }
        if (e.getSource().equals(bt_tops)) {// CONSULTAR JUGADORES
            ls_lista.setVisible(!ls_lista.isVisible());
            listScroller.setVisible(!listScroller.isVisible());

        }
        if (e.getSource().equals(bt_reiniciar)) {// REINICIAR JUEGO
            wind.dispose();
            new Hernandez296Proy2();
        }

    }

}
