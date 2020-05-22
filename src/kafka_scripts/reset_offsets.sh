#!/bin/bash

cd ../../kafka_2.12-2.5.0/

if [ $# -lt 1 ];  then
  echo "Bad arguments."
  exit 1
fi
if [ $# -eq 2 ];  then
  DEFAULT_OFFSET=0
  offset=${2:-$DEFAULT_OFFSET}
  kafka-consumer-groups.sh --bootstrap-server localhost:9092 \
  --group chat \
  --reset-offsets --shift-by $offset --execute \
  --topic $1
fi
if [ $# -eq 1 ];  then
  kafka-consumer-groups.sh --bootstrap-server localhost:9092 \
  --group chat --reset-offsets --to-earliest --execute \
  --topic $1
fi