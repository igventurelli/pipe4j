# Pipe4j

## Description

Pipe4j is a Java library for chaining actions through a pipeline with streamlined exception handling, eliminating the need for traditional try/catch blocks. It provides a cleaner and more readable way to write Java code by leveraging functional programming principles.

## Features

- **Cleaner Code**: Simplify code readability and maintenance by chaining operations.
- **Exception Handling**: Gracefully handle exceptions, including checked exceptions, within the pipeline.
- **Flexible Typing**: Mix and match different data types within the pipeline.
- **Functional Programming**: Utilize Java's functional programming capabilities with lambda expressions and method references.

## Installation

Add the following dependency to your project (e.g., Maven, Gradle).

### Maven

```xml
<dependency>
    <groupId>io.igventurelli</groupId>
    <artifactId>pipe4j</artifactId>
    <version>1.0.24</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.igventurelli:pipe4j:1.0.24'
```

## Usage

### Basic Example

```java
package com.example.demo;

import io.igventurelli.Pipe4j;

import java.util.Random;

public class DemoApplication {

    public static void main(String[] args) {
        var random = new Random().nextInt(100);
        
        var pipe = Pipe4j
            .from(10)
            .perform(input -> input * 2)
            .perform(res -> res / 4)
            .perform(res -> new SomeService().shallIThrowException(random % 2 == 0, res), e -> {
                System.out.println("An exception has been thrown: [" + e.getMessage() + "] - Providing fallback value:");
                return 99;
            })
            .get();
        
        System.out.println(pipe);
    }

    private static class SomeService {
        public String shallIThrowException(boolean shallI, Integer operand) throws Exception {
            if (shallI) {
                throw new Exception("A checked exception has been thrown");
            }
            return "It works, your result is: " + operand;
        }
    }
}
```

### API Reference

#### `from(T input)`

- **Description**: Initializes the pipeline with an input value.
- **Parameters**: `T input` - The initial input value.
- **Returns**: `Pipe4j<T>` - A new Pipe4j instance.

#### `perform(ThrowableFunction<? super T, ? extends U, E> callable)`

- **Description**: Chains an action that takes the input from the previous step and returns a result.
- **Parameters**:
    - `callable` - A function that performs an action and returns a result.
- **Returns**: `Pipe4j<U>` - A new Pipe4j instance with the result of the callable.

#### `perform(ThrowableFunction<? super T, ? extends U, E> callable, Function<? super Throwable, ? extends U> fallback)`

- **Description**: Chains an action with exception handling. If an exception occurs, the fallback function is used.
- **Parameters**:
    - `callable` - A function that performs an action and returns a result.
    - `fallback` - A function that handles exceptions and returns a fallback value.
- **Returns**: `Pipe4j<U>` - A new Pipe4j instance with the result of the callable or fallback.

#### `get()`

- **Description**: Ends the pipeline and returns the final result.
- **Returns**: `T` - The final value produced by the pipeline.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.