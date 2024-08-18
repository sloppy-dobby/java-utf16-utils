# Java utilities for UTF-16
![Maven Central](https://img.shields.io/badge/Maven%20Central-0.0.1-blue?style=flat)
![License](https://img.shields.io/badge/License-MIT%20License-green?style=flat)
![Status](https://img.shields.io/badge/Status-Active-lime?style=flat)

Utilities for working with UTF-16 in Java. The minimum supported version of JDK is 1.8.

## Prerequisites
While working on a few libraries for my pet projects, I needed to develop additional supporting libs - including a library that would allow me to work with UTF-16 surrogates and surrogate pairs.

At the moment, the library contains classes and methods that allow you to determine whether a symbol is a surrogate, whether two symbols are a valid surrogate pair.

There are also methods for various manipulations with character sequences - check the sequence for surrogates, only surrogates, high/low surrogates, surrogate pairs and "unattached" surrogates (which are not part of the surrogate pair), as well as methods for trimming character sequences.

## Usage

> **Note**
> 
> The library uses local term such as `unattached surrogate` - this is a surrogate that is not part of the surrogate pair in the char sequence.

##### Example 1. Checking whether the symbol is a surrogate.

```java
import com.sloppydobby.java.utf16.SurrogateUtils;

public class MyClass {

    public void myMethod() {
        // Check for space char
        SurrogateUtils.isSurrogate(' '); // false
        
        // Check for min high surrogate char
        SurrogateUtils.isSurrogate('\uD800'); // true

        // Check for max high surrogate char
        SurrogateUtils.isSurrogate('\uDBFF'); // true

        // Check for min low surrogate char
        SurrogateUtils.isSurrogate('\uDC00'); // true

        // Check for max low surrogate char
        SurrogateUtils.isSurrogate('\uDFFF'); // true
    }

}
```

##### Example 2. Checking whether the symbol is a high surrogate.

```java
import com.sloppydobby.java.utf16.SurrogateUtils;

public class MyClass {
    
    public void myMethod() {
        // Check for space char
        SurrogateUtils.isHighSurrogate(' '); // false

        // Check for min high surrogate char
        SurrogateUtils.isHighSurrogate('\uD800'); // true

        // Check for max high surrogate char
        SurrogateUtils.isHighSurrogate('\uDBFF'); // true

        // Check for min low surrogate char
        SurrogateUtils.isHighSurrogate('\uDC00'); // false

        // Check for max low surrogate char
        SurrogateUtils.isHighSurrogate('\uDFFF'); // false
    }
    
}
```

##### Example 3. Checking whether the symbol is a low surrogate.

```java
import com.sloppydobby.java.utf16.SurrogateUtils;

public class MyClass {
    
    public void myMethod() {
        // Check for space char
        SurrogateUtils.isLowSurrogate(' '); // false

        // Check for min high surrogate char
        SurrogateUtils.isLowSurrogate('\uD800'); // false

        // Check for max high surrogate char
        SurrogateUtils.isLowSurrogate('\uDBFF'); // false

        // Check for min low surrogate char
        SurrogateUtils.isLowSurrogate('\uDC00'); // true

        // Check for max low surrogate char
        SurrogateUtils.isLowSurrogate('\uDFFF'); // true
    }
    
}
```

##### Example 4. Check that two characters make a surrogate pair.

```java
import com.sloppydobby.java.utf16.SurrogatePairUtils;

public class MyClass {
    
    public void myMethod() {
        // Check for space char and min high surrogate 
        SurrogatePairUtils.isSurrogatePair(' ', '\uD800'); // false

        // Check for space min high surrogate and min low surrogate 
        SurrogatePairUtils.isSurrogatePair('\uD800', '\uDC00'); // true

        // Check for space max high surrogate and max low surrogate 
        SurrogatePairUtils.isSurrogatePair('\uDBFF', '\uDFFF'); // true

        // Check for space min low surrogate and min high surrogate 
        SurrogatePairUtils.isSurrogatePair('\uDC00', '\uD800'); // false
    }
    
}
```

##### Example 5. Check that char sequence contains a surrogates.

```java
import com.sloppydobby.java.utf16.CharSequenceUtils;

public class MyClass {
    
    public void myMethod() {
        // Method returns false for a string without surrogates
        CharSequenceUtils.containsSurrogates("Hello, world!"); // false

        // Method returns true for a string with min high surrogate
        CharSequenceUtils.containsSurrogates("Hello, world!\uD800"); // true

        // Method returns true for a string with max high surrogate
        CharSequenceUtils.containsSurrogates("Hello, world!\uDBFF"); // true

        // Method returns true for a string with min low surrogate
        CharSequenceUtils.containsSurrogates("Hello, world!\uDC00"); // true

        // Method returns true for a string with max low surrogate
        CharSequenceUtils.containsSurrogates("Hello, world!\uDFFF"); // true

        // Method returns true for a string with emoji
        CharSequenceUtils.containsSurrogates("Hello, world! 😀"); // true
    }
    
}
```

##### Example 6. Check that char sequence contains a high surrogates.

```java
import com.sloppydobby.java.utf16.CharSequenceUtils;

public class MyClass {
    
    public void myMethod() {
        // Method returns false for a string without surrogates
        CharSequenceUtils.containsHighSurrogates("Hello, world!"); // false

        // Method returns true for a string with min high surrogate
        CharSequenceUtils.containsHighSurrogates("Hello, world!\uD800"); // true

        // Method returns true for a string with max high surrogate
        CharSequenceUtils.containsHighSurrogates("Hello, world!\uDBFF"); // true

        // Method returns false for a string with min low surrogate
        CharSequenceUtils.containsHighSurrogates("Hello, world!\uDC00"); // false

        // Method returns false for a string with max low surrogate
        CharSequenceUtils.containsHighSurrogates("Hello, world!\uDFFF"); // false

        // Method returns true for a string with emoji
        CharSequenceUtils.containsHighSurrogates("Hello, world! 😀"); // true
    }
    
}
```

##### Example 6. Check that char sequence contains a low surrogates.

```java
import com.sloppydobby.java.utf16.CharSequenceUtils;

public class MyClass {
    
    public void myMethod() {
        // Method returns false for a string without surrogates
        CharSequenceUtils.containsLowSurrogates("Hello, world!"); // false

        // Method returns false for a string with min high surrogate
        CharSequenceUtils.containsLowSurrogates("Hello, world!\uD800"); // false

        // Method returns false for a string with max high surrogate
        CharSequenceUtils.containsLowSurrogates("Hello, world!\uDBFF"); // false

        // Method returns true for a string with min low surrogate
        CharSequenceUtils.containsLowSurrogates("Hello, world!\uDC00"); // true

        // Method returns true for a string with max low surrogate
        CharSequenceUtils.containsLowSurrogates("Hello, world!\uDFFF"); // true

        // Method returns true for a string with emoji
        CharSequenceUtils.containsLowSurrogates("Hello, world! 😀"); // true
    }
    
}
```

## Maven
If you are using the Maven build system, then you can add the library to your project by adding dependency in to target module `pom.xml` file:

```xml
<dependency>
    <groupId>com.sloppydobby</groupId>
    <artifactId>java-utf16-utils</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Gradle
If you are using the Gradle build system, then you can add the library to your project by adding dependency in to target module `build.gradle` file:

```groovy
implementation 'com.sloppydobby:java-utf16-utils:0.0.1'
```

## Build
If for some reason you can't add the library to your project as Maven Central dependency, you can build the library manually by following commamd:

```bash
mvn clean package
```
