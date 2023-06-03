package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App {
    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String path3) {
        CompletableFuture<String> string1 = CompletableFuture.supplyAsync(() ->
        {
            try {
                return Files.readAllLines(Paths.get(path1)).get(0);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
        CompletableFuture<String> string2 = CompletableFuture.supplyAsync(() ->
        {
            try {
                return Files.readAllLines(Paths.get(path2)).get(0);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
        File absPath = new File(path1);
        String resultPath = Objects.requireNonNullElse(path3, absPath.getParent() + "/newFile.txt");
        return CompletableFuture.supplyAsync(() -> {
                CompletableFuture<String> result = string1.thenCombine(string2, String::concat);
            try {
                Files.write(Paths.get(resultPath), result.get().getBytes());
            } catch (IOException | InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            try {
                return result.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("NoSuchFileException");
            return null;
        });
    }
    // END
    public static void main(String[] args) throws Exception {
        // BEGIN
        System.out.println(App.unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "dest.txt"));
        // END
    }
}

