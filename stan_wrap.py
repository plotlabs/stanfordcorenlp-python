import jpype
from jpype import *

#jpype.startJVM(jpype.getDefaultJVMPath())
jvmPath = jpype.getDefaultJVMPath() 
print jvmPath
#jpype.startJVM(jvmPath, "-ea", "-Djava.class.path=/Users/vedanshikataria/Documents/practice_python/PlotLabs/wrapper/stanford-corenlp-3.9.1.jar")
jpype.startJVM(jvmPath, "-Djava.class.path=stanford-corenlp-3.9.1")

#jpype.java.lang.System.out.println("hello world")

# stanPkg = jpype.JPackage('edu').stanford.nlp.naturalli
# print stanPkg
ie_obj = jpype.JClass('edu').stanford.nlp.naturalli
get_ie = ie_obj.OpenIEDemo.main()

#try = jpype.JPackage('edu').stanford.nlp.naturalli.JpypeTrial
#jp_trial = try.call_try()
#print jp_trial

jpype.shutdownJVM()