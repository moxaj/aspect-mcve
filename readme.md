# MCVE for [StackOverflow issue #64718434](https://stackoverflow.com/q/64718434/1082681)

To run, either launch the `AspectMcveApplication` or invoke `gradlew :bootRun`.

Check stdout, you should see `You should never see this message!` printed, which indicates the problem.

If you want to repeat the triggering the aspect, simply use `curl http://localhost:8080/foo`.

The problem only occurs when using Spring AOP.
The program automatically detects if AspectJ via LTW (load-time weaving) is used instead.

You can use AspectJ LTW by adding `-javaagent:/path/to/aspectjweaver.jar -javaagent:/path/to/spring-instrument.jar` to the command line.
Then the message `You should never see this message!` disappears.
