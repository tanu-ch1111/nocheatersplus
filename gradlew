#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Resolve symlinks
PRG="$0"
while [ -h "$PRG" ] ; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=$(dirname "$PRG")/"$link"
  fi
done

SAVED="$(pwd)"
cd "$(dirname "$PRG")/" >/dev/null
APP_HOME="$(pwd -P)"
cd "$SAVED" >/dev/null

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

# Make sure JAVA_HOME is set and points to a valid JVM
if [ -z "$JAVA_HOME" ]; then
  echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
  echo "Please set the JAVA_HOME variable in your environment to match the location of your Java installation."
  exit 1
fi

# Determine the Java command to use to start the JVM.
JAVA_EXEC="$JAVA_HOME/bin/java"

# Load user-specific JVM options
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

exec "$JAVA_EXEC" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"