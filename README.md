# CLI-File-Explorer
This is a Command Line Interface File Explorer made in JAVA

# Description
CLI File Explorer
1. Introduction:
The CLI File Explorer is a command-line-based tool that allows users to navigate and manage files and directories on their system. It provides essential file operations such as listing, creating, deleting, renaming, copying, and moving files and folders, along with additional utilities like searching, reading file content, and retrieving file information.
2. Features:
•	List files and directories (ls)
•	Change directory (cd)
•	Create a directory (mkdir)
•	Delete files or directories (rm)
•	Create a new file (touch)
•	Move or rename files and directories (mv)
•	Copy files and directories (cp)
•	Search for a file (search)
•	Read file content (cat)
•	Get file or folder information (info)
•	Exit the program (exit)
3. Technologies Used:
•	Language: Java
•	Libraries: java.nio.file, java.io.IOException, java.util.Scanner
4. Implementation Details:
The program operates within an infinite loop, taking user input for commands and executing corresponding methods. It utilizes java.nio.file for file handling and ensures proper error handling for robust execution.
5. Important Classes and Methods:
Main Class: CLIFileExplorer
This class serves as the entry point of the program. It initializes the CLI interface and continuously listens for user commands.
Important Methods and Their Functions:
1.	listFiles():
o	Lists all files and directories in the current directory.
o	Uses Files.list(currentDirectory) to fetch file names.
2.	changeDirectory(String folderName):
o	Changes the current working directory.
o	Supports navigation to parent (..) and child directories.
o	Uses .getParent(), .resolve(), and .normalize() to handle paths.
3.	createDirectory(String folderName):
o	Creates a new directory inside the current directory.
4.	deleteFileOrFolder(String name):
o	Deletes a file or folder.
o	If deleting a folder, calls deleteDirectoryRecursively().
5.	createFile(String filename):
o	Creates a new file in the current directory.
6.	deleteDirectoryRecursively(Path dir):
o	Recursively deletes a folder and all its contents.
o	Uses Files.walk() and .forEach() to traverse and delete files.
7.	moveFileOrFolder(String sourceName, String destinationName):
o	Moves or renames a file or folder.
o	Uses Files.move() with StandardCopyOption.REPLACE_EXISTING.
8.	copyFileOrFolder(String sourceName, String destinationName):
o	Copies a file or an entire directory.
o	Recursively copies directory contents using Files.walk() and .relativize().
9.	searchFileOrFolder(String fileName):
o	Searches for a file or folder in the current directory and subdirectories.
o	Uses Files.walk() and .forEach() to scan all files.
10.	readFile(String filename):
o	Reads and prints the contents of a file.
o	Uses Files.lines() to read file content line by line.
11.	getFileOrFolderInfo(String name):
o	Retrieves and displays information about a file or directory.
o	Provides file size, type (file/folder), and last modified time.
Key Java Concepts Used:
•	Exception Handling:
o	Methods declare throws IOException for file operations.
o	try-catch blocks handle errors, using e.getMessage() for error reporting.
•	Path and File Handling:
o	.resolve() is used to construct paths relative to the current directory.
o	.normalize() ensures correct and clean path representation.
o	.getParent() retrieves the parent directory of a given path.
o	.toAbsolutePath() returns the full absolute path of a file or folder.
•	Stream and Iteration:
o	.forEach() is used with Files.list(), Files.walk(), and other operations to iterate over files.
o	.toArray() is used to convert a stream to an array for processing.
•	File Copy and Move:
o	StandardCopyOption.REPLACE_EXISTING ensures files are overwritten during copy/move operations.
o	.relativize() is used when copying directory structures to maintain relative paths.
6. Challenges Faced & Solutions:
•	Handling recursive file deletion: Implemented deleteDirectoryRecursively() to remove directories and subdirectories.
•	Ensuring correct path resolution: Used Paths.resolve() to handle relative paths properly.
•	Handling invalid user input: Added validation and exception handling to prevent crashes.
7. Future Improvements:
•	Implement a history feature to store previously executed commands.
•	Add file compression and extraction functionalities.
•	Implement a GUI version using JavaFX or Swing.
8. Conclusion:
The CLI File Explorer provides a powerful way to manage files and directories using a command-line interface. It covers essential file operations and ensures efficient handling of user commands. Future enhancements can improve usability and expand its functionality.

