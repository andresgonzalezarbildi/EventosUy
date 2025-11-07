#!/bin/bash
set -e
ROOT=$(pwd)
rm -f *.jar *.war


cd entrega1
mvn clean package -DskipTests
cp $(find target -name "*.jar" | grep -v "original" | head -n 1) "$ROOT"
cd "$ROOT"

cd entrega2
mvn clean package -DskipTests
cp $(find target -name "*.war" | head -n 1) "$ROOT"
cd "$ROOT"

cd mobile
mvn clean package -DskipTests
cp $(find target -name "*.war" | head -n 1) "$ROOT"
cd "$ROOT"

ls -1 *.jar *.war 2>/dev/null
