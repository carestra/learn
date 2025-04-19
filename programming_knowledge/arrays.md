# Arrays
An array is a container object that holds a fixed number of values of any data type,
including primitive types such as int, double, and boolean, as well as object types such as String and Integer.
In Java, an array is a data structure that can store a fixed-size sequence of elements of the same data type.
The length of an array is established when the array is created, which means that the array size is set
permanently when the array is initialized.

```
    After creation, its length is fixed.
    Java arrays do not expand and contract.
```
Each item in an array is called an element, and each element is accessed by its numerical index.
Arrays in Java are zero-indexed, which means that the first element in an array has an index of 0,
the second element has an index of 1, and so on.

You can also declare an array of arrays (also known as a multidimensional array) by using two or more sets of brackets,
such as `String[][] twoDimensionalStringArray`, `Double[][][] threeDimensionalDoubleArray`.
The elements of a multidimensional array may have different array sizes. For example a 2d-array may be an array
containing 2 array elements, one have length of 2 and the other having length 5, ( {{1,2}, {77,78,79,80,81}} ) 

## Memory management
A reference is an arrow pointing to a particular object or array.
In Java, the only way to access an object or array is through a reference.
When you declare a local variable or field which has a class or array type, what you are really doing is defining
a memory cell that can hold a reference to that kind of class or array.
The important thing to understand about references is that copying a reference does not copy the object.

Every array in java is object only, hence arrays will be stored in the heap area.
Heap area can be accessed by multiple threads and hence the data stored in the heap memory is not thread safe.

The equality and inequality operators (“==” and “!=”) are defined for references.
These will tell you whether two references point to the same object.
However, they will not tell you anything about the contents of the objects (or arrays) themselves!
To compare the contents of two objects to see if they are the same, you should use the `equals()` method of the object.
Using “==” or “!=” to compare objects instead of the equals method is a very common bug in Java programs,
even for experienced programmers.

The special reference null represents the absence of any object or array.  Basically, it means “points to nothing”.
If you try to use a null value—for example, by invoking a method on it—a NullPointerException will be thrown.

## Declaration
An array declaration has two components: 
    - the array's type 
    - and the array's name
An array's type is written as `type[]`, where type is the data type of the contained elements;
the brackets are special symbols indicating that this variable holds an array.
The size of the array is not part of its type (which is why the brackets are empty).
An array's name can be anything you want, provided that it follows the rules and conventions.
As with variables of other types, the declaration does not actually create an array;
it simply tells the compiler that this variable will hold an array of the specified type.
Example
```
    byte[] anArrayOfBytes;
    short[] anArrayOfShorts;
    long[] anArrayOfLongs;
    float[] anArrayOfFloats;
    double[] anArrayOfDoubles;
    boolean[] anArrayOfBooleans;
    char[] anArrayOfChars;
    String[] anArrayOfStrings;
```
You can also place the brackets after the array's name:
```
    // this form is discouraged
    float anArrayOfFloats[];
```
However, convention discourages this form; the brackets identify the array type and should appear
with the type designation.

## Creation
Here are various approaches to initialize an array in Java:

    - An array can be set up to a specific size using the `new` operator. The new operator allocates memory for
        the array and automatically initializes all of its elements their default values,
        see Default values section below. This approach involves declaring an array variable and then initializing
        it with values in a separate statement.
        ```
            // Declare an array of integers, all elements have default value of 0.
            int[] anArray = new int[5];
            // initiate `anArray` one by one.
            anArray[0] = 100; // initialize first element
            anArray[1] = 200; // initialize second element
            anArray[2] = 300; // and so forth
            // initiate `anArray` using a Loop.
            for (int i = 0; i < anArray.length; i++) {
                anArray[i] = i*2;
            }
            // initiate `anArray` using the util class `Arrays`.
            Arrays.fill(anArray, 2); // Fill the array with value 2.
            Arrays.fill(anArray, 1, 3, 0); // Fill the array from index 1 to 2 with value 0.

            // initializing a 2-dimensional array, 3 rows x 4 cols matrix.
            int[][] matrix = new int[3][4];
            // initiate `anArray` pone by one.
            matrix[0][1] = 100; // initialize the element in the first row, second colums.
            // initiate `matrix` using a Loop.
            for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
                int elementIndex = matrix[rowIndex].length;
                for (int colIndex = 0; colIndex < elementIndex; colIndex++) {
                    matrix[rowIndex][colIndex] = rowIndex + colIndex;
                }
            }
        ```
    - An array can be declare and initialize in a single statement using the `new` operator, followed with the elements
        values. When using this approach, you don't need to specify the size of the array explicitly, as it is inferred.
        ```
            // 
            String[] coffies = new String[]{"Affogato", "Americano", "Cappuccino"};

            // initializing a 2-dimensional array, 2x2 matrix.
            int[][] matrix = {{1, 3}, {2, 4}};
        ```
    - An array can also be declare and initialize in a single statement using brakets containing the elements.
        When using this approach, you don't need to specify the size of the array explicitly, as it is inferred.
        ```
            // creates an empty array of integers, remember that after creation the array can not be resized,
            // e.g. has a fixed size (in this case length = 0)
            int[] empty = {};
            
            // creates a String array of 3 elements
            String[] copyFrom = {"Affogato", "Americano", "Cappuccino", "Corretto"};

            // initializing a 2-dimensional array, 3x3 matrix.
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ```

