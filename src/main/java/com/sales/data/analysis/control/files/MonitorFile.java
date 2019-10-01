package com.sales.data.analysis.control.files;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class MonitorFile {

    private final ReaderFile readerFile;
    private final WriterFile writerFile;

    public void processExistingFiles(){

        try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.home"), "data/in/"))) {

            walk.filter(Files::isRegularFile).forEach(p -> {
                try {
                    writerFile.write(readerFile.read(p), p.getFileName().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void monitorFiles() throws IOException, InterruptedException {

        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(System.getProperty("user.home"), "data/in/");

        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                writerFile.write(readerFile.read(Paths.get(System.getProperty("user.home"), "data/in/" + event.context())), event.context().toString());
            }
            key.reset();
        }

    }

}
