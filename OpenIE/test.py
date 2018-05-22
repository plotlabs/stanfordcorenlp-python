import PyStanfordCoreNLP

stan_nlp_obj = PyStanfordCoreNLP.getStanNLP()
doc = stan_nlp_obj.getAnnotationOfText("Obama was born in Hawaii. He is our president.")
print "Doc: ", doc
output = stan_nlp_obj.printSplitClauses(doc)
print output