# ManyGUI
## Graphical User Interface Tool for Debugging Many-cores

This repository stores the NetBeans project of the graphical debugger for many-cores called ManyGUI. 
The current version support OpenPiton (https://parallel.princeton.edu/openpiton/).

We modified OpenPiton to support the debugging features of ManyGUI. **Please follow this steps to use the tool:**

1. Download and install OpenPiton: 
   > https://github.com/PrincetonUniversity/openpiton

2. Runs the many-core testcase in original OpenPiton version. Check if the testcase is running as expected following the OpenPiton original guidelines.

3. **Patch your OpenPiton project with new files**: copy the content of the folder "OpenPitonPath" located at root of this repository to your root folder of your OpenPiton directory.
   > The goal of this patch is to insert monitors inside the original code of OpenPiton, allowing it to be integrated with ManyGUI.

4. Set the bin/ folder as a enviroment variable:
   > export PITON_ROOT=/home/user/openpiton   
   > export PATH=\${PITON_ROOT}/bin:${PATH}

5. Runs a given application using the many-core testcase
   1. Goes into PITON_ROOT/build  
   2. Calls the following command:
        > openpiton 3 3 -all **<name_of_your_c_file>** -debug
    1. The **<name_of_your_c_file>** is the name of the .c file that implements your application. It must be inside PITON_ROOT/piton/verif/diag/c/riscv/ariane

6. The simulation should start and ManyGUI must be openned.


## Video tutorials:

### Version 1: 
[![IMAGE ALT TEXT HERE](https://i9.ytimg.com/vi/XOHMqZNIwks/mq1.jpg?sqp=CMTe7owG&rs=AOn4CLDErcdypEBFOoGvY5EyYqIg0sdBBQ)](https://youtu.be/XOHMqZNIwks)
### Version 2:
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/YOUTUBE_VIDEO_ID_HERE/0.jpg)](https://youtu.be/VU6yRrICwsk)