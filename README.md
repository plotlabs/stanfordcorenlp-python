# Stanford-CoreNLP Python Wrapper

> A Python wrapper for Stanford-CoreNLP version 3.9.1 using Jpype

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## How to install

Clone the repository
```
git clone https://github.com/plotlabs/stanfordcorenlp-python.git
```
Run the following Commands to compile the Java code:
```
export CLASS_PATH=/*:/resources/*
javac -cp CLASS_PATH Py4j_try.java
```

Run the setup.py file

```
python setup.py install
```


## Requirements
Modified Stanford Core-NLP jar file for version 3.9.1 is included in the commit. 

The version of Jpype is defined in the requirements.txt.
