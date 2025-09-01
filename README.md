# Polynomial Reconstruction

This project reconstructs a polynomial of degree **k-1** from given data points using **Lagrange interpolation**.  
It was built as part of a coding assessment for **Hashira**.

## Features
- Parses input data (`n`, `k`, and base/value pairs).
- Converts values from any base (2–36) into decimal.
- Reconstructs the polynomial using the first `k` points.
- Outputs polynomial coefficients as **integers**.

##  Requirements
- Java 8 or later
- No external libraries required

##  Input Format
The program expects JSON input of this form:


## How to Run :

- Compile the program:

- javac Main.java

- Run the program with input from a JSON file:

- java Main < input.json

- where input.json contains the sample JSON shown above.

## Example Output
P(x) = -2290267902684206894*x^6 + 6502753730491247010*x^5 
       - 8085996710982869385*x^4 + 3544566661514267414*x^3 
       - 980682513391185101*x^2 + 9215056263760595751*x - 7548020092495763752

```json
{
  "keys": { "n": 10, "k": 7 },
  "1": { "base": "6", "value": "13444211440455345511" },
  "2": { "base": "15", "value": "aed7015a346d635" },
  "3": { "base": "15", "value": "6aeeb69631c227c" },
  "4": { "base": "16", "value": "e1b5e05623d881f" },
  "5": { "base": "8", "value": "316034514573652620673" },
  "6": { "base": "3", "value": "2122212201122002221120200210011020220200" },
  "7": { "base": "3", "value": "20120221122211000100210021102001201112121" },
  "8": { "base": "6", "value": "20220554335330240002224253" },
  "9": { "base": "12", "value": "45153788322a1255483" },
  "10": { "base": "7", "value": "1101613130313526312514143" }
}