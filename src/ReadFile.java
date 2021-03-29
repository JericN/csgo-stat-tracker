import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ReadFile extends MethodTools {

    final int MAX_MATCH_COUNT = 999;
    String datafile;
    String userid;
    int total_match;

    public int initiate() {
        pr("Method:Read File");
        stablish();
        pr("Feching Data");
        fetchData();
        pr("Triming Data");
        trimData();
        return total_match;
    }

    public void stablish() {
        for (int i = 0; i < MAX_MATCH_COUNT; i++) {
            stats.add(new ArrayList<String>());
            players.add(new ArrayList<String>());
        }
    }

    public void fetchData() {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setDialogTitle("Open Text File");

        int rc = filechooser.showOpenDialog(null);
        while (rc == JFileChooser.APPROVE_OPTION && !filechooser.getSelectedFile().getName().endsWith(".html")) {
            JOptionPane.showMessageDialog(null,
                    "The file " + filechooser.getSelectedFile() + " is not java source file.", "Open Error",
                    JOptionPane.ERROR_MESSAGE);
            rc = filechooser.showOpenDialog(null);
        }
        File openfile = null;
        openfile = filechooser.getSelectedFile();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(openfile);
        } catch (FileNotFoundException e) {
            pr("FileNotFoundException e");
        }

        StringBuilder contentBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        try {
            String str;
            while ((str = br.readLine()) != null) {
                contentBuilder.append(str);
            }
            br.close();
        } catch (IOException e) {
            pr("IOException e");
        }

        datafile = contentBuilder.toString();
        datafile = datafile.replaceAll("\\s", "");
    }

    public void trimData() {
        String content = datafile;
        String str = "MatchDuration";

        // Get total games
        total_match = 0;
        while (content.contains(str)) {
            content = content.substring(content.indexOf(str) + 1);
            total_match++;
        }
        content = datafile;

        // get steamID
        String flag;
        content = content.substring(content.indexOf("<!--profilearea-->"));
        content = content.substring(content.indexOf(flag = "<ahref=\"https://steamcommunity.com/") + flag.length());
        content = content.substring(content.indexOf('/') + 1);
        userid = content.substring(0, content.indexOf('/'));

        // stream match results
        content = content.substring(content.indexOf("MatchResults"));
        boolean team;
        String temp;
        String temp2;
        int time;

        for (int num = 0; num < total_match; num++) {
            // Get Map
            content = content.substring(content.indexOf(flag = "Competitive") + flag.length());
            temp = content.substring(0, content.indexOf("</td>"));
            stats.get(num).add(temp);

            // Get Date
            content = content.substring(content.indexOf(flag = "<td>") + flag.length());
            for (int i = 0; i < 2; i++) {
                temp = content.substring(0, content.indexOf("-"));
                content = content.substring(content.indexOf("-") + 1);
                stats.get(num).add(temp);
            }
            temp = content.substring(0, 2);
            stats.get(num).add(temp);
            Collections.swap(stats.get(num), 1, 3);

            // get wait time and game duration
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    flag = "WaitTime:";
                } else {
                    flag = "MatchDuration:";
                }
                content = content.substring(content.indexOf(flag) + flag.length());
                temp = content.substring(0, 2);
                time = Integer.parseInt(temp);
                time *= 60;
                temp = content.substring(3, 5);
                time += Integer.parseInt(temp);
                temp = Integer.toString(time);
                stats.get(num).add(temp);
            }

            // get scoreboard
            temp2 = content.substring(content.indexOf(flag = "\"csgo_scoreboard_score\">") + flag.length());
            temp = temp2.substring(0, temp2.indexOf(':'));
            stats.get(num).add(temp);
            temp = temp2.substring(temp2.indexOf(':') + 1, temp2.indexOf("</td>"));
            stats.get(num).add(temp);

            // get team side
            if (content.indexOf(userid) > content.indexOf("\"csgo_scoreboard_score\">")) {
                team = false;
            } else {
                team = true;
            }

            // get players names
            for (int i = 0; i < 10; i++) {
                content = content
                        .substring(content.indexOf(flag = "<aclass=\"linkTitle\"href=\"https://steamcommunity.com/")
                                + flag.length());
                content = content.substring(content.indexOf('/') + 1);
                players.get(num).add(content.substring(0, content.indexOf('"')));

                if (players.get(num).get(i).compareTo(userid) == 0) {
                    content = content.substring(content.indexOf(flag = "<td>") + flag.length());
                    for (int j = 0; j < 7; j++) {
                        temp = content.substring(0, content.indexOf("</td>"));
                        if (j == 4) {
                            if (content.indexOf("</td>") == 3) {
                                temp = "1";
                            }
                        }
                        if (j == 4 || j == 5) {
                            temp = temp.replace("&nbsp;", "0");
                            temp = temp.replaceAll("[^a-zA-Z0-9]", "");
                            if (temp == "") {
                                temp = "0";
                            }
                        }
                        stats.get(num).add(temp);
                        if (j != 6) {
                            content = content.substring(content.indexOf(flag = "<td>") + flag.length());
                        }
                    }
                }
            }
            // get result
            int A = Integer.parseInt(stats.get(num).get(6));
            int B = Integer.parseInt(stats.get(num).get(7));
            if (team) {
                if (A > B) {
                    temp = "Win";
                } else if (A < B) {
                    temp = "Lost";
                } else {
                    temp = "Tie";
                }
            } else {
                if (A > B) {
                    temp = "Lost";
                } else if (A < B) {
                    temp = "Win";
                } else {
                    temp = "Tie";
                }
            }
            stats.get(num).add(temp);
            pr("Parsing Match data: " + num);
        }
    }

    public void test1() {
        for (int i = 0; i < 100; i++) {
            prx(datafile.charAt(i));
        }
        pr("");

    }

    public void test2() {
        for (int i = 0; i < total_match; i++) {
            for (int j = 0; j < 16; j++) {
                prx(stats.get(i).get(j) + "|");
            }
            pr("");
        }

    }
}
/*
 * <th>Ping</th> <th>K</th> <th>A</th> <th>D</th> <th></th> <th>HSP</th>
 * <th>Score</th>
 */