# User input

## Command line arguments

## Console
https://docs.oracle.com/javase/8/docs/api/java/io/Console.html
```
    Console console = System.console();
```
### Read operations
#### Token
#### Line
#### Password

### Flush
### Close

## Scanner
https://docs.oracle.com/javase/8/docs/api///index.html?java/util/Scanner.html

Use Scanner to read the input into tokens.
```
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(new File("test.txt"));
    Scanner scanner3 = new Scanner(new FileInputStream("test.txt"));
   
    String input = "Hello 1 F 3.5";
    Scanner scanner4 = new Scanner(input);
     
    // use System.setIn(…) to simulate some input coming from the Console
    String input = "Hello";
    InputStream stdin = System.in;
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner5 = new Scanner(System.in);
    
```
### Read operations
#### Token
#### Line
#### Password

### Flush
### Close

## Reader
The Java I/O API defines two kinds of content for a resource:
- character content, think of a text file, a XML or JSON document,
- byte content, think of an image or a video.
It also defines two operations on this content: reading and writing.

The Java I/O API defines four base classes, that are abstract,
each modeling a type of I/O stream and a specific operation.

|                      | Reading     | Writing      |
|----------------------|-------------|--------------|
| Streams of character | Reader      | Writer       |
| Streams of bytes     | InputStream | OutputStream |

All character stream classes are descended from Reader and Writer.
All byte streams are descended from InputStream and OutputStream.

### Streams of character
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/io/Reader.html
#### BufferedReader
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/io/BufferedReader.html

Use BufferedReader when we want to read the input into lines.
```
    BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader reader2 = new BufferedReader(new FileReader("test.txt"));
    BufferedReader reader3 = java.nio.file.Files.newBufferedReader(Paths.get("test.txt"));
```
##### File

### Streams of bytes
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/io/InputStream.html
#### DataInputStream
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/io/DataInputStream.html

Is used to read primitive data types like int, float, boolean, and strings from an input stream in a way that works
across different machines.
```
    DataInputStream reader = new DataInputStream(System.in);
```
##### File

## NIO
### Files
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/nio/file/Files.html
Java NIO class `Files` allows us to generate a Stream<String> of a text file through the lines() method.
Every line of the text becomes an element of the stream:
```
    Path path = Paths.get("C:\\file.txt");
    Stream<String> streamOfStrings = Files.lines(path);
    Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
```
### ByteChannel
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/nio/channels/ByteChannel.html
Usage Examples:
```
    Path path = ...

    // open file for reading
    ReadableByteChannel rbc = Files.newByteChannel(path, EnumSet.of(READ)));

    // open file for writing to the end of an existing file, creating
    // the file if it doesn't already exist
    WritableByteChannel wbc = Files.newByteChannel(path, EnumSet.of(CREATE,APPEND));

    // create file with initial permissions, opening it for both reading and writing
    FileAttribute<Set<PosixFilePermission>> perms = ...
    SeekableByteChannel sbc = Files.newByteChannel(path, EnumSet.of(CREATE_NEW,READ,WRITE), perms);

```

#### SeekableByteChannel
https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/nio/channels/SeekableByteChannel.html
SeekableByteChannel provides a channel to read and manipulate a given file.
It produces a faster performance than standard I/O classes as it’s backed by an auto-resizing byte array.
```
    try (SeekableByteChannel ch = java.nio.file.Files.newByteChannel(Paths.get(fileName), StandardOpenOption.READ)) {
        ByteBuffer bf = ByteBuffer.allocate(1000);
        while (ch.read(bf) > 0) {
            bf.flip();
            // System.out.println(new String(bf.array()));
            bf.clear();
        }
    }
```
The only drawback of this approach is that we need to specify the buffer size explicitly using the allocate() method.
