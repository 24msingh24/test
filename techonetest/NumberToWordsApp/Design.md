# Design Rationale: Number to Words Converter

## Language Used
During my university courses, I extensively used **Java**, especially in game development, and have written unit tests in Java using **JUnit** and **Mockito**, managing builds and test execution with Maven. I have applied the same principles here. I am also learning **C#** and doing unit tests with it.

## Approach Used
The solution uses an **iterative approach with a stack** to convert numbers into words. Numbers are processed in chunks of three digits (hundreds, tens, ones), starting from the smallest group (units) and moving up to thousands, millions, etc. The stack helps reverse the order so the final words come out correctly.

### Why This Approach
1. **Handles big numbers safely:** Works from 0 up to billions without worrying about recursion limits.  
2. **Efficient:** Avoids deep recursion and possible stack overflows.  
3. **Easy to read:** Each three-digit chunk is handled consistently, keeping the code clear.  
4. **Edge cases:** Decimals are handled separately for cents, so we can format outputs like "dollars and cents" cleanly.

### Other Approaches Considered
1. **Recursive method:**  
   - Could split numbers and handle each piece recursively.  
   - **Not used** because recursion could fail on very large numbers and is slightly harder to debug.  

2. **Using a library:**  
   - Could rely on third-party code to convert numbers.  
   - **Not used** because the task requires a custom implementation.  

3. **Manual mapping of all numbers:**  
   - Could write words for every possible number.  
   - **Not practical** for large numbers.

### Conclusion
The iterative + stack method provides a good balance of **efficiency, clarity, safety, and maintainability**, making it well-suited for a real-world application.
