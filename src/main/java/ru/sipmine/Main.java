package ru.sipmine;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    private static CommandLine cmd;

    public static void main(String[] args) {
        Options options = new Options();
        Option appendOpt = new Option("a", false, "append result to old output");
        Option outputOpt = new Option("o", true, "custom output file");
        Option prefixOpt = new Option("p", true, "add prefix to output file");
        Option shortOpt = new Option("s", false, "Short stats");
        Option fullOpt = new Option("f", false, "full stats");
        options.addOption(appendOpt);
        options.addOption(outputOpt);
        options.addOption(prefixOpt);
        options.addOption(shortOpt);
        options.addOption(fullOpt);
        CommandLineParser parser = new DefaultParser();
        try {
            cmd = parser.parse(options, args);
            FileProcesing fileProcesing = new FileProcesing(cmd.hasOption("a"),
                    cmd.hasOption("o") ? cmd.getOptionValue("o") : "",
                    cmd.hasOption("p") ? cmd.getOptionValue("p") : "");
            
            if (fileProcesing.validFilePath() ) {

                Filtering filtering = new Filtering();
                for (int i = 0; i < args.length; i++) {
                    if (fileProcesing.isFiles(args[i])) {

                        fileProcesing.addFile(args[i]);
                        List<String> contentFile = fileProcesing.getDateFromFile();
                        Iterator<String> iterator = contentFile.iterator();
                        while (iterator.hasNext()) {
                            filtering.typeValid(iterator.next());
                        }
                    }
                }

                // create file
                fileProcesing.outputFile(filtering.getFloatList());
                fileProcesing.outputFile(filtering.getLongList());
                fileProcesing.outputFile(filtering.getStringList());
                if (cmd.hasOption("s")) {
                    filtering.shortStats(fileProcesing);
                } else if (cmd.hasOption("f")) {
                    filtering.fullStats(fileProcesing);
                }
            } else {
                System.out.println("no such dir");
            }
            if (fileProcesing.fileLenght() == 0) {
                System.out.println("zero file input");
            }
        } catch (ParseException e) {
        }
    }
}