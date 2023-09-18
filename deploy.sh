#!/bin/bash
BUILD_JAR=$(ls /home/ec2-user/action/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "> build 파일명: $JAR_NAME" >> /home/ec2-user/action/deploy.log

DEPLOY_PATH=/home/ec2-user/action/build/libs/

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ec2-user/action/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ec2-user/action/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

# 파일이 존재하는지 확인
if [ ! -f "$DEPLOY_JAR" ]
then
  echo "> JAR 파일이 존재하지 않습니다. 20초 대기 후에 다시 시도합니다." >> /home/ec2-user/action/deploy.log
  sleep 20
fi

# 파일이 존재하는지 다시 확인
if [ ! -f "$DEPLOY_JAR" ]
then
  echo "> JAR 파일이 여전히 존재하지 않습니다. 종료합니다." >> /home/ec2-user/action/deploy.log
  exit 1
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ec2-user/action/deploy.log
nohup java -cp $DEPLOY_PATH -jar $DEPLOY_JAR >> /home/ec2-user/deploy.alog 2>/home/ec2-user/action/deploy_err.log &