### Default values
Array memory is allocated on creation. When we initialize an array, it allocates the memory according to the size
and type of array.
Using the `new` operator to declare the array size, it initializes all the elements with a `null` value for
reference types and the default value (zero) for primitive types.

### Length
When you initialize Java arrays with the new keyword, the size argument is of type int.
The 32-bit Java int can go to a maximum of 2,147,483,647, so that is the theoretical maximum Java array size.
However, virtual machines for different operating systems may not allocate every available bit to the elements
in the Java array. Thus, the maximum number of elements a Java array can hold is typically a little less than
the upper limit of a Java int.

To find the size of a Java array, query an array’s `length` property since Java arrays does not have `size()` nor
`length()` methods.
The size or length count of an array in Java includes both null and non-null characters.
```
    Integer[] exampleArray = {1,2,3,null,5};
    // exampleArraySize will have value 5
    int exampleArraySize = exampleArray.length;
```

## Operations
### Print
The `java.util.Arrays` contains a `toString()` method to print the content of an array.
It can not be use for multidimensional arrays.
```
String Arrays.toString(type[] anArray)
    Returns a string representation of the contents of the specified array.
    The string representation consists of a list of the array's elements, enclosed in square brackets ( "[]").
    Adjacent elements are separated by the characters ", " (a comma followed by a space).
    Elements are converted to strings as by String.valueOf(long).
    Returns "null" if `anArray` is null .
```

```
    Integer[] exampleArray = {1,2,3,null,5};
    // will output a memory location of the Integer array, such as [Ljava.lang.Integer;@2641e737
    System.out.println(exampleArray);
    
    // Use the java.util.Arrays.toString() method
    // will output [1, 2, 3, null, 5]
    System.out.println(Arrays.toString(exampleArray));
```

### Compare
Since arrays are Objects in Java, lets refresh some basic concepts:

* Objects have references and values
* Two equal references should point to the same value
* Two equal values don’t necessarily have the same references
* Two different values should have different references
* Primitive values only get compared per value
* String literals only get compared per value

#### Comparing Object References
If we have two references pointing to the same array (pointing to the same memory location), we should always get
a result true in an equals comparison with the == operator or the equals() method.
```
    String[] planes1 = new String[] { "A320", "B738", "A321", "A319" };
    // Two equal references should point to the same value
    String[] planes2 = planes1;
    // yield true since the referns are equals
    planes1 == plane2;
    
    // Two equal values ({ "A320", "B738", "A321", "A319" }) don’t necessarily have the same references
    String[] planes3 = new String[] { "A320", "B738", "A321", "A319" }; // same content and order of planes1
    // yield false since the referns are different
    planes1 == plane3;
```

#### Comparing Arrays with Arrays.equals
To check if two arrays are equal in terms of their contents, Java provides the `Arrays.equals()` static method.
This method will iterate through the arrays, per position in parallel, and apply the == operator,
for every pair of elements.

