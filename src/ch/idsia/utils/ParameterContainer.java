package ch.idsia.utils;

import ch.idsia.ai.agents.Agent;
import ch.idsia.ai.agents.AgentsPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy, firstName_at_idsia_dot_ch
 * Date: May 5, 2009
 * Time: 9:34:33 PM
 * Package: ch.idsia.utils
 */
public class ParameterContainer {
    protected static HashMap<String, String> defaultOptionsHashMap = null;
    private static List<String> allowedOptions = null;
    protected HashMap<String, String> optionsHashMap = new HashMap<String, String>();
    private String[] allowed = null;

    public ParameterContainer() {
        if (allowed == null)
            allowed = new String[]{
                    "-ag",
                    "-agentName",
                    "-attemptsNumber",
                    "-e",
                    "-echo",
                    "-ewf",
                    "-fastTCP",
                    "-exitWhenFinished",
                    "-gameViewer",
                    "-gameViewerContinuousUpdates",
                    "-gui",
                    "-gv",
                    "-gvc",
                    "-i",
                    "-ld",
                    "-levelDifficulty",
                    "-levelLength",
                    "-levelRandSeed",
                    "-levelType",
                    "-ll",
                    "-ls",
                    "-lt",
                    "-m",
                    "-mm",
                    "-maxFPS",
                    "-not",
                    "-matLabFile",
                    "-pauseWorld",
                    "-port",
                    "-powerRestoration",
                    "-pr",
                    "-pw",
                    "-server",
                    "-ssiw",
                    "-stopSimulationIfWin",
                    "-t",
                    "-tc",
                    "-tl",
                    "-toolsConfigurator",
                    "-vaot",
                    "-viewAlwaysOnTop",
                    "-viewLocationX",
                    "-viewLocationY",
                    "-vis",
                    "-visual",
                    "-vlx",
                    "-vly",
                    "-ze",
                    "-zm"
            };
        if (allowedOptions == null) {
            allowedOptions = new ArrayList<String>();
            Collections.addAll(allowedOptions, allowed);
        }

        InitDefaults();
    }

    public static void InitDefaults() {
        if (defaultOptionsHashMap != null)
            return;
        else {
            defaultOptionsHashMap = new HashMap<String, String>();
//            new HumanKeyboardAgent();
            defaultOptionsHashMap.put("-ag", "CheaterKeyboardAgent"); //defaultOptionsHashMap.put("-agentName",
            // "NoAgent");
            defaultOptionsHashMap.put("-echo", "on"); //defaultOptionsHashMap.put("-echo","off");
            defaultOptionsHashMap.put("-ewf", "off"); //defaultOptionsHashMap.put("-exitWhenFinished","off");
            defaultOptionsHashMap.put("-fastTCP", "off"); //
            defaultOptionsHashMap.put("-gv", "on"); //defaultOptionsHashMap.put("-gameViewer","off");
            defaultOptionsHashMap.put("-gvc", "on"); //defaultOptionsHashMap.put("-gameViewerContinuousUpdates","off");
            defaultOptionsHashMap.put("-i", "off"); // Invulnerability    刀枪不入
            defaultOptionsHashMap.put("-ld", "3"); //defaultOptionsHashMap.put("-levelDifficulty","0");
            defaultOptionsHashMap.put("-ll", "320"); //defaultOptionsHashMap.put("-levelLength","320");
            defaultOptionsHashMap.put("-ls", "1"); //defaultOptionsHashMap.put("-levelRandSeed","1");
            defaultOptionsHashMap.put("-lt", "1"); //defaultOptionsHashMap.put("-levelType","1");
            defaultOptionsHashMap.put("-maxFPS", "off"); //defaultOptionsHashMap.put("-maxFPS","off"); 最大频率,速进
            defaultOptionsHashMap.put("-m", ""); //defaultOptionsHashMap.put("-matLabFile","DefaultMatlabFile");
            defaultOptionsHashMap.put("-mm", "2");
            defaultOptionsHashMap.put("-not", "5"); //defaultOptionsHashMap.put("-attemptsNumber","5");
            defaultOptionsHashMap.put("-pw", "off"); //defaultOptionsHashMap.put("-pauseWorld","off");        //屏蔽妖怪
            defaultOptionsHashMap.put("-port", "4242"); //defaultOptionsHashMap.put("-port","4242");
            defaultOptionsHashMap.put("-pr", "on"); //defaultOptionsHashMap.put("-powerRestoration","off"); //自动回血
            defaultOptionsHashMap.put("-ssiw", "off"); //defaultOptionsHashMap.put("-stopSimulationIfWin","off");
            defaultOptionsHashMap.put("-server", "off");
            defaultOptionsHashMap.put("-t", "on"); //defaultOptionsHashMap.put("-timer","on");
            defaultOptionsHashMap.put("-tl", "200"); //defaultOptionsHashMap.put("-timer","on");
            defaultOptionsHashMap.put("-tc", "on"); //defaultOptionsHashMap.put("-toolsConfigurator","off");
            defaultOptionsHashMap.put("-vaot", "off"); //defaultOptionsHashMap.put("-viewAlwaysOnTop","off");
            defaultOptionsHashMap.put("-vlx", "100"); //defaultOptionsHashMap.put("-viewLocationX","0");
            defaultOptionsHashMap.put("-vly", "100"); //defaultOptionsHashMap.put("-viewLocationY","0");
            defaultOptionsHashMap.put("-vis", "on"); //defaultOptionsHashMap.put("-visual","on");     //展示游戏画面
            defaultOptionsHashMap.put("-zm", "1");
            defaultOptionsHashMap.put("-ze", "0");
        }
    }

