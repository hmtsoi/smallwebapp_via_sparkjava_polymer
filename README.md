# smallwebapp_via_sparkjava_polymer

This is a small webapp using Polymer ( http://www.polymer-project.org ) and the Spark framework ( http://sparkjava.com ). 

Warning: This program is for demonstration only. For production environment, it is necessary to impose extra security measures. 

##  Software Requirements

This code works on an updated Mac or Ubuntu environment with the following:

- Java 8
- Gradle 2.2.1
- Bower 1.3.12

## Implementation

Run the following on a shell to install the necessary polymer elements and prepare the directory to launch the webapp.

```
./public/bower_install_polymer.sh
gradle wrapper
```

Then run `./gradlew run` and open `localhost:8080` on an updated Chrome or Firefox.


