# DataStructures_BinaryNumbers

Implements a binary number type in Java using array-backed storage (little-endian). Supports creation from strings or a fixed length, right shifts, addition with overflow detection, and decimal conversion. Focuses on data-structure design and explicit bit logic—no built-in integer shortcuts.

---

## Overview
This project models binary arithmetic at the data-structure level.
It avoids Java’s native number types to highlight bitwise logic, carry propagation, and manual array manipulation.

---

## Features
- Create from string (`"1011"`) or fixed length (zero-filled)
- Validate input (only `'0'` and `'1'`)
- Right shift with zero fill (`shiftR(int)`)
- Add two binaries of equal length (`add(BinaryNumber)`), set overflow on carry-out
- Convert to decimal (`toDecimal()`)
- Clear overflow flag (`clearOverflow()`)
- Robust error handling and validation

---

## Representation
- **Endian:** Little-endian (index 0 is least significant bit)  
- **Storage:** `int[] data` (each entry `0` or `1`)  
- **Overflow:** Boolean flag (`overflow`) set when addition exceeds bit length  

---

## Usage

**Compile**
```bash
# From project root (adjust path if needed)
javac -d out src/homework1/BinaryNumber.java
```

**Quick Start (JShell or main class)**
```java
import homework1.BinaryNumber;

// Construct
BinaryNumber a = new BinaryNumber("1011"); // 11
BinaryNumber b = new BinaryNumber("0010"); // 2

// Add (same length required)
a.add(b);
System.out.println(a.toString());   // 1101
System.out.println(a.toDecimal());  // 13

// Shift right by 3 (zero-filled)
a.shiftR(3);
System.out.println(a.toString());   // 0001101

// Overflow example
BinaryNumber x = new BinaryNumber("1111");
BinaryNumber y = new BinaryNumber("0001");
x.add(y);
System.out.println(x.toString());   // Overflow
x.clearOverflow();
```
> Note: The `homework1` package implies the file path `src/homework1/BinaryNumber.java`.

---

## API Summary

### Constructors
```java
BinaryNumber(int length);     // zero-initialized
BinaryNumber(String bits);    // validates '0'/'1' only
```

### Queries
```java
int getLength();
int getDigit(int index);      // throws if out of range
int toDecimal();
String toString();            // returns "Overflow" if overflow flag is set
```

### Operations
```java
void shiftR(int amount);      // throws if amount <= 0
void add(BinaryNumber other); // throws if lengths differ
void clearOverflow();
```

---

## Error Handling
| Condition | Behavior |
|------------|-----------|
| Invalid digit string | Throws exception with clear message |
| Index out of bounds | `getDigit()` throws |
| Shift amount ≤ 0 | `shiftR()` throws |
| Addition length mismatch | `add()` throws |
| Overflow | Flag set; call `clearOverflow()` |

---

## Test Ideas
- Add two numbers with and without overflow  
- Verify `toDecimal()` outputs expected values  
- Shift by various amounts (1, length, >length)  
- Ensure invalid input (like `"10a1"`) triggers exceptions  

---

## Notes
- Written for clarity and learning: loops and logic are explicit rather than optimized.
