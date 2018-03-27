package hermielabGraphwalker;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSinkGraphML;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) throws IOException, JDOMException {
        Graph g = new DefaultGraph("g");
        FileSource fs = FileSourceFactory.sourceFor("model.dot");
        fs.addSink(g);
        fs.readAll("model.dot");

        FileSinkGraphML fsout = new FileSinkGraphML();
        fsout.writeAll(g, "model.graphml");
        fixGraphML("model.graphml");
    }




    public static void fixGraphML(String path) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new File(path));
        System.out.println(document);
    }

}
