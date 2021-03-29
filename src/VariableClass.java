import org.jfree.data.time.TimeSeries;

import java.util.ArrayList;

public class VariableClass {

    static ArrayList<ArrayList<String>> stats = new ArrayList<ArrayList<String>>();
    static ArrayList<ArrayList<String>> players = new ArrayList<ArrayList<String>>();
    static ArrayList<String> map_choice = new ArrayList<String>();
    static ArrayList<String> teammate = new ArrayList<String>();
    static ArrayList<Integer> match = new ArrayList<Integer>();
    static ArrayList<Float> data = new ArrayList<Float>();
    static ArrayList<ArrayList<TimeSeries>> timeseries = new ArrayList<ArrayList<TimeSeries>>();
    final String[] list_month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    final String labels[] = {"DustII", "Mirage", "Inferno", "Cache", "Overpass", "Train", "Nuke"};
    final String graph_cstat_choice[] = {"Kills", "Assists", "Deaths", "Mvp's", "HS%", "Points", "Winrate", "KD"};
    final String graph_cgroup_choice[] = {"Day", "Month", "Year"};

    static int class_group = 0;
    static int class_size = 0;
    static int class_stat = 9;
    static boolean class_onlyave = false;

    static int start_index = 0;
    static int end_index = 0;
    static int total_games = 0;
    static int lifetime_games = 0;
    static int total_wins = 0;
    static int total_lost = 0;
    static int total_tie = 0;
    static int total_kill = 0;
    static int total_assist = 0;
    static int total_death = 0;
    static int total_mvp = 0;
    static float ave_ping = 0;
    static float ave_kill = 0;
    static float ave_assist = 0;
    static float ave_death = 0;
    static float ave_mvp = 0;
    static float ave_headshot = 0;
    static float ave_points = 0;
    static float ave_kd = 0;
    static float ave_winrate = 0;
}

/*
0-Map
1-Day
2-Month
3-Year
4-Wait Time(s)
5-Match Time(s)
6-Team A Rounds
7-Team B Rounds
8-Ping
9-Kills
10-Assists
11-Deaths
12-Mvp's
13-HS%
14-Points
15-Result
*/
