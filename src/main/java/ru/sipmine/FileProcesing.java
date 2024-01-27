package ru.sipmine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// this class is responsible for file processing. 

public class FileProcesing {
    private boolean appendOpt = false;
    private String outputOpt = "";
    private String prefix = "";
    private List<String> files;

    public FileProcesing(boolean appendOpt, String outputOpt, String prefix) {
        this.appendOpt = appendOpt;
        this.outputOpt = outputOpt;
        if (this.outputOpt.isEmpty()) {
            this.outputOpt = new File("").getAbsolutePath() + "/";

        }
        this.prefix = prefix;
        this.files = new ArrayList<>();
    }

    public void addFile(String file) {
        this.files.add(file);
    }

    public int fileLenght() {
        return files.size();
    }

    /*
     * pulls strings from the file and writes them into an array for further
     * processing
     */
    public List<String> getDateFromFile() {
        Iterator<String> fileI = this.files.iterator();
        List<String> list = null;
        while (fileI.hasNext()) {

            Path pathFile = Paths.get(fileI.next());
            try {
                BufferedReader reader = Files.newBufferedReader(pathFile);
                list = reader.lines().toList();

            } catch (IOException e) {
                System.out.println("error reading");
            }
        }
        return list;

    }

    /*
     * Check & valid method 
     */
    public boolean isFiles(String file) {
        return file.contains(".txt");
    }

    public boolean validFilePath() {
        File file = new File(outputOpt);
        return file.isDirectory();
    }

    public boolean isListNotEmpty(List<?> list) {
        return !list.isEmpty();
    }

    /**
     * creates an output file with the specified parameters
     * 
     * @param listType - A list with which stores items of a certain type
     */
    public void outputFile(List<?> listType) {
        if (isListNotEmpty(listType)) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(

                        new FileWriter(outputOpt + prefix + listType.get(0).getClass().getSimpleName() + ".txt",
                                appendOpt));
                Iterator<?> iterator = listType.iterator();
                while (iterator.hasNext()) {
                    bufferedWriter.write(iterator.next().toString());
                    bufferedWriter.write("\n");
                }
                bufferedWriter.close();

            } catch (IOException e) {
                System.out.println("error writing");
            }
        }
    }

    

}