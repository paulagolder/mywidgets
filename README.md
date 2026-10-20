
Collection of tools for GUI creation

## Building

    ./gradlew build     # build/libs/mywidgets.jar
    ./gradlew deploy    # copy it to ../../lib/mywidgets.jar (used by raceresults, myELH)
    ./gradlew run       # start the mywidget_gui demo

myContacts builds this library from source (it includes `../mywidgets` in its
Gradle build), so it needs no deploy.
