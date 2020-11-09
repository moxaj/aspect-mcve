Simple MCVE for [this stackoverflow issue](https://stackoverflow.com/questions/64718434/spring-aop-pointcut-applying-to-method-only-when-joinpoint-proceed-is-invoked).

To run, either launch the `AspectMcveApplication`, or invoke `gradlew :bootRun`. Then, simply
`curl http://localhost:8080/foo`, and check the stdout (you should see `You should never see this message!` printed,
which indicates the problem).
