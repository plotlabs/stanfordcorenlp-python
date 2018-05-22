from py4j.java_gateway import JavaGateway

def getStanNLP():
	gateway = JavaGateway()
	stan_nlp_obj = gateway.entry_point
	return stan_nlp_obj