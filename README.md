# CLI File Explorer

This is a Command Line Interface (CLI) File Explorer developed in Java.

## Description

### Introduction

The CLI File Explorer is a command-line-based tool that allows users to navigate and manage files and directories on their system. It provides essential file operations such as listing, creating, deleting, renaming, copying, and moving files and folders, along with additional utilities like searching, reading file content, and retrieving file information.

### Features

- List files and directories (`ls`)
- Change directory (`cd`)
- Create a directory (`mkdir`)
- Delete files or directories (`rm`)
- Create a new file (`touch`)
- Move or rename files and directories (`mv`)
- Copy files and directories (`cp`)
- Search for a file (`search`)
- Read file content (`cat`)
- Get file or folder information (`info`)
- Exit the program (`exit`)

### Technologies Used

- **Language**: Java
- **Libraries**: `java.nio.file`, `java.io.IOException`, `java.util.Scanner`

### Implementation Details

The program operates within an infinite loop, taking user input for commands and executing corresponding methods. It utilizes `java.nio.file` for file handling and ensures proper error handling for robust execution.

### Important Classes and Methods

#### Main Class: `CLIFileExplorer`

This class serves as the entry point of the program. It initializes the CLI interface and continuously listens for user commands.

#### Key Methods:

1. **listFiles()**: Lists all files and directories in the current directory.
2. **changeDirectory(String folderName)**: Changes the current working directory.
3. **createDirectory(String folderName)**: Creates a new directory inside the current directory.
4. **deleteFileOrFolder(String name)**: Deletes a file or folder (recursively if needed).
5. **createFile(String filename)**: Creates a new file in the current directory.
6. **moveFileOrFolder(String sourceName, String destinationName)**: Moves or renames a file or folder.
7. **copyFileOrFolder(String sourceName, String destinationName)**: Copies a file or an entire directory.
8. **searchFileOrFolder(String fileName)**: Searches for a file or folder in the current directory and subdirectories.
9. **readFile(String filename)**: Reads and prints the contents of a file.
10. **getFileOrFolderInfo(String name)**: Retrieves and displays information about a file or directory.

### Key Java Concepts Used

- **Exception Handling**: Ensures robust error handling with `throws IOException` and `try-catch` blocks.
- **Path and File Handling**: Utilizes `Paths.resolve()`, `normalize()`, `getParent()`, and `toAbsolutePath()` for proper path management.
- **Streams and Iteration**: Uses `.forEach()`, `Files.list()`, and `Files.walk()` for efficient file operations.
- **File Copy and Move**: Implements `StandardCopyOption.REPLACE_EXISTING` for overwriting during copy/move operations.

### Challenges Faced & Solutions

- **Recursive file deletion**: Implemented `deleteDirectoryRecursively()` for safe directory removal.
- **Path resolution**: Used `Paths.resolve()` to handle relative paths properly.
- **User input validation**: Added exception handling to prevent invalid commands from crashing the program.

### Conclusion

The CLI File Explorer provides a powerful way to manage files and directories using a command-line interface. It covers essential file operations and ensures efficient handling of user commands. Future enhancements can improve usability and expand its functionality.

