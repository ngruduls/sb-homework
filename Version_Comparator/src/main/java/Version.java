package main.java;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Version implements Comparable<Version> {
    private final String version;
    private final int major;
    private final int minor;
    private final int patch;
    private final boolean isSnapshot;

    public Version(String version) {

    }

    public boolean isSnapshot() {
        return isSnapshot;
    }

    @Override
    public int compareTo(Version other) {
        return 0;
    }

}
