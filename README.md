
# Consecutive Quadratic Prime Number Calculator
## TABLE OF CONTENTS
-[Features](#features)
-[Installation](#installation)
-[Usage](#usage)
-[File Formats](#file-formats)
-[API](#api)
-[Multithreaded Implementation](#multithreading)
-[gRPC Setup](#grpc)
-[BenchmarkTesting](#benchmarktesting)
-[Visualization](#visualization)

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

# gRPC / Proto Setup
2. Generating Java Classes from .proto Files

The project automatically generates protobuf and gRPC Java classes when you run:

     ./gradlew build


or manually:

     ./gradlew generateProto


Generated files will appear in:

build/generated/source/proto/main/java/
build/generated/source/proto/main/grpc/


These folders are already added to the Gradle sourceSets so the generated classes compile automatically.

4. Build Requirements for Protobuf & gRPC

The project uses the following versions, configured in build.gradle:

protoc: 3.24.x

gRPC Java generator: 1.63.x

javax.annotation-api: added as compileOnly so @Generated compiles on Java 17+ without affecting runtime

No additional setup is required — simply run the Gradle build and the gRPC stubs will be generated and compiled automatically.

# Multithreaded Implementation
Added multi threading for performance and scalability. Threading is limited to 4 threads and waits 1 minute between each dispatch to make sure that system does not overload. 

# BenchmarkTesting
The Performance Improvement (V2): The core computation component was improved by migrating from a standard fixed thread pool to an optimized Work Stealing Pool (Executors.newWorkStealingPool()).

The Optimization: Unlike the baseline which incurs overhead by parsing strings inside the pipeline, the V2 implementation processes Integer data directly and uses work-stealing algorithms to better balance tasks across CPU cores. This addresses the bottleneck found in the respond method, resulting in a minimum of 10% speedup for large datasets.

Running the Integration Benchmark: The benchmark uses a large, synthetic dataset to measure the end-to-end time of the baseline multithreaded implementation (MultiThreadedNetworkAPI) versus the optimized implementation (Version2Implementation).

# Visualization
Make sure you have python installed on your PC. The setup either requires Live Server or Python to host the server. 

Open a terminal and navigate to the project folder.

 For Python 3
      python -m http.server 8000

Your visualization is now live at: http://localhost:8000/viz_client.html

Run the AutomatedLauncher class (as a Java Application).

Follow the console prompts to enter your input file path (e.g., input.txt) and output location.

The system will automatically detect the viz_client folder and update the graph data immediately after computation finishes.

Refresh your browser at http://localhost:8000/viz_client.html.

X-Axis: The Input Coefficients (a) from your file.

Y-Axis: The calculated Maximum Consecutive Primes (n).

Hover: detailed tooltips showing exact values.

 
![Diagram for APIs](https://github.com/CPS353-Suny-New-Paltz/project-starter-code-kevmill29/blob/feature/DesignDiagram.png?raw=true)

