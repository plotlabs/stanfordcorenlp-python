from py4j.java_gateway import JavaGateway

gateway = JavaGateway()
HW = gateway.entry_point.getHelloWorld()
print "received HW vaue: ", HW

doc = gateway.entry_point.getAnnotationOfText("Obama was born in Hawaii. He is our president.")
print "Doc: ", doc

#gateway.entry_point.getOpenIE(doc)
gateway.entry_point.printSplitClauses(doc)