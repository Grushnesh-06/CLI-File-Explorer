import java.nio.file.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class CLIFileExplorer {
    private static Path currentDirectory = Paths.get(System.getProperty("user.dir")); 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("CLI File Explorer started.");
        System.out.println("Basic commands: [ls] [cd] [mkdir] [rm] [touch] [mv] [cp] [search] [cat] [info] [exit]");
        while (true) {
            System.out.print(currentDirectory.toAbsolutePath() + ">");
            String command = sc.nextLine().trim();
            String[] parts = command.split(" ", 10);
            String action = parts[0];

            try {
                switch (action) {
                    case "ls":
                        listFiles();
                        break;

                    case "cd":
                        if (parts.length > 1) changeDirectory(parts[1]);
                        else System.out.println("Usage: cd <folder>");
                        break;

                    case "mkdir":
                        if (parts.length > 1) createDirectory(parts[1]);
                        else System.out.println("Usage: mkdir <folder>");
                        break;

                    case "rm":
                        if (parts.length > 1) deleteFileOrFolder(parts[1]);
                        else System.out.println("Usage: rm <file/folder>");
                        break;

                    case "touch":
                        if (parts.length > 1) createFile(parts[1]);
                        else System.out.println("Usage: touch <filename>");
                        break;
                    
                    case "mv":
                        if(parts.length > 2) {
                            String sourceName = parts[1].replace("\"","");
                            String destinationName = parts[2].replace("\"","");
                            moveFileOrFolder(sourceName, destinationName);
                        } else{ 
                            System.out.println("Usage: mv <source> <destination>");
                        }
                        break;

                    case "cp":
                        if(parts.length > 2) copyFileOrFolder(parts[1], parts[2]);
                        else System.out.println("Usage: cp <source> <destination>");
                        break;

                    case "search":
                        if(parts.length > 1) searchFileOrFolder(parts[1]);
                        else System.out.println("Usage: search <filename>");
                        break;
                    
                    case "cat":
                        if(parts.length > 1) readFile(parts[1]);
                        else System.out.println("Usage: cat <filename>");
                        break;

                    case "info":
                        if(parts.length > 1) getFileOrFolderInfo(parts[1]);
                        else System.out.println("Usage: info <filename>");
                        break;

                    case "exit":
                        System.out.println("Exiting the Explorer..."); 
                        sc.close(); 
                        return;

                    default:
                        System.out.println("Invalid command");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void listFiles() throws IOException {
        try (var stream = Files.list(currentDirectory)) {
            stream.forEach(path -> {
                System.out.println((Files.isDirectory(path) ? "[DIR] " : "[FILE] ") + path.getFileName());
            });
        }
    }

    private static void changeDirectory(String folderName) {
        Path newDir;
        if (folderName.equals("..")) {
            newDir = currentDirectory.getParent();
        } else {
            newDir = currentDirectory.resolve(folderName).normalize();
        }

        if (newDir != null && Files.isDirectory(newDir)) {
            currentDirectory = newDir;
        } else {
            System.out.println("Folder not found."); 
        }
    }

    private static void createDirectory(String folderName) throws IOException {
        Path newDir = currentDirectory.resolve(folderName);
        Files.createDirectory(newDir);
        System.out.println("Directory created: " + folderName);
    }

    private static void deleteFileOrFolder(String name) throws IOException {
        Path target = currentDirectory.resolve(name);
        if (!Files.exists(target)) {
            System.out.println("File/Folder not found.");
            return;
        }

        if (Files.isDirectory(target)) {
            deleteDirectoryRecursively(target);
            System.out.println("Folder deleted: " + name);
        } else {
            Files.delete(target); 
            System.out.println("File deleted: " + name);
        }
    }

    private static void createFile(String filename) throws IOException {
        Path filePath = currentDirectory.resolve(filename);
        Files.createFile(filePath);
        System.out.println("File created: " + filename);
    }

    private static void deleteDirectoryRecursively(Path dir) throws IOException { 
        if (Files.isDirectory(dir)) {
            try (var stream = Files.list(dir)) {
                for (Path file : stream.toArray(Path[]::new)) {
                    deleteDirectoryRecursively(file);
                }
            }
            Files.delete(dir);
        } else {
            Files.delete(dir);
        }
    }

    private static void moveFileOrFolder(String sourceName, String destinationName) throws IOException{
        Path source = currentDirectory.resolve(sourceName);
        Path destination = currentDirectory.resolve(destinationName);
        
        System.out.println("Source Path: " + source.toAbsolutePath());
        System.out.println("Destination Path: " + destination.toAbsolutePath());

        if(!Files.exists(source)){
            System.out.println("Source file/folder not found.");
            return;
        }

        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Moved/Renamed: "+ sourceName + "->" + destinationName);
    }

    private static void copyFileOrFolder(String sourceName, String destinationName) throws IOException{
        Path source = currentDirectory.resolve(sourceName);
        Path destination = currentDirectory.resolve(destinationName);

        if(!Files.exists(source)) {
            System.out.println("Source file/folder not found.");
            return;
        }

        if(Files.isDirectory(source)){
            Files.walk(source)
                .forEach(sourcePath -> {
                    try{
                        Path targetPath = destination.resolve(source.relativize(sourcePath));
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e){
                        System.out.println("Error copying: " + e.getMessage());
                    }
                });
        } else{
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        }
       System.out.println("Copied: " + sourceName + " -> " + destinationName);
    } 

    private static void searchFileOrFolder(String fileName) throws IOException {
        Files.walk(currentDirectory)
            .filter(path -> path.getFileName().toString().equalsIgnoreCase(fileName))
            .forEach(path -> System.out.println("Found: "+ path.toAbsolutePath()));
    }

    private static void readFile(String filename) throws IOException {
        Path filePath = currentDirectory.resolve(filename);

        if(!Files.exists(filePath)) {
            System.out.println("File not found.");
            return;
        }

        Files.lines(filePath).forEach(System.out::println);
    }

    private static void getFileOrFolderInfo(String name) throws IOException {
        Path target = currentDirectory.resolve(name);

        if(!Files.exists(target)) {
            System.out.println("File/Folder not found.");
            return;
        }

        System.out.println("Name: " + target.getFileName());
        System.out.println("Type: " + (Files.isDirectory(target) ? "Folder" : "File"));
        System.out.println("Size: " + Files.size(target)+"bytes");
        System.out.println("Last Modified: " + Files.getLastModifiedTime(target));
    }
}