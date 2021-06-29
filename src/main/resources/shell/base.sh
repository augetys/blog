start(){
	echo "base服务启动了..."
	nohup java -jar base-1.0.0-SNAPSHOT.jar --spring.profiles.active=test >base.out &
	echo "启动完成..."
}
stop(){
	echo "停止base-1.0.0-SNAPSHOT.jar服务..."
	kill -9 $(ps -ef | grep base-1.0.0-SNAPSHOT.jar | grep -v grep)
                echo "停止完成..."
}
case $1 in
  start)
   start
  ;;
  stop)
   stop
  ;;
  restart)
   $0 stop
   sleep 10
   $0 start
  ;;
  *)
   echo "Usage: {start|stop|restart}"
  ;;
esac
exit 0
