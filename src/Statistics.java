import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

import java.util.ArrayList;

public class Statistics extends MethodTools {

    float[][] value;
    int[][] count;
    float[][] temp_win;
    float[][] temp_lost;
    float twin = 0;
    float tlost = 0;
    float tvalue = 0;
    float tcount = 0;
    int cur_date[] = {0, 0, 0, 0};
    int temp_date[] = {0, 0, 0, 0};
    boolean next = false;
    Day cur_day = new Day();
    ArrayList<String> stat;


    public void evaluating_data(String input) {
        timeseries.clear();
        if (input == "general") {
            analysis_general();
        } else if (input == "graph") {
            analysis_timeplot();
        }
    }

    public void analysis_general() {
        int[] sum = new int[7];
        int[] flag_stat = {8, 9, 10, 11, 12, 13, 14};
        for (int k = 0; k < 7; k++) {
            sum[k] = 0;
        }

        for (int i = 0; i < match.size(); i++) {
            int num = match.get(i);
            if (stats.get(num).get(15).contains("Win")) {
                total_wins++;
            } else if (stats.get(num).get(15).contains("Lost")) {
                total_lost++;
            } else if (stats.get(num).get(15).contains("Tie")) {
                total_tie++;
            } else {
                pr("error 102");
            }
            for (int j = 0; j < 7; j++) {
                sum[j] = sum[j] + xint(stats.get(num).get(flag_stat[j]));
            }
        }
        total_kill = sum[1];
        total_assist = sum[2];
        total_death = sum[3];
        total_mvp = sum[4];
        ave_ping = (float) sum[0] / total_games;
        ave_kill = (float) sum[1] / total_games;
        ave_assist = (float) sum[2] / total_games;
        ave_death = (float) sum[3] / total_games;
        ave_mvp = (float) sum[4] / total_games;
        ave_headshot = (float) sum[5] / total_games;
        ave_points = (float) sum[6] / total_games;
        if (total_wins == 0) {
            ave_winrate = 0;
        } else {
            ave_winrate = (float) total_wins / (total_games - total_tie);
        }
        if (total_kill == 0) {
            ave_kd = 0;
        } else if (ave_death == 0) {
            ave_kd = 99;
        } else {
            ave_kd = ave_kill / ave_death;
        }
    }

    public void tp_append() {
        for (int j = 0; j < map_choice.size(); j++) {
            if (stat.get(0).compareTo(map_choice.get(j)) == 0) {
                if (class_stat <= 14) {
                    count[0][j]++;
                    value[0][j] += xflt(stat.get(class_stat));
                } else if (class_stat == 15) {
                    if (stat.get(15).contains("Win")) {
                        count[0][j]++;
                        temp_win[0][j]++;
                    } else if (stat.get(15).contains("Lost")) {
                        count[0][j]++;
                        temp_lost[0][j]++;
                    }
                } else if (class_stat == 16) {
                    count[0][j]++;
                    temp_win[0][j] += xflt(stat.get(9));
                    temp_lost[0][j] += xflt(stat.get(11));
                }
                return;
            }
        }
    }

    public void tp_next() {
        for (int j = 0; j < map_choice.size(); j++) {
            tcount += count[0][j];
        }
        if (tcount >= class_size) {
            if (class_stat <= 14) {
                for (int j = 0; j < map_choice.size(); j++) {
                    if (count[0][j] > 0) {
                        tvalue += value[0][j];
                        timeseries.get(0).get(j).add(cur_day, value[0][j] / count[0][j]);
                    }
                    count[0][j] = 0;
                    value[0][j] = 0;
                }
                timeseries.get(0).get(map_choice.size()).add(cur_day, tvalue / tcount);
            } else if (class_stat == 15) {
                for (int j = 0; j < map_choice.size(); j++) {
                    if (count[0][j] > 0) {
                        twin += temp_win[0][j];
                        tlost += temp_lost[0][j];
                        timeseries.get(0).get(j).add(cur_day, (float) temp_win[0][j] / (temp_win[0][j] + temp_lost[0][j]));
                    }
                    temp_win[0][j] = 0;
                    temp_lost[0][j] = 0;
                }
                timeseries.get(0).get(map_choice.size()).add(cur_day, (float) twin / (twin + tlost));
            } else if (class_stat == 16) {
                for (int j = 0; j < map_choice.size(); j++) {
                    if (count[0][j] > 0) {
                        twin += temp_win[0][j];
                        tlost += temp_lost[0][j];
                        timeseries.get(0).get(j).add(cur_day, (float) temp_win[0][j] / temp_lost[0][j]);
                    }
                }
                timeseries.get(0).get(map_choice.size()).add(cur_day, (float) twin / tlost);
            }
        }
    }

    public void tp_reset() {

        for (int j = 0; j < map_choice.size(); j++) {
            count[0][j] = 0;
            value[0][j] = 0;
            temp_win[0][j] = 0;
            temp_lost[0][j] = 0;
        }
        tvalue = 0;
        tcount = 0;
        twin = 0;
        tlost = 0;

        for (int j = 0; j < 3; j++) {
            cur_date[j] = temp_date[j];
        }
        cur_day = new Day(cur_date[0], cur_date[1], cur_date[2]);

    }

    public void analysis_timeplot() {
        value = new float[4][map_choice.size()];
        count = new int[4][map_choice.size()];
        temp_win = new float[4][map_choice.size()];
        temp_lost = new float[4][map_choice.size()];
        tvalue = 0;
        tcount = 0;
        twin = 0;
        tlost = 0;

        for (int i = 0; i < 4; i++) {
            timeseries.add(new ArrayList<TimeSeries>());
        }

        for (int i = 0; i < map_choice.size(); i++) {
            timeseries.get(0).add(new TimeSeries(map_choice.get(i)));
            count[0][i] = 0;
            value[0][i] = 0;
            temp_win[0][i] = 0;
            temp_lost[0][i] = 0;
        }
        timeseries.get(0).add(new TimeSeries("All"));

        for (int i = 0; i < match.size(); i++) {
            stat = stats.get(match.get(i));

            // get date
            for (int j = 0; j < 3; j++) {
                temp_date[j] = xint(stat.get(j + 1));
            }

            // just to kick start
            if (i == 0) {
                for (int j = 0; j < 3; j++) {
                    cur_date[j] = temp_date[j];
                }
                cur_day = new Day(cur_date[0], cur_date[1], cur_date[2]);
            }

            // check if changes on class is made
            next = false;
            for (int j = 2; j >= class_group; j--) {
                if (cur_date[j] > temp_date[j]) {
                    next = true;
                    break;
                }
            }

            if (!next) {
                tp_append();
            } else {
                tp_next();
                tp_reset();
                tp_append();
            }
        }
        tp_next();
    }
}
