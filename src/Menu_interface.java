import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Menu_interface extends MethodTools implements ActionListener {

    Statistics doStat = new Statistics();
    ReadFile reader = new ReadFile();
    Graph graph = new Graph();
    //edits temp = new edits();

    ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
    Panel main_panel;
    JLabel main_panel_background;
    JPanel stat_general_frame;
    JLabel stat_panel_bg;
    JLabel menu_panel_bg;
    JButton file_button;
    JButton general_button;
    JButton graph_button;
    JComboBox select_cstat;
    JComboBox select_cgroup;
    JCheckBox select_average;
    JComboBox comboBox_0;
    JComboBox comboBox_1;
    JComboBox comboBox_2;
    JComboBox comboBox_3;
    JTextField player_input;
    JLabel player1;
    JLabel player2;
    JLabel player3;
    JLabel player4;
    JButton remove1_button;
    JButton remove2_button;
    JButton remove3_button;
    JButton remove4_button;
    JLabel r_wins;
    JLabel r_lost;
    JLabel r_tie;
    JLabel r_winrate;
    JLabel r_ping;
    JLabel r_hs;
    JLabel r_score;
    JLabel r_mvp;
    JLabel r_games;
    JLabel r_kill;
    JLabel r_assist;
    JLabel r_death;
    JLabel r_kd;
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;
    Image img4 = null;
    Font csfont = null;
    Color col_cs;
    String[] get_month_raw = new String[2];
    int[] get_month = new int[2];
    int[] get_year = new int[2];
    boolean action = true;
    boolean rightpane_stat = true;
    private JFrame frame;
    private JPanel graphwindow;
    private JPanel stat_panel_frame;
    private JPanel menu_panel_frame;
    private JPanel stat_graph_frame;
    private JLabel stat_general_bg;
    private JTextField select_csize;

    public Menu_interface() {

        setup();
        initialize();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu_interface window = new Menu_interface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Error_101");
                }
            }
        });
    }

    public void setup() {
        for (int i = 0; i < 4; i++) {
            teammate.add("Empty");
        }
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("cs_regular.ttf");
        try {
            csfont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(22f);
        } catch (FontFormatException | IOException e) {
            pr("Error_102");
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(csfont);

        try {
            img1 = ImageIO.read(new File("image/start_bg.jpg"));
            img2 = ImageIO.read(new File("image/button1_bg.png"));
            img3 = ImageIO.read(new File("image/menu_bg.jpg"));
            img4 = ImageIO.read(new File("image/stat_bg.jpg"));
        } catch (IOException e) {
            pr("Error_103");
        }

        col_cs = new Color(170, 196, 224);
    }

    private void initialize() {

        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 992, 558);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        main_panel = new Panel();
        main_panel.setBounds(0, 0, 986, 529);
        main_panel.setLayout(null);
        frame.getContentPane().add(main_panel);

        main_panel_background = new JLabel("");
        main_panel_background.setBounds(0, 0, 986, 529);
        img1 = img1.getScaledInstance(main_panel_background.getWidth(), main_panel_background.getHeight(),
                Image.SCALE_SMOOTH);
        main_panel_background.setIcon(new ImageIcon(img1));
        main_panel.add(main_panel_background);

        menu_panel_frame = new JPanel();
        menu_panel_frame.setBounds(0, 0, 210, 529);
        //main_panel.add(menu_panel_frame);
        menu_panel_frame.setLayout(null);

        stat_panel_frame = new JPanel();
        stat_panel_frame.setBounds(210, 0, 776, 529);
        //main_panel.add(stat_panel_frame);
        stat_panel_frame.setLayout(null);

        stat_general_frame = new JPanel();
        stat_general_frame.setBounds(10, 115, 750, 385);
        //stat_panel_frame.add(stat_general_frame);
        stat_general_frame.setLayout(null);

        stat_graph_frame = new JPanel();
        stat_graph_frame.setBounds(10, 55, 750, 445);
        //stat_panel_frame.add(stat_graph_frame);
        stat_graph_frame.setLayout(null);

        group_stat_panel();
        group_menu_panel();
        group_main_buttons();
    }

    public void group_stat_panel() {

        subgroup_stat_general_panel();
        subgroup_stat_graph_panel();
        stat_panel_bg = new JLabel();
        stat_panel_bg.setBounds(0, 0, 776, 529);
        img4 = img4.getScaledInstance(stat_panel_bg.getWidth(), stat_panel_bg.getHeight(), Image.SCALE_SMOOTH);
        stat_panel_bg.setIcon(new ImageIcon(img4));
    }

    public void subgroup_stat_general_panel() {
        JLabel s_win = new JLabel("Wins");
        s_win.setForeground(Color.WHITE);
        stat_general_frame.add(s_win);
        s_win.setFont(new Font("Cambria", Font.BOLD, 16));
        s_win.setBounds(42, 82, 50, 23);

        JLabel s_lost = new JLabel("Lost");
        s_lost.setForeground(Color.WHITE);
        stat_general_frame.add(s_lost);
        s_lost.setFont(new Font("Cambria", Font.BOLD, 16));
        s_lost.setBounds(42, 117, 50, 23);

        JLabel s_tie = new JLabel("Tie");
        s_tie.setForeground(Color.WHITE);
        stat_general_frame.add(s_tie);
        s_tie.setFont(new Font("Cambria", Font.BOLD, 16));
        s_tie.setBounds(42, 152, 50, 23);

        JLabel s_winrate = new JLabel("Win%");
        s_winrate.setForeground(Color.WHITE);
        stat_general_frame.add(s_winrate);
        s_winrate.setFont(new Font("Cambria", Font.BOLD, 16));
        s_winrate.setBounds(42, 187, 50, 23);

        JLabel s_ping = new JLabel("Ping");
        s_ping.setForeground(Color.WHITE);
        stat_general_frame.add(s_ping);
        s_ping.setFont(new Font("Cambria", Font.BOLD, 16));
        s_ping.setBounds(42, 222, 50, 23);

        JLabel s_hs = new JLabel("HS%");
        s_hs.setForeground(Color.WHITE);
        stat_general_frame.add(s_hs);
        s_hs.setFont(new Font("Cambria", Font.BOLD, 16));
        s_hs.setBounds(393, 51, 50, 23);

        JLabel s_mvp = new JLabel("Mvp");
        s_mvp.setForeground(Color.WHITE);
        stat_general_frame.add(s_mvp);
        s_mvp.setFont(new Font("Cambria", Font.BOLD, 16));
        s_mvp.setBounds(393, 86, 50, 23);

        JLabel s_score = new JLabel("Score");
        s_score.setForeground(Color.WHITE);
        stat_general_frame.add(s_score);
        s_score.setFont(new Font("Cambria", Font.BOLD, 16));
        s_score.setBounds(393, 121, 50, 23);

        JLabel s_games = new JLabel("Games");
        s_games.setForeground(Color.WHITE);
        stat_general_frame.add(s_games);
        s_games.setLabelFor(stat_general_frame);
        s_games.setFont(new Font("Cambria", Font.BOLD, 16));
        s_games.setBounds(42, 47, 50, 23);

        JLabel s_kill = new JLabel("Kill");
        s_kill.setForeground(Color.WHITE);
        stat_general_frame.add(s_kill);
        s_kill.setFont(new Font("Cambria", Font.BOLD, 16));
        s_kill.setBounds(220, 47, 50, 23);

        JLabel s_assist = new JLabel("Wins");
        s_assist.setForeground(Color.WHITE);
        stat_general_frame.add(s_assist);
        s_assist.setFont(new Font("Cambria", Font.BOLD, 16));
        s_assist.setBounds(220, 82, 50, 23);

        JLabel s_death = new JLabel("Death");
        s_death.setForeground(Color.WHITE);
        stat_general_frame.add(s_death);
        s_death.setFont(new Font("Cambria", Font.BOLD, 16));
        s_death.setBounds(220, 117, 50, 23);

        JLabel s_kd = new JLabel("KD");
        s_kd.setForeground(Color.WHITE);
        stat_general_frame.add(s_kd);
        s_kd.setFont(new Font("Cambria", Font.BOLD, 16));
        s_kd.setBounds(220, 152, 50, 23);

        r_wins = new JLabel("00");
        r_wins.setForeground(Color.WHITE);
        stat_general_frame.add(r_wins);
        r_wins.setFont(new Font("Cambria", Font.BOLD, 16));
        r_wins.setBounds(121, 82, 50, 23);

        r_lost = new JLabel("00");
        r_lost.setForeground(Color.WHITE);
        stat_general_frame.add(r_lost);
        r_lost.setFont(new Font("Cambria", Font.BOLD, 16));
        r_lost.setBounds(121, 117, 50, 23);

        r_tie = new JLabel("00");
        r_tie.setForeground(Color.WHITE);
        stat_general_frame.add(r_tie);
        r_tie.setFont(new Font("Cambria", Font.BOLD, 16));
        r_tie.setBounds(121, 152, 50, 23);

        r_winrate = new JLabel("00");
        r_winrate.setForeground(Color.WHITE);
        stat_general_frame.add(r_winrate);
        r_winrate.setFont(new Font("Cambria", Font.BOLD, 16));
        r_winrate.setBounds(121, 187, 50, 23);

        r_ping = new JLabel("00");
        r_ping.setForeground(Color.WHITE);
        stat_general_frame.add(r_ping);
        r_ping.setFont(new Font("Cambria", Font.BOLD, 16));
        r_ping.setBounds(121, 222, 67, 23);

        r_hs = new JLabel("00");
        r_hs.setForeground(Color.WHITE);
        stat_general_frame.add(r_hs);
        r_hs.setFont(new Font("Cambria", Font.BOLD, 16));
        r_hs.setBounds(494, 51, 50, 23);

        r_score = new JLabel("00");
        r_score.setForeground(Color.WHITE);
        stat_general_frame.add(r_score);
        r_score.setFont(new Font("Cambria", Font.BOLD, 16));
        r_score.setBounds(494, 121, 50, 23);

        r_mvp = new JLabel("00");
        r_mvp.setForeground(Color.WHITE);
        stat_general_frame.add(r_mvp);
        r_mvp.setFont(new Font("Cambria", Font.BOLD, 16));
        r_mvp.setBounds(494, 86, 50, 23);

        r_games = new JLabel("00");
        r_games.setForeground(Color.WHITE);
        stat_general_frame.add(r_games);
        r_games.setFont(new Font("Cambria", Font.BOLD, 16));
        r_games.setBounds(121, 51, 50, 23);

        r_kill = new JLabel("00");
        r_kill.setForeground(Color.WHITE);
        stat_general_frame.add(r_kill);
        r_kill.setFont(new Font("Cambria", Font.BOLD, 16));
        r_kill.setBounds(299, 51, 50, 23);

        r_assist = new JLabel("00");
        r_assist.setForeground(Color.WHITE);
        stat_general_frame.add(r_assist);
        r_assist.setFont(new Font("Cambria", Font.BOLD, 16));
        r_assist.setBounds(299, 82, 50, 23);

        r_death = new JLabel("00");
        r_death.setForeground(Color.WHITE);
        stat_general_frame.add(r_death);
        r_death.setFont(new Font("Cambria", Font.BOLD, 16));
        r_death.setBounds(299, 117, 50, 23);

        r_kd = new JLabel("00");
        r_kd.setForeground(Color.WHITE);
        stat_general_frame.add(r_kd);
        r_kd.setFont(new Font("Cambria", Font.BOLD, 16));
        r_kd.setBounds(299, 152, 50, 23);

        stat_general_bg = new JLabel();
        stat_general_bg.setBounds(0, 0, 750, 385);
        stat_general_bg.setOpaque(true);
        stat_general_bg.setBackground(new Color(24, 44, 51, 150));
        stat_general_frame.add(stat_general_bg);
    }

    public void subgroup_stat_graph_panel() {
        graphwindow = new JPanel();
        graphwindow.setBounds(10, 70, 730, 365);
        graphwindow.setBackground(Color.WHITE);
        stat_graph_frame.add(graphwindow);

        select_cstat = new JComboBox();
        select_cstat.setBounds(60, 12, 98, 22);
        stat_graph_frame.add(select_cstat);
        select_cstat.addActionListener(this);
        for (int i = 0; i < graph_cstat_choice.length; i++) {
            select_cstat.addItem(graph_cstat_choice[i]);
        }

        select_cgroup = new JComboBox();
        select_cgroup.setBounds(60, 40, 98, 22);
        stat_graph_frame.add(select_cgroup);
        select_cgroup.addActionListener(this);
        for (int i = 0; i < graph_cgroup_choice.length; i++) {
            select_cgroup.addItem(graph_cgroup_choice[i]);
        }

        select_csize = new JTextField();
        select_csize.setBounds(244, 13, 50, 20);
        stat_graph_frame.add(select_csize);
        select_csize.addActionListener(this);

        JLabel lblNewLabel_5 = new JLabel("Stat");
        lblNewLabel_5.setBounds(12, 15, 50, 16);
        stat_graph_frame.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Group");
        lblNewLabel_6.setBounds(12, 43, 50, 16);
        stat_graph_frame.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Min Match");
        lblNewLabel_7.setBounds(176, 15, 67, 16);
        stat_graph_frame.add(lblNewLabel_7);

        select_average = new JCheckBox("Average");
        select_average.setBounds(172, 38, 112, 24);
        stat_graph_frame.add(select_average);
        select_average.addActionListener(this);


    }

    public void group_menu_panel() {
        subgroup_menu_panel_map();
        subgroup_menu_panel_date();
        subgroup_menu_panel_player();

        menu_panel_bg = new JLabel();
        menu_panel_bg.setBounds(0, 0, 210, 529);
        img3 = img3.getScaledInstance(menu_panel_bg.getWidth(), menu_panel_bg.getHeight(), Image.SCALE_SMOOTH);
        menu_panel_bg.setIcon(new ImageIcon(img3));
        menu_panel_frame.add(menu_panel_bg);
    }

    public void subgroup_menu_panel_map() {
        JLabel lblNewLabel = new JLabel("Choose Maps");
        lblNewLabel.setForeground(col_cs);
        lblNewLabel.setFont(csfont.deriveFont(22f));
        lblNewLabel.setBounds(10, 10, 156, 20);
        menu_panel_frame.add(lblNewLabel);

        for (int i = 0; i < labels.length; i++) {
            JCheckBox checkbox = new JCheckBox(labels[i]);
            checkboxes.add(checkbox);
            checkboxes.get(i).setBounds(25, 41 + (28 * i), 97, 23);
            checkboxes.get(i).setOpaque(false);
            checkboxes.get(i).setForeground(col_cs);
            checkboxes.get(i).setActionCommand("check_map");
            checkboxes.get(i).addActionListener(this);
            menu_panel_frame.add(checkboxes.get(i));
        }
    }

    public void subgroup_menu_panel_date() {
        JLabel lblNewLabel_1 = new JLabel("Choose Date");
        lblNewLabel_1.setFont(csfont.deriveFont(22f));
        lblNewLabel_1.setBounds(10, 240, 156, 20);
        lblNewLabel_1.setForeground(col_cs);
        menu_panel_frame.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Start");
        lblNewLabel_2.setFont(new Font("Cambria", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(10, 267, 36, 23);
        lblNewLabel_2.setForeground(col_cs);
        menu_panel_frame.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("End");
        lblNewLabel_3.setFont(new Font("Cambria", Font.PLAIN, 12));
        lblNewLabel_3.setBounds(10, 302, 36, 23);
        lblNewLabel_3.setForeground(col_cs);
        menu_panel_frame.add(lblNewLabel_3);

        //////// COMBOBOX
        comboBox_0 = new JComboBox();
        comboBox_0.setBounds(40, 270, 63, 22);
        for (int i = 0; i < 8; i++) {
            comboBox_0.addItem(2020 - i);
        }
        comboBox_0.setSelectedItem(2013);
        get_year[0] = (int) comboBox_0.getSelectedItem();
        comboBox_0.addActionListener(this);
        menu_panel_frame.add(comboBox_0);

        comboBox_2 = new JComboBox();
        comboBox_2.setBounds(40, 302, 63, 22);
        for (int i = 0; i < 8; i++) {
            comboBox_2.addItem(2020 - i);
        }
        comboBox_2.setSelectedItem(2020);
        get_year[1] = (int) comboBox_2.getSelectedItem();
        comboBox_2.addActionListener(this);
        menu_panel_frame.add(comboBox_2);

        comboBox_1 = new JComboBox();
        comboBox_1.setBounds(110, 270, 89, 22);
        for (int i = 0; i < 12; i++) {
            comboBox_1.addItem(list_month[i]);
        }
        comboBox_1.setSelectedItem("January");
        get_month[0] = comboBox_1.getSelectedIndex() + 1;
        comboBox_1.addActionListener(this);
        comboBox_1.setActionCommand("run");
        menu_panel_frame.add(comboBox_1);

        comboBox_3 = new JComboBox();
        comboBox_3.setBounds(110, 302, 89, 22);
        for (int i = 0; i < 12; i++) {
            comboBox_3.addItem(list_month[i]);
        }
        comboBox_3.setSelectedItem("December");
        get_month[1] = comboBox_3.getSelectedIndex() + 1;
        comboBox_3.addActionListener(this);
        menu_panel_frame.add(comboBox_3);
    }

    public void subgroup_menu_panel_player() {
        JLabel lblNewLabel_4 = new JLabel("Choose Players");
        lblNewLabel_4.setBounds(10, 334, 189, 23);
        lblNewLabel_4.setForeground(col_cs);
        lblNewLabel_4.setFont(csfont.deriveFont(22f));
        menu_panel_frame.add(lblNewLabel_4);

        player_input = new JTextField();
        player_input.setBounds(10, 361, 185, 23);
        menu_panel_frame.add(player_input);
        player_input.setColumns(10);
        player_input.addActionListener(this);

        player1 = new JLabel("Empty");
        player1.setFont(new Font("Dialog", Font.BOLD, 12));
        player1.setBounds(35, 393, 164, 20);
        player1.setForeground(col_cs);
        menu_panel_frame.add(player1);

        player2 = new JLabel("Empty");
        player2.setFont(new Font("Dialog", Font.BOLD, 12));
        player2.setBounds(35, 423, 164, 20);
        player2.setForeground(col_cs);
        menu_panel_frame.add(player2);

        player3 = new JLabel("Empty");
        player3.setFont(new Font("Dialog", Font.BOLD, 12));
        player3.setBounds(35, 453, 164, 20);
        player3.setForeground(col_cs);
        menu_panel_frame.add(player3);

        player4 = new JLabel("Empty");
        player4.setFont(new Font("Dialog", Font.BOLD, 12));
        player4.setBounds(35, 483, 164, 20);
        player4.setForeground(col_cs);
        menu_panel_frame.add(player4);

        remove1_button = new JButton("1");
        remove1_button.setBounds(10, 393, 20, 20);
        menu_panel_frame.add(remove1_button);
        remove1_button.addActionListener(this);
        remove1_button.setActionCommand("removeid");

        remove2_button = new JButton("2");
        remove2_button.setBounds(10, 423, 20, 20);
        menu_panel_frame.add(remove2_button);
        remove2_button.addActionListener(this);
        remove2_button.setActionCommand("removeid");

        remove3_button = new JButton("3");
        remove3_button.setBounds(10, 453, 20, 20);
        menu_panel_frame.add(remove3_button);
        remove3_button.addActionListener(this);
        remove3_button.setActionCommand("removeid");

        remove4_button = new JButton("4");
        remove4_button.setBounds(10, 483, 20, 20);
        menu_panel_frame.add(remove4_button);
        remove4_button.addActionListener(this);
        remove4_button.setActionCommand("removeid");

    }

    public void group_main_buttons() {
        file_button = new JButton("");
        file_button.setBounds(430, 230, 135, 50);
        file_button.addActionListener(this);
        img2 = img2.getScaledInstance(file_button.getWidth(), file_button.getHeight(), Image.SCALE_SMOOTH);
        file_button.setIcon(new ImageIcon(img2));
        file_button.setContentAreaFilled(false);
        file_button.setBorderPainted(false);
        main_panel_background.add(file_button);

        general_button = new JButton("General");
        general_button.setBounds(667, 20, 78, 30);
        stat_panel_frame.add(general_button);
        general_button.addActionListener(this);
        general_button.setActionCommand("switch");

        graph_button = new JButton("Graphs");
        graph_button.setBounds(570, 20, 78, 30);
        stat_panel_frame.add(graph_button);
        graph_button.addActionListener(this);
        graph_button.setActionCommand("switch");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == comboBox_0) {
            cb_0();
        } else if (e.getSource() == comboBox_1) {
            cb_1();
        } else if (e.getSource() == comboBox_2 && action) {
            cb_2();
        } else if (e.getSource() == comboBox_3 && action) {
            cb_3();
        } else if (e.getActionCommand() == "check_map") {
            run_data();
        } else if (e.getSource() == player_input) {
            String temp = player_input.getText();
            player_input.setText(null);
            temp = temp.replaceAll("[^a-zA-Z0-9]", "").replaceAll("\n", "").replaceAll("\r", "").replaceAll("\\s", "");
            if (temp.length() < 1) {
                pr("Nothing");
                return;
            }
            if (!teammate.contains(temp)) {
                teammate.add(0, temp);
            }
            if (teammate.size() > 4) {
                teammate.remove(4);
            }
            player1.setText(teammate.get(0));
            player2.setText(teammate.get(1));
            player3.setText(teammate.get(2));
            player4.setText(teammate.get(3));
            run_data();
        } else if (e.getActionCommand() == "removeid") {
            String temp1 = ((AbstractButton) e.getSource()).getText();
            int temp2 = Integer.parseInt(temp1);
            teammate.remove(temp2 - 1);
            teammate.add("Empty");
            player1.setText(teammate.get(0));
            player2.setText(teammate.get(1));
            player3.setText(teammate.get(2));
            player4.setText(teammate.get(3));
            run_data();
        } else if (e.getSource() == file_button) {
            lifetime_games = reader.initiate();
            run_data();
            main_panel.remove(main_panel_background);
            main_panel.add(menu_panel_frame);
            main_panel.add(stat_panel_frame);
            stat_panel_frame.add(stat_general_frame);
            stat_panel_frame.add(general_button);
            stat_panel_frame.add(graph_button);
            stat_panel_frame.add(stat_panel_bg);


            SwingUtilities.updateComponentTreeUI(stat_general_frame);
        } else if (e.getActionCommand() == "switch") {
            if (e.getSource() == general_button) {
                stat_panel_frame.remove(stat_panel_bg);
                stat_panel_frame.remove(stat_graph_frame);
                stat_panel_frame.add(stat_general_frame);
                stat_panel_frame.add(stat_panel_bg);
                run_data();
                SwingUtilities.updateComponentTreeUI(stat_panel_frame);
            } else if (e.getSource() == graph_button) {
                stat_panel_frame.remove(stat_panel_bg);
                stat_panel_frame.remove(stat_general_frame);
                stat_panel_frame.add(stat_graph_frame);
                stat_panel_frame.add(stat_panel_bg);
                run_data();
                SwingUtilities.updateComponentTreeUI(stat_panel_frame);

            }
        } else if (e.getSource() == select_cstat) {
            class_stat = select_cstat.getSelectedIndex() + 9;
            run_graph();
        } else if (e.getSource() == select_cgroup) {
            class_group = select_cgroup.getSelectedIndex();
            run_graph();
        } else if (e.getSource() == select_csize) {
            try {
                class_size = Integer.parseInt(select_csize.getText());
            } catch (NumberFormatException nfe) {
                class_size = 0;
                select_csize.setText("0");
            }
            run_graph();

        } else if (e.getSource() == select_average) {
            class_onlyave = select_average.isSelected();
            run_graph();
        }
        SwingUtilities.updateComponentTreeUI(frame);
        SwingUtilities.updateComponentTreeUI(main_panel);
        SwingUtilities.updateComponentTreeUI(stat_panel_frame);
        SwingUtilities.updateComponentTreeUI(menu_panel_frame);
        SwingUtilities.updateComponentTreeUI(stat_graph_frame);
        SwingUtilities.updateComponentTreeUI(stat_general_frame);
    }

    public void cb_0() {
        action = false;
        get_year[0] = (int) comboBox_0.getSelectedItem();
        int temp_year = (int) comboBox_2.getSelectedItem();
        try {
            comboBox_2.removeAllItems();
        } catch (NullPointerException e1) {
            pr("Error_105");
        }
        for (int i = 0; i < 8; i++) {
            if (get_year[0] <= (2020 - i)) {
                comboBox_2.addItem(2020 - i);
            }
        }
        if (get_year[0] > temp_year) {

            comboBox_2.setSelectedItem(get_year[0]);
        } else {
            comboBox_2.setSelectedItem(temp_year);
        }
        action = true;
        cb_2();
    }

    public void cb_1() {
        action = false;
        get_month_raw[0] = (String) comboBox_1.getSelectedItem();
        get_month[0] = monthToInt(get_month_raw[0]);

        get_month_raw[1] = (String) comboBox_3.getSelectedItem();
        int temp_month = monthToInt(get_month_raw[1]);

        try {
            comboBox_3.removeAllItems();
        } catch (NullPointerException e1) {
            pr("Error_106");
        }

        if (get_year[0] == get_year[1]) {
            for (int i = 0; i < 12; i++) {
                if (get_month[0] <= i + 1) {
                    comboBox_3.addItem(list_month[i]);
                }
            }
            if (get_month[0] >= temp_month) {

                comboBox_3.setSelectedItem(get_month[0]);
            } else {
                comboBox_3.setSelectedItem(get_month_raw[1]);
            }
        } else {
            for (int i = 0; i < 12; i++) {
                comboBox_3.addItem(list_month[i]);
            }
            comboBox_3.setSelectedItem(list_month[temp_month - 1]);
        }
        action = true;
        cb_3();
    }

    public void cb_2() {
        get_year[1] = (int) comboBox_2.getSelectedItem();
        cb_1();
    }

    public void cb_3() {
        get_month_raw[1] = (String) comboBox_3.getSelectedItem();
        get_month[1] = monthToInt(get_month_raw[1]);
        run_data();
    }

    public void run_data() {
        pr("Reseting Variable State");
        reset_stats();
        pr("Updating Search Parameters");
        update_search();
        pr("Evaluating Information");
        if (stat_general_frame.getParent() == stat_panel_frame) {
            run_general();
        } else if (stat_graph_frame.getParent() == stat_panel_frame) {
            run_graph();
        }
        //temp.dates();
    }

    public void run_general() {

        if (total_games != 0) {

            doStat.evaluating_data("general");
        }
        pr("Generating Information");

        r_games.setText(yint(total_games));
        r_wins.setText(yint(total_wins));
        r_lost.setText(yint(total_lost));
        r_tie.setText(yint(total_tie));
        r_winrate.setText(yflt(ave_winrate));
        r_ping.setText(yflt(ave_ping));
        r_kill.setText(yflt(ave_kill));
        r_assist.setText(yflt(ave_assist));
        r_death.setText(yflt(ave_death));
        r_kd.setText(yflt(ave_kd));
        r_mvp.setText(yflt(ave_mvp));
        r_hs.setText(yflt(ave_headshot));
        r_score.setText(yflt(ave_points));
    }

    public void run_graph() {

        //if (total_games != 0) {
        doStat.evaluating_data("graph");
        //}
        pr("Generating Information");
        graphwindow.setLayout(new java.awt.BorderLayout());
        graphwindow.removeAll();
        graphwindow.add(graph.lineGraph());
        graphwindow.validate();

    }

    public void reset_stats() {
        timeseries.clear();
        map_choice.clear();
        match.clear();
        start_index = 0;
        end_index = 0;
        total_games = 0;
        total_wins = 0;
        total_lost = 0;
        total_tie = 0;
        ave_ping = 0;
        ave_kill = 0;
        ave_assist = 0;
        ave_death = 0;
        ave_mvp = 0;
        ave_headshot = 0;
        ave_points = 0;
        ave_kd = 0;
        ave_winrate = 0;
    }

    public void update_search() {
        ArrayList<String> row_array;
        for (int i = lifetime_games - 1; i >= 0; i--) {
            row_array = stats.get(i);
            int A = Integer.parseInt(row_array.get(3));
            if (A > get_year[0]) {
                start_index = i;
                break;
            } else if (A == get_year[0]) {
                int B = Integer.parseInt(row_array.get(2));
                if (B >= get_month[0]) {
                    start_index = i;
                    break;
                } else if (i == 0 && B < get_month[0]) {
                    start_index = -1;
                    break;
                }
            }
        }
        for (int i = 0; i < lifetime_games; i++) {
            row_array = stats.get(i);
            int A = Integer.parseInt(row_array.get(3));
            if (A <= get_year[1]) {
                int B = Integer.parseInt(row_array.get(2));
                if (B <= get_month[1]) {
                    end_index = i;
                    break;
                } else if (i == lifetime_games - 1) {
                    end_index = -1;
                    break;
                }
            } else if (i == lifetime_games - 1) {
                end_index = -1;
                break;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (checkboxes.get(i).isSelected()) {
                map_choice.add(checkboxes.get(i).getText());
            }
        }
        if (end_index * start_index < 0 || map_choice.size() == 0) {
            total_games = 0;
            return;
        }

        outer0:
        for (int i = end_index; i <= start_index; i++) {
            boolean present = true;
            for (int k = 0; k < map_choice.size(); k++) {
                if (stats.get(i).get(0).contains(map_choice.get(k))) {
                    break;
                } else if (k == map_choice.size() - 1) {
                    continue outer0;
                }
            }

            outer1:
            for (int m = 0; m < 4; m++) {
                if (teammate.get(m).compareTo("Empty") == 0) {
                    // if(m==0)present=true;
                    break outer1;
                }
                for (int n = 0; n < 10; n++) {
                    if (teammate.get(m).compareTo(players.get(i).get(n)) == 0) {
                        continue outer1;
                    } else {
                        present = false;
                    }
                }
                if (!present) {
                    continue outer0;
                }
            }
            match.add(i);
        }
        total_games = match.size();
    }
}
