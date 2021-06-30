start(){
	echo "blog服务启动了..."
	nohup java -jar blog-1.0.0-SNAPSHOT.jar --spring.profiles.active=test >blog.out &
	echo "启动完成..."
}
stop(){
	echo "停止blog-1.0.0-SNAPSHOT.jar服务..."
	kill -9 $(ps -ef | grep blog-1.0.0-SNAPSHOT.jar | grep -v grep)
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
