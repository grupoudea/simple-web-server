# ---------------------- EXEC JGKServer ------------------------
#!/bin/bash
cd /home/ubuntu/JGKServer;/usr/lib/jvm/java-8-openjdk-arm64/bin/java -jar simple-web-server-v1.0.0-jar-with-dependencies.jar 8082 jgkserver &> /dev/null < /dev/null &
