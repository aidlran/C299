package classroster.ui;

public interface UserIO {

    void print(String text);

    String readString(String prompt);

    int readInt(String prompt);
    int readInt(String prompt, int min, int max);

    long readLong(String prompt);
    long readLong(String prompt, long min, long max);

    double readDouble(String prompt);
    double readDouble(String prompt, double min, double max);
}
