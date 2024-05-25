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
        if (version == null) {
            throw new IllegalArgumentException("'version' must not be null!");
        }

        String regex = "\\d+(\\.\\d+){0,2}(-SNAPSHOT)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(version);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("'version' must match: 'major.minor.patch(-SNAPSHOT)'!");
        }

        this.version = version;
        String[] parts = version.split("-")[0].split("\\.");
        this.major = Integer.parseInt(parts[0]);
        this.minor = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        this.patch = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
        this.isSnapshot = version.endsWith("-SNAPSHOT");
    }

    public boolean isSnapshot() {
        return isSnapshot;
    }

    @Override
    public int compareTo(Version other) {
        if (other == null) {
            throw new IllegalArgumentException("'other' must not be null!");
        }

        if (this.isSnapshot && !other.isSnapshot) {
            return -1;
        }
        if (!this.isSnapshot && other.isSnapshot) {
            return 1;
        }

        if (this.major != other.major) {
            return Integer.compare(this.major, other.major);
        }
        if (this.minor != other.minor) {
            return Integer.compare(this.minor, other.minor);
        }
        return Integer.compare(this.patch, other.patch);
    }

    public static void main(String[] args) {
        Version version1 = new Version("0.0.1");
    }
}