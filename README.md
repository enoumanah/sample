# Name Search Similarity App

This is a Java-based web application built with **Vaadin** that allows users to search for names using both **spelling similarity** (Levenshtein Distance) and **phonetic similarity** (Double Metaphone). It's designed to match user input against a dataset of names, even if the spelling isn't exact.

## 🔍 Features

- Search names with fuzzy logic
- Uses:
  - **Levenshtein Distance** for spelling similarity
  - **Double Metaphone** for phonetic similarity
- Displays:
  - Name
  - Levenshtein Score
  - Phonetic Score
  - Combined Total Score
  - Data source label (`PEP`)
- Basic notifications for no-match results

## 🛠 Technologies

- Java 17+
- Vaadin 23+
- Apache Commons Codec (for Double Metaphone)