```
    String[] planes1 = new String[] { "A320", "B738", "A321", "A319" };
    String[] planes2 = new String[] { "A320", "B738", "A321", "A319" };
    String[] planes3 = new String[] { "A319", "A320", "B738", "A321" }; // different orden than planes1
    
    // yields true
    Arrays.equals(planes1, planes2)
    
    // yields false, since comparing postions, e.g. planes1[0] = "A320" != "A319" = planes3[0].
    Arrays.equals(planes1, planes3)
    
    int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] matrix3 = {{4, 5, 6}, {7, 8, 9}, {1, 2, 3}}; // different orden than matrix1
    
    // yields true since every element comparation matrix1[0][index] == matrix2[0][index] will yield true, e.g. 1==1 
    Arrays.equals(matrix1[0], matrix2[0])
    
    // yields false, since every element comparation matrix1[index] == matrix2[index] will yield false, e.g.
    // [1, 2, 3] == [1, 2, 3] will compare object referens, see above 'Comparing Object References'.
    Arrays.equals(matrix1, matrix2)
    
    // yields false, since comparing object references.
    Arrays.equals(matrix1, matrix3)
```

#### Comparing Arrays with Arrays.deepEquals
Use the deepEquals method when we want to check the equality between two nested or multidimensional arrays or
compare two arrays composed of user-defined objects.
We’ll start by having a look at the method signature:
```
    public static boolean deepEquals(Object[] a1, Object[] a2)
```
From the method signature, we notice that we cannot use `deepEquals` to compare two unidimensional arrays
of primitive data types. For this, we must either box the primitive array to its corresponding wrapper or
use the `Arrays.equals` method, which has overloaded methods for primitive arrays.
Comparing two multidimensional arrays of primitive data types works well with `deepEquals`.

The Arrays.deepEquals method returns:
* true if both parameters are the same object (have the same reference)
* true if both parameters are null
* false if only one of the two parameters is null
* false if the arrays have different lengths
* true if both arrays are empty
* true if the arrays contain the same number of elements and every pair of subelements are deeply equal
* false in other cases

```
    String[] stringArray1 = new String[]{ "string1", "string2", "string3" };
    String[] stringArray2 = new String[]{ "string1", "string2", "string3" };
    String[] stringArray3 = new String[]{ "string2", "string3", "string1" }; // different orden than stringArray1
    // yields true, since the arrays contain the same number of elements and every pair of subelements are deeply equal.
    Arrays.deepEquals(stringArray1, stringArray2);
    // yields false since the first element in each array are different.
    Arrays.deepEquals(stringArray1, stringArray3);
    
    int[][] anArray = { { 1, 2, 3 }, { 4, 5, 6, 9 }, { 7 } };
    int[][] anotherArray = { { 1, 2, 3 }, { 4, 5, 6, 9 }, { 7 } };
    // yields true
    Arrays.deepEquals(anArray, anotherArray);
```

When comparing arrays of user-defined objects it is important to override the equals and hashcode method.
Otherwise, the default equals method will compare only the references of the objects.
See https://www.baeldung.com/java-equals-hashcode-contracts

#### Comparing Arrays Lexicographically
#### Comparing Arrays with a Comparator

### Add
### Remove
### Replace
### Prepend
### Append
### Traversing
#### Search/Find
#### Min/Max (reduce)
#### for-each loop/enhanced for loop
### Shrink
### Expand
### Copy
Arrays are a powerful and useful concept used in programming. Java SE provides methods to perform some of the most
common manipulations related to arrays.

The `System` class has an `arraycopy()` method that you can use to efficiently copy data from one array into another:
```
    String[] copyFrom = {"Affogato", "Americano", "Cappuccino", "Corretto"};
    String[] copyTo = new String[2];
    // copyFrom to copyTo elements "Americano", "Cappuccino".
    System.arraycopy(copyFrom, 1, copyTo, 0, 2);
```

For your convenience, Java SE provides several methods for performing array manipulations
(common tasks, such as copying, sorting and searching arrays) in the `java.util.Arrays` class.
```
    String[] copyFrom = {
            "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
            "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
            "Marocchino", "Ristretto" };

    // No need to first create a destination array since the method returns an array
    String[] copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);
```

### Subset
### Split
#### Equal size
### Map/Transform
### Arrays to Collection
#### List
#### Set
#### Map
### Arrays to Stream

# 2-dimensional arrays
## Operations
### IndexOf
### Length
### Print
#### Table
### Extract all columns
### Extract all rows
### Extract all diagonals in 2-dimensional square arrays

# Multi dimensional arrays
## Operations
### IndexOf
### Length
### Print