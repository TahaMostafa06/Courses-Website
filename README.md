# SkillForge Course Website

> [!IMPORTANT]
> For teaching staff & teaching assistants:
> please note that this repository used to host Lab 6's solution. If you were redirected here from the link to Lab 6, this repository now hosts the solution to Lab 7 - which should explain the more recent commits.

## E-Learning Platform
An E-Learning platform "SkillForge" is implemented using Java Swing for the frontend, and a backend that manages databases, login sessions, permissions and data entry.

## Validations
**Input validation**\
Input validation is done mostly on the frontend.

**Database validation**\
The project assumes all JSON database files follow their corresponding schemas. No validation or handling is taken care of. Duplicate keys will not all be taken in, only the last key - this is a limitation of the used JSON library (GSON) and its underlying data structures (Maps/ Sets).

student1 - student12
instructor - instructor
abc - abcabc123

