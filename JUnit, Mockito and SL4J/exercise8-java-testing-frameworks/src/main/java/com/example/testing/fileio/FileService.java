package com.example.testing.fileio;

public class FileService {

    private final SimpleFileReader fileReader;
    private final SimpleFileWriter fileWriter;

    public FileService(SimpleFileReader fileReader, SimpleFileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        String processed = "Processed " + content;
        fileWriter.write(processed);
        return processed;
    }
}
