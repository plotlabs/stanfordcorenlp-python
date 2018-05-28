import PyStanfordCoreNLP

stan_nlp_obj = PyStanfordCoreNLP.getStanNLP()
doc = stan_nlp_obj.getAnnotationOfText("Obama was born in Hawaii. He is our president.")
print "Doc: ", doc
output = stan_nlp_obj.printSplitClauses(doc)
print output

stan_nlp_obj.getOpenIE(doc)

#prop = open('../resources/properties.txt', 'r').read()
#print prop
#stan_nlp_obj.trainAndWrite("../resources/ner-model.ser.gz", prop, "../resources/train.txt")
stan_nlp_obj.trainAndWrite("../resources/ner-model.ser.gz", "/Users/vedanshikataria/Documents/practice_python/PlotLabs/stanfordcorenlp-python/resources/properties.txt", "/Users/vedanshikataria/Documents/practice_python/PlotLabs/stanfordcorenlp-python/resources/train.tsv")


#trainFile = /Users/vedanshikataria/Documents/practice_python/PlotLabs/stanfordcorenlp-python/resources/train.tsv
#serializeTo = /Users/vedanshikataria/Documents/practice_python/PlotLabs/stanfordcorenlp-python/resources/ner-model.ser.gz
#useBoundarySequences=true
#useNeighborNGrams=true
#useTaggySequences=true
#printFeatures=true
#saveFeatureIndexToDisk = true
#useObservedSequencesOnly = true
#useWordPairs = true
#wordShape=chris2useLC