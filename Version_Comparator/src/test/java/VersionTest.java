import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class VersionTest {

    @Test
    public void testNullVersion() {
        try {
            new Version(null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("'version' must not be null!");
        }
    }

    @Test
    public void testInvalidVersionFormat() {
        try {
            new Version("1.a");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("'version' must match: 'major.minor.patch(-SNAPSHOT)'!");
        }
    }

    @Test
    public void testValidVersions() {
        Version v1 = new Version("1");
        assertThat(v1.major).isEqualTo(1);
        assertThat(v1.minor).isEqualTo(0);
        assertThat(v1.patch).isEqualTo(0);
        assertThat(v1.isSnapshot()).isFalse();

        Version v2 = new Version("1.2");
        assertThat(v2.major).isEqualTo(1);
        assertThat(v2.minor).isEqualTo(2);
        assertThat(v2.patch).isEqualTo(0);
        assertThat(v2.isSnapshot()).isFalse();

        Version v3 = new Version("1.2.3");
        assertThat(v3.major).isEqualTo(1);
        assertThat(v3.minor).isEqualTo(2);
        assertThat(v3.patch).isEqualTo(3);
        assertThat(v3.isSnapshot()).isFalse();

        Version v4 = new Version("1.2.3-SNAPSHOT");
        assertThat(v4.major).isEqualTo(1);
        assertThat(v4.minor).isEqualTo(2);
        assertThat(v4.patch).isEqualTo(3);
        assertThat(v4.isSnapshot()).isTrue();
    }

    @Test
    public void testCompareTo() {
        Version v1 = new Version("1.0.0-SNAPSHOT");
        Version v2 = new Version("1.0.0");
        Version v3 = new Version("1.1.0");
        Version v4 = new Version("2.0.0");

        assertThat(v1.compareTo(v2)).isLessThan(0);     // v1 < v2
        assertThat(v2.compareTo(v1)).isGreaterThan(0);  // v2 > v1
        assertThat(v2.compareTo(v3)).isLessThan(0);     // v2 < v3
        assertThat(v3.compareTo(v2)).isGreaterThan(0);  // v3 > v2
        assertThat(v3.compareTo(v4)).isLessThan(0);     // v3 < v4
        assertThat(v4.compareTo(v3)).isGreaterThan(0);  // v4 > v3
    }
}
