import PyStanfordCoreNLP

stan_nlp_obj = PyStanfordCoreNLP.getStanNLP()
doc = stan_nlp_obj.getAnnotationOfText("Obama was born in Hawaii. He is our president.")
print "Doc: ", doc
output = stan_nlp_obj.printSplitClauses(doc)
print output

stan_nlp_obj.getOpenIE(doc)

model_file_path = "/Users/vedanshikataria/Documents/practice_python/PlotLabs/stanfordcorenlp-python/resources/"
model_name = "ner-model.ser.gz"
properties_path = "/Users/vedanshikataria/Documents/practice_python/PlotLabs/stanfordcorenlp-python/resources/properties.txt"
training_file_path = "/Users/vedanshikataria/Documents/practice_python/PlotLabs/stanfordcorenlp-python/resources/train.json"
stan_nlp_obj.trainAndWrite(model_file_path, model_name, properties_path, training_file_path)

tagged_output = stan_nlp_obj.doTagging(model_file_path+"target/"+model_name, "samsung mobile phones")
print tagged_output