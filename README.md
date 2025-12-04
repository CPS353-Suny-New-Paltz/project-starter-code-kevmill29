
# Consecutive Quadratic Prime Number Calculator
## TABLE OF CONTENTS
-[Features](#features)
-[Installation](#installation)
-[Usage](#usage)
-[File Formats](#file-formats)
-[API](#API)
-[Multithreaded Implementation](#MultiThreading)


# Features
- Read integers from (for now!)text, (coming soon)JSON, or CSV files
- Write computation results back to files with optional delimiters
- Simple API method to start comuation and return results
- Multi-threaded execution for improved performance and concurrency testing

# Installation
-Requires Java 21
-Requires Gradle 8.6 for building and running
-Requires JUNIT 5 & Junit 4
build project with gradle build

# Usage
1. Prepare a text file with integers on each line
2. Call the 3 APIs and methods on network api to start process
3. check the output file for results
call networkAPI, processorAPI, and ComputeComponent
networkAPI api = new ImplementNetworkAPI();
api.respond(takes in user request that has input location, output location, and delimiter(will assign default if null))

# File-Formats
-TXT
-JSON(TBA)
-CSV(TBA)

# API
-Coordination(NetworkAPI) This review the user request and provide instructions to the other API
-Storage(ProcessorAPI) This will read the input file, stream the integers found and create a list, pass the list to the computation component, wait for the computation component to complete the computations and write an output file. 
-Computation(ComputeComponent) This is responsible for creating a new list with the answers for the Storage component to create the file. 


Will finish the rest of this soon as the project progresses

This project will feature Project Euler Problem 27, Quadratic Primes. The goal of this is to take the quadratic formula ie [(n * n) + an + b.] and figure out which combination of integers(with the limitation that it cannot be lower than -1000 and higher than 1000) can be used for variable a and variable b to find the highest numbers of primes with n starting at 0 and increasing.  Refer to : https://projecteuler.net/problem=27

The program takes in two locations: the input source where a text file containing integers is located(only one variable will be needed which will be variable a as variable b will start at 10 and gradually increase all the way to 1000), and an output source where the answer key will be created and sent to by the program afterwards. The program will then give the most consecutive primes from the best combinator of a,b, and n. 

Number of consecutive primes starting from n = 0 :  40    

The Coordination component or NetworkAPI is in charge of calling the ProcessorAPI(Storage Component) after it validates the user request to be successful. Once this is done it ProcessorAPI will then read the integers from the location specified by the user given that it is in the proper format for the storage component to read. Once finished the Coordination component will then call the Computation Component to read the data in storage component and start the computations.

# Multithreaded Implementation
Added multi threading for performance and scalability. Threading is limited to 4 threads and waits 1 minute between each dispatch to make sure that system does not overload. 


![Diagram for APIs](https://github.com/CPS353-Suny-New-Paltz/project-starter-code-kevmill29/blob/feature/DesignDiagram.png?raw=true)