    public static String getDefaultParameterValue(String param) {
        if (allowedOptions.contains(param)) {
            assert (defaultOptionsHashMap.get(param) != null);
            return defaultOptionsHashMap.get(param);
        } else {
            System.err.println("Reques for Default Parameter " + param + " Failed. Typo?");
            return "";
        }
    }

    public void addParameterValue(String param, String value) {
        if (allowedOptions.contains(param)) {
            assert (optionsHashMap.get(param) == null);
            optionsHashMap.put(param, value);
        } else
            System.err.println("Parameter " + param + " is not valid. Typo?");
    }

    public void setParameterValue(String param, String value) {
        try {
            if (allowedOptions.contains(param)) {
                optionsHashMap.put(param, value);
            } else {
                throw new IllegalArgumentException("Parameter " + param + " is not valid. Typo?");
            }
        } catch (IllegalArgumentException e) {

            System.err.println("Error: Undefined parameter '" + param + " " + value + "'");
            System.err.println(e.getMessage());
            System.err.println("Some defaults might be used instead");
        }
    }

    public String getParameterValue(String param) {
        if (allowedOptions.contains(param)) {
            if (optionsHashMap.get(param) == null) {
                //System.err.println("InfoWarning: Default value '" + defaultOptionsHashMap.get(param) + "' for " +
                // param +
                //        " used");
                optionsHashMap.put(param, defaultOptionsHashMap.get(param));
            }
            return optionsHashMap.get(param);
        } else {
            System.err.println("Parameter " + param + " is not valid. Typo?");
            return "";
        }
    }

    public int i(String s) {
        return Integer.parseInt(s);
    }

    public String s(Object i) {
        return String.valueOf(i);
    }

    public String s(Agent a) {
        try {
            if (AgentsPool.getAgentByName(a.getName()) == null)
                AgentsPool.addAgent(a);
            return a.getName();
        } catch (NullPointerException e) {
            System.err.println("ERROR: Agent Not Found");
            return "";
        }
    }

    public Agent a(String s) {
        return AgentsPool.getAgentByName(s);
    }

    public boolean b(String s) {
        return "on".equals(s) || Boolean.valueOf(s);
    }
}