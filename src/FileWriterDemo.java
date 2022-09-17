import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileWriterDemo {

    private PrintWriter out;
    private Scanner in;

    public FileWriterDemo(String fileName) throws Exception {
        out = new PrintWriter(new FileWriter(fileName));
        in = new Scanner(new BufferedReader(new FileReader(fileName)));
    }

    public void writeLine(String text) {
        out.println(text);
    }

    public void close() {
        out.flush();
        out.close();
    }

    public void read() {
        while (in.hasNextLine())
            System.out.println(in.nextLine());
    }

    public static void main(String[] args) throws Exception {
        FileWriterDemo demo = new FileWriterDemo("Test.txt");
        demo.writeLine("This is a line of text.");
        demo.writeLine("And another, both written to a file.");
        demo.close();
        demo.read();
    }
}
