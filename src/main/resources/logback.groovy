import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.INFO

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
	pattern = "[%-5level] [%thread] %d{yyyy-MM-dd HH:mm:ss.SSS}: %logger: %msg%n"
  }
}
root(INFO, ["STDOUT"])