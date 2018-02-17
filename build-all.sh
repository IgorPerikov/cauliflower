#!/bin/bash
cd cauliflower-notes
./build.sh
cd ..
cd cauliflower-front
./build.sh
cd ..
cd cauliflower-auth
./build.sh
