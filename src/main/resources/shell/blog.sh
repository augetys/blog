start(){
	echo "blog服务启动了..."
	nohup java -jar blog-1.0.0-SNAPSHOT.jar --spring.profiles.active=pro >blog.out &
	echo "启动完成..."
}
stop(){
	echo "停止blog-1.0.0-SNAPSHOT.jar服务..."
  pid=`ps -ef | grep blog-1.0.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
	kill -9 $pid
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